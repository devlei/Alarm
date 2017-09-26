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
import phonealarm.iss.com.alarm.utils.IntentUtils;
import phonealarm.iss.com.alarm.utils.ToastUtils;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class InfoAdapter extends RecyclerView.Adapter {

    private Context mContext;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mContext = recyclerView.getContext();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info, parent, false);
        return new InfoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof InfoViewHolder) {
            InfoViewHolder viewHolder = (InfoViewHolder) holder;
            int[] typeArray = getTypeArray(mContext);
            if (typeArray != null && position < typeArray.length) {
                int type = typeArray[position];
                viewHolder.mIconIv.setBackgroundResource(getIconResId(mContext, type));
                viewHolder.mNameTv.setText(getNameResId(mContext, type));
                viewHolder.itemView.setTag(type);
            }
        }
    }

    @Override
    public int getItemCount() {
        int[] typeArray = getTypeArray(mContext);
        if (typeArray != null) {
            return typeArray.length;
        }
        return 0;
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
                    IntentUtils.openCommonSearch(itemView.getContext(), R.integer.type_cases);
                } else if (type == getInteger(itemView.getContext(), R.integer.type_vehicle_track)) {
                    IntentUtils.openCommonSearch(itemView.getContext(), R.integer.type_vehicle_track);
                } else if (type == getInteger(itemView.getContext(), R.integer.type_suspect_track)) {
                    IntentUtils.openCommonSearch(itemView.getContext(), R.integer.type_suspect_track);
                } else if (type == getInteger(itemView.getContext(), R.integer.type_people_lost)) {
                    IntentUtils.openCommonSearch(itemView.getContext(), R.integer.type_people_lost);
                } else if (type == getInteger(itemView.getContext(), R.integer.type_lost_found)) {
                    IntentUtils.openCommonSearch(itemView.getContext(), R.integer.type_lost_found);
                } else if (type == getInteger(itemView.getContext(), R.integer.type_safe)) {
                    ToastUtils.showToast(view.getContext(), R.string.function_not_open);
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
        } else if (type == getInteger(context, R.integer.type_safe)) {
            iconResId = R.drawable.icon_safe;
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
        } else if (type == getInteger(context, R.integer.type_safe)) {
            nameResId = R.string.safe_info;
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
