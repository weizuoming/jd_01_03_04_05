package com.dash.a1511n.presenter;

import com.dash.a1511n.model.AddNewAddrModel;
import com.dash.a1511n.model.bean.AddNewAddrBean;
import com.dash.a1511n.presenter.inter.AddNewAddrPresenterInter;
import com.dash.a1511n.view.Iview.AddNewAddrInter;
import com.dash.a1511n.view.activity.AddNewAddrActivity;

/**
 * Created by Dash on 2018/2/26.
 */
public class AddNewAddrPresenter implements AddNewAddrPresenterInter {

    private AddNewAddrInter addNewAddrInter;
    private AddNewAddrModel addNewAddrModel;

    public AddNewAddrPresenter(AddNewAddrInter addNewAddrInter) {
        this.addNewAddrInter = addNewAddrInter;
        addNewAddrModel = new AddNewAddrModel(this);
    }

    public void addNewAddr(String addNewAddrUrl, String uid, String addr, String phone, String name) {

        addNewAddrModel.addNewAddr(addNewAddrUrl,uid,addr,phone,name);
    }

    @Override
    public void onAddAddrSuccess(AddNewAddrBean addNewAddrBean) {
        addNewAddrInter.onAddNewAddrSuccess(addNewAddrBean);
    }
}
