package com.wanny.amssutdents.framework_ui.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.psnl.hzq.tool.TimeEx;
import com.wanny.amssutdents.amsstudent_business.location_save.LocationBody;
import com.wanny.amssutdents.amsstudent_business.location_save.SaveLocaImpl;
import com.wanny.amssutdents.amsstudent_business.location_save.SvaeLocPresenter;
import com.wanny.amssutdents.framework_care.OrdinalResultEntity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/2/28 17:20
 */

public class LocationUploadService extends Service {

    private SvaeLocPresenter svaeLocPresenter;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        svaeLocPresenter = new SvaeLocPresenter(saveLoca);
    }


    private SaveLocaImpl saveLoca = new SaveLocaImpl() {
        @Override
        public void success(OrdinalResultEntity entity) {

        }

        @Override
        public void fail(int code, String errorMessage) {

        }

        @Override
        public void loadIng(String title) {

        }

        @Override
        public void hide() {

        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        getLocation();
        return START_STICKY;
    }
    private String locationProvider  = LocationManager.GPS_PROVIDER;
    private void getLocation() {
        //获取地理位置管理器
        LocationManager mlocationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> prividerLists = mlocationmanager.getProviders(true);
        if (prividerLists.contains(LocationManager.GPS_PROVIDER)) {
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (prividerLists.contains(LocationManager.NETWORK_PROVIDER)) {
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String provider = mlocationmanager.getBestProvider(criteria,
                true);
        Location location = mlocationmanager.getLastKnownLocation(locationProvider);
        if (location != null) {
            updateWithNewLocation(location);
            //showLocation(location);
        } else {
           Toast.makeText(this , "无法获取当前位置",Toast.LENGTH_SHORT).show();

        }
//监视地理位置变化
        mlocationmanager.requestLocationUpdates(locationProvider, 120000, 1, locationListener);
    }

    /**
     * LocationListern监听器
     * 参数：地理位置提供器、监听位置变化的时间间隔、位置变化的距离间隔、LocationListener监听器
     */
    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(Location location) {
            //如果位置发生变化,重新显示
            updateWithNewLocation(location);
        }
    };
    private double lat;
    private double lng;
    private void updateWithNewLocation(Location location) {
        String coordinate;
        String addressStr = "no address \n";
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            //double lat = 39.25631486;
            //double lng = 115.63478961;
            coordinate = "Latitude：" + lat + "\nLongitude：" + lng;
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(lat,
                        lng, 1);
                StringBuilder sb = new StringBuilder();
                if (addresses.size() > 0) {
                    Address address = addresses.get(0);
                    for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                        sb.append(address.getAddressLine(i)).append(" ");
                    }
                /*sb.append(address.getCountryName());
                Log.i("location", "address.getCountryName()==" + address.getCountryName());//国家名*/
                    sb.append(address.getLocality()).append(" ");
                    Log.i("location", "address.getLocality()==" + address.getLocality());//城市名
                    sb.append(address.getSubLocality());
                    Log.i("location", "address.getSubLocality()=2=" + address.getSubLocality());//---区名
                    addressStr = sb.toString();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //如果用户没有允许app访问位置信息 则默认取上海松江经纬度的数据
            lat = 39.25631486;
            lng = 115.63478961;
            coordinate = "no coordinate!\n";
        }
        Log.i("location", "经纬度为===" + coordinate);
        Log.i("location", "地址为====" + addressStr);
        //提交经纬度
       //执行网络请求
        LocationBody body = new LocationBody();
        body.setMac("");
        body.setStudentNumber("");
        body.setDatetime(TimeEx.getStringTime14());
        body.setLatitude(location.getLatitude());
        body.setLongitude(location.getLongitude());
        svaeLocPresenter.submitFileInfo(body);

    }

}
