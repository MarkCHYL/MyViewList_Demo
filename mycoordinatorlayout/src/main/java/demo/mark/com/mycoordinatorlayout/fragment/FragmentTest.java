package demo.mark.com.mycoordinatorlayout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.mark.com.mycoordinatorlayout.R;

/**
 * Created by mark on 2017/10/24.
 * $desc$
 * Mail: 2285581945@qq.com
 */

public class FragmentTest extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test,container,false);
    }
}
