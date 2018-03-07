package com.dash.a1511n.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dash.a1511n.R;
import com.dash.a1511n.util.ChenJinUtil;

/**
 * Created by Dash on 2018/1/23.
 */
public class FragmentFaXian extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TextView textView = new TextView(getActivity());
        textView.setText("发现");

        return textView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initChenJin();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (! hidden) {
            initChenJin();
        }
    }

    private void initChenJin() {
        ChenJinUtil.setStatusBarColor(getActivity(), getResources().getColor(R.color.colorPrimaryDark));
    }
}
