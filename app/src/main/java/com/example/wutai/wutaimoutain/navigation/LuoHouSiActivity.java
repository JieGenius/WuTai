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

import com.example.wutai.wutaimoutain.LuoHouSIVoice.DaXiongBaoDianActivity;
import com.example.wutai.wutaimoutain.LuoHouSIVoice.KaiHuaXianFoActivity;
import com.example.wutai.wutaimoutain.LuoHouSIVoice.WenShuDianActivity;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.DaBaiTaActivity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.DaCangJingGeActivity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.DaCiYanShouBaoDianActivity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.TianWangDianActivity;
import com.example.wutai.wutaimoutain.jianjie_new.Jianjie_dadian_actiivty;
import com.example.wutai.wutaimoutain.yinglian.YinglianShowActivity;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

public class LuoHouSiActivity extends AppCompatActivity implements View.OnClickListener,PopupMenu.OnMenuItemClickListener{
    private Button KHXFD,DXBD,WSD,TWD;
    private String content;
    private Context mContex;
    private String name = "罗睺寺";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luo_hou_si);
        initView();
    }

    public void initView(){
        //初始化声音设置
        SpeechUtility.createUtility(LuoHouSiActivity.this, SpeechConstant.APPID +"=5b63c383");  //=后面这里要替换成自己申请的 AppID
        KHXFD = (Button)findViewById(R.id.luo_hou_si_kai_hua_xian_fo);
        KHXFD.setOnClickListener(this);
        DXBD = (Button)findViewById(R.id.luo_hou_si_da_xiong_bao_dian);
        DXBD.setOnClickListener(this);
        WSD = (Button)findViewById(R.id.luo_hou_si_wen_shu_dian);
        WSD.setOnClickListener(this);
        TWD = (Button)findViewById(R.id.luo_hou_si_tian_wang_dian);
        TWD.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.luo_hou_si_kai_hua_xian_fo:
                //创建弹出式菜单对象（最低版本11）
                PopupMenu popup = new PopupMenu(this, v);//第二个参数是绑定的那个view
                //获取菜单填充器
                MenuInflater inflater = popup.getMenuInflater();
                //填充菜单
                inflater.inflate(R.menu.luo_hou_si_kai_hua_xian_fo_menu, popup.getMenu());
                //绑定菜单项的点击事件
                popup.setOnMenuItemClickListener(LuoHouSiActivity.this);
                //显示(这一行代码不要忘记了)
                popup.show();
                break;
            case R.id.luo_hou_si_da_xiong_bao_dian:
                PopupMenu popup2 = new PopupMenu(this,v);
                MenuInflater inflater2 = popup2.getMenuInflater();
                inflater2.inflate(R.menu.luo_hou_si_da_xiong_bao_dian_menu,popup2.getMenu());
                popup2.setOnMenuItemClickListener(LuoHouSiActivity.this);
                popup2.show();
                break;
            case R.id.luo_hou_si_wen_shu_dian:
                PopupMenu popup3 = new PopupMenu(this,v);
                MenuInflater inflater3 = popup3.getMenuInflater();
                inflater3.inflate(R.menu.luo_hou_si_wen_shu_dian_menu,popup3.getMenu());
                popup3.setOnMenuItemClickListener(LuoHouSiActivity.this);
                popup3.show();
                break;
            case R.id.luo_hou_si_tian_wang_dian:
                PopupMenu popup4 = new PopupMenu(this,v);
                MenuInflater inflater4 = popup4.getMenuInflater();
                inflater4.inflate(R.menu.luo_hou_si_tian_wang_dian_menu,popup4.getMenu());
                popup4.setOnMenuItemClickListener(LuoHouSiActivity.this);
                popup4.show();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.KHXF_yinglian_detail:
                YinglianShowActivity.actionstart(LuoHouSiActivity.this,name,"开花献佛殿");
                break;
            case R.id.KHXF_voice_desc:
                startActivity(new Intent(LuoHouSiActivity.this, KaiHuaXianFoActivity.class));
                break;
            case R.id.KHXF_content:
                Jianjie_dadian_actiivty.actionstart1(LuoHouSiActivity.this,name,"开花献佛殿");
                break;
            case R.id.DXBD_yinglian_detail:
                YinglianShowActivity  .actionstart(LuoHouSiActivity.this,name,"大雄宝殿");
                break;
            case R.id.DXBD_voice_desc:
                startActivity(new Intent(LuoHouSiActivity.this,DaXiongBaoDianActivity.class));
                break;
            case R.id.DXBD_content:
                Jianjie_dadian_actiivty.actionstart1(LuoHouSiActivity.this,name,"大雄宝殿");
                break;
            case R.id.WSD_yinglian_detail:
                YinglianShowActivity  .actionstart(LuoHouSiActivity.this,name,"文殊殿");
                break;
            case R.id.WSD_voice_desc:
                startActivity(new Intent(LuoHouSiActivity.this, WenShuDianActivity.class));
                break;
            case R.id.WSD_content:
                Jianjie_dadian_actiivty.actionstart1(LuoHouSiActivity.this,name,"文殊殿");
                break;
            case R.id.TWD_yinglian_detail:
                YinglianShowActivity  .actionstart(LuoHouSiActivity.this,name,"天王殿");
                break;
            case R.id.TWD_voice_desc:
                startActivity(new Intent(LuoHouSiActivity.this, com.example.wutai.wutaimoutain.LuoHouSIVoice.TianWangDianActivity.class));
                break;
            case R.id.TWD_content:
                Jianjie_dadian_actiivty.actionstart1(LuoHouSiActivity.this,name,"天王殿");
                break;
            default:
                break;
        }
        return true;
    }


}
