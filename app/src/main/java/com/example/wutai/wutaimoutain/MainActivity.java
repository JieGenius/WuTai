package com.example.wutai.wutaimoutain;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.wutai.wutaimoutain.Services.services;
import com.example.wutai.wutaimoutain.Talk.AllTalk;
import com.example.wutai.wutaimoutain.allsimiaos.MoveDBFile;
import com.example.wutai.wutaimoutain.init.Setall;
import com.example.wutai.wutaimoutain.mine.ContractUs;
import com.example.wutai.wutaimoutain.mine.LoginActivity;
import com.example.wutai.wutaimoutain.mine.SettingActivity;
import com.example.wutai.wutaimoutain.mine.UserInfo;
import com.example.wutai.wutaimoutain.myfragment.HomeFragment;
import com.example.wutai.wutaimoutain.myfragment.BudCulFragmnet;
import com.example.wutai.wutaimoutain.myfragment.MineFragmnet;
import com.example.wutai.wutaimoutain.sendTalk.SendTalkActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationBar.OnTabSelectedListener {
    private static final String TAG = "MainActivity";
    public static AllTalk allTalkData;
    private BottomNavigationBar bottomNavigationBar;
    private SearchView searchView = null;
    private SearchView.SearchAutoComplete mSearchAutoComplete = null;
    int lastSelectedPosition = 0;
    private HomeFragment homeFragment;
    private MineFragmnet mineFragmnet;
    private BudCulFragmnet budCulFragmnet;
    private com.example.wutai.wutaimoutain.myfragment.TalkFragment talkFragment;
    private SharedPreferences sharedPreferences;
    private NavigationView navigationView;
    private long currentTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = getSharedPreferences("user_message", Context.MODE_PRIVATE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                MoveDBFile.move(getApplicationContext());
                Setall.setdata(MainActivity.this);
            }
        }).start();
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_user1);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        //CircleImageView circleImageView = navigationView.findViewById(R.id.nav_civ_user_head_pic);
        navigationView.setNavigationItemSelectedListener(this);
        drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

                TextView userPhoneNumber = navigationView.findViewById(R.id.nav_tv_user_phone);
                TextView userName = navigationView.findViewById(R.id.nav_tv_user_name);
                CircleImageView imageView = navigationView.findViewById(R.id.nav_civ_user_head_pic);
                userPhoneNumber.setText("TEL:" + sharedPreferences.getString("phone", "8888"));
                userName.setText("用户名：" + sharedPreferences.getString("name", "游客"));
                if (UserInfo.stream2Drawable(sharedPreferences.getString("userPicture", "")) != null) {
                    imageView.setImageDrawable(UserInfo.stream2Drawable(sharedPreferences.getString("userPicture", "")));
                } else {
                    imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.default_head_pic));
                }
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomset();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = this.getFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (homeFragment == null) {
                    lastSelectedPosition = 0;
                    homeFragment = homeFragment.newInstance("首页");
                }
                transaction.replace(R.id.tb, homeFragment);
                break;
            case 1:
                if (talkFragment == null) {
                    lastSelectedPosition = 1;
                    talkFragment = talkFragment.newInstance("漫游五台");
                }
                transaction.replace(R.id.tb, talkFragment);
                break;
            case 2:
                if (budCulFragmnet == null) {
                    lastSelectedPosition = 2;
                    budCulFragmnet = budCulFragmnet.newInstance("佛教文化");
                }
                transaction.replace(R.id.tb, budCulFragmnet);
                break;
            case 3:
                if (mineFragmnet == null) {
                    lastSelectedPosition = 3;
                    mineFragmnet = mineFragmnet.newInstance("我的");
                }
                transaction.replace(R.id.tb, mineFragmnet);
                break;
            default:
                break;
        }

        transaction.commit();// 事务提交
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        getTalkData();
    }

    /**
     * 设置释放Fragment 事务
     */
    @Override
    public void onTabReselected(int position) {

    }

    public void bottomset() {
        bottomNavigationBar
                .setTabSelectedListener(MainActivity.this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setActiveColor("#286fe1") //选中颜色
                .setInActiveColor("#000000")//未选中颜色
                .setBarBackgroundColor("#ffffff");//导航栏背景色
        /** 添加导航按钮 */
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.shouye11, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.quanzi, "漫游五台"))
                .addItem(new BottomNavigationItem(R.drawable.fojiao, "佛教文化"))
                .addItem(new BottomNavigationItem(R.drawable.mine, "我的"))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise(); //initialise 一定要放在 所有设置的最后一项

        setDefaultFragment();//设置默认导航栏

    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        homeFragment = HomeFragment.newInstance("首页");
        transaction.replace(R.id.tb, homeFragment);
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Drawer 打开时候回退的作用变成关闭Drawe
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
                return true;
            } else if (System.currentTimeMillis() - currentTime < 1500) {
                finish();
                return true;
            } else {
                currentTime = System.currentTimeMillis();
                Toast.makeText(this, "再次点击返回按钮结束程序", Toast.LENGTH_SHORT).show();
                return false;
            }
        }


        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_view, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        searchView.setIconified(true);
//        searchView.setIconifiedByDefault(false);//默认为true在框内，设置false则在框外
//        searchView.setSubmitButtonEnabled(true);//显示提交按钮
        searchView.setQueryHint("此处输入相关搜索内容");
        searchView.setMaxWidth(1250);
        mSearchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);

        //设置输入框提示文字样式
        mSearchAutoComplete.setHintTextColor(getResources().getColor(android.R.color.white));//设置提示文字颜色
        mSearchAutoComplete.setTextColor(getResources().getColor(android.R.color.white));//设置内容文字颜色


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //提交按钮的点击事件
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //当输入框内容改变的时候回调

//                if (TextUtils.isEmpty(newText)){
//                    listView.setVisibility(View.GONE);
//                }else {
//                    querymsg(newText);
//                    listView.setVisibility(View.VISIBLE);
//                }

                return true;
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomNavigationBar.setVisibility(View.INVISIBLE);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                bottomNavigationBar.setVisibility(View.VISIBLE);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        .

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.send_talk) {//分享
            boolean isLogin = sharedPreferences.getBoolean("isLogin", false);
            if (isLogin) {
                Intent intent = new Intent(MainActivity.this, SendTalkActivity.class);
                startActivity(intent);
            } else {
                Snackbar.make(searchView, "您当前未登录，是否跳到登录界面", 3000).setAction("是", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent1);
                    }
                }).show();
            }
        } else if (id == R.id.nav_manage) {//设置
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {//分享
            Resources resources = this.getResources();
            int imageId = getResources().getIdentifier("fengmian1_show","drawable",getPackageName());
            String path = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources.getResourcePackageName(imageId) + "/" + resources.getResourceTypeName(imageId) + "/" + resources.getResourceEntryName(imageId);
            Uri imageUri = Uri.parse(path);
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            shareIntent.setType("image/*");
            startActivity(Intent.createChooser(shareIntent, "分享到："));
        } else if (id == R.id.nav_send) {//联系我们
            Intent intent = new Intent(this,ContractUs.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void getTalkData() {
        String url = services.urlGetTalk;
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        requestBody.addFormDataPart("page", "0");
        final Request request = new Request.Builder().url(url).post(requestBody.build()).tag(MainActivity.this).build();
        //client.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
        client.newBuilder().readTimeout(10, TimeUnit.MINUTES).connectTimeout(10, TimeUnit.MINUTES).writeTimeout(10, TimeUnit.MINUTES).build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                allTalkData = new Gson().fromJson(response.body().string(), AllTalk.class);
                if (talkFragment != null) {
                    if (talkFragment.list != null) {
                        //talkFragment.list.addAll(allTalkData.getTalkArr());
                        talkFragment.handler.sendEmptyMessage(5);
                    }
                }
            }
        });
    }
}
