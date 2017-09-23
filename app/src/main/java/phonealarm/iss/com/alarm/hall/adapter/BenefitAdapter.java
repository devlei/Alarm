package phonealarm.iss.com.alarm.hall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.hall.adapter.BenefitAdapter.BenefitViewHolder;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class BenefitAdapter extends RecyclerView.Adapter<BenefitViewHolder> {

    private Context mContext;

    @Override
    public BenefitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_common, parent, false);
        return new BenefitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BenefitViewHolder holder, int position) {
        String[] benefitArray = getBenefitArray(mContext);
        if (benefitArray != null) {
            holder.mItemName.setText(benefitArray[position]);
        }
    }

    @Override
    public int getItemCount() {
        String[] benefitArray = getBenefitArray(mContext);
        if (benefitArray != null) {
            return benefitArray.length;
        }
        return 0;
    }

    /**
     * Created by weizhilei on 2017/9/23.
     */
    class BenefitViewHolder extends ViewHolder implements OnClickListener {

        public ImageView mItemIcon;
        public TextView mItemName;

        public BenefitViewHolder(View itemView) {
            super(itemView);
            mItemIcon = itemView.findViewById(R.id.item_icon);
            mItemName = itemView.findViewById(R.id.item_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    /**
     * 获取我的惠民数组
     *
     * @param context
     * @return
     */
    private String[] getBenefitArray(Context context) {
        if (context != null) {
            String[] benefitArray = context.getResources().getStringArray(R.array.my_benefit);
            return benefitArray;
        }
        return null;
    }

}
