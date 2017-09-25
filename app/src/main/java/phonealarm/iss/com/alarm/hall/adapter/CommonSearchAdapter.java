package phonealarm.iss.com.alarm.hall.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.bean.beLost.BelostInfo;
import phonealarm.iss.com.alarm.bean.carinfo.CarInfo;
import phonealarm.iss.com.alarm.bean.caseinfo.CaseInfo;
import phonealarm.iss.com.alarm.bean.lost.LostInfo;
import phonealarm.iss.com.alarm.bean.searchalarm.AlarmInfoBean;
import phonealarm.iss.com.alarm.bean.suspect.SuspectInfo;
import phonealarm.iss.com.alarm.hall.adapter.CommonSearchAdapter.CommonSearchViewHolder;
import phonealarm.iss.com.alarm.utils.CollectionUtils;
import phonealarm.iss.com.alarm.utils.DateUtils;
import phonealarm.iss.com.alarm.utils.IntentUtils;
import phonealarm.iss.com.alarm.utils.ToastUtils;

import java.util.List;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class CommonSearchAdapter extends RecyclerView.Adapter<CommonSearchViewHolder> {

    private int mTypeResId;

    private List<CaseInfo> mCasesInfoList;
    private List<CarInfo> mCarInfoList;
    private List<SuspectInfo> mSuspectInfoList;
    private List<BelostInfo> mBelostInfoList;
    private List<LostInfo> mLostInfoList;
    private List<AlarmInfoBean> mAlarmInfoList;


    public CommonSearchAdapter(int typeResId) {
        this.mTypeResId = typeResId;
    }

    public void setCasesInfoList(List<CaseInfo> casesInfoList) {
        mCasesInfoList = casesInfoList;
    }

    public void setCarInfoList(List<CarInfo> carInfoList) {
        mCarInfoList = carInfoList;
    }

    public void setSuspectInfoList(List<SuspectInfo> suspectInfoList) {
        mSuspectInfoList = suspectInfoList;
    }

    public void setBelostInfoList(List<BelostInfo> belostInfoList) {
        mBelostInfoList = belostInfoList;
    }

    public void setLostInfoList(List<LostInfo> lostInfoList) {
        mLostInfoList = lostInfoList;
    }

    public void setAlarmInfoList(List<AlarmInfoBean> alarmInfoList) {
        mAlarmInfoList = alarmInfoList;
    }

    @Override
    public CommonSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_common, parent, false);
        return new CommonSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommonSearchViewHolder holder, int position) {
        LayoutParams params = holder.itemView.getLayoutParams();
        if (params instanceof MarginLayoutParams) {
            MarginLayoutParams lParams = (MarginLayoutParams) params;
            if (position == 0) {
                lParams.topMargin = 0;
            } else {
                lParams.topMargin = holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.s_5);
            }
            holder.itemView.setLayoutParams(lParams);
        }

        switch (mTypeResId) {
            case R.integer.type_cases:
                setCasesData(holder, position);
                break;
            case R.integer.type_vehicle_track:
                setVehicleTrackData(holder, position);
                break;
            case R.integer.type_suspect_track:
                setSuspectTrackData(holder, position);
                break;
            case R.integer.type_people_lost:
                setPeopleLostData(holder, position);
                break;
            case R.integer.type_lost_found:
                setLostFoundData(holder, position);
                break;
            case R.integer.type_police_interact:
                setPoliceInteractData(holder, position);
                break;
            case R.integer.type_alarm_history:
                setAlarmHistoryData(holder, position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        switch (mTypeResId) {
            case R.integer.type_cases:
                return mCasesInfoList != null ? mCasesInfoList.size() : 0;
            case R.integer.type_vehicle_track:
                return mCarInfoList != null ? mCarInfoList.size() : 0;
            case R.integer.type_suspect_track:
                return mSuspectInfoList != null ? mSuspectInfoList.size() : 0;
            case R.integer.type_people_lost:
                return mBelostInfoList != null ? mBelostInfoList.size() : 0;
            case R.integer.type_lost_found:
                return mLostInfoList != null ? mLostInfoList.size() : 0;
            case R.integer.type_police_interact:
                // TODO: 2017/9/25 weizhilei 缺少警民互动接口
                break;
            case R.integer.type_alarm_history:
                return mAlarmInfoList != null ? mAlarmInfoList.size() : 0;
        }
        return 0;
    }

    /**
     * 设置要案数据
     *
     * @param holder
     * @param position
     */
    private void setCasesData(CommonSearchViewHolder holder, int position) {
        if (!CollectionUtils.isEmpty(mCasesInfoList) && position < mCasesInfoList.size()) {
            CaseInfo caseInfo = mCasesInfoList.get(position);
            if (caseInfo != null) {
                holder.mTopTv.setText(caseInfo.getCases_theme());
                holder.mTopTimeTv.setText(caseInfo.getCases_sendtime());
                holder.mMiddleTv.setText(caseInfo.getCases_content());
                // TODO: 2017/9/25 weizhilei 需要确认字段 
                holder.mBottomTv.setText("要案发布机构要案发布机构要案发布机构要案发布机构要案发布机构");
                holder.mBottomTimeTv.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置车辆追踪数据
     *
     * @param holder
     * @param position
     */
    private void setVehicleTrackData(CommonSearchViewHolder holder, int position) {
        if (!CollectionUtils.isEmpty(mCarInfoList) && position < mCarInfoList.size()) {
            CarInfo carInfo = mCarInfoList.get(position);
            if (carInfo != null) {
                String title = carInfo.getCar_num() + "," + carInfo.getCar_type() + "," + carInfo.getCar_color();
                holder.mTopTv.setText(title);
                holder.mTopTimeTv.setText(carInfo.getCar_date());
                // TODO: 2017/9/25 weizhilei 无字段
                holder.mMiddleTv.setText("「已追踪59天1小时」于2017-9-31 10：25时许，啦啦啦啦啦");
                holder.mBottomTv.setText("北京市朝阳区和平街15区");
                holder.mBottomTimeTv.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置疑犯追踪数据
     *
     * @param holder
     * @param position
     */
    private void setSuspectTrackData(CommonSearchViewHolder holder, int position) {
        if (!CollectionUtils.isEmpty(mSuspectInfoList) && position < mSuspectInfoList.size()) {
            SuspectInfo suspectInfo = mSuspectInfoList.get(position);
            if (suspectInfo != null) {
                // TODO: 2017/9/25 weizhilei 缺少年龄字段
                String title = suspectInfo.getSuspect_name() + "," + suspectInfo.getSuspect_sex();
                holder.mTopTv.setText(title);
                // TODO: 2017/9/25 weizhilei 缺少右上角日期 
                holder.mTopTimeTv.setText(DateUtils.formatDate(DateUtils.Y_M_D_H_M, System.currentTimeMillis()));
                // TODO: 2017/9/25 weizhilei 缺少描述字段
                holder.mMiddleTv.setText("「已追踪59天1小时」于2017-9-31 10：25时许，啦啦啦啦啦");
                // TODO: 2017/9/25 weizhilei 缺少address
                holder.mBottomTv.setText("北京市朝阳区和平街15区");
                holder.mBottomTimeTv.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置人员走失数据
     *
     * @param holder
     * @param position
     */
    private void setPeopleLostData(CommonSearchViewHolder holder, int position) {
        if (!CollectionUtils.isEmpty(mBelostInfoList) && position < mBelostInfoList.size()) {
            BelostInfo belostInfo = mBelostInfoList.get(position);
            if (belostInfo != null) {
                String title = belostInfo.getBeLost_name() + "," + belostInfo.getBeLost_sex() + "," + belostInfo
                        .getBeLost_age();
                holder.mTopTv.setText(title);
                holder.mTopTimeTv.setText(belostInfo.getBeLost_date());
                // TODO: 2017/9/25 weizhilei 缺少描述和地址字段
                holder.mMiddleTv.setText("「已丢失59天1小时」于2017-9-31 10：25时许，啦啦啦啦啦");
                holder.mBottomTv.setText("北京市朝阳区和平街15区");
                holder.mBottomTimeTv.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置遗失招领数据
     *
     * @param holder
     * @param position
     */
    private void setLostFoundData(CommonSearchViewHolder holder, int position) {
        if (!CollectionUtils.isEmpty(mLostInfoList) && position < mLostInfoList.size()) {
            LostInfo lostInfo = mLostInfoList.get(position);
            if (lostInfo != null) {
                // TODO: 2017/9/25 weizhilei 缺少标题
                holder.mTopTv.setText("遗失招领标题" + position);
                holder.mTopTimeTv.setText(lostInfo.getLost_date());
                holder.mMiddleTv.setText(lostInfo.getLost_info());
                holder.mBottomTv.setText(lostInfo.getLost_unit());
                holder.mBottomTimeTv.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置警民互动数据
     *
     * @param holder
     * @param position
     */
    private void setPoliceInteractData(CommonSearchViewHolder holder, int position) {
        holder.mTopTv.setText(DateUtils.formatDate(DateUtils.Y_M_D_H_M, System.currentTimeMillis()));
        holder.mTopTimeTv.setVisibility(View.GONE);
        holder.mMiddleTv.setText("详情描述详情描述详情描述详情描述详情描述详情描述详情描述详情描述详情描述详情描述详情描述");
        holder.mBottomTv.setText("永丰派出所昌平区回龙观");
        holder.mBottomTimeTv.setVisibility(View.GONE);
    }

    /**
     * 设置报警历史数据
     *
     * @param holder
     * @param position
     */
    private void setAlarmHistoryData(CommonSearchViewHolder holder, int position) {
        if (!CollectionUtils.isEmpty(mAlarmInfoList) && position < mAlarmInfoList.size()) {
            AlarmInfoBean alarmInfo = mAlarmInfoList.get(position);
            if (alarmInfo != null) {
                holder.mTopTv.setText(alarmInfo.getAlarm_status());
                holder.mTopTimeTv.setVisibility(View.GONE);
                holder.mMiddleTv.setText(alarmInfo.getAlarm_content());
                holder.mBottomTv.setText(alarmInfo.getAlarm_addres());
                holder.mBottomTimeTv.setText(alarmInfo.getRptalarm_time());
            }
        }
    }

    /**
     * Created by weizhilei on 2017/9/23.
     */
    class CommonSearchViewHolder extends ViewHolder implements OnClickListener {
        private TextView mTopTv;
        private TextView mTopTimeTv;
        private TextView mMiddleTv;
        private TextView mBottomTv;
        private TextView mBottomTimeTv;

        public CommonSearchViewHolder(View itemView) {
            super(itemView);
            mTopTv = (TextView) itemView.findViewById(R.id.common_top);
            mTopTimeTv = (TextView) itemView.findViewById(R.id.common_top_time);
            mMiddleTv = (TextView) itemView.findViewById(R.id.common_middle);
            mBottomTv = (TextView) itemView.findViewById(R.id.common_bottom);
            mBottomTimeTv = (TextView) itemView.findViewById(R.id.common_bottom_time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (mTypeResId) {
                case R.integer.type_cases:
                    // TODO: 2017/9/25 weizhilei 没有H5字段
                    IntentUtils.openWebView(v.getContext());
                    break;
                case R.integer.type_vehicle_track:
                    IntentUtils.openVehicleTrack(v.getContext());
                    break;
                case R.integer.type_suspect_track:
                    IntentUtils.openSuspectTrack(v.getContext());
                    break;
                case R.integer.type_people_lost:
                    IntentUtils.openPeopleLost(v.getContext());
                    break;
                case R.integer.type_lost_found:
                    IntentUtils.openLostFound(v.getContext());
                    break;
                case R.integer.type_police_interact:
                    IntentUtils.openPoliceInteractDetail(v.getContext());
                    break;
                case R.integer.type_alarm_history:
                    ToastUtils.showToast(v.getContext(), R.string.alarm_history);
                    break;
            }

        }
    }

}
