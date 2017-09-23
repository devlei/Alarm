package phonealarm.iss.com.alarm.personal.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.personal.adapter.EmergencyContactAdapter.EmergencyContactViewHolder;
import phonealarm.iss.com.alarm.utils.ToastUtils;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class EmergencyContactAdapter extends RecyclerView.Adapter<EmergencyContactViewHolder> {

    @Override
    public EmergencyContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_emergency_contact, parent, false);
        return new EmergencyContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmergencyContactViewHolder holder, int position) {
        holder.mNameTv.setText("永丰派出所" + position);
        holder.mPhoneTv.setText(String.valueOf((15212345678l + 1l)));
        holder.mAddressTv.setText("永丰派出所昌平区回龙观" + position);
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    /**
     * Created by weizhilei on 2017/9/23.
     */
    class EmergencyContactViewHolder extends ViewHolder implements OnClickListener {
        private TextView mNameTv;
        private TextView mPhoneTv;
        private TextView mAddressTv;

        public EmergencyContactViewHolder(View itemView) {
            super(itemView);
            mNameTv = (TextView) itemView.findViewById(R.id.ec_name);
            mPhoneTv = (TextView) itemView.findViewById(R.id.ec_phone);
            mAddressTv = (TextView) itemView.findViewById(R.id.ec_address);
            itemView.findViewById(R.id.ec_call).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // TODO: 2017/9/23 weizhilei 拨打电话
            ToastUtils.showToast(v.getContext(), "拨打电话");
        }
    }

}
