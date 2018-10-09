package com.example.wutai.wutaimoutain.Simiao_excess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Simiao_juti.Every_Simiao_Activity;

public class Activity_excess extends AppCompatActivity {
    private Button bt_xiantong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excess);
        bt_xiantong = (Button)findViewById(R.id.excess_xiantong);
        bt_xiantong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Every_Simiao_Activity.actionstart(Activity_excess.this,"显通寺");
            }
        });
    }
}
