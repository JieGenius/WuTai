package com.example.wutai.wutaimoutain.navigation;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.wutai.wutaimoutain.BaiDuMap.GetPositionActivity;
import com.example.wutai.wutaimoutain.MainActivity;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Utils.FileUtils;

import java.io.InputStream;

public class NavigationHomeActivity extends AppCompatActivity implements View.OnClickListener,PopupMenu.OnMenuItemClickListener{
    private com.getbase.floatingactionbutton.FloatingActionButton nationalProtect,provinceProtect,backHome,getBaiduMap;
    private HotClickView mHotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mHotView = new HotClickView(this);
        setContentView(R.layout.activity_navigation_home);
        mHotView = (HotClickView)findViewById(R.id.hot_view);
        initView();
//        setContentView(mHotView, new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        initDatas();
    }


    protected void initDatas() {
        AssetManager assetManager = getResources().getAssets();
        InputStream imgInputStream = null;
        InputStream fileInputStream = null;
        try{
            imgInputStream = assetManager.open("home_map_1.jpg");
            fileInputStream = assetManager.open("home_map.xml");
            mHotView.setImageBitmap(fileInputStream,imgInputStream, HotClickView.FIT_XY);
            mHotView.setHandler(mHandler, CLICK_TYPE);
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            FileUtils.closeInputStream(imgInputStream);
            FileUtils.closeInputStream(fileInputStream);
        }
    }

    public void initView(){
        nationalProtect = (com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.national_treasure);
        nationalProtect.setOnClickListener(this);
        provinceProtect = (com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.province_treasure);
        provinceProtect.setOnClickListener(this);
        provinceProtect = (com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.province_treasure);
        backHome = (com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.back_home);
        backHome.setOnClickListener(this);
        getBaiduMap = (com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.get_baidu_map);
        getBaiduMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.national_treasure:
                //创建弹出式菜单对象（最低版本11）
                PopupMenu popup = new PopupMenu(this, v);//第二个参数是绑定的那个view
                //获取菜单填充器
                MenuInflater inflater = popup.getMenuInflater();
                //填充菜单
                inflater.inflate(R.menu.national_protect_menu, popup.getMenu());
                //绑定菜单项的点击事件
                popup.setOnMenuItemClickListener(NavigationHomeActivity.this);
                //显示(这一行代码不要忘记了)
                popup.show();
                break;
            case R.id.province_treasure:
                //创建弹出式菜单对象（最低版本11）
                PopupMenu popup2 = new PopupMenu(this, v);//第二个参数是绑定的那个view
                //获取菜单填充器
                MenuInflater inflater1 = popup2.getMenuInflater();
                //填充菜单
                inflater1.inflate(R.menu.province_protect_menu, popup2.getMenu());
                //绑定菜单项的点击事件
                popup2.setOnMenuItemClickListener(NavigationHomeActivity.this);
                //显示(这一行代码不要忘记了)
                popup2.show();
                break;
            case R.id.back_home:
                startActivity(new Intent(NavigationHomeActivity.this, MainActivity.class));
                break;
            case R.id.get_baidu_map:
                startActivity(new Intent(NavigationHomeActivity.this, GetPositionActivity.class));
                break;

            default:
                break;
        }
    }



    //弹出式菜单的单击事件处理
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.xiantongsi:
                startActivity(new Intent(NavigationHomeActivity.this,XianTongSiActivity.class));
                break;
            case R.id.tayuansi:
                startActivity(new Intent(NavigationHomeActivity.this,TaYuanSiActivity.class));
                break;
            case R.id.pu_sa_ding:
                startActivity(new Intent(NavigationHomeActivity.this,PuSaDingActivity.class));
                break;
            case R.id.luo_hou_si:
                startActivity(new Intent(NavigationHomeActivity.this,LuoHouSiActivity.class));
                break;
            case R.id.bi_shan_si:
                startActivity(new Intent(NavigationHomeActivity.this,BiShanSiActivity.class));
                break;
            case R.id.shu_xiang_si:
                startActivity(new Intent(NavigationHomeActivity.this,ShuXiangSiActivity.class));
                break;
            case R.id.nan_shan_si:
                startActivity(new Intent(NavigationHomeActivity.this,NanShanSiActivity.class));
                break;
            case R.id.jin_ge_si:
                startActivity(new Intent(NavigationHomeActivity.this,JinGeSiActivity.class));
                break;
            case R.id.long_quan_si:
                startActivity(new Intent(NavigationHomeActivity.this,LongQuanSiActivity.class));
                break;
            case R.id.yuan_zhao_si:
                startActivity(new Intent(NavigationHomeActivity.this,YuanZhaoSiActivity.class));
                break;
            default:
                break;
        }
        return false;
    }

    private final static int CLICK_TYPE = 1;
    protected Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if(!NavigationHomeActivity.this.isFinishing()) {
                if(msg.what == CLICK_TYPE) {
//					HotArea hotArea = (HotArea) msg.obj;
//					Intent intent = new Intent();
//					intent.setClass(SendTalkActivity.this, DetailActivity.class);
//					intent.putExtra("HotArea", hotArea);
//					startActivity(intent);
                }
            }
        };
    };

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.navigation_home,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        super.onOptionsItemSelected(item);
//        switch (item.getItemId()){
//            case R.id.menu_bt:
//                Toast.makeText(NavigationHomeActivity.this,"点击了菜单",Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return true;
//    }
}
