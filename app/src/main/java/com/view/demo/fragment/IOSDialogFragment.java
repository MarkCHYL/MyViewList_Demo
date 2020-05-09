package com.view.demo.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.view.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 仿IOS底部弹框
 * Created by Mark on 2017/10/17.
 */
public class IOSDialogFragment extends DialogFragment {

    private View rootView;
    private boolean isAnimation = false;//用来判断是否多次点击。防止多次执行
    private List<String> items = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        rootView = inflater.inflate(R.layout.fragment_ios_dialog,container,false);

        items.add("关闭");
        items.add("确定");
        //改变DecorView的背景色,设置它的背景色为透明即可
        setBackground();
        //设置布局的位置
        setPosition();
        //动画
        slideToUp(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化
        initViews(view);
    }

    private void initViews(View view) {
        TextView titleText = (TextView)view.findViewById(R.id.tv_title);
        titleText.setText("标题内容");

        ListView listView = (ListView) view.findViewById(R.id.lv_menu);
        listView.setAdapter(new ArrayAdapter(getActivity(),R.layout.menu_item,R.id.tv_menu_item,items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0)
                {
                    Toast.makeText(getActivity(),"关闭",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),"确定",Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button cancel = (Button)view.findViewById(R.id.btn_cancel);
        cancel.setText("取消");
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDismiss(getDialog());
            }
        });
    }

    private void setBackground() {
        View decorView = getDialog().getWindow().getDecorView();
//        decorView.setPadding(0,0,0,0);//消除间隙 源码中其实也给DecorView设置了padding值
        decorView.setBackgroundColor(getResources().getColor(R.color.translate));

    }

    private void setPosition() {
        Window window = getDialog().getWindow();
      /*  //也可以这样设置背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));*/
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
    }

    //向上弹出的动画
    public void slideToUp(View view){
        //ABSOLUTE是绝对坐标，RELATIVE_TO_SELF是相对于自身，
        // RELATIVE_TO_PARENT是相对于父View。
        Animation slide = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,0.0f,
                Animation.RELATIVE_TO_SELF,0.0f,
                Animation.RELATIVE_TO_SELF,1.0f, Animation.RELATIVE_TO_SELF,0.0f);

        slide.setDuration(1000);
        slide.setFillEnabled(true);
        slide.setFillAfter(true);
        view.startAnimation(slide);
    }

    //向下隐藏的动画
    public void slideToDown(View view){
        //ABSOLUTE是绝对坐标，RELATIVE_TO_SELF是相对于自身，
        // RELATIVE_TO_PARENT是相对于父View。
        Animation slide = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,0.0f,
                Animation.RELATIVE_TO_SELF,0.0f,
                Animation.RELATIVE_TO_SELF,0.0f, Animation.RELATIVE_TO_SELF,1.0f);

        slide.setDuration(1000);
        slide.setFillEnabled(true);
        slide.setFillAfter(true);
        view.startAnimation(slide);
        //监听动画
        //然后再更换IOSDialogFragment.this.dismiss() -> IOSDialogFragment.super.dismiss()
        slide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimation = false;
                IOSDialogFragment.super.dismiss();////弹框消失
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (isAnimation){
            return;
        }
        isAnimation = true;
        slideToDown(rootView);
    }
}
