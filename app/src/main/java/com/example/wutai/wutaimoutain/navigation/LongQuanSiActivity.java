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
import com.example.wutai.wutaimoutain.ShengBao.LongQuanSiVoice.LQSDaXiongBaoDianActivity;
import com.example.wutai.wutaimoutain.ShengBao.LongQuanSiVoice.LQSGuanYinDianActivity;
import com.example.wutai.wutaimoutain.ShengBao.LongQuanSiVoice.LQSTianWangDianActivity;
import com.example.wutai.wutaimoutain.ShengBao.LongQuanSiVoice.LQSWuGuanTangActivity;
import com.example.wutai.wutaimoutain.ShengBao.LongQuanSiVoice.LQSZuShiDianActivity;
import com.example.wutai.wutaimoutain.jianjie_new.Jianjie_dadian_actiivty;
import com.example.wutai.wutaimoutain.yinglian.YinglianShowActivity;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;


public class LongQuanSiActivity extends AppCompatActivity implements View.OnClickListener,PopupMenu.OnMenuItemClickListener{
    private Button DXBD,GYD,TWD,ZSD,WGT;
    private String content;
    private Context mContex;
    private String name = "龙泉寺";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_quan_si);
        initView();
        mContex = getApplicationContext();
    }

    public void initView(){
        //初始化声音设置
        SpeechUtility.createUtility(LongQuanSiActivity.this, SpeechConstant.APPID +"=5b63c383");  //=后面这里要替换成自己申请的 AppID
        DXBD = (Button)findViewById(R.id.LQS_da_xiong_bao_dian);
        DXBD.setOnClickListener(this);
        GYD = (Button)findViewById(R.id.LQS_guan_yin_dian);
        GYD.setOnClickListener(this);
        TWD = (Button)findViewById(R.id.LQS_tian_wang_dian);
        TWD.setOnClickListener(this);
        ZSD = (Button)findViewById(R.id.LQS_zu_shi_dian);
        ZSD.setOnClickListener(this);
        WGT = (Button)findViewById(R.id.LQS_wu_guan_tang);
        WGT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.LQS_da_xiong_bao_dian:
                //创建弹出式菜单对象（最低版本11）
                PopupMenu popup = new PopupMenu(this, v);//第二个参数是绑定的那个view
                //获取菜单填充器
                MenuInflater inflater = popup.getMenuInflater();
                //填充菜单
                inflater.inflate(R.menu.lqs_da_xiong_bao_dian_menu, popup.getMenu());
                //绑定菜单项的点击事件
                popup.setOnMenuItemClickListener(LongQuanSiActivity.this);
                //显示(这一行代码不要忘记了)
                popup.show();
                break;
            case R.id.LQS_guan_yin_dian:
                PopupMenu popup2 = new PopupMenu(this,v);
                MenuInflater inflater2 = popup2.getMenuInflater();
                inflater2.inflate(R.menu.lqs_guan_yin_dian_menu,popup2.getMenu());
                popup2.setOnMenuItemClickListener(LongQuanSiActivity.this);
                popup2.show();
                break;
            case R.id.LQS_tian_wang_dian:
                PopupMenu popup3 = new PopupMenu(this,v);
                MenuInflater inflater3 = popup3.getMenuInflater();
                inflater3.inflate(R.menu.lqs_tian_wang_dian_menu,popup3.getMenu());
                popup3.setOnMenuItemClickListener(LongQuanSiActivity.this);
                popup3.show();
                break;
            case R.id.LQS_zu_shi_dian:
                PopupMenu popup4 = new PopupMenu(this,v);
                MenuInflater inflater4 = popup4.getMenuInflater();
                inflater4.inflate(R.menu.lqs_zu_shi_dian_menu,popup4.getMenu());
                popup4.setOnMenuItemClickListener(LongQuanSiActivity.this);
                popup4.show();
                break;
            case R.id.LQS_wu_guan_tang:
                PopupMenu popup5 = new PopupMenu(this,v);
                MenuInflater inflater5 = popup5.getMenuInflater();
                inflater5.inflate(R.menu.lqs_wu_guan_tang_menu,popup5.getMenu());
                popup5.setOnMenuItemClickListener(LongQuanSiActivity.this);
                popup5.show();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.DXBD_yinglian_detail:
                YinglianShowActivity.actionstart(LongQuanSiActivity.this,name,"大雄宝殿");
                break;
            case R.id.DXBD_voice_desc:
                startActivity(new Intent(LongQuanSiActivity.this, LQSDaXiongBaoDianActivity.class));
                break;
            case R.id.DXBD_content:
                Jianjie_dadian_actiivty.actionstart1(LongQuanSiActivity.this,name,"龙泉寺大雄宝殿");
                break;
            case R.id.GYD_yinglian_detail:
                YinglianShowActivity  .actionstart(LongQuanSiActivity.this,name,"观音殿");
                break;
            case R.id.GYD_voice_desc:
                startActivity(new Intent(LongQuanSiActivity.this, LQSGuanYinDianActivity.class));
                break;
            case R.id.GYD_content:
                Jianjie_dadian_actiivty.actionstart1(LongQuanSiActivity.this,name,"龙泉寺观音殿");
                break;
            case R.id.TWD_yinglian_detail:
                YinglianShowActivity  .actionstart(LongQuanSiActivity.this,name,"天王殿");
                break;
            case R.id.TWD_voice_desc:
                startActivity(new Intent(LongQuanSiActivity.this, LQSTianWangDianActivity.class));
                break;
            case R.id.TWD_content:
                Jianjie_dadian_actiivty.actionstart1(LongQuanSiActivity.this,name,"龙泉寺天王殿");
                break;
            case R.id.WGT_yinglian_detail:
                YinglianShowActivity  .actionstart(LongQuanSiActivity.this,name,"五观堂");
                break;
            case R.id.WGT_voice_desc:
                startActivity(new Intent(LongQuanSiActivity.this, LQSWuGuanTangActivity.class));
                break;
            case R.id.WGT_content:
                Jianjie_dadian_actiivty.actionstart1(LongQuanSiActivity.this,name,"龙泉寺五观堂");
                break;
            case R.id.ZSD_yinglian_detail:
                YinglianShowActivity.actionstart(LongQuanSiActivity.this,name,"祖师殿");
                break;
            case R.id.ZSD_voice_desc:
                startActivity(new Intent(LongQuanSiActivity.this, LQSZuShiDianActivity.class));
                break;
            case R.id.ZSD_content:
                Jianjie_dadian_actiivty.actionstart1(LongQuanSiActivity.this,name,"龙泉寺祖师殿");
                break;
            default:
                break;
        }
        return true;
    }

}
