package com.example.wutai.wutaimoutain.myfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Stroy.Single_stroy_activity;
import com.example.wutai.wutaimoutain.Stroy.Stroy_show_activity_1;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BudCulFragmnet extends Fragment {
    @BindView(R.id.bud_cur_ll1)
    LinearLayout budCurLl1;
    @BindView(R.id.bud_cur_ll2)
    LinearLayout budCurLl2;
    @BindView(R.id.bud_cur_ll3)
    LinearLayout budCurLl3;
    @BindView(R.id.bud_cur_ll4)
    LinearLayout budCurLl4;
    @BindView(R.id.bud_cur_ll5)
    LinearLayout budCurLl5;
    Unbinder unbinder;

    public static BudCulFragmnet newInstance(String param1) {
        BudCulFragmnet fragment = new BudCulFragmnet();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;

    }

    public BudCulFragmnet() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mapfragment, container, false);
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bud_cur_ll1, R.id.bud_cur_ll2, R.id.bud_cur_ll3, R.id.bud_cur_ll4, R.id.bud_cur_ll5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bud_cur_ll1:
                Stroy_show_activity_1.actionstart(getActivity(),"佛教简介","");
                break;
            case R.id.bud_cur_ll2:
                Stroy_show_activity_1.actionstart(getActivity(),"信教行为过程","");
                break;
            case R.id.bud_cur_ll3:
                Stroy_show_activity_1.actionstart(getActivity(),"佛教术语","");
                break;
            case R.id.bud_cur_ll4:
                Stroy_show_activity_1.actionstart(getActivity(),"文殊菩萨","");
                break;
            case R.id.bud_cur_ll5:
                Stroy_show_activity_1.actionstart(getActivity(),"药师佛","");
                break;
        }
    }
}
