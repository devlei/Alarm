package com.iss.phonealarm.personal.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import com.iss.phonealarm.R;
import com.iss.phonealarm.bean.contact.GetContact;
import com.iss.phonealarm.personal.adapter.EmergencyContactAdapter.EmergencyContactViewHolder;
import com.iss.phonealarm.utils.CollectionUtils;
import com.iss.phonealarm.utils.Utils;

import java.util.List;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class EmergencyContactAdapter extends RecyclerView.Adapter<EmergencyContactViewHolder> {

    private List<GetContact> mContactList;

    public void setContactList(List<GetContact> contactList) {
        mContactList = contactList;
    }

    @Override
    public EmergencyContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_emergency_contact, parent, false);
        return new EmergencyContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmergencyContactViewHolder holder, int position) {
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
        if (!CollectionUtils.isEmpty(mContactList) && position < mContactList.size()) {
            GetContact contactInfo = mContactList.get(position);
            if (contactInfo != null) {
                holder.mNameTv.setText(contactInfo.getContacts_name());
                holder.mPhoneTv.setText(contactInfo.getContacts_phone());
                holder.mAddressTv.setText(contactInfo.getContacts_address());
                holder.mContactInfo = contactInfo;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mContactList != null ? mContactList.size() : 0;
    }

    /**
     * Created by weizhilei on 2017/9/23.
     */
    class EmergencyContactViewHolder extends ViewHolder implements OnClickListener {
        private TextView mNameTv;
        private TextView mPhoneTv;
        private TextView mAddressTv;
        private GetContact mContactInfo;

        public EmergencyContactViewHolder(View itemView) {
            super(itemView);
            mNameTv = (TextView) itemView.findViewById(R.id.ec_name);
            mPhoneTv = (TextView) itemView.findViewById(R.id.ec_phone);
            mAddressTv = (TextView) itemView.findViewById(R.id.ec_address);
            itemView.findViewById(R.id.ec_call).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mContactInfo != null) {
                Utils.call(v.getContext(), mContactInfo.getContacts_phone());
            }
        }
    }

}
