package com.view.mark_festival_sms.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据的单列   提供伪数据
 * Created by mark on 2017/10/15.
 */

public class FestivalLab {

    public static FestivalLab mInstance;
    private List<FestivalBean>  mFestivals = new ArrayList<>();
    private FestivalLab(){
        mFestivals.add(new FestivalBean(1,"国庆节"));
        mFestivals.add(new FestivalBean(2,"中秋节"));
        mFestivals.add(new FestivalBean(3,"儿童节"));
        mFestivals.add(new FestivalBean(4,"春节"));
        mFestivals.add(new FestivalBean(5,"七夕节"));
        mFestivals.add(new FestivalBean(6,"端午节"));
        mFestivals.add(new FestivalBean(7,"元旦"));
    }
    private List<FestivalBean> getmFestivals(){
        return new ArrayList<FestivalBean>(mFestivals);
    }
    public FestivalBean getmFestivalById(int fesId)
    {
        for (FestivalBean bean:mFestivals) {
            if (bean.getId() == fesId){
                return bean;
            }
        }
        return null;
    }

    public static FestivalLab getmInstance() {
        if (mInstance == null){
            synchronized (FestivalLab.class){
                if (mInstance == null){
                    mInstance = new FestivalLab();
                }
            }
        }
        return mInstance;
    }
}
