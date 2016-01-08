package org.fireking.universal_chart.view.ui;

import org.fireking.universal_chart.bean.CategoryBean;

import java.util.List;

/**
 * Created by wangg on 2016/1/8.
 */
public interface IMainView extends IBaseView {

    public void setCategory(List<CategoryBean> beanList);
}
