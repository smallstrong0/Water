package com.smallstrong.water.main.demo.weight;

import com.smallstrong.water.R;
import com.smallstrong.water.base.BaseActivity;
import com.smallstrong.water.main.demo.contract.DemoContract;
import com.smallstrong.water.main.demo.presenter.DemoPresenter;

public class DemoActivity extends BaseActivity<DemoPresenter> implements DemoContract.DemoView {


    @Override
    protected DemoPresenter loadPresenter() {
        return new DemoPresenter();
    }

    @Override
    public void reLoad() {

    }

    @Override
    protected void initData() {
        mPresenter.getMeg();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setStatusLayout() {

    }

    @Override
    public void shaowToast(String str) {

    }


}
