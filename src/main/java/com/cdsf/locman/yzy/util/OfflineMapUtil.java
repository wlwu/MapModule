package com.cdsf.locman.yzy.util;

import android.content.Context;
import android.widget.TextView;

import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.cdsf.locman.yzy.R;

/**
 * Created by Administrator on 2016/10/31.
 */
public class OfflineMapUtil {

    public static String formatDataSize(int size) {
        String ret = "";
        if (size < (1024 * 1024)) {
            ret = String.format("%dK", size / 1024);
        } else {
            ret = String.format("%.1fM", size / (1024 * 1024.0));
        }
        return ret;
    }
    public static void setOfflineDownloadStatus(Context mContext, TextView statusView, MKOLUpdateElement element){
        switch (element.status) {
            case MKOLUpdateElement.DOWNLOADING:
                statusView.setTextColor(mContext.getResources().getColor(R.color.primaryColor));
                statusView.setText("(正在下载)");
                break;
            case MKOLUpdateElement.FINISHED:
                statusView.setTextColor(mContext.getResources().getColor(R.color.font_color_black));
                statusView.setText("(已下载)");
                break;
            case MKOLUpdateElement.WAITING:
                statusView.setTextColor(mContext.getResources().getColor(R.color.primaryColor));
                statusView.setText("(等待下载)");
                break;
            case MKOLUpdateElement.SUSPENDED:
                statusView.setTextColor(mContext.getResources().getColor(R.color.font_color_red));
                statusView.setText("(已暂停)");
                break;
        }
    }
    public static void setOfflineDownloadStatusWithPercent(Context mContext, TextView statusView, MKOLUpdateElement element){
        String text="";
        int netType=NetUtil.netType(mContext);
        if(netType!=NetUtil.NET_WIFI){

        }
        switch (element.status) {
            case MKOLUpdateElement.DOWNLOADING:
                statusView.setTextColor(mContext.getResources().getColor(R.color.primaryColor));
                statusView.setText("正在下载"+element.ratio+"%");
                break;
            case MKOLUpdateElement.FINISHED:
                statusView.setTextColor(mContext.getResources().getColor(R.color.font_color_black));
                statusView.setText("已下载");
                break;
            case MKOLUpdateElement.WAITING:
                statusView.setTextColor(mContext.getResources().getColor(R.color.primaryColor));
                statusView.setText("等待下载");
                break;
            case MKOLUpdateElement.SUSPENDED:
                statusView.setTextColor(mContext.getResources().getColor(R.color.font_color_red));
                statusView.setText("已暂停"+element.ratio+"%");
                break;
            case MKOLUpdateElement.eOLDSNetError:
                statusView.setTextColor(mContext.getResources().getColor(R.color.font_color_red));
                statusView.setText("网络异常"+element.ratio+"%");
                break;
            case MKOLUpdateElement.eOLDSWifiError:
                statusView.setTextColor(mContext.getResources().getColor(R.color.font_color_red));
                statusView.setText("wifi网络异常"+element.ratio+"%");
                break;
        }
    }
}
