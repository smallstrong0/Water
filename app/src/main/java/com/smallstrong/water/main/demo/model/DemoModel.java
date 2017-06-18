package com.smallstrong.water.main.demo.model;

import com.smallstrong.water.base.BaseModel;
import com.smallstrong.water.base.BaseSubscriber;
import com.smallstrong.water.bean.DemoBean;
import com.smallstrong.water.main.demo.presenter.DemoPresenter;


/**
 * @author smallstrong
 *         created at 2017/6/18 下午9:03
 */

public class DemoModel extends BaseModel {
    DemoPresenter demoPresenter;

    public DemoModel(DemoPresenter demoPresenter) {
        this.demoPresenter = demoPresenter;
    }



    @Override
    public void getData() {//Todo 传参数问题
        waterApi.test("")
                .safeSubscribe(new BaseSubscriber<DemoBean>(demoPresenter) {
                    @Override
                    public void onNext(DemoBean demoBean) {
                        demoPresenter.onResponse(demoBean);
                    }
                });

    }
}
