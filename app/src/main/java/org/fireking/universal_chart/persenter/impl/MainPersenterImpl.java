package org.fireking.universal_chart.persenter.impl;

import org.fireking.universal_chart.bean.CategoryBean;
import org.fireking.universal_chart.model.ICategoryModel;
import org.fireking.universal_chart.model.impl.CategoryModelImpl;
import org.fireking.universal_chart.persenter.IMainPersenter;
import org.fireking.universal_chart.view.ui.IMainView;

import java.util.List;

/**
 * Created by wangg on 2016/1/8.
 */
public class MainPersenterImpl implements IMainPersenter {

    public ICategoryModel mCategoryModel;
    public IMainView mMainView;

    public MainPersenterImpl(IMainView iMainView) {
        this.mCategoryModel = new CategoryModelImpl();
        this.mMainView = iMainView;
    }

    @Override
    public void getCategory() {
        mMainView.showLoading();
        //这里假定开启网络进行请求
        List<CategoryBean> beans = null;
        try {
            Thread.sleep(5000);
            beans = mCategoryModel.getCategory();
        } catch (InterruptedException e) {
            e.printStackTrace();
            mMainView.showEmpty();
            return;
        } finally {
            mMainView.hideLoading();
        }

        if (beans == null) {
            mMainView.showEmpty();
            return;
        }
        mMainView.setCategory(beans);
    }
}
