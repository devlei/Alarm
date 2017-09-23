package phonealarm.iss.com.alarm.hall.adapter;

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
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.hall.HeaderSpanSizeLookup;
import phonealarm.iss.com.alarm.hall.IHeader;
import phonealarm.iss.com.alarm.utils.ToastUtils;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class InfoAdapter extends RecyclerView.Adapter implements IHeader {

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
                return new InfoHeaderViewHolder(headerView);
            default:
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info, parent, false);
                return new InfoViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            if (holder instanceof InfoHeaderViewHolder) {
                InfoHeaderViewHolder headerViewHolder = (InfoHeaderViewHolder) holder;
                headerViewHolder.mNameTv.setText(R.string.release_information);
            }
        } else {
            if (holder instanceof InfoViewHolder) {
                InfoViewHolder viewHolder = (InfoViewHolder) holder;
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
        return 5;
    }

    /**
     * Created by weizhilei on 2017/9/23.
     */
    class InfoHeaderViewHolder extends ViewHolder {
        public TextView mNameTv;

        public InfoHeaderViewHolder(View itemView) {
            super(itemView);
            mNameTv = (TextView) itemView.findViewById(R.id.header_name);
        }
    }

    /**
     * Created by weizhilei on 2017/9/23.
     */
    class InfoViewHolder extends ViewHolder implements OnClickListener {
        public ImageView mIconIv;
        public TextView mNameTv;

        public InfoViewHolder(View itemView) {
            super(itemView);
            mIconIv = (ImageView) itemView.findViewById(R.id.item_icon);
            mNameTv = (TextView) itemView.findViewById(R.id.item_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getTag() != null) {
                Integer type = (Integer) view.getTag();
                if (type == getInteger(itemView.getContext(), R.integer.type_cases)) {
                    ToastUtils.showToast(itemView.getContext(), R.string.cases);
                } else if (type == getInteger(itemView.getContext(), R.integer.type_vehicle_track)) {
                    ToastUtils.showToast(itemView.getContext(), R.string.vehicle_track);
                } else if (type == getInteger(itemView.getContext(), R.integer.type_suspect_track)) {
                    ToastUtils.showToast(itemView.getContext(), R.string.suspect_track);
                } else if (type == getInteger(itemView.getContext(), R.integer.type_people_lost)) {
                    ToastUtils.showToast(itemView.getContext(), R.string.people_lost);
                } else if (type == getInteger(itemView.getContext(), R.integer.type_lost_found)) {
                    ToastUtils.showToast(itemView.getContext(), R.string.lost_found);
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
            return context.getResources().getIntArray(R.array.type_release_info);
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
        if (type == getInteger(context, R.integer.type_cases)) {
            iconResId = R.drawable.icon_cases;
        } else if (type == getInteger(context, R.integer.type_vehicle_track)) {
            iconResId = R.drawable.icon_vehicle_track;
        } else if (type == getInteger(context, R.integer.type_suspect_track)) {
            iconResId = R.drawable.icon_suspect_track;
        } else if (type == getInteger(context, R.integer.type_people_lost)) {
            iconResId = R.drawable.icon_people_lost;
        } else if (type == getInteger(context, R.integer.type_lost_found)) {
            iconResId = R.drawable.icon_lost_found;
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
        if (type == getInteger(context, R.integer.type_cases)) {
            nameResId = R.string.cases;
        } else if (type == getInteger(context, R.integer.type_vehicle_track)) {
            nameResId = R.string.vehicle_track;
        } else if (type == getInteger(context, R.integer.type_suspect_track)) {
            nameResId = R.string.suspect_track;
        } else if (type == getInteger(context, R.integer.type_people_lost)) {
            nameResId = R.string.people_lost;
        } else if (type == getInteger(context, R.integer.type_lost_found)) {
            nameResId = R.string.lost_found;
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
