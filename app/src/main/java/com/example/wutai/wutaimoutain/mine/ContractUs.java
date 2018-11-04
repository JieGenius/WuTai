package com.example.wutai.wutaimoutain.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.wutai.wutaimoutain.R;

public class ContractUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract__us);

        Toolbar toolbar = findViewById(R.id.contract_us_toolbar);
        setSupportActionBar(toolbar);

    }
}
