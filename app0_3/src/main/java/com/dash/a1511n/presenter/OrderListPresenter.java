package com.dash.a1511n.presenter;

import com.dash.a1511n.model.OrderListModel;
import com.dash.a1511n.model.bean.OrderListBean;
import com.dash.a1511n.presenter.inter.OrderListPresenterInter;
import com.dash.a1511n.view.Iview.FragmentOrderListInter;
import com.dash.a1511n.view.fragment.FragmentAllOrder;

/**
 * Created by Dash on 2018/2/25.
 */
public class OrderListPresenter implements OrderListPresenterInter {

    private FragmentOrderListInter fragmentOrderListInter;
    private OrderListModel orderListModel;

    public OrderListPresenter(FragmentOrderListInter fragmentOrderListInter) {
        this.fragmentOrderListInter = fragmentOrderListInter;
        orderListModel = new OrderListModel(this);
    }

    public void getOrderData(String orderListUrl, String uid, int page) {

        orderListModel.getOrderData(orderListUrl,uid,page);

    }

    @Override
    public void onOrderDataSuccess(OrderListBean orderListBean) {

        fragmentOrderListInter.onOrderDataSuccess(orderListBean);
    }
}
