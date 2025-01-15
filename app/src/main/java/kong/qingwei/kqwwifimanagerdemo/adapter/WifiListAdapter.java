package kong.qingwei.kqwwifimanagerdemo.adapter;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongqw.wifilibrary.WiFiManager;

import java.util.ArrayList;
import java.util.List;

import kong.qingwei.kqwwifimanagerdemo.R;

/**
 *
 * @author kqw
 * @date 2016/8/2
 * Wifi列表的数据适配器
 */
public class WifiListAdapter extends BaseAdapter {

    private static final String TAG = "WifiListAdapter";
    private List<ScanResult> scanResults;
    private Context mContext;

    public WifiListAdapter(Context context) {
        mContext = context.getApplicationContext();
        this.scanResults = new ArrayList<>();
    }

    public void refreshData(List<ScanResult> scanResults) {
        if (null != scanResults) {
            Log.i(TAG, "refreshData 1 : " + scanResults.size());
            // 去重
            scanResults = WiFiManager.excludeRepetition(scanResults);
            Log.i(TAG, "refreshData 2 : " + scanResults.size());
            // 清空数据
            this.scanResults.clear();
            // 更新数据
            this.scanResults.addAll(scanResults);
        }
        // 更新显示
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return scanResults.size();
    }

    @Override
    public Object getItem(int position) {
        return scanResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.item_wifi, null);
            holder = new ViewHolder();
            holder.ssid = (TextView) (convertView).findViewById(R.id.ssid);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ScanResult scanResult = scanResults.get(position);
        holder.ssid.setText("热点名称：" + scanResult.SSID + "\n信号强度：" + WifiManager.calculateSignalLevel(scanResult.level, 5) + "/5\n加密方式：" + scanResult.capabilities);
        return convertView;
    }

    private class ViewHolder {
        private TextView ssid;
    }
}
