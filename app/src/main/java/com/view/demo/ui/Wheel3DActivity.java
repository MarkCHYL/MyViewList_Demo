package com.view.demo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.view.demo.R;
import com.view.demo.model.TestDatas;
import com.view.demo.widget.WheelView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 3D旋转效果
 * Created by Mark on 2017/9/29.
 */

public class Wheel3DActivity extends AppCompatActivity {
    private WheelView wv_city, wv_county, wv_name;

    private CityAdapter cityAdapter;
    private CountyAdapter countyAdapter;

    private TextView tv_city, tv_county, tv_number;
    private List<String> strs;

    private WheelView wv_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_3dwheel);
        setTitle("Mark先生");

        wv_city = (WheelView) findViewById(R.id.wv_city);
        wv_county = (WheelView) findViewById(R.id.wv_county);
        wv_number = (WheelView) findViewById(R.id.wv_number);
        wv_name = (WheelView) findViewById(R.id.wv_name);
        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_county = (TextView) findViewById(R.id.tv_county);
        tv_number = (TextView) findViewById(R.id.tv_number);

        /* 市滑轮控件 */

        cityAdapter = new CityAdapter();
        wv_city.setAdapter(cityAdapter);
        wv_city.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                tv_city.setText("市: "+cityAdapter.getItem(index));
                strs = Arrays.asList(TestDatas.AREAS[index]);
                countyAdapter.strs.clear();
                countyAdapter.strs.addAll(strs);
                countyAdapter.notifyDataSetChanged();
                wv_county.setCurrentItem(0);
                tv_county.setText("县: "+countyAdapter.getItem(0));
            }
        });



        /* 区滑轮控件 */
        countyAdapter = new CountyAdapter();
        strs = Arrays.asList(TestDatas.AREAS[0]);
        countyAdapter.strs.clear();
        countyAdapter.strs.addAll(strs);
        countyAdapter.notifyDataSetChanged();
        wv_county.setAdapter(new WheelView.WheelAdapter() {
            @Override
            public int getItemCount() {
                return strs.size();
            }

            @Override
            public String getItem(int index) {
                return strs.get(index);
            }
        });
        wv_county.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                tv_county.setText("县: "+countyAdapter.getItem(index));
            }
        });


        /*  名字适配  */
        wv_name.setAdapter(new WheelView.WheelAdapter() {
            @Override
            public int getItemCount() {
                return 20;
            }

            @Override
            public String getItem(int index) {
                return "Mark 大叔";
            }
        });

        /* 水平滑轮控件 */
        wv_number.setAdapter(new WheelView.WheelAdapter() {
            @Override
            public int getItemCount() {
                return 100;
            }

            @Override
            public String getItem(int index) {
                return String.valueOf(index);
            }
        });
        wv_number.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                tv_number.setText("水平布局"+index);
            }
        });
        wv_number.setCurrentItem(88);
    }

    private class CityAdapter extends WheelView.WheelAdapter {
        @Override
        public int getItemCount() {
            return TestDatas.NAMES.length;
        }

        @Override
        public String getItem(int index) {
            return TestDatas.NAMES[index];
        }
    }

    private class CountyAdapter extends WheelView.WheelAdapter {
        private List<String> strs;

        CountyAdapter() {
            strs = new ArrayList<>();
        }

        @Override
        public int getItemCount() {
            return strs.size();
        }

        @Override
        public String getItem(int index) {
            return strs.get(index);
        }
    }


}
