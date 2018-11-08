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

import com.example.wutai.wutaimoutain.PuSaDingVoice.DaXiongBaoDianActivity;
import com.example.wutai.wutaimoutain.PuSaDingVoice.SengSheActivity;
import com.example.wutai.wutaimoutain.PuSaDingVoice.TianWangDian;
import com.example.wutai.wutaimoutain.PuSaDingVoice.WenShuDianActivity;
import com.example.wutai.wutaimoutain.PuSaDingVoice.WuGuanTangActivity;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.DaBaiTaActivity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.DaCangJingGeActivity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.DaCiYanShouBaoDianActivity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.TianWangDianActivity;
import com.example.wutai.wutaimoutain.jianjie_new.Jianjie_dadian_actiivty;
import com.example.wutai.wutaimoutain.yinglian.YinglianShowActivity;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import de.hdodenhof.circleimageview.CircleImageView;

public class PuSaDingActivity extends AppCompatActivity implements View.OnClickListener,PopupMenu.OnMenuItemClickListener{
    private CircleImageView SS,WGT,WSD,DXBD,TWD;
    private String content;
    private Context mContex;
    private String name = "菩萨顶";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pu_sa_ding);
        initView();
    }
    public void initView(){
        //初始化声音设置
        SpeechUtility.createUtility(PuSaDingActivity.this, SpeechConstant.APPID +"=5b63c383");  //=后面这里要替换成自己申请的 AppID
        SS = (CircleImageView) findViewById(R.id.pu_sa_ding_seng_she);
        SS.setOnClickListener(this);
        WGT = (CircleImageView) findViewById(R.id.pu_sa_ding_wu_guan_tang);
        WGT.setOnClickListener(this);
        WSD = (CircleImageView) findViewById(R.id.pu_sa_ding_wen_shu_dian);
        WSD.setOnClickListener(this);
        DXBD = (CircleImageView) findViewById(R.id.pu_sa_ding_da_xiong_bao_dian);
        DXBD.setOnClickListener(this);
        TWD = (CircleImageView) findViewById(R.id.pu_sa_ding_tian_wang_dian);
        TWD.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pu_sa_ding_seng_she:
                //创建弹出式菜单对象（最低版本11）
                PopupMenu popup = new PopupMenu(this, v);//第二个参数是绑定的那个view
                //获取菜单填充器
                MenuInflater inflater = popup.getMenuInflater();
                //填充菜单
                inflater.inflate(R.menu.pu_sa_ding_seng_she_menu, popup.getMenu());
                //绑定菜单项的点击事件
                popup.setOnMenuItemClickListener(PuSaDingActivity.this);
                //显示(这一行代码不要忘记了)
                popup.show();
                break;
            case R.id.pu_sa_ding_wu_guan_tang:
                PopupMenu popup2 = new PopupMenu(this,v);
                MenuInflater inflater2 = popup2.getMenuInflater();
                inflater2.inflate(R.menu.pu_sa_ding_wu_guan_tang_menu,popup2.getMenu());
                popup2.setOnMenuItemClickListener(PuSaDingActivity.this);
                popup2.show();
                break;
            case R.id.pu_sa_ding_wen_shu_dian:
                PopupMenu popup3 = new PopupMenu(this,v);
                MenuInflater inflater3 = popup3.getMenuInflater();
                inflater3.inflate(R.menu.pu_sa_ding_wen_shu_dian_menu,popup3.getMenu());
                popup3.setOnMenuItemClickListener(PuSaDingActivity.this);
                popup3.show();
                break;
            case R.id.pu_sa_ding_da_xiong_bao_dian:
                PopupMenu popup4 = new PopupMenu(this,v);
                MenuInflater inflater4 = popup4.getMenuInflater();
                inflater4.inflate(R.menu.pu_sa_ding_da_xiong_bao_dian_menu,popup4.getMenu());
                popup4.setOnMenuItemClickListener(PuSaDingActivity.this);
                popup4.show();
                break;
            case R.id.pu_sa_ding_tian_wang_dian:
                PopupMenu popup5 = new PopupMenu(this,v);
                MenuInflater inflater5 = popup5.getMenuInflater();
                inflater5.inflate(R.menu.pu_sa_ding_tian_wang_dian_menu,popup5.getMenu());
                popup5.setOnMenuItemClickListener(PuSaDingActivity.this);
                popup5.show();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.SS_yinglian_detail:
                YinglianShowActivity.actionstart(PuSaDingActivity.this,name,"藏经阁");
                break;
            case R.id.SS_voice_desc:
                startActivity(new Intent(PuSaDingActivity.this, SengSheActivity.class));
                break;
            case R.id.SS_content:
                Jianjie_dadian_actiivty.actionstart1(PuSaDingActivity.this,name,"藏经阁");
                break;
            case R.id.WGT_yinglian_detail:
                YinglianShowActivity  .actionstart(PuSaDingActivity.this,name,"五观堂");
                break;
            case R.id.WGT_voice_desc:
                startActivity(new Intent(PuSaDingActivity.this, WuGuanTangActivity.class));
                break;
            case R.id.WGT_content:
                Jianjie_dadian_actiivty.actionstart1(PuSaDingActivity.this,name,"五观堂");
                break;
            case R.id.WSD_yinglian_detail:
                YinglianShowActivity  .actionstart(PuSaDingActivity.this,name,"文殊殿");
                break;
            case R.id.WSD_voice_desc:
                startActivity(new Intent(PuSaDingActivity.this, WenShuDianActivity.class));
                break;
            case R.id.WSD_content:
                Jianjie_dadian_actiivty.actionstart1(PuSaDingActivity.this,name,"文殊殿");
                break;
            case R.id.DXBD_yinglian_detail:
                YinglianShowActivity  .actionstart(PuSaDingActivity.this,name,"大雄宝殿");
                break;
            case R.id.DXBD_voice_desc:
                startActivity(new Intent(PuSaDingActivity.this, DaXiongBaoDianActivity.class));
                break;
            case R.id.DXBD_content:
                Jianjie_dadian_actiivty.actionstart1(PuSaDingActivity.this,name,"大雄宝殿");
                break;
            case R.id.TWD_yinglian_detail:
                YinglianShowActivity  .actionstart(PuSaDingActivity.this,name,"天王殿");
                break;
            case R.id.TWD_voice_desc:
                startActivity(new Intent(PuSaDingActivity.this, TianWangDian.class));
                break;
            case R.id.TWD_content:
                Jianjie_dadian_actiivty.actionstart1(PuSaDingActivity.this,name,"天王殿");
            default:
                break;
        }
        return true;
    }

}
