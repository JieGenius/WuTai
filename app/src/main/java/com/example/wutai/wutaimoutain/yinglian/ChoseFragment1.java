package com.example.wutai.wutaimoutain.yinglian;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.VR.VR_showactivity;
import com.example.wutai.wutaimoutain.init.Dadian;
import com.example.wutai.wutaimoutain.init.Query_wu;
import com.example.wutai.wutaimoutain.init.SimiaoItem;

import java.util.ArrayList;
import java.util.List;

public class ChoseFragment1 extends Fragment {

    public static final int LEVEL_BAOHU = 0;
    public static final int LEVEL_SIMIAO =1;
    public static final int LEVEL_DADIAN = 2;
    private TextView title_text;
    private Button backbutton;
    private ListView listview;
    private ArrayAdapter<String> adapter;
    private List<String> datalist = new ArrayList<>();
    private List<String> baohus = new ArrayList<>();
    private List<SimiaoItem> simiaos = new ArrayList<>();
    private List<Dadian> dadains= new ArrayList<>();
    private SimiaoItem simiaoItem;
    private Dadian dadian;
    private int currentlevel;
    private String baohu;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choseare1,container,false);
        title_text = (TextView)view.findViewById(R.id.title_text1);
        backbutton = (Button)view.findViewById(R.id.back_button1);
        listview = (ListView)view.findViewById(R.id.listview1);
        adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,datalist);
        listview.setAdapter(adapter);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentlevel == LEVEL_BAOHU){
                    baohu = baohus.get(position);
                    System.out.println(baohu+"/////////////");
                    query_simiao();
                }else if (currentlevel == LEVEL_SIMIAO){

                    simiaoItem = simiaos.get(position);
                    String simiao = simiaoItem.getName();
                    System.out.println(getActivity()+"/*/**/*/*//*/*");
                    VR_showactivity.actionstart0(getActivity(),simiao);
                    getActivity().finish();


                }
            }
        });
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentlevel == LEVEL_DADIAN){
                    query_simiao();
                }else if (currentlevel == LEVEL_SIMIAO){
                    query_baohu();
                }
            }
        });
        query_baohu();
    }
    public void query_baohu(){
        title_text.setText("五台山");
        backbutton.setVisibility(View.GONE);
        initdata();
        if (baohus.size()>0){
            datalist.clear();
            for (String string : baohus){
                datalist.add(string);
            }
            adapter.notifyDataSetChanged();
            listview.setSelection(0);
            currentlevel = LEVEL_BAOHU;
            System.out.println(currentlevel+"//////0.0.0.0.0.0.");
        }else Toast.makeText(getActivity(),"没有数据",Toast.LENGTH_SHORT).show();

    }

    public void query_simiao(){
        title_text.setText(baohu);
        backbutton.setVisibility(View.VISIBLE);
        String level;
        if (baohu.equals("国家级保护寺庙")) level = "0";
        else level = "1";
        simiaos = Query_wu.query_simiao1(level);
        if (simiaos.size()>0){
            datalist.clear();
            for (SimiaoItem simia: simiaos){
                datalist.add(simia.getName());
            }
            adapter.notifyDataSetChanged();
            listview.setSelection(0);
            currentlevel = LEVEL_SIMIAO;

        }else Toast.makeText(getActivity(),"没有数据",Toast.LENGTH_SHORT).show();

    }


    public void query_dadian(){
        title_text.setText(simiaoItem.getName());
        backbutton.setVisibility(View.VISIBLE);
        dadains = Query_wu.query_dadian(simiaoItem.getName());
        if (dadains.size()>0){
            datalist.clear();
            for (Dadian dadian:dadains){
                datalist.add(dadian.getName());
            }
            adapter.notifyDataSetChanged();
            listview.setSelection(0);
            currentlevel = LEVEL_DADIAN;

        }else Toast.makeText(getActivity(),"没有数据",Toast.LENGTH_SHORT).show();

    }
    public void initdata(){
        baohus.add("国家级保护寺庙");
        baohus.add("山西省级保护寺庙");




    }
}
