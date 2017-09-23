package phonealarm.iss.com.alarm.personal.adapter;

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
import phonealarm.iss.com.alarm.personal.adapter.AlarmHistoryAdapter.AlarmHistoryViewHolder;
import phonealarm.iss.com.alarm.utils.DateUtils;
import phonealarm.iss.com.alarm.utils.ToastUtils;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class AlarmHistoryAdapter extends RecyclerView.Adapter<AlarmHistoryViewHolder> {

    @Override
    public AlarmHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm_history, parent, false);
        return new AlarmHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlarmHistoryViewHolder holder, int position) {
        if (position == 0) {
            LayoutParams params = holder.itemView.getLayoutParams();
            if (params instanceof MarginLayoutParams) {
                MarginLayoutParams lParams = (MarginLayoutParams) params;
                lParams.topMargin = 0;
                holder.itemView.setLayoutParams(lParams);
            }
        }
        holder.mStateTv.setText("正在处理" + position);
        holder.mDescTv.setText("详情描述详情描述详情描述详情描述详情描述详情描述详情描述详情描述详情描述详情描述详情描述");
        holder.mAddressTv.setText("永丰派出所昌平区回龙观永丰派出所昌平区回龙观永丰派出所昌平区回龙观永丰派出所昌平区回龙观");
        holder.mTimeTv.setText(DateUtils.formatDate(DateUtils.Y_M_D_H_M, System.currentTimeMillis()));
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    /**
     * Created by weizhilei on 2017/9/23.
     */
    class AlarmHistoryViewHolder extends ViewHolder implements OnClickListener {
        private TextView mStateTv;
        private TextView mDescTv;
        private TextView mAddressTv;
        private TextView mTimeTv;

        public AlarmHistoryViewHolder(View itemView) {
            super(itemView);
            mStateTv = (TextView) itemView.findViewById(R.id.ah_state);
            mDescTv = (TextView) itemView.findViewById(R.id.ah_desc);
            mAddressTv = (TextView) itemView.findViewById(R.id.ah_address);
            mTimeTv = (TextView) itemView.findViewById(R.id.ah_time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // TODO: 2017/9/23 weizhilei 报警历史
            ToastUtils.showToast(v.getContext(), R.string.alarm_history);
        }
    }

}
