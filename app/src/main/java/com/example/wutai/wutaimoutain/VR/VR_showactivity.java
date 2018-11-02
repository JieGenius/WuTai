package com.example.wutai.wutaimoutain.VR;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.yinglian.YinglianShowActivity;

import java.util.Date;

public class VR_showactivity extends AppCompatActivity {
     private WebView webView;
     private android.support.v7.widget.Toolbar toolbar;
     private DrawerLayout drawerLayout;
     private Intent intent;
     private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr_showactivity);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_vr);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.vr_toolbar_vr);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        webView = (WebView)findViewById(R.id.ve_show);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        intent = getIntent();
        String simaio = intent.getStringExtra("simaio");
        if (simaio == null) {
            webView.loadUrl("https://i.svrvr.com/?a=wapview&id=r87739&code=0236FScD0HCune2hB6dD02CddD06FScc&state=blinq");
            getSupportActionBar().setTitle("VR");
        }else{
            getSupportActionBar().setTitle(simaio+"·VR");
            url = "https://baidu.com";
            if (simaio.equals("塔院寺"))   url = "https://i.svrvr.com/?a=wapview&id=b34189&code=023XO6LB0eXGHe2JSxMB0eb4LB0XO6LQ&state=blinq";
            else if (simaio.equals("菩萨顶")) url = "https://720yun.com/t/1f2jOssfsy3";
            else if (simaio.equals("殊像寺")) url = "https://i.svrvr.com/?a=wapview&id=r87739&code=0236FScD0HCune2hB6dD02CddD06FScc&state=blinq";
            if(url.equals("https://baidu.com")){
                Toast.makeText(this,"很抱歉，暂无该寺庙的VR,已自动为您显示塔院寺的VR",Toast.LENGTH_LONG).show();
                url = "https://i.svrvr.com/?a=wapview&id=b34189&code=023XO6LB0eXGHe2JSxMB0eb4LB0XO6LQ&state=blinq";
            }
            webView.loadUrl(url);

        }
    }
    public static void actionstart0(Context context,String simiao){
        Intent intent = new Intent(context, VR_showactivity.class);
        intent.putExtra("simaio",simiao);
        context.startActivity(intent);
    }
}
