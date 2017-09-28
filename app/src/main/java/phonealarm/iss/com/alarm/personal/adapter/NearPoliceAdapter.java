package phonealarm.iss.com.alarm.personal.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.personal.adapter.NearPoliceAdapter.NearPoliceViewHolder;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class NearPoliceAdapter extends RecyclerView.Adapter<NearPoliceViewHolder> {

    @Override
    public NearPoliceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_near_police, parent, false);
        return new NearPoliceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NearPoliceViewHolder holder, int position) {
        holder.mNameTv.setText("永丰派出所" + position + "    地址详情地址详情地址详情地址详情地址详情地址详情");
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    /**
     * Created by weizhilei on 2017/9/23.
     */
    class NearPoliceViewHolder extends ViewHolder {
        private TextView mNameTv;

        public NearPoliceViewHolder(View itemView) {
            super(itemView);
            mNameTv = (TextView) itemView.findViewById(R.id.police_name);
        }
    }

}
