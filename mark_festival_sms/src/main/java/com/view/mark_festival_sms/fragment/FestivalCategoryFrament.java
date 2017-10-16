package com.view.mark_festival_sms.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.view.mark_festival_sms.bean.FestivalBean;

/**
 *
 * Created by Mark on 2017/10/13.
 */

public class FestivalCategoryFrament extends Fragment {

    private GridView gridView;
    private ArrayAdapter<FestivalBean> arrayAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new FrameLayout(getActivity());
    }
}
