package com.smallstrong.water.main.demo.model;

import com.smallstrong.water.base.BaseModel;
import com.smallstrong.water.bean.DemoBean;
import com.smallstrong.water.main.demo.presenter.DemoPresenter;

import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;


/**
 *
 *@author smallstrong
 *created at 2017/6/18 下午4:54
 */
 
public class DemoModel extends BaseModel {
    DemoPresenter demoPresenter;
    public DemoModel(DemoPresenter demoPresenter){
        this.demoPresenter = demoPresenter;
    }


    public void loder(){

        waterApi.test("")
                .subscribe(new FlowableSubscriber<DemoBean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Subscription s) {

                    }

                    @Override
                    public void onNext(DemoBean demoBean) {
                        demoPresenter.onResponse(demoBean );
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });




    }



}
