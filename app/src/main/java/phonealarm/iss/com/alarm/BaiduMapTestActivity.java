package phonealarm.iss.com.alarm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhaocuilong on 2017/9/27.
 */

public class BaiduMapTestActivity extends Activity implements View.OnClickListener {

    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private BitmapDescriptor bitmap;
    private Marker marker;
    private LatLng currentLatLng;//最后一次点击的定位位置
    private List<TextView> mList;
    //定位
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //获取定位结果
            currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            System.out.println("==dd=" + location.getLocationDescribe());
            List<Poi> poiList = location.getPoiList();//获取当前位置周边POI信息
            setViewText2(mList, poiList);

            if (null != mBaiduMap) {//定位当前位置
                markPoint(mBaiduMap, currentLatLng);
                setPointCenter(mBaiduMap, currentLatLng);
            }
            if (location.getLocType() == BDLocation.TypeNetWorkException) {
                Toast.makeText(BaiduMapTestActivity.this, "定位失败,请检查网络！", Toast.LENGTH_SHORT).show();
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                Toast.makeText(BaiduMapTestActivity.this, "定位失败", Toast.LENGTH_SHORT).show();
            }
        }
    };

    //POI检索
    private PoiSearch mPoiSearch;
    private OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {
        public void onGetPoiResult(PoiResult result) {
            //获取POI检索结果
            if (result.error == SearchResult.ERRORNO.NO_ERROR) {
                setViewText(mList, result.getAllPoi());
            } else {
                setTextInvisibale(mList);
                Toast.makeText(BaiduMapTestActivity.this, "暂未查找到信息", Toast.LENGTH_SHORT).show();
            }

        }

        public void onGetPoiDetailResult(PoiDetailResult result) {
            //获取Place详情页检索结果
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }
    };
    private TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7;
    private ImageView backorigin;
    public static final String MAP_TITLE = "MAP_TITLE";


    public static void open(Activity context, String title, int requestCode) {
        if (context != null) {
            Intent intent = new Intent(context, BaiduMapTestActivity.class);
            intent.putExtra("MAP_TITLE", title);
            context.startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidumap_test);
        bitmap = BitmapDescriptorFactory.fromResource(R.drawable.map_location);
        backorigin = (ImageView) findViewById(R.id.backorigin);
        mMapView = (MapView) findViewById(R.id.bmapView);
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        Intent intent = getIntent();
        if (null != intent) {
            String title = intent.getStringExtra(MAP_TITLE);
            titleTv.setText(title);
        }
        findViewById(R.id.title_other).setVisibility(View.INVISIBLE);
        findViewById(R.id.title_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textView1 = (TextView) findViewById(R.id.text1);
        textView2 = (TextView) findViewById(R.id.text2);
        textView3 = (TextView) findViewById(R.id.text3);
        textView4 = (TextView) findViewById(R.id.text4);
        textView5 = (TextView) findViewById(R.id.text5);
        textView6 = (TextView) findViewById(R.id.text6);
        textView7 = (TextView) findViewById(R.id.text7);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);
        textView6.setOnClickListener(this);
        textView7.setOnClickListener(this);
        mList = new ArrayList<>();
        mList.add(textView1);
        mList.add(textView2);
        mList.add(textView3);
        mList.add(textView4);
        mList.add(textView5);
        mList.add(textView6);
        mList.add(textView7);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                currentLatLng = latLng;
                markPoint(mBaiduMap, latLng);
                setPointCenter(mBaiduMap, latLng);
                poi2(latLng);//搜索周边
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
        //回到定位点
        backorigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != currentLatLng) {
                    markPoint(mBaiduMap, currentLatLng);
                    setPointCenter(mBaiduMap, currentLatLng);
                    poi2(currentLatLng);//搜索周边
                }
            }
        });
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        location();
        mLocationClient.start();
        //检索
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(poiListener);


    }

    private void setTextInvisibale(List<TextView> mList) {

        if (null != mList && mList.size() > 0) {
            for (int i = 0; i < mList.size(); i++) {
                mList.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    public void setViewText2(List<TextView> mList, List<Poi> poiList) {

        if (null == mList || mList.size() == 0) {
            return;
        }
        if (null != poiList && poiList.size() > 0) {
            setTextInvisibale(mList);
            for (int i = 0; i < poiList.size(); i++) {
                Poi poi = poiList.get(i);
                TextView textView = mList.get(i);
                if (null != poi) {
                    textView.setVisibility(View.VISIBLE);
                    String address = poi.getName();
                    textView.setText(address);
                }
            }
        }
    }

    public void setViewText(List<TextView> mList, List<PoiInfo> allPoi) {

        if (null == mList || mList.size() == 0) {
            return;
        }
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).setVisibility(View.INVISIBLE);
        }
        if (null != allPoi && allPoi.size() > 0) {
            for (int i = 0; i < allPoi.size(); i++) {
                PoiInfo poiInfo = allPoi.get(i);
                TextView textView = mList.get(i);
                if (null != poiInfo) {
                    textView.setVisibility(View.VISIBLE);
                    String address = poiInfo.address + poiInfo.name;
                    textView.setText(address);
                }
            }
        }
    }

    public void poi2(LatLng latLng) {
        if (null != latLng) {
            PoiNearbySearchOption option = new PoiNearbySearchOption();
            option.keyword("号");
            option.location(latLng);
            option.pageNum(0);
            option.pageCapacity(7);
            option.radius(300);
            option.sortType(PoiSortType.distance_from_near_to_far);
            mPoiSearch.searchNearby(option);
        }
    }

    private void location() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        int span = 0;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps
        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocationClient.setLocOption(option);
    }


    private void setPointCenter(BaiduMap mBaiduMap, LatLng point) {
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(point)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }


    private void markPoint(BaiduMap mBaiduMap, LatLng point) {
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        if (null != marker) {
            marker.remove();
        }
        marker = (Marker) mBaiduMap.addOverlay(option);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        mPoiSearch.destroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onClick(View v) {
        String string = null;
        switch (v.getId()) {
            case R.id.text1:
                string = textView1.getText().toString();
                break;
            case R.id.text2:
                string = textView2.getText().toString();
                break;
            case R.id.text3:
                string = textView3.getText().toString();
                break;
            case R.id.text4:
                string = textView4.getText().toString();
                break;
            case R.id.text5:
                string = textView5.getText().toString();
                break;
            case R.id.text6:
                string = textView6.getText().toString();
                break;
            case R.id.text7:
                string = textView7.getText().toString();
                break;
        }
        Intent mIntent = new Intent();
        mIntent.putExtra("Address", string);
        mIntent.putExtra("WEIDU", currentLatLng.latitude);
        mIntent.putExtra("JINGDU", currentLatLng.longitude);
        setResult(2003, mIntent);
        finish();
        System.out.println("====string=>" + string);
    }
}
