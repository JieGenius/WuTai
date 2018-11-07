package com.example.wutai.wutaimoutain.navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.ShengBao.ShuXiangSiVoice.SXSDaWenShuDianActivity;
import com.example.wutai.wutaimoutain.ShengBao.ShuXiangSiVoice.SXSHouGaoDianActivity;
import com.example.wutai.wutaimoutain.ShengBao.ShuXiangSiVoice.SXSJiaLanDianActivity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.DaBaiTaActivity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.DaCangJingGeActivity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.DaCiYanShouBaoDianActivity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.TianWangDianActivity;
import com.example.wutai.wutaimoutain.jianjie_new.Jianjie_dadian_actiivty;
import com.example.wutai.wutaimoutain.yinglian.YinglianShowActivity;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;


public class ShuXiangSiActivity extends AppCompatActivity implements View.OnClickListener,PopupMenu.OnMenuItemClickListener{
    private Button HGD,DWSD,JLD;
    private String content;
    private Context mContex;
    private String name = "殊像寺";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shu_xiang_si);
        initView();
        mContex = getApplicationContext();
    }

    public void initView(){
        //初始化声音设置
        SpeechUtility.createUtility(ShuXiangSiActivity.this, SpeechConstant.APPID +"=5b63c383");  //=后面这里要替换成自己申请的 AppID
        HGD = (Button)findViewById(R.id.SXS_hou_gao_dian);
        HGD.setOnClickListener(this);
        DWSD = (Button)findViewById(R.id.SXS_da_wen_shu_dian);
        DWSD.setOnClickListener(this);
        JLD = (Button)findViewById(R.id.SXS_jia_lan_dian);
        JLD.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.SXS_hou_gao_dian:
                //创建弹出式菜单对象（最低版本11）
                PopupMenu popup = new PopupMenu(this, v);//第二个参数是绑定的那个view
                //获取菜单填充器
                MenuInflater inflater = popup.getMenuInflater();
                //填充菜单
                inflater.inflate(R.menu.sxs_hou_gao_dian_menu, popup.getMenu());
                //绑定菜单项的点击事件
                popup.setOnMenuItemClickListener(ShuXiangSiActivity.this);
                //显示(这一行代码不要忘记了)
                popup.show();
                break;
            case R.id.SXS_da_wen_shu_dian:
                PopupMenu popup2 = new PopupMenu(this,v);
                MenuInflater inflater2 = popup2.getMenuInflater();
                inflater2.inflate(R.menu.sxs_da_wen_shu_dian_menu,popup2.getMenu());
                popup2.setOnMenuItemClickListener(ShuXiangSiActivity.this);
                popup2.show();
                break;
            case R.id.SXS_jia_lan_dian:
                PopupMenu popup3 = new PopupMenu(this,v);
                MenuInflater inflater3 = popup3.getMenuInflater();
                inflater3.inflate(R.menu.sxs_jia_lan_dian,popup3.getMenu());
                popup3.setOnMenuItemClickListener(ShuXiangSiActivity.this);
                popup3.show();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.DWSD_yinglian_detail:
                YinglianShowActivity.actionstart(ShuXiangSiActivity.this,name,"殊像寺大文殊殿");
                break;
            case R.id.DWSD_voice_desc:
                startActivity(new Intent(ShuXiangSiActivity.this, SXSDaWenShuDianActivity.class));
                break;
            case R.id.DWSD_content:
                Jianjie_dadian_actiivty.actionstart1(ShuXiangSiActivity.this,name,"殊像寺大文殊殿");
                break;
            case R.id.HGD_yinglian_detail:
                YinglianShowActivity  .actionstart(ShuXiangSiActivity.this,name,"殊像寺后高殿");
                break;
            case R.id.HGD_voice_desc:
                startActivity(new Intent(ShuXiangSiActivity.this, SXSHouGaoDianActivity.class));
                break;
            case R.id.HGD_content:
                Jianjie_dadian_actiivty.actionstart1(ShuXiangSiActivity.this,name,"殊像寺后高殿");
                break;
            case R.id.JLD_yinglian_detail:
                YinglianShowActivity  .actionstart(ShuXiangSiActivity.this,name,"殊像寺珈蓝殿");
                break;
            case R.id.JLD_voice_desc:
                startActivity(new Intent(ShuXiangSiActivity.this, SXSJiaLanDianActivity.class));
                break;
            case R.id.JLD_content:
                Jianjie_dadian_actiivty.actionstart1(ShuXiangSiActivity.this,name,"殊像寺珈蓝殿");
                break;
            default:
                break;
        }
        return true;
    }

}
