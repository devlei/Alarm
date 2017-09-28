package phonealarm.iss.com.alarm.hall.filter;

import android.text.TextUtils;
import android.widget.Filter;
import phonealarm.iss.com.alarm.bean.interactquery.InterQueryInfo;
import phonealarm.iss.com.alarm.hall.adapter.CommonSearchAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weizhilei on 2017/9/28.
 */
public class InteractFilter extends Filter {

    private CommonSearchAdapter mAdapter;

    //原始数据
    private List<InterQueryInfo> mInterQueryInfoList;

    //原始备份数据
    private List<InterQueryInfo> mOriginalValues;

    //过滤器上的锁可以同步复制原始数据
    private final Object mLock = new Object();

    public InteractFilter(CommonSearchAdapter adapter, List<InterQueryInfo> interQueryInfoList) {
        mAdapter = adapter;
        mInterQueryInfoList = interQueryInfoList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        //初始化过滤结果对象
        FilterResults results = new FilterResults();
        //原始数据备份为空时，上锁，同步复制原始数据
        if (mOriginalValues == null) {
            synchronized (mLock) {
                mOriginalValues = new ArrayList<InterQueryInfo>(mInterQueryInfoList);
            }
        }
        //关键字为null时
        if (TextUtils.isEmpty(constraint)) {
            ArrayList<InterQueryInfo> list;
            //同步复制一个原始备份数据
            synchronized (mLock) {
                list = new ArrayList<>(mOriginalValues);
            }
            //此时返回的results就是原始的数据，不进行过滤
            results.values = list;
            results.count = list.size();
        } else {
            String prefixString = constraint.toString().toLowerCase();//转化为小写

            ArrayList<InterQueryInfo> values;
            //同步复制一个原始备份数据
            synchronized (mLock) {
                values = new ArrayList<>(mOriginalValues);
            }
            final int count = values.size();
            //过滤后的list
            final ArrayList<InterQueryInfo> newValues = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                final InterQueryInfo value = values.get(i);
                //标题为过滤条件
                final String valueText = value.getFk_date();
                if (valueText.contains(prefixString)) {
                    newValues.add(value);
                }
            }
            results.values = newValues;
            results.count = newValues.size();
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        if (results.count > 0) {
            //有关键字的时候刷新数据
            mAdapter.setInterQueryInfoList((List<InterQueryInfo>) results.values);
            mAdapter.notifyDataSetChanged();
        } else {
            //关键字不为零但是过滤结果为空刷新数据
            if (constraint.length() != 0) {
                mAdapter.notifyDataSetChanged();
                return;
            }
        }
    }

}
