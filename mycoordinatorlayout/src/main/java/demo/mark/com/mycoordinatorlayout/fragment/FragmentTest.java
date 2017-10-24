package demo.mark.com.mycoordinatorlayout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import demo.mark.com.mycoordinatorlayout.MyAdapter;
import demo.mark.com.mycoordinatorlayout.R;

/**
 * Created by mark on 2017/10/24.
 * $desc$
 * Mail: 2285581945@qq.com
 */

public class FragmentTest extends Fragment {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<String> datas = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test,container,false);

        initView(view);
        setContents();
        return view;
    }

    private void setContents() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

    private void initView(View view) {
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        for (int i = 0; i < 20; i++) {
            datas.add("Mark先生");
        }
        adapter = new MyAdapter(datas,getActivity());
    }
}
