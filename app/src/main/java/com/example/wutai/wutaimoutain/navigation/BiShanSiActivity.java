package com.example.wutai.wutaimoutain.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import com.example.wutai.wutaimoutain.BiShanSiVoice.CangJingGeActivity;
import com.example.wutai.wutaimoutain.BiShanSiVoice.JieTanDianActivity;
import com.example.wutai.wutaimoutain.BiShanSiVoice.LeiYinSiActivity;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.DaBaiTaActivity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.DaCangJingGeActivity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.DaCiYanShouBaoDianActivity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.TianWangDianActivity;
import com.example.wutai.wutaimoutain.jianjie_new.Jianjie_dadian_actiivty;
import com.example.wutai.wutaimoutain.yinglian.YinglianShowActivity;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

public class BiShanSiActivity extends AppCompatActivity implements View.OnClickListener,PopupMenu.OnMenuItemClickListener{
    private Button CJG,JTD,LYS,TWD; // 藏经阁、戒坛殿、雷音寺、天王殿
    private String content;
    private Context mContex;
    private String name = "碧山寺";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bi_shan_si);
        initView();
        mContex = getApplicationContext();
    }

    public void initView(){
        //初始化声音设置
        SpeechUtility.createUtility(BiShanSiActivity.this, SpeechConstant.APPID +"=5b63c383");  //=后面这里要替换成自己申请的 AppID
        CJG = (Button)findViewById(R.id.bi_shan_si_cang_jing_ge);
        CJG.setOnClickListener(this);
        JTD = (Button)findViewById(R.id.bi_shan_si_jie_tan_dian);
        JTD.setOnClickListener(this);
        LYS = (Button)findViewById(R.id.bi_shan_si_lei_yin_si);
        LYS.setOnClickListener(this);
        TWD = (Button)findViewById(R.id.bi_shan_si_tian_wang_dian);
        TWD.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bi_shan_si_cang_jing_ge:
                //创建弹出式菜单对象（最低版本11）
                PopupMenu popup = new PopupMenu(this, v);//第二个参数是绑定的那个view
                //获取菜单填充器
                MenuInflater inflater = popup.getMenuInflater();
                //填充菜单
                inflater.inflate(R.menu.bi_shan_si_cang_jing_ge_menu, popup.getMenu());
                //绑定菜单项的点击事件
                popup.setOnMenuItemClickListener(BiShanSiActivity.this);
                //显示(这一行代码不要忘记了)
                popup.show();
                break;
            case R.id.bi_shan_si_jie_tan_dian:
                PopupMenu popup2 = new PopupMenu(this,v);
                MenuInflater inflater2 = popup2.getMenuInflater();
                inflater2.inflate(R.menu.bi_shan_si_jie_tan_dian_menu,popup2.getMenu());
                popup2.setOnMenuItemClickListener(BiShanSiActivity.this);
                popup2.show();
                break;
            case R.id.bi_shan_si_lei_yin_si:
                PopupMenu popup3 = new PopupMenu(this,v);
                MenuInflater inflater3 = popup3.getMenuInflater();
                inflater3.inflate(R.menu.bi_shan_si_lei_yin_si_menu,popup3.getMenu());
                popup3.setOnMenuItemClickListener(BiShanSiActivity.this);
                popup3.show();
                break;
            case R.id.bi_shan_si_tian_wang_dian:
                PopupMenu popup4 = new PopupMenu(this,v);
                MenuInflater inflater4 = popup4.getMenuInflater();
                inflater4.inflate(R.menu.bi_shan_si_tian_wang_dian_menu,popup4.getMenu());
                popup4.setOnMenuItemClickListener(BiShanSiActivity.this);
                popup4.show();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.CJG_yinglian_detail:
                YinglianShowActivity.actionstart(BiShanSiActivity.this,name,"藏经阁");
                break;
            case R.id.CJG_voice_desc:
                startActivity(new Intent(BiShanSiActivity.this, CangJingGeActivity.class));
                break;
            case R.id.CJG_content:
                Jianjie_dadian_actiivty.actionstart1(BiShanSiActivity.this,name,"藏经阁");
                break;
            case R.id.JTD_yinglian_detail:
                YinglianShowActivity  .actionstart(BiShanSiActivity.this,name,"戒坛殿");
                break;
            case R.id.JTD_voice_desc:
                startActivity(new Intent(BiShanSiActivity.this, JieTanDianActivity.class));
                break;
            case R.id.JTD_content:
                Jianjie_dadian_actiivty.actionstart1(BiShanSiActivity.this,name,"戒坛殿");
                break;
            case R.id.LYS_yinglian_detail:
                YinglianShowActivity  .actionstart(BiShanSiActivity.this,name,"雷音寺");
                break;
            case R.id.LYS_voice_desc:
                startActivity(new Intent(BiShanSiActivity.this,LeiYinSiActivity.class));
                break;
            case R.id.LYS_content:
                Jianjie_dadian_actiivty.actionstart1(BiShanSiActivity.this,name,"雷音寺");
                break;
            case R.id.TWD_yinglian_detail:
                YinglianShowActivity  .actionstart(BiShanSiActivity.this,name,"天王殿");
                break;
            case R.id.TWD_voice_desc:
                startActivity(new Intent(BiShanSiActivity.this, com.example.wutai.wutaimoutain.BiShanSiVoice.TianWangDianActivity.class));
                break;
            case R.id.TWD_content:
                Jianjie_dadian_actiivty.actionstart1(BiShanSiActivity.this,name,"天王殿");
                break;
            default:
                break;
        }
        return true;
    }

}
