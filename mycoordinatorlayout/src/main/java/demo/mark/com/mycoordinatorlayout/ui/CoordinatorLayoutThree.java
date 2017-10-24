package demo.mark.com.mycoordinatorlayout.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import demo.mark.com.mycoordinatorlayout.R;
import demo.mark.com.mycoordinatorlayout.fragment.FragmentTest;

/**
 * Created by mark on 2017/10/24.
 * $desc$ AppBarLayout嵌套TabLayout
 * Mail: 2285581945@qq.com
 */

public class CoordinatorLayoutThree extends AppCompatActivity {
    private TabLayout tabs;
    private ViewPager viewpager;
    private String[] mTile = new String[]{"tab1", "tab2", "tab3"};
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        initViews();
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "FAB", Snackbar.LENGTH_LONG)
                        .setAction("Cancel", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //这里单击事件代表消除Action后的响应事件

                            }
                        }).show();
            }
        });
        setcontents();
    }

    private void setcontents() {
        // App Logo
        toolbar.setLogo(R.mipmap.ic_launcher);
        // Title
        toolbar.setTitle("Mark");
        // Sub Title
        toolbar.setSubtitle("Sub title");

        setSupportActionBar(toolbar);
        // Navigation Icon 要設定在 setSupoortActionBar 才有作用
        // 否則會出現 back button
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);

        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new FragmentTest();
            }

            @Override
            public int getCount() {
                return mTile.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTile[position];
            }
        });
        tabs.setupWithViewPager(viewpager);
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabs = (TabLayout) findViewById(R.id.tabs);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
    }
}
