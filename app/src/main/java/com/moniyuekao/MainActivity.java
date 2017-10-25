package com.moniyuekao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.moniyuekao.API.API;
import com.moniyuekao.Presenter.presenter;
import com.moniyuekao.View.PresenterView;

import java.util.List;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity implements PresenterView<Commoninfo> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        presenter  pre=new presenter(this);
        pre.getData(API.GETCARTS,"91");
    }

    private void initView() {

    }

    @Override
    public void onFailure(Call call, Exception e) {

    }

    @Override
    public void onResponse(Commoninfo commoninfo) {
        List<Commoninfo.DataBean> data = commoninfo.getData();
        Log.e("Tag",data.toString());
        Toast.makeText(this,commoninfo.toString(),Toast.LENGTH_LONG).show();

    }


}
