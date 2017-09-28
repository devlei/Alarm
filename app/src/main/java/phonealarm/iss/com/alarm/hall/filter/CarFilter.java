package phonealarm.iss.com.alarm.hall.filter;

import android.text.TextUtils;
import android.widget.Filter;
import phonealarm.iss.com.alarm.bean.carinfo.CarInfo;
import phonealarm.iss.com.alarm.hall.adapter.CommonSearchAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weizhilei on 2017/9/28.
 */
public class CarFilter extends Filter {

    private CommonSearchAdapter mAdapter;

    //原始数据
    private List<CarInfo> mCarInfoList;

    //原始备份数据
    private List<CarInfo> mOriginalValues;

    //过滤器上的锁可以同步复制原始数据
    private final Object mLock = new Object();

    public CarFilter(CommonSearchAdapter adapter, List<CarInfo> carInfoList) {
        mAdapter = adapter;
        mCarInfoList = carInfoList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        //初始化过滤结果对象
        FilterResults results = new FilterResults();
        //原始数据备份为空时，上锁，同步复制原始数据
        if (mOriginalValues == null) {
            synchronized (mLock) {
                mOriginalValues = new ArrayList<CarInfo>(mCarInfoList);
            }
        }
        //关键字为null时
        if (TextUtils.isEmpty(constraint)) {
            ArrayList<CarInfo> list;
            //同步复制一个原始备份数据
            synchronized (mLock) {
                list = new ArrayList<>(mOriginalValues);
            }
            //此时返回的results就是原始的数据，不进行过滤
            results.values = list;
            results.count = list.size();
        } else {
            String prefixString = constraint.toString().toLowerCase();//转化为小写

            ArrayList<CarInfo> values;
            //同步复制一个原始备份数据
            synchronized (mLock) {
                values = new ArrayList<>(mOriginalValues);
            }
            final int count = values.size();
            //过滤后的list
            final ArrayList<CarInfo> newValues = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                final CarInfo value = values.get(i);
                //标题为过滤条件
                final String valueText = value.getCar_num() + value.getCar_type() + value.getCar_type();
                if (valueText.contains(prefixString)) {
                    newValues.add(value);
                }
            }
            results.values = newValues;
            results.count = newValues.size();
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        mCarInfoList = (List<CarInfo>) results.values;
        if (results.count > 0) {
            mAdapter.setCarInfoList((List<CarInfo>) results.values);
            mAdapter.notifyDataSetChanged();
        } else {
            //关键字不为零但是过滤结果为空刷新数据
            if (constraint.length() != 0) {
                mAdapter.notifyDataSetChanged();
                return;
            }
        }
    }

}
