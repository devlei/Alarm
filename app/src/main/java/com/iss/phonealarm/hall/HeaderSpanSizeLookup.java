package com.iss.phonealarm.hall;

import android.support.v7.widget.GridLayoutManager;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class HeaderSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private IHeader mIHeader;

    public HeaderSpanSizeLookup(IHeader iHeader) {
        mIHeader = iHeader;
    }

    @Override
    public int getSpanSize(int position) {
        int spanSize = 1;
        if (mIHeader != null) {
            if (mIHeader.isHeaderPosition(position)) {
                spanSize = mIHeader.getHeaderSpanSize();
            }
        }
        return spanSize;
    }

}
