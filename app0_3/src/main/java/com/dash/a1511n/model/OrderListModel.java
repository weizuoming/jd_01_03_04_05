package com.dash.a1511n.model;

import com.dash.a1511n.model.bean.OrderListBean;
import com.dash.a1511n.presenter.OrderListPresenter;
import com.dash.a1511n.presenter.inter.OrderListPresenterInter;
import com.dash.a1511n.util.CommonUtils;
import com.dash.a1511n.util.OkHttp3Util_03;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Dash on 2018/2/25.
 */
public class OrderListModel {
    private OrderListPresenterInter orderListPresenterInter;

    public OrderListModel(OrderListPresenterInter orderListPresenterInter) {
        this.orderListPresenterInter = orderListPresenterInter;
    }

    public void getOrderData(String orderListUrl, String uid, int page) {

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("page", String.valueOf(page));

        OkHttp3Util_03.doPost(orderListUrl, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {

                    String json = response.body().string();

                    final OrderListBean orderListBean = new Gson().fromJson(json,OrderListBean.class);
                    CommonUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            orderListPresenterInter.onOrderDataSuccess(orderListBean);
                        }
                    });

                }
            }
        });

    }
}
