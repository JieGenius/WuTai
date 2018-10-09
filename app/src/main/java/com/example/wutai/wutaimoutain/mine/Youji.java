package com.example.wutai.wutaimoutain.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import com.example.wutai.wutaimoutain.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Youji extends AppCompatActivity {

    @BindView(R.id.mine_youji_add)
    FloatingActionButton mineYoujiAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_youji);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.mine_youji_add)
    public void onViewClicked() {
        Intent intent = new Intent(this,YoujiEditor.class);
        startActivity(intent);
    }
}
