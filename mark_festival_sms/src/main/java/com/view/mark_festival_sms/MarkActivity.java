package com.view.mark_festival_sms;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.view.mark_festival_sms.fragment.FestivalCategoryFrament;

public class MarkActivity extends AppCompatActivity {

    private TabLayout tb_mark;
    private ViewPager vp_mark;

    private String[] mTile = new String[]{"节日短信","发送记录"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);

        initView();
        setcontents();
    }

    private void setcontents() {
        vp_mark.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new FestivalCategoryFrament();
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
        tb_mark.setupWithViewPager(vp_mark);
    }

    private void initView() {
        vp_mark = (ViewPager) findViewById(R.id.vp_mark);
        tb_mark = (TabLayout) findViewById(R.id.tb_mark);
    }
}
