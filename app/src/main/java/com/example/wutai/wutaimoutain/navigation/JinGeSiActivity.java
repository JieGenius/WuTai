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
import com.example.wutai.wutaimoutain.ShengBao.JinGeSiVoice.JGSDaBeiDianActivity;
import com.example.wutai.wutaimoutain.ShengBao.JinGeSiVoice.JGSDaXiongBaoDianActivity;
import com.example.wutai.wutaimoutain.ShengBao.JinGeSiVoice.JGSTianWangDianActivity;
import com.example.wutai.wutaimoutain.ShengBao.JinGeSiVoice.JGSWoFoDianActivity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.DaBaiTaActivity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.DaCangJingGeActivity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.DaCiYanShouBaoDianActivity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.TianWangDianActivity;
import com.example.wutai.wutaimoutain.jianjie_new.Jianjie_dadian_actiivty;
import com.example.wutai.wutaimoutain.yinglian.YinglianShowActivity;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;


public class JinGeSiActivity extends AppCompatActivity implements View.OnClickListener,PopupMenu.OnMenuItemClickListener{
    private Button WFD,DXBD,DBD,TWD;
    private String content;
    private Context mContex;
    private String name = "金阁寺";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jin_ge_si);
        initView();
        mContex = getApplicationContext();
    }

    public void initView(){
        //初始化声音设置
        SpeechUtility.createUtility(JinGeSiActivity.this, SpeechConstant.APPID +"=5b63c383");  //=后面这里要替换成自己申请的 AppID
        WFD = (Button)findViewById(R.id.JGS_wo_fo_dian);
        WFD.setOnClickListener(this);
        DXBD = (Button)findViewById(R.id.JGS_da_xiong_bao_dian);
        DXBD.setOnClickListener(this);
        DBD = (Button)findViewById(R.id.JGS_da_bei_dian);
        DBD.setOnClickListener(this);
        TWD = (Button)findViewById(R.id.JGS_tian_wang_dian);
        TWD.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.JGS_wo_fo_dian:
                //创建弹出式菜单对象（最低版本11）
                PopupMenu popup = new PopupMenu(this, v);//第二个参数是绑定的那个view
                //获取菜单填充器
                MenuInflater inflater = popup.getMenuInflater();
                //填充菜单
                inflater.inflate(R.menu.jgs_wo_fo_dian_menu, popup.getMenu());
                //绑定菜单项的点击事件
                popup.setOnMenuItemClickListener(JinGeSiActivity.this);
                //显示(这一行代码不要忘记了)
                popup.show();
                break;
            case R.id.JGS_da_xiong_bao_dian:
                PopupMenu popup2 = new PopupMenu(this,v);
                MenuInflater inflater2 = popup2.getMenuInflater();
                inflater2.inflate(R.menu.jgs_da_xiong_bao_dian_menu,popup2.getMenu());
                popup2.setOnMenuItemClickListener(JinGeSiActivity.this);
                popup2.show();
                break;
            case R.id.JGS_da_bei_dian:
                PopupMenu popup3 = new PopupMenu(this,v);
                MenuInflater inflater3 = popup3.getMenuInflater();
                inflater3.inflate(R.menu.jgs_da_bei_dian_menu,popup3.getMenu());
                popup3.setOnMenuItemClickListener(JinGeSiActivity.this);
                popup3.show();
                break;
            case R.id.JGS_tian_wang_dian:
                PopupMenu popup4 = new PopupMenu(this,v);
                MenuInflater inflater4 = popup4.getMenuInflater();
                inflater4.inflate(R.menu.jgs_tian_wang_dian_menu,popup4.getMenu());
                popup4.setOnMenuItemClickListener(JinGeSiActivity.this);
                popup4.show();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.WFD_yinglian_detail:
                YinglianShowActivity.actionstart(JinGeSiActivity.this,name,"金阁寺卧佛殿");
                break;
            case R.id.WFD_voice_desc:
                startActivity(new Intent(JinGeSiActivity.this, JGSWoFoDianActivity.class));
                break;
            case R.id.WFD_content:
                Jianjie_dadian_actiivty.actionstart1(JinGeSiActivity.this,name,"金阁寺卧佛殿");
                break;
            case R.id.DXBD_yinglian_detail:
                YinglianShowActivity  .actionstart(JinGeSiActivity.this,name,"金阁寺大雄宝殿");
                break;
            case R.id.DXBD_voice_desc:
                startActivity(new Intent(JinGeSiActivity.this, JGSDaXiongBaoDianActivity.class));
                break;
            case R.id.DXBD_content:
                Jianjie_dadian_actiivty.actionstart1(JinGeSiActivity.this,name,"金阁寺大雄宝殿");
                break;
            case R.id.DBD_yinglian_detail:
                YinglianShowActivity  .actionstart(JinGeSiActivity.this,name,"金阁寺大悲殿");
                break;
            case R.id.DBD_voice_desc:
                startActivity(new Intent(JinGeSiActivity.this, JGSDaBeiDianActivity.class));
                break;
            case R.id.DBD_content:
                Jianjie_dadian_actiivty.actionstart1(JinGeSiActivity.this,name,"金阁寺大悲殿");
                break;
            case R.id.TWD_yinglian_detail:
                YinglianShowActivity  .actionstart(JinGeSiActivity.this,name,"金阁寺天王殿");
                break;
            case R.id.TWD_voice_desc:
                startActivity(new Intent(JinGeSiActivity.this, TianWangDianActivity.class));
                break;
            case R.id.TWD_content:
                Jianjie_dadian_actiivty.actionstart1(JinGeSiActivity.this,name,"金阁寺天王殿");
                break;
            default:
                break;
        }
        return true;
    }

}
