package com.cdsf.locman.yzy.util;

import android.content.Context;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.baidu.mapapi.utils.DistanceUtil;

public class LocateUtils {
    private Context context;
    private LocationClient mLocationClient = null;
    public LocateUtils instance;
    public LocationMode mode;

    public LocateUtils(Context context) {
        this.context = context;
        initLocationClient();
    }

    public LocateUtils(Context context, LocationMode mode) {
        this.context = context;
        initLocationClient();
        this.mode = mode;
    }

    public void setListener(BDLocationListener listener) {
        mLocationClient.registerLocationListener(listener);
    }
    public void unRegisterListener(BDLocationListener listener){
        mLocationClient.unRegisterLocationListener(listener);
    }
    public boolean isLocationClientRun() {
        if (mLocationClient != null) {
            if (mLocationClient.isStarted())
                return true;
            else return false;
        } else {
            return false;
        }
    }

    private void initLocationClient() {
        mLocationClient = new LocationClient(context);
        LocationClientOption options = new LocationClientOption();
        options.setOpenGps(true);
        options.setCoorType("bd09ll"); // 设置坐标类型
        // options.setCoorType("");
        options.setAddrType("all");
        options.setScanSpan(1000);
        if (mode != null) {
            options.setLocationMode(mode);
        }
        mLocationClient.setLocOption(options);
    }

    public void startLocate() {
        if (mLocationClient != null && !mLocationClient.isStarted()) {
            mLocationClient.start();
        }
    }

    public void stopLocate() {
        if (mLocationClient != null && mLocationClient.isStarted()) {
            mLocationClient.stop();
        }
    }

    public void onDestroy() {
        stopLocate();
        mLocationClient = null;
    }

    public static LatLng convert2BaiduLL(LatLng sourceLatLng) {
        // 将google地图、soso地图、aliyun地图、mapabc地图和amap地图// 所用坐标转换成百度坐标
        CoordinateConverter converter = new CoordinateConverter();
        converter.from(CoordinateConverter.CoordType.COMMON);
        // sourceLatLng待转换坐标
        converter.coord(sourceLatLng);
        return converter.convert();
    }

    public static LatLng string2LL(String s) {
        String[] str = s.split(",");
        if (str.length == 2) {
            double lat = Double.parseDouble(str[0]);
            double lon = Double.parseDouble(str[1]);
            LatLng ll = new LatLng(lat, lon);
            return ll;
        }
        return new LatLng(100, 30);
    }

    public static String ll2String(LatLng ll) {
        return ll.latitude + "," + ll.longitude;
    }


    public static double getDistance(String p1, String p2) {
        String[] place = p1.split(",");
        LatLng pt1 = new LatLng(Double.parseDouble(place[0]),
                Double.parseDouble(place[1]));
        place = p2.split(",");
        LatLng pt2 = new LatLng(Double.parseDouble(place[1]),
                Double.parseDouble(place[0]));
        return DistanceUtil.getDistance(pt1, pt2);
    }

    public static String getDistanceString(String p1, String p2) {
        String[] place = p1.split(",");
        if (place.length != 2) {
            return "未知";
        }
        LatLng pt1 = new LatLng(Double.parseDouble(place[0]),
                Double.parseDouble(place[1]));
        place = p2.split(",");
        if (place.length != 2) {
            return "未知";
        }
        LatLng pt2 = new LatLng(Double.parseDouble(place[1]),
                Double.parseDouble(place[0]));
        Double distance = DistanceUtil.getDistance(pt1, pt2);
        String dis;
        if (distance < 1000) {
            dis = distance.intValue() + "m";
        } else {
            dis = (float) ((int) (distance / 100)) / 10 + "km";
        }
        return dis;
    }

    public static String getDistanceString(LatLng p1, LatLng p2) {
        Double distance = DistanceUtil.getDistance(p1, p2);
        String dis;
        if (distance < 1000) {
            dis = distance.intValue() + "m";
        } else {
            dis = (float) ((int) (distance / 100)) / 10 + "km";
        }
        return dis;
    }

    public static boolean isFiled(LatLng p1, LatLng p2) {
        Double distance = DistanceUtil.getDistance(p1, p2);
        if (distance < 50) {
            return true;
        }
        return false;
    }
}
