package com.kongqw.wifilibrary.listener;

/**
 *
 * @author kqw
 * @date 2016/8/4
 * WIFI打开关闭的回调接口
 */
public interface OnWifiEnabledListener {

    /**
     * WIFI开关的回调
     *
     * @param enabled true 可用 false 不可用
     */
    void onWifiEnabled(boolean enabled);
}
