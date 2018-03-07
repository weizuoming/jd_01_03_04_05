package com.dash.a1511n.view.Iview;

import com.dash.a1511n.model.bean.DefaultAddrBean;

/**
 * Created by Dash on 2018/2/26.
 */
public interface DefaultAddrInter {
    void onGetDefaultAddrSuccess(DefaultAddrBean defaultAddrBean);

    void onGetDefaultAddrEmpty();
}
