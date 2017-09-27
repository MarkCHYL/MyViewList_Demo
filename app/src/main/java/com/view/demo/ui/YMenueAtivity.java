package com.view.demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.view.demo.R;
import com.yanzhikai.ymenuview.Circle8YMenu;
import com.yanzhikai.ymenuview.SquareYMenu;
import com.yanzhikai.ymenuview.TreeYMenu;
import com.yanzhikai.ymenuview.YMenu;
import com.yanzhikai.ymenuview.YMenuView;

/**
 * doYMunue
 * 地址：https://github.com/MarkCHYL/YMenuView
 * Created by Mark on 2017/9/27.
 */

public class YMenueAtivity extends AppCompatActivity implements YMenu.OnOptionsClickListener{

    private SquareYMenu ymv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ymenue);

        initView();
    }

    private void initView() {
        ymv = (SquareYMenu) findViewById(R.id.ymv);
        ymv.setOnOptionsClickListener(this);
        ymv.setBanArray(3,4,7,5,6);
        ymv.setOptionDrawableIds(R.drawable.zero,R.drawable.one,R.drawable.two,
                R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.six,
                R.drawable.seven,R.drawable.eight);
    }

    @Override
    public void onOptionsClick(int index) {
        switch (index){
            case 0:
                makeToast("0");
                break;
            case 1:
                makeToast("1");
                break;
            case 2:
                makeToast("2");
                break;
            case 3:
                makeToast("3");
                break;
            case 4:
                makeToast("4");
                break;
            case 5:
                makeToast("5");
                break;
            case 6:
                makeToast("6");
                break;
            case 7:
                makeToast("7");
                break;
            case 8:
                makeToast("8");
                break;

        }
    }
    private void makeToast(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
