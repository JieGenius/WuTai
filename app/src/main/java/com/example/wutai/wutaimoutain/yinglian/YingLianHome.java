package com.example.wutai.wutaimoutain.yinglian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.wutai.wutaimoutain.R;

public class YingLianHome extends AppCompatActivity {
    private LinearLayout bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ying_lian_home);
        bt = (LinearLayout)findViewById(R.id.ying_lian_home);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YinglianShowActivity.actionstart0(YingLianHome.this);
            }
        });
    }
}
