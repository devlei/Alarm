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
import phonealarm.iss.com.alarm.hall.adapter.CommonSearchAdapter.CommonSearchViewHolder;
import phonealarm.iss.com.alarm.utils.DateUtils;
import phonealarm.iss.com.alarm.utils.IntentUtils;
import phonealarm.iss.com.alarm.utils.ToastUtils;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class CommonSearchAdapter extends RecyclerView.Adapter<CommonSearchViewHolder> {

    private int mTypeResId;

    public CommonSearchAdapter(int typeResId) {
        this.mTypeResId = typeResId;
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
        return 50;
    }

    /**
     * 设置要案数据
     *
     * @param holder
     */
    private void setCasesData(CommonSearchViewHolder holder, int position) {
        holder.mTopTv.setText("要案标题" + position);
        holder.mTopTimeTv.setText(DateUtils.formatDate(DateUtils.D_M_H_M, System.currentTimeMillis()));
        holder.mMiddleTv.setText("要案内容要案内容要案内容要案内容要案内容要案内容要案内容");
        holder.mBottomTv.setText("要案发布机构要案发布机构要案发布机构要案发布机构要案发布机构");
        holder.mBottomTimeTv.setVisibility(View.GONE);
    }

    /**
     * 设置车辆追踪数据
     *
     * @param holder
     */
    private void setVehicleTrackData(CommonSearchViewHolder holder, int position) {
        holder.mTopTv.setText("京A09876K，大型货车，红色" + position);
        holder.mTopTimeTv.setText(DateUtils.formatDate(DateUtils.Y_M_D_H_M, System.currentTimeMillis()));
        holder.mMiddleTv.setText("「已追踪59天1小时」于2017-9-31 10：25时许，啦啦啦啦啦");
        holder.mBottomTv.setText("北京市朝阳区和平街15区");
        holder.mBottomTimeTv.setVisibility(View.GONE);
    }

    /**
     * 设置疑犯追踪数据
     *
     * @param holder
     */
    private void setSuspectTrackData(CommonSearchViewHolder holder, int position) {
        holder.mTopTv.setText("姓名，男，32岁" + position);
        holder.mTopTimeTv.setText(DateUtils.formatDate(DateUtils.Y_M_D_H_M, System.currentTimeMillis()));
        holder.mMiddleTv.setText("「已追踪59天1小时」于2017-9-31 10：25时许，啦啦啦啦啦");
        holder.mBottomTv.setText("北京市朝阳区和平街15区");
        holder.mBottomTimeTv.setVisibility(View.GONE);
    }

    /**
     * 设置人员走失数据
     *
     * @param holder
     */
    private void setPeopleLostData(CommonSearchViewHolder holder, int position) {
        holder.mTopTv.setText("姓名，男，32岁" + position);
        holder.mTopTimeTv.setText(DateUtils.formatDate(DateUtils.Y_M_D_H_M, System.currentTimeMillis()));
        holder.mMiddleTv.setText("「已丢失59天1小时」于2017-9-31 10：25时许，啦啦啦啦啦");
        holder.mBottomTv.setText("北京市朝阳区和平街15区");
        holder.mBottomTimeTv.setVisibility(View.GONE);
    }

    /**
     * 设置遗失招领数据
     *
     * @param holder
     */
    private void setLostFoundData(CommonSearchViewHolder holder, int position) {
        holder.mTopTv.setText("遗失招领标题" + position);
        holder.mTopTimeTv.setText(DateUtils.formatDate(DateUtils.Y_M_D_H_M, System.currentTimeMillis()));
        holder.mMiddleTv.setText("物品详情物品详情物品详情物品详情物品详情物品详情物品详情物品详情");
        holder.mBottomTv.setText("北京市朝阳区和平街15区");
        holder.mBottomTimeTv.setVisibility(View.GONE);
    }

    /**
     * 设置警民互动数据
     *
     * @param holder
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
     */
    private void setAlarmHistoryData(CommonSearchViewHolder holder, int position) {
        holder.mTopTv.setText("正在处理" + position);
        holder.mTopTimeTv.setVisibility(View.GONE);
        holder.mMiddleTv.setText("详情描述详情描述详情描述详情描述详情描述详情描述详情描述详情描述详情描述详情描述详情描述");
        holder.mBottomTv.setText("永丰派出所昌平区回龙观");
        holder.mBottomTimeTv.setText(DateUtils.formatDate(DateUtils.Y_M_D_H_M, System.currentTimeMillis()));
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
            // TODO: 2017/9/24 weizhilei 搜索展示
            switch (mTypeResId) {
                case R.integer.type_cases:
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
