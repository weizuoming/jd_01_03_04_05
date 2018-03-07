package com.dash.a1511n.presenter;

import com.dash.a1511n.model.GetDefaultAddrModel;
import com.dash.a1511n.model.bean.DefaultAddrBean;
import com.dash.a1511n.presenter.inter.GetDefaultAddrPresenterInter;
import com.dash.a1511n.view.Iview.DefaultAddrInter;
import com.dash.a1511n.view.activity.MakeSureOrderActivity;

/**
 * Created by Dash on 2018/2/26.
 */
public class GetDefaultAddrPresenter implements GetDefaultAddrPresenterInter {

    private DefaultAddrInter defaultAddrInter;
    private GetDefaultAddrModel getDefaultAddrModel;

    public GetDefaultAddrPresenter(DefaultAddrInter defaultAddrInter) {
        this.defaultAddrInter = defaultAddrInter;
        getDefaultAddrModel = new GetDefaultAddrModel(this);
    }

    public void getDefaultAddr(String getDefaultAddrUrl, String uid) {
        getDefaultAddrModel.getDefaultAddr(getDefaultAddrUrl,uid);
    }

    @Override
    public void onGetDefaultAddrSuccess(DefaultAddrBean defaultAddrBean) {
        defaultAddrInter.onGetDefaultAddrSuccess(defaultAddrBean);
    }

    @Override
    public void onGetDefaultAddrEmpty() {
        defaultAddrInter.onGetDefaultAddrEmpty();
    }
}
