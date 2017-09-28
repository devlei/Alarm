package com.iss.phonealarm.hall.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.iss.phonealarm.R;
import com.iss.phonealarm.hall.HeaderSpanSizeLookup;
import com.iss.phonealarm.hall.IHeader;
import com.iss.phonealarm.utils.IntentUtils;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class BenefitAdapter extends RecyclerView.Adapter implements IHeader {

    private static final int TYPE_HEADER = 1;

    private Context mContext;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mContext = recyclerView.getContext();
        LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new HeaderSpanSizeLookup(this));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                View headerView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_header, parent, false);
                return new BenefitHeaderViewHolder(headerView);
            default:
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_benefit, parent, false);
                return new BenefitViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            if (holder instanceof BenefitHeaderViewHolder) {
                BenefitHeaderViewHolder headerViewHolder = (BenefitHeaderViewHolder) holder;
                headerViewHolder.mNameTv.setText(R.string.my_benefit);
            }
        } else {
            if (holder instanceof BenefitViewHolder) {
                BenefitViewHolder viewHolder = (BenefitViewHolder) holder;
                int[] typeArray = getTypeArray(mContext);
                if (typeArray != null && position - 1 < typeArray.length) {
                    int type = typeArray[position - 1];
                    viewHolder.mIconIv.setBackgroundResource(getIconResId(mContext, type));
                    viewHolder.mNameTv.setText(getNameResId(mContext, type));
                    viewHolder.itemView.setTag(type);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        int[] typeArray = getTypeArray(mContext);
        if (typeArray != null) {
            return typeArray.length + 1;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderPosition(position)) return TYPE_HEADER;
        return super.getItemViewType(position);
    }

    @Override
    public boolean isHeaderPosition(int position) {
        if (position == 0) return true;
        return false;
    }

    @Override
    public int getHeaderSpanSize() {
        return 4;
    }

    /**
     * Created by weizhilei on 2017/9/23.
     */
    class BenefitHeaderViewHolder extends ViewHolder {
        public TextView mNameTv;

        public BenefitHeaderViewHolder(View itemView) {
            super(itemView);
            mNameTv = (TextView) itemView.findViewById(R.id.header_name);
        }
    }

    /**
     * Created by weizhilei on 2017/9/23.
     */
    class BenefitViewHolder extends ViewHolder implements OnClickListener {

        public ImageView mIconIv;
        public TextView mNameTv;

        public BenefitViewHolder(View itemView) {
            super(itemView);
            mIconIv = (ImageView) itemView.findViewById(R.id.item_icon);
            mNameTv = (TextView) itemView.findViewById(R.id.item_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getTag() != null) {
                Integer type = (Integer) view.getTag();
                if (type == getInteger(itemView.getContext(), R.integer.type_alarm)) {//一键报警
                    IntentUtils.openFastAlarmActivity(view.getContext(), R.integer.type_alarm);
                } else if (type == getInteger(itemView.getContext(), R.integer.type_eager_report)) {//热心举报
                    IntentUtils.openFastAlarmActivity(view.getContext(), R.integer.type_eager_report);
                } else if (type == getInteger(view.getContext(), R.integer.type_police_interact)) {
                    IntentUtils.openCommonSearch(view.getContext(), R.integer.type_police_interact);
                } else if (type == getInteger(itemView.getContext(), R.integer.type_rent_collect)) {
                    IntentUtils.openRentCollect(view.getContext());
                } else if (type == getInteger(itemView.getContext(), R.integer.type_hotel_collect)) {
                    IntentUtils.openHotalCollect(view.getContext());
                }
            }
        }
    }

    /**
     * 获取种类数组
     *
     * @param context
     * @return
     */
    private int[] getTypeArray(Context context) {
        if (context != null) {
            return context.getResources().getIntArray(R.array.type_benefit);
        }
        return null;
    }

    /**
     * 获取iconResId
     *
     * @param context
     * @param type
     * @return
     */
    private int getIconResId(Context context, int type) {
        int iconResId = R.color.colorPrimary;
        if (type == getInteger(context, R.integer.type_alarm)) {
            iconResId = R.drawable.icon_alarm;
        } else if (type == getInteger(context, R.integer.type_eager_report)) {
            iconResId = R.drawable.icon_eager_report;
        } else if (type == getInteger(context, R.integer.type_police_interact)) {
            iconResId = R.drawable.icon_people_lost;
        } else if (type == getInteger(context, R.integer.type_rent_collect)) {
            iconResId = R.drawable.icon_rent_collect;
        } else if (type == getInteger(context, R.integer.type_hotel_collect)) {
            iconResId = R.drawable.icon_hotel_collect;
        }
        return iconResId;
    }

    /**
     * 获取nameResId
     *
     * @param type
     * @return
     */
    private int getNameResId(Context context, int type) {
        int nameResId = R.string.cases;
        if (type == getInteger(context, R.integer.type_alarm)) {
            nameResId = R.string.alarm;
        } else if (type == getInteger(context, R.integer.type_eager_report)) {
            nameResId = R.string.eager_report;
        } else if (type == getInteger(context, R.integer.type_police_interact)) {
            nameResId = R.string.police_interact;
        } else if (type == getInteger(context, R.integer.type_rent_collect)) {
            nameResId = R.string.rent_collect;
        } else if (type == getInteger(context, R.integer.type_hotel_collect)) {
            nameResId = R.string.hotel_collect;
        }
        return nameResId;
    }

    /**
     * @param context
     * @param resId
     * @return
     */
    private int getInteger(Context context, int resId) {
        if (context != null) {
            return context.getResources().getInteger(resId);
        }
        return 0;
    }

}
