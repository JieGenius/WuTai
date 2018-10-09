package com.example.wutai.wutaimoutain.yinglian;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.init.Query_wu;
import com.example.wutai.wutaimoutain.init.Shiyi;
import com.example.wutai.wutaimoutain.init.Yinglian;

import java.util.ArrayList;
import java.util.List;

public class YinglianShowActivity extends AppCompatActivity {
    private ArrayList<Yinglian> yinglians1;
    private Intent intent1,intent;
    private ViewPager viewPager;
    private Button detailIntro;
    DrawerLayout drawerLayout;
    private android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yinglian_show);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        viewPager = findViewById(R.id.couplet_view_page);
        detailIntro = findViewById(R.id.couplet_bt_detail_intro);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.yinglian_toolbar_kn);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        intent1 = getIntent();
        if(intent1.getStringExtra("name")!=null){
            getSupportActionBar().setTitle(intent1.getStringExtra("name")+"·楹联");
        }
        else{
            getSupportActionBar().setTitle("楹联");
        }
        if (intent1.getStringExtra("dadian")!= null){
            yinglians1 = (ArrayList<Yinglian>) Query_wu.query_yinglian1(intent1.getStringExtra("name"),intent1.getStringExtra("dadian"));
        }else if (intent1.getStringExtra("dadian")==null&&intent1.getStringExtra("name")!= null){
            yinglians1 = (ArrayList<Yinglian>) Query_wu.query_yinglian(intent1.getStringExtra("name"));
        }else yinglians1 = (ArrayList<Yinglian>) Query_wu.query_yinglian0();
        Toast.makeText(this,"楹联数量："+yinglians1.size(),Toast.LENGTH_SHORT).show();
        viewPager.setAdapter(new CoupletViewPage());

        intent = new Intent(YinglianShowActivity.this,YinglianDetal.class);
        detailIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(yinglians1.size() == 0){
                    Toast.makeText(YinglianShowActivity.this,"未收集到当前大殿的数据",Toast.LENGTH_SHORT).show();
                    return;
                }
                int pos = viewPager.getCurrentItem();
                intent.putExtra("writer",yinglians1.get(pos).getWriter());
                intent.putExtra("dadian",yinglians1.get(pos).getDadian());
                intent.putExtra("size",yinglians1.get(pos).getSize());
                intent.putExtra("simiao",yinglians1.get(pos).getSimiao());
                intent.putExtra("font",yinglians1.get(pos).getFont());
                intent.putExtra("type",yinglians1.get(pos).getType());
                intent.putExtra("zhiyi",yinglians1.get(pos).getZhiyi());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public static void actionstart0(Context context){
        Intent intent = new Intent(context, YinglianShowActivity.class);
        context.startActivity(intent);
    }
    public static void actionstart(Context context, String name,String dadian){
        Intent intent = new Intent(context, YinglianShowActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("dadian",dadian);
        context.startActivity(intent);

    }
    public static void actionstart1(Context context, String name){
        Intent intent = new Intent(context, YinglianShowActivity.class);
        intent.putExtra("name",name);
        context.startActivity(intent);
    }

    class CoupletViewPage extends PagerAdapter{
        LayoutInflater inflater;
        List<View> list;
        public CoupletViewPage() {
            inflater = getLayoutInflater();
            list = new ArrayList<>();
            for(int i = 0;i<yinglians1.size();i++){
                View view = inflater.inflate(R.layout.couplet_view_page_item,null);
                ImageView iv = view.findViewById(R.id.couplet_view_page_iv_item);
                iv.setImageResource(yinglians1.get(i).getImgid());
                list.add(view);
            }
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(list.get(position));
        }

    }

}
