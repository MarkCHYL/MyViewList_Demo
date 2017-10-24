package demo.mark.com.mycoordinatorlayout.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import demo.mark.com.mycoordinatorlayout.MarkActivity;
import demo.mark.com.mycoordinatorlayout.MyAdapter;
import demo.mark.com.mycoordinatorlayout.R;
import demo.mark.com.mycoordinatorlayout.fragment.FragmentTest;

/**
 * Created by mark on 2017/10/24.
 * $desc$ AppBarLayout嵌套TabLayout
 * Mail: 2285581945@qq.com
 */

public class CoordinatorLayoutFour extends AppCompatActivity {

    private RecyclerView rvToDoList;
    private List<String> datas = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

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
        adapter = new MyAdapter(datas,this);
        rvToDoList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvToDoList.setAdapter(adapter);
    }

    private void initViews() {
        rvToDoList = (RecyclerView) findViewById(R.id.rvToDoList);

        for (int i = 0; i < 20; i++) {
            datas.add("Mark先生");
        }
    }
}
