package com.example.wutai.wutaimoutain.TaYuanSiVoice;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Utils.AudioUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

public class DaBaiTaActivity extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView showImgView,voiceIconImgView;
    private TextView voiceContent;
    private Context mContex;
    private boolean isPause;
    private FloatingActionButton pauseAndContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da_bai_ta);
        initView();
    }

    public void initView(){

        //设置声音
        SpeechUtility.createUtility(DaBaiTaActivity.this, SpeechConstant.APPID +"=5b63c383");  //=后面这里要替换成自己申请的 AppID
        mContex = getApplicationContext();
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        collapsingToolbarLayout =  (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        showImgView = (ImageView) findViewById(R.id.da_bai_ta_img);
        voiceContent = (TextView)findViewById(R.id.voice_content);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle("大白塔");
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.dingxiangse));
        collapsingToolbarLayout.setExpandedTitleMarginBottom(10);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorAccent));
        Glide.with(mContex).load(R.mipmap.da_bai_ta_voice).into(showImgView);
        String content = mContex.getString(R.string.da_bai_ta_text);
        voiceContent.setText(content);
        AudioUtils.getInstance().init(DaBaiTaActivity.this); //初始化语音对象
        //从string里面获取文本
        AudioUtils.getInstance().speakText(content); //播放语音
        pauseAndContinue = (FloatingActionButton)findViewById(R.id.pause_and_continue);
        pauseAndContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isPause){
                    AudioUtils.getInstance().pauseSpeak();
                    isPause = true;
                }
                else {
                    AudioUtils.getInstance().continueSpeak();
                    isPause = false;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AudioUtils.getInstance().stopSpeack();
    }
}