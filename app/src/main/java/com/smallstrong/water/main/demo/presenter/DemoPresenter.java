package com.smallstrong.water.main.demo.presenter;


import com.smallstrong.water.base.BasePresenter;
import com.smallstrong.water.main.demo.contract.DemoContract;
import com.smallstrong.water.main.demo.model.DemoModel;
import com.smallstrong.water.main.demo.weight.DemoActivity;

/**
 *
 *@author smallstrong
 *created at 2017/6/18 下午9:03
 */
 

public class DemoPresenter extends BasePresenter<DemoActivity> implements
        DemoContract.DemoPresenter {
    private DemoModel demoModel;

    public DemoPresenter() {
        demoModel = new DemoModel(this);
    }


    @Override
    public void getMeg() {
        demoModel.getData();

    }


    @Override
    public void onResponse(Object obj) {
        mView.shaowToast("haha");
    }
}
