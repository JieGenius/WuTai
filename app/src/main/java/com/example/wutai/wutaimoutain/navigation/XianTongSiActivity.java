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
import com.example.wutai.wutaimoutain.XianTongSiVoice.DaWenShuDianActivity;
import com.example.wutai.wutaimoutain.XianTongSiVoice.DaXiongBaoDianActivity;
import com.example.wutai.wutaimoutain.XianTongSiVoice.GuanYinDianActivity;
import com.example.wutai.wutaimoutain.XianTongSiVoice.WuliangDianActivity;
import com.example.wutai.wutaimoutain.yinglian.YinglianShowActivity;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import de.hdodenhof.circleimageview.CircleImageView;

public class XianTongSiActivity extends AppCompatActivity implements View.OnClickListener,PopupMenu.OnMenuItemClickListener{
    private FloatingActionButton backNavigation;
    private CircleImageView WLD,DXBD,DWSD,GYD;
    private Context mContex;
    private String content;
    private String name = "显通寺";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xian_tong_si);
        initView();
    }

    public void initView(){
        // 初始化
        SpeechUtility.createUtility(XianTongSiActivity.this, SpeechConstant.APPID +"=5b63c383");  //=后面这里要替换成自己申请的 AppID
        WLD = (CircleImageView) findViewById(R.id.wu_liang_dian);
        WLD.setOnClickListener(this);
        DXBD = (CircleImageView)findViewById(R.id.da_xiong_bao_dian);
        DXBD.setOnClickListener(this);
        DWSD = (CircleImageView)findViewById(R.id.da_wen_shu_dian);
        DWSD.setOnClickListener(this);
        GYD = (CircleImageView)findViewById(R.id.guan_yin_dian);
        GYD.setOnClickListener(this);
        mContex = getApplicationContext();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wu_liang_dian:
                //创建弹出式菜单对象（最低版本11）
                PopupMenu popup = new PopupMenu(this, v);//第二个参数是绑定的那个view
                //获取菜单填充器
                MenuInflater inflater = popup.getMenuInflater();
                //填充菜单
                inflater.inflate(R.menu.wu_liang_dian_menu, popup.getMenu());
                //绑定菜单项的点击事件
                popup.setOnMenuItemClickListener(XianTongSiActivity.this);
                //显示(这一行代码不要忘记了)
                popup.show();

                break;
            case R.id.da_xiong_bao_dian:
                    PopupMenu popup2 = new PopupMenu(this, v);//第二个参数是绑定的那个view
                    //获取菜单填充器
                    MenuInflater inflater2 = popup2.getMenuInflater();
                    //填充菜单
                    inflater2.inflate(R.menu.da_xiong_bao_dian_menu, popup2.getMenu());
                    //绑定菜单项的点击事件
                    popup2.setOnMenuItemClickListener(XianTongSiActivity.this);
                    //显示(这一行代码不要忘记了)
                    popup2.show();
                break;
            case R.id.da_wen_shu_dian:
                PopupMenu popup3 = new PopupMenu(this, v);//第二个参数是绑定的那个view
                //获取菜单填充器
                MenuInflater inflater3 = popup3.getMenuInflater();
                //填充菜单
                inflater3.inflate(R.menu.da_wen_shu_dian_menu, popup3.getMenu());
                //绑定菜单项的点击事件
                popup3.setOnMenuItemClickListener(XianTongSiActivity.this);
                //显示(这一行代码不要忘记了)
                popup3.show();
                break;
            case R.id.guan_yin_dian:
                PopupMenu popup4 = new PopupMenu(this,v);
                MenuInflater inflater4 = popup4.getMenuInflater();
                inflater4.inflate(R.menu.guan_yin_dian_menu,popup4.getMenu());
                popup4.setOnMenuItemClickListener(XianTongSiActivity.this);
                //显示(这一行代码不要忘记了)
                popup4.show();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.WLD_yinglian_detail:
                YinglianShowActivity.actionstart(XianTongSiActivity.this,name,"无量殿");
                break;
            case R.id.WLD_voice_desc:
                startActivity(new Intent(XianTongSiActivity.this, WuliangDianActivity.class));
                break;
            case R.id.WLD_content:
                break;
            case R.id.DXBD_yinglian_detail:
                YinglianShowActivity.actionstart(XianTongSiActivity.this,name,"大雄宝殿");
                break;
            case R.id.DXBD_voice_desc:
                startActivity(new Intent(XianTongSiActivity.this, DaXiongBaoDianActivity.class));
                break;
            case R.id.DXBD_content:
                break;
            case R.id.DWSD_yinglian_detail:
                YinglianShowActivity.actionstart(XianTongSiActivity.this,name,"大文殊殿");
                break;
            case R.id.DWSD_voice_desc:
                startActivity(new Intent(XianTongSiActivity.this, DaWenShuDianActivity.class));
                break;
            case R.id.DWSD_content:
                break;
            case R.id.GYD_yinglian_detail:
                YinglianShowActivity.actionstart(XianTongSiActivity.this,name,"观音殿");
                break;
            case R.id.GYD_voice_desc:
                startActivity(new Intent(XianTongSiActivity.this, GuanYinDianActivity.class));
                break;
            case R.id.GYD_content:
                break;
            default:
                break;
        }
        return true;
    }
}
