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
    private List<Msg> msgs = new ArrayList<>();
    private FestivalLab(){
        mFestivals.add(new FestivalBean(1,"国庆节"));
        mFestivals.add(new FestivalBean(2,"中秋节"));
        mFestivals.add(new FestivalBean(3,"儿童节"));
        mFestivals.add(new FestivalBean(4,"春节"));
        mFestivals.add(new FestivalBean(5,"七夕节"));
        mFestivals.add(new FestivalBean(6,"端午节"));
        mFestivals.add(new FestivalBean(7,"元旦"));

        msgs.add(new Msg(1,1,"If living on the earth is a mission from the lord… living with youis the award of the lord."));
        msgs.add(new Msg(2,1,"Within you I lose myself, without you I find myself wanting to be lost again."));
        msgs.add(new Msg(3,3,"Love is a chord in life, not a solo."));
        msgs.add(new Msg(4,4,"Wish you a happy new year and a good fortune in the coming year when we will share our happiness, think of our good friends, andour dreams come true!"));
        msgs.add(new Msg(5,5,"Every day I miss you. It is a hard time for me to miss you but itis even harder not to do so. In such a contrary mood, I miss you deeply! A happy Valentines Day to you!"));
        msgs.add(new Msg(6,1,"Those days when we were together appear in my mind time after time,because they were so joyful, happy, blest, disappointing, sad and painful. I miss you ,and miss you so mach."));
        msgs.add(new Msg(7,1,"Never stop smiling, not even when you're sad, some man fall in love with your smile."));
        msgs.add(new Msg(1,2,"If living on the earth is a mission from the lord… living with youis the award of the lord."));
        msgs.add(new Msg(2,2,"Within you I lose myself, without you I find myself wanting to be lost again."));
        msgs.add(new Msg(3,7,"Love is a chord in life, not a solo."));
        msgs.add(new Msg(4,3,"Wish you a happy new year and a good fortune in the coming year when we will share our happiness, think of our good friends, andour dreams come true!"));
        msgs.add(new Msg(5,6,"Every day I miss you. It is a hard time for me to miss you but itis even harder not to do so. In such a contrary mood, I miss you deeply! A happy Valentines Day to you!"));
        msgs.add(new Msg(6,6,"Those days when we were together appear in my mind time after time,because they were so joyful, happy, blest, disappointing, sad and painful. I miss you ,and miss you so mach."));
        msgs.add(new Msg(7,7,"Never stop smiling, not even when you're sad, some man fall in love with your smile."));

    }
    public List<FestivalBean> getmFestivals(){
        return new ArrayList<FestivalBean>(mFestivals);
    }
    public List<Msg> getMsgs(){
        return msgs;
    }
    public List<Msg> getMsgByFestivalId(int fesId)
    {
        List<Msg> msgs1 = new ArrayList<>();
        for (Msg msg:msgs)
        {
            if (msg.getFestivalId() == fesId){
                msgs1.add(msg);
            }
        }
        return msgs1;
    }
    public Msg getMsgByMsgId(int id){
        for (Msg msg:msgs) {
            if (msg.getId() == id){
                return msg;
            }
        }
        return null;
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
