package com.example.wutai.wutaimoutain.Stroy;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.init.Chuanshuo;
import com.example.wutai.wutaimoutain.init.FoJiaowenhua;
import com.example.wutai.wutaimoutain.init.Query_wu;

import java.util.List;

public class Stroy_show_activity_1 extends AppCompatActivity {
    private TextView stroy_content;
    private android.support.v7.widget.Toolbar toolbar;
    private ImageView imageView;
    private Intent intent;
    private List<Chuanshuo> name;
    private List<FoJiaowenhua> name1;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stroy_content);
        stroy_content = (TextView)findViewById(R.id.stroy_content);
        imageView = (ImageView)findViewById(R.id.stroy_img);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.stroy_item_tolbar);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collaping_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_wuta);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        intent = getIntent();
        collapsingToolbarLayout.setTitle(intent.getStringExtra("name"));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.realred));
        if (intent.getStringExtra("type").equals("传说")) {
            name = Query_wu.query_chuanshuo1(intent.getStringExtra("name"));
            System.out.println(name.get(0).getChuanshuopic_id());
            Glide.with(Stroy_show_activity_1.this).load(name.get(0).getChuanshuopic_id()).into(imageView);
            stroy_content.setText(name.get(0).getContent());
        }
        else {
            name1 = Query_wu.query_foujiaowenhua(intent.getStringExtra("name"));
            Glide.with(Stroy_show_activity_1.this).load(name1.get(0).getId_foujiao()).into(imageView);
            stroy_content.setText(name1.get(0).getContent());
        };

    }
    public static void actionstart(Context context, String name,String type){
        Intent intent = new Intent(context, Stroy_show_activity_1.class);
        intent.putExtra("name",name);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }
}
