package com.example.hello.rikao20180228;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private AMap aMap;
    private MapView mapView;
    private Button location;
    private Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        aMap = mapView.getMap();

        aMap.setTrafficEnabled(true);// 显示实时交通状况
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 卫星地图模式

        location =findViewById(R.id.location);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AMapLocationClient mlocationClient;

                AMapLocationClientOption mLocationOption = null;
                mlocationClient = new AMapLocationClient(MainActivity.this);

                mLocationOption = new AMapLocationClientOption();

                mlocationClient.setLocationListener((AMapLocationListener) MainActivity.this);
                mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
                mLocationOption.setInterval(2000);
                mlocationClient.setLocationOption(mLocationOption);
                mlocationClient.startLocation();
            }
        });

        search =  findViewById(R.id.search);
        final EditText editText = (EditText) findViewById(R.id.et_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyWord = editText.getText().toString().trim();
                int currentPage = 0;
                PoiSearch.Query query = new PoiSearch.Query(keyWord, "", "北京市");
                query.setPageSize(20);// 设置每页最多返回多少条poiitem
                query.setPageNum(currentPage);// 设置查第一页

                PoiSearch poiSearch = new PoiSearch(MainActivity.this, query);
                poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
                    @Override
                    public void onPoiSearched(PoiResult poiResult, int i) {

                    }

                    @Override
                    public void onPoiItemSearched(PoiItem poiItem, int i) {

                    }
                });

                poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(myLoaction.getLatitude(),
                        myLoaction.getLongitude()), 1000));//设置周边搜索的中心点以及半径
                poiSearch.searchPOIAsyn();// 异步搜索
            }
        });
    }

    AMapLocation myLoaction = null;


    public void onLocationChanged(AMapLocation amapLocation) {
        myLoaction=amapLocation;

        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                double latitude = amapLocation.getLatitude();//获取纬度
                double longitude = amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息

                String address = amapLocation.getAddress();
                String city = amapLocation.getCity();
                String country = amapLocation.getCountry();
                String province = amapLocation.getProvince();
                String street = amapLocation.getStreet();

                Toast.makeText(MainActivity.this, latitude + "==" + longitude + "--" + address + "==" + city + "==" + country + "--" + province + "--" + street, Toast.LENGTH_SHORT).show();

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
            } else {
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }
}
