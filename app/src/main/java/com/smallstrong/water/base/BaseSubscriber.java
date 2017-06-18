package com.smallstrong.water.base;

import com.smallstrong.water.app.AppContext;
import com.smallstrong.water.utils.NetworkUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by smallstrong on 2017/6/18.
 */

public abstract class BaseSubscriber<T> implements Subscriber<T> {

    BasePresenter basePresenter;

    public BaseSubscriber(BasePresenter basePresenter) {
        this.basePresenter = basePresenter;
    }

    @Override
    public void onSubscribe(Subscription s) {

        if (!NetworkUtil.isNetworkAvailable(AppContext.getAppContext())) {
            basePresenter.showErrorView("网络不可用");
            s.cancel();//直接取消发送后续不处理
        }else {
            basePresenter.showLoadingView();
        }

    }



    @Override
    public void onError(Throwable t) {
        basePresenter.showErrorView(t.getMessage());//Todo 服务器或者解析错误
    }

    @Override
    public void onComplete() {


    }


}
