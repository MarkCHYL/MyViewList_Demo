package demo.mark.com.mycoordinatorlayout.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import demo.mark.com.mycoordinatorlayout.MyAdapter;
import demo.mark.com.mycoordinatorlayout.R;
import demo.mark.com.mycoordinatorlayout.base.BaseFragmentAdapter;
import demo.mark.com.mycoordinatorlayout.fragment.ListFragment;

import static demo.mark.com.mycoordinatorlayout.R.id.rvToDoList;

/**
 * Created by mark on 2017/10/24.
 * $desc$ AppBarLayout嵌套TabLayout
 * Mail: 2285581945@qq.com
 */

public class CoordinatorLayoutFive extends AppCompatActivity {

    private ViewPager viewPager;
    private Toolbar mToolbar;
    private TabLayout tabLayout;
    private List<Fragment> mFragments;
    private String[]  mTitles=new String[]{
            "主页","微博","相册"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);

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

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setcontents() {
        mToolbar.setTitle("Mark");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            ListFragment listFragment = ListFragment.newInstance(mTitles[i]);
            mFragments.add(listFragment);
        }
        BaseFragmentAdapter adapter =
                new BaseFragmentAdapter(getSupportFragmentManager(),mFragments,mTitles);



        viewPager.setAdapter(adapter);
    }
}
