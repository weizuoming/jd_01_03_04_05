package com.dash.a1511n.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dash.a1511n.R;
import com.dash.a1511n.model.bean.GetAllAddrBean;
import com.dash.a1511n.presenter.GetAllAddrPresenter;
import com.dash.a1511n.util.ApiUtil;
import com.dash.a1511n.util.ChenJinUtil;
import com.dash.a1511n.util.CommonUtils;
import com.dash.a1511n.view.Iview.GetAllAddrInter;
import com.dash.a1511n.view.adapter.GetAllAddrAdapter;

public class ChooseAddrActivity extends AppCompatActivity implements View.OnClickListener,GetAllAddrInter {

    private ImageView imageView;
    private TextView text_manage;
    private ListView list_view_addr;
    private GetAllAddrPresenter getAllAddrPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_addr);

        imageView = findViewById(R.id.detail_image_back);
        text_manage = findViewById(R.id.text_manage);
        list_view_addr = findViewById(R.id.list_view_addr);

        //点击事件
        imageView.setOnClickListener(this);
        text_manage.setOnClickListener(this);

        getAllAddrPresenter = new GetAllAddrPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取地址列表的最新数据
        getAllAddrPresenter.getAllAddr(ApiUtil.GET_ALL_ADDR_URL, CommonUtils.getString("uid"));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.detail_image_back:

                finish();
                break;
            case R.id.text_manage://管理地址

                Intent intent = new Intent(ChooseAddrActivity.this,ManageAddrActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onGetAllAddrSuccess(final GetAllAddrBean getAllAddrBean) {
        if ("0".equals(getAllAddrBean.getCode())) {

            //设置适配器
            GetAllAddrAdapter getAllAddrAdapter = new GetAllAddrAdapter(ChooseAddrActivity.this, getAllAddrBean.getData());
            list_view_addr.setAdapter(getAllAddrAdapter);

            //设置条目的点击事件
            list_view_addr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Intent intent = new Intent();

                    intent.putExtra("addrBean",getAllAddrBean.getData().get(position));

                    setResult(2002,intent);
                    finish();

                }
            });

        }
    }
}
