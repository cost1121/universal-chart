package org.fireking.universal_chart;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.fireking.universal_chart.bean.CategoryBean;
import org.fireking.universal_chart.persenter.IMainPersenter;
import org.fireking.universal_chart.persenter.impl.MainPersenterImpl;
import org.fireking.universal_chart.view.adapter.MainPagerAdapter;
import org.fireking.universal_chart.view.ui.IMainView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView {

    private TabLayout tabs;
    private ViewPager pager;

    private MainPagerAdapter pagerAdapter;

    private IMainPersenter mainPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabs = (TabLayout) this.findViewById(R.id.tabs);
        pager = (ViewPager) this.findViewById(R.id.pager);

        mainPersenter = new MainPersenterImpl(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //模拟数据加载
        mainPersenter.getCategory();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setCategory(List<CategoryBean> beanList) {
        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), beanList);
        pager.setAdapter(pagerAdapter);
        tabs.setupWithViewPager(pager);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }
}
