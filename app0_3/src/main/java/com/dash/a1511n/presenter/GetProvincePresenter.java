package com.dash.a1511n.presenter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.dash.a1511n.model.GetProvinceModel;
import com.dash.a1511n.model.bean.ProvinceBean;
import com.dash.a1511n.presenter.inter.GetProvincePresenterInter;
import com.dash.a1511n.view.Iview.GetProvinceInter;
import com.dash.a1511n.view.fragment.FragmentProvince;

import java.util.List;

/**
 * Created by Dash on 2018/2/27.
 */
public class GetProvincePresenter implements GetProvincePresenterInter {

    private GetProvinceInter getProvinceInter;
    private GetProvinceModel getProvinceModel;

    public GetProvincePresenter(GetProvinceInter getProvinceInter) {
        this.getProvinceInter = getProvinceInter;
        getProvinceModel = new GetProvinceModel(this);
    }

    public void getProvince(Context context) {
        getProvinceModel.getProvince(context);
    }

    @Override
    public void onGetProvince(List<ProvinceBean> list) {
        getProvinceInter.onGetProvince(list);
    }
}
