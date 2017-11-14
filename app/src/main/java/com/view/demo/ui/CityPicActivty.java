package com.view.demo.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.view.demo.R;
import com.view.demo.bean.CityModel;
import com.view.demo.bean.DistrictModel;
import com.view.demo.bean.ProvinceModel;
import com.view.demo.view.PickLocateDialog;

/**
 * Created by Mark on 2017/11/13.
 */

public class CityPicActivty extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citypic);
    }

    public void clickPicCity(View view){
        PickLocateDialog dialog = new PickLocateDialog(this, new PickLocateDialog.AddressPickedListener() {
            @Override
            public void onAddressPicked(ProvinceModel province, CityModel city, DistrictModel district) {
                Toast.makeText(CityPicActivty.this,"选择的地址是：" + province + "--" + city + "--" + district,Toast.LENGTH_SHORT).show();
            }
        });
        dialog.locatePickDialog();
    }
}
