package com.iss.phonealarm.hall.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.iss.phonealarm.R;
import com.iss.phonealarm.bean.beLost.BelostInfo;
import com.iss.phonealarm.bean.carinfo.CarInfo;
import com.iss.phonealarm.bean.caseinfo.CaseInfo;
import com.iss.phonealarm.bean.interactquery.InterQueryInfo;
import com.iss.phonealarm.bean.lost.LostInfo;
import com.iss.phonealarm.bean.searchalarm.AlarmInfoBean;
import com.iss.phonealarm.bean.suspect.SuspectInfo;
import com.iss.phonealarm.hall.adapter.CommonSearchAdapter.CommonSearchViewHolder;
import com.iss.phonealarm.hall.filter.*;
import com.iss.phonealarm.utils.CollectionUtils;
import com.iss.phonealarm.utils.IntentUtils;

import java.util.List;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class CommonSearchAdapter extends RecyclerView.Adapter<CommonSearchViewHolder> implements Filterable {

    private int mTypeResId;

    private List<CaseInfo> mCasesInfoList;
    private List<CarInfo> mCarInfoList;
    private List<SuspectInfo> mSuspectInfoList;
    private List<BelostInfo> mBelostInfoList;
    private List<LostInfo> mLostInfoList;
    private List<InterQueryInfo> mInterQueryInfoList;
    private List<AlarmInfoBean> mAlarmInfoList;

    private CaseFilter mCaseFilter;
    private CarFilter mCarFilter;
    private SuspectFilter mSuspectFilter;
    private PeopleLostFilter mPeopleLostFilter;
    private LostFoundFilter mLostFoundFilter;
    private InteractFilter mInteractFilter;
    private AlarmHistoryFilter mAlarmHistoryFilter;

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

    public void setInterQueryInfoList(List<InterQueryInfo> interQueryInfoList) {
        mInterQueryInfoList = interQueryInfoList;
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
                return mInterQueryInfoList != null ? mInterQueryInfoList.size() : 0;
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
                holder.mBottomTv.setText(caseInfo.getSendproxy());
                holder.mBottomTimeTv.setVisibility(View.GONE);
                holder.mCasesInfo = caseInfo;
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
                holder.mMiddleTv.setText(carInfo.getPursuit_time() + carInfo.getCar_info());
                holder.mBottomTv.setText(carInfo.getCar_adress());
                holder.mBottomTimeTv.setVisibility(View.GONE);
                holder.mCarInfo = carInfo;
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
                String title = suspectInfo.getSuspect_name() + "," + suspectInfo.getSuspect_sex();
                holder.mTopTv.setText(title);
                holder.mTopTimeTv.setText(suspectInfo.getSuspect_sendtime());
                holder.mMiddleTv.setText(suspectInfo.getPursuit_time() + suspectInfo.getSuspect_case());
                holder.mBottomTv.setText(suspectInfo.getSuspect_adress());
                holder.mBottomTimeTv.setVisibility(View.GONE);
                holder.mSuspectInfo = suspectInfo;
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
                holder.mMiddleTv.setText(belostInfo.getPursuit_time() + belostInfo.getBeLost_information());
                holder.mBottomTv.setText(belostInfo.getBeLost_site());
                holder.mBottomTimeTv.setVisibility(View.GONE);
                holder.mBelostInfo = belostInfo;
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
                holder.mTopTv.setText(lostInfo.getLost_name());
                holder.mTopTimeTv.setText(lostInfo.getLost_date());
                holder.mMiddleTv.setText(lostInfo.getLost_info());
                holder.mBottomTv.setText(lostInfo.getLost_site());
                holder.mBottomTimeTv.setVisibility(View.GONE);
                holder.mLostInfo = lostInfo;
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
        if (!CollectionUtils.isEmpty(mInterQueryInfoList) && position < mInterQueryInfoList.size()) {
            InterQueryInfo interQueryInfo = mInterQueryInfoList.get(position);
            if (interQueryInfo != null) {
                holder.mTopTv.setText(interQueryInfo.getFk_date());
                holder.mTopTimeTv.setVisibility(View.GONE);
                holder.mMiddleTv.setText(interQueryInfo.getFk_content());
                holder.mBottomTv.setText(interQueryInfo.getReply_content());
                holder.mBottomTimeTv.setVisibility(View.GONE);
                holder.mInterQueryInfo = interQueryInfo;
            }
        }
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
                holder.mAlarmInfo = alarmInfo;
            }
        }
    }

    @Override
    public Filter getFilter() {
        switch (mTypeResId) {
            case R.integer.type_cases:
                if (mCaseFilter == null) {
                    mCaseFilter = new CaseFilter(this, mCasesInfoList);
                }
                return mCaseFilter;
            case R.integer.type_vehicle_track:
                if (mCarFilter == null) {
                    mCarFilter = new CarFilter(this, mCarInfoList);
                }
                return mCarFilter;
            case R.integer.type_suspect_track:
                if (mSuspectFilter == null) {
                    mSuspectFilter = new SuspectFilter(this, mSuspectInfoList);
                }
                return mSuspectFilter;
            case R.integer.type_people_lost:
                if (mPeopleLostFilter == null) {
                    mPeopleLostFilter = new PeopleLostFilter(this, mBelostInfoList);
                }
                return mPeopleLostFilter;
            case R.integer.type_lost_found:
                if (mLostFoundFilter == null) {
                    mLostFoundFilter = new LostFoundFilter(this, mLostInfoList);
                }
                return mLostFoundFilter;
            case R.integer.type_police_interact:
                if (mInteractFilter == null) {
                    mInteractFilter = new InteractFilter(this, mInterQueryInfoList);
                }
                return mInteractFilter;
            case R.integer.type_alarm_history:
                if (mAlarmHistoryFilter == null) {
                    mAlarmHistoryFilter = new AlarmHistoryFilter(this, mAlarmInfoList);
                }
                return mAlarmHistoryFilter;
        }
        return null;
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

        private CaseInfo mCasesInfo;
        private CarInfo mCarInfo;
        private SuspectInfo mSuspectInfo;
        private BelostInfo mBelostInfo;
        private LostInfo mLostInfo;
        private InterQueryInfo mInterQueryInfo;
        private AlarmInfoBean mAlarmInfo;

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
                    if (mCasesInfo != null) {
                        IntentUtils.openWebView(v.getContext(), mCasesInfo);
                    }
                    break;
                case R.integer.type_vehicle_track:
                    IntentUtils.openVehicleTrack(v.getContext(), mCarInfo);
                    break;
                case R.integer.type_suspect_track:
                    IntentUtils.openSuspectTrack(v.getContext(), mSuspectInfo);
                    break;
                case R.integer.type_people_lost:
                    IntentUtils.openPeopleLost(v.getContext(), mBelostInfo);
                    break;
                case R.integer.type_lost_found:
                    IntentUtils.openLostFound(v.getContext(), mLostInfo);
                    break;
                case R.integer.type_police_interact:
                    IntentUtils.openPoliceInteractDetail(v.getContext(), mInterQueryInfo);
                    break;
                case R.integer.type_alarm_history:
                    IntentUtils.openAlarmHistory(v.getContext(), mAlarmInfo);
                    break;
            }

        }
    }

}
