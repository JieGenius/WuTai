package com.example.wutai.wutaimoutain.ManYouWuTai;

import com.example.wutai.wutaimoutain.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class ScrollingActivity extends AppCompatActivity {
    ImageButton bt1;
    ImageButton bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wu_tai_activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bt1 = findViewById(R.id.bt_ding);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bt1.isSelected()){
                    bt1.setSelected(true);
                    bt1.setImageResource(R.drawable.ding);
                }
                else{
                    bt1.setSelected(false);
                    bt1.setImageResource(R.drawable.unding);
                }
            }
        });
        bt2 = findViewById(R.id.bt_collect);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bt2.isSelected()){
                    bt2.setSelected(true);
                    bt2.setImageResource(R.drawable.roam_wu_tai_collect_icon);
                }
                else{
                    bt2.setSelected(false);
                    bt2.setImageResource(R.drawable.uncollect);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }
}
