package com.example.wutai.wutaimoutain.Simiao_juti;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.init.Dadian;
import com.example.wutai.wutaimoutain.init.Query_wu;
import com.example.wutai.wutaimoutain.init.SimiaoItem;

import java.util.ArrayList;
import java.util.List;

public class Simple_text_Activity extends AppCompatActivity {
    private SimiaoItem simiaoItem_temp = null;
    private RecyclerView recyclerView;
    private String name;
    private ArrayList<Jianjie> jianjies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_text_);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        List<SimiaoItem> simiaoItems = Query_wu.query_simiao(name);
        for (SimiaoItem simiaoItem : simiaoItems){
            if (simiaoItem.getName().equals(name)) {simiaoItem_temp = simiaoItem;
                break;
            }
        }
        initview();
        initlist();
        JianjieAdapter adapter = new JianjieAdapter(Simple_text_Activity.this,jianjies);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public static void actionstart(Context context, String name){
        Intent intent = new Intent(context,Simple_text_Activity.class);
        intent.putExtra("name",name);
        context.startActivity(intent);
    }
    public void initview(){
        recyclerView = (RecyclerView)findViewById(R.id.jianjie_show_simiao);
    }
    public void initlist(){
        jianjies.clear();
        jianjies.add(new Jianjie(simiaoItem_temp.getJianjie_imgid(),
                R.drawable.temple_xt_hall_iv_diagram,simiaoItem_temp.getName(),simiaoItem_temp.getJianjie(),true));
        List<Dadian> dadians = Query_wu.query_dadian(name);
        for (Dadian dadian:dadians){
            Jianjie jianjie = new Jianjie(dadian.getDadianjianjie_imgid(),
                    dadian.getFouxiang_imgid(),dadian.getName(),dadian.getJianjie_dadian(),false);
            jianjies.add(jianjie);
        }
    }
}
