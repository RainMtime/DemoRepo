package rainmtime.com.demorepo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.Proxy;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rainmtime.com.demorepo.movies.adapter.MainViewPagerAdapter;
import rainmtime.com.demorepo.movies.ui.MovieFragment;
import rainmtime.com.demorepo.test_code.TestActivity;
import rainmtime.com.demorepo.test_code.dagger2.ActivityComponent;
import rainmtime.com.demorepo.test_code.dagger2.ActivityModule;
import rainmtime.com.demorepo.test_code.dagger2.DaggerActivityComponent;
import rainmtime.com.demorepo.test_code.dagger2.UserModel;
import rainmtime.com.demorepo.test_code.okhttp.OkHttpActivity;
import rainmtime.com.demorepo.test_code.proxy_test.DynamicProxy;
import rainmtime.com.demorepo.test_code.proxy_test.Sell;
import rainmtime.com.demorepo.test_code.proxy_test.Vendor;
import rainmtime.com.demorepo.test_code.stickerbubbleanimtest.StickerBubbleActivity;
import rainmtime.com.demorepo.utils.ResUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    @BindView(R.id.fableft)
    FloatingActionButton mFabLeft;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @Inject
    UserModel mUserModel;

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        initView();
        initPagerAdapterAndTabs();


        //dagger2 代码片段
        dagger2Test();
        //动态代理测试代码
        dynamicProxyTest();
    }


    private void dagger2Test() {
        mActivityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule()).build();
        mActivityComponent.inject(this);
    }

    private void dynamicProxyTest() {
        DynamicProxy inter = new DynamicProxy(new Vendor());

        Sell sell = (Sell) Proxy.newProxyInstance(Sell.class.getClassLoader(), new Class[]{Sell.class}, inter);

        sell.ad();
        sell.sell();
    }


    private void initView() {

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

        mFabLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //测试嵌套滚动行为
//                Intent intent = new Intent(MainActivity.this, NestedActivity.class);
//                startActivity(intent);

                //测试跳转到拖动的Activity
//                Intent intent = new Intent(MainActivity.this, ViewDragHelperActivity.class);
//                startActivity(intent);

                //测试矢量图相关逻辑
//                Intent intent = new Intent(MainActivity.this, TestActivity.class);
//                startActivity(intent);

                //测试相关粘滞canvas 动画
//                Intent intent1 = new Intent(MainActivity.this, StickerBubbleActivity.class);
//                startActivity(intent1);

                //测试okhttp相关代码
                Intent intent = new Intent(MainActivity.this, OkHttpActivity.class);
                startActivity(intent);
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }


    private void initPagerAdapterAndTabs() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MovieFragment());

        ArrayList<String> fragmentTitles = new ArrayList<>();
        fragmentTitles.add(ResUtils.getString(R.string.tab_one_title));
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragments, fragmentTitles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
