package demo.mark.com.mycoordinatorlayout.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import demo.mark.com.mycoordinatorlayout.R;

/**
 * Created by mark on 2017/10/24.
 * $desc$ CoordinatorLayout与FloatingActionButton
 * FloatingActionButton是最简单的使用CoordinatorLayout的例子，
 * FloatingActionButton默认使用FloatingActionButton.Behavior。
 * Mail: 2285581945@qq.com
 */

public class CoordinatorLayoutOne extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"FAB",Snackbar.LENGTH_LONG)
                        .setAction("Cancel", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //这里单击事件代表消除Action后的响应事件

                            }
                        }).show();
            }
        });
    }
}
