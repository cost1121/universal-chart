package org.fireking.universal_chart.model.impl;

import org.fireking.universal_chart.bean.CategoryBean;
import org.fireking.universal_chart.model.ICategoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangg on 2016/1/8.
 */
public class CategoryModelImpl implements ICategoryModel {

    @Override
    public List<CategoryBean> getCategory() {
        List<CategoryBean> bean = new ArrayList<>();
        bean.add(new CategoryBean("1", "circle", "circle", "环形图标"));
        return bean;
    }
}
