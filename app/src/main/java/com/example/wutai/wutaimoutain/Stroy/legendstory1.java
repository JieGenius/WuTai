package com.example.wutai.wutaimoutain.Stroy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.wutai.wutaimoutain.R;

public class legendstory1 extends AppCompatActivity {
    TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legendstory1);
        textView = (TextView)findViewById(R.id.textview1);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
