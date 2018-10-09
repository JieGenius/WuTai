package com.example.wutai.wutaimoutain.Simiao_excess;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Simiao_juti.Every_Simiao_Activity;

import java.util.Timer;
import java.util.TimerTask;

public class Singel_simiao_Activity extends AppCompatActivity {
    private ImageView stage;
    private static Timer timer;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every__simiao_);
        stage = (ImageView)findViewById(R.id.stage_simiao);
        intent = getIntent();
        if (intent == null){
            System.out.println("intent为空");
        }else System.out.println("不为空");
        if (stage == null){
            System.out.println("stage为空");
        }
        stage.setImageResource(intent.getIntExtra("idof_xiantong",0));
        if (timer == null) {
            timer = new Timer();
            timer.schedule(timerTask,3000);
        } else ;
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Intent intent1 = new Intent(Singel_simiao_Activity.this, Every_Simiao_Activity.class);
                if (intent.getIntExtra("idof_xiantong",0)==R.drawable.xiantongsisi){
                    intent1.putExtra("name_simiao","显通寺");}
                startActivity(intent1);
                finish();
            }
            super.handleMessage(msg);
        }
    };
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };
}
