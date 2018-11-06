package com.example.wutai.wutaimoutain.sendTalk;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Services.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SendTalkActivity extends MPermissionsActivity {

    private static final String TAG = "SendTalkActivity";
    @BindView(R.id.talk_ib_back)
    TextView backButton;
    @BindView(R.id.talk_et_content)
    EditText contentEditText;
    @BindView(R.id.talk_tv_send)
    TextView talkTvSend;
    @BindView(R.id.send_talk_open_pic_album_bt)
    ImageButton sendTalkOpenPicAlbumBt;
    @BindView(R.id.send_talk_open_camera_bt)
    ImageButton sendTalkOpenCameraBt;
    private GridView noScrollgridview;
    private static final int TAKE_PICTURE = 0x000000;
    private static final int SHOWPICTURES = 0x0010;
    private GridAdapter adapter;

    private TextView send_TextView;
    private ImageButton openAlbum;
    private ImageButton openCamera;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_talk_main);
        ButterKnife.bind(this);
        //动态判断权限
        requestPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 0x0001);
        contentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (contentEditText.getText().length() == 0) {
                    send_TextView.setEnabled(false);
                } else {
                    send_TextView.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        Bimp.bmp.clear();
        Bimp.drr.clear();
        Bimp.max = 0;
    }

    /**
     * 权限成功回调函数
     *
     * @param requestCode
     */

    public void permissionSuccess(int requestCode) {
        super.permissionSuccess(requestCode);
        switch (requestCode) {
            case 0x0001:
                Init();
                break;
        }
    }

    public void Init() {
        //初始化控件
        noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);
        //设置gridview分割线为透明
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        //初始化适配器
        adapter = new GridAdapter(this);
        //更新数据
        adapter.update();
        Log.e(TAG, "Init: update");
        //绑定图片数据
        noScrollgridview.setAdapter(adapter);
        //绑定点击事件
        noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /***
             *
             * @param arg0 arg0 ->官方为parent  相当于listview Y适配器的一个指针，可以通过它来获得Y里装着的一切东西，再通俗点就是说告诉你，你点的是Y，不是X - -
             * @param arg1 arg1 p->(view)是你点b item的view的句柄，就是你可以用这个view，来获得b里的控件的id后操作控件
             * @param arg2 arg2 p->(position)是b在Y适配器里的位置（生成listview时，适配器一个一个的做item，然后把他们按顺序排好队，在放到listview里，意思就是这个b是第position号做好的）
             * @param arg3 arg3->(id)id 是b在listview Y里的第几行的位置（很明显是第2行），大部分时候position和id的值是一样的，如果需要的话，你可以自己加个log把position和id都弄出
             *             来在logcat里瞅//瞅，//看了之后心里才踏实。
             */

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //判断点击的是否是图片
                if (arg2 == Bimp.bmp.size()) {
                    //显示选择提示窗
                    new PopupWindows(SendTalkActivity.this, noScrollgridview);
                } else {
                    //进入图片预览页面
                    Intent intent = new Intent(SendTalkActivity.this, PhotoActivity.class);
                    //传递图片标识
                    intent.putExtra("ID", arg2);

                    startActivityForResult(intent, SHOWPICTURES);
                }
            }
        });

        send_TextView = (TextView) findViewById(R.id.talk_tv_send);
        send_TextView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                List<String> list = new ArrayList<String>();
                for (int i = 0; i < Bimp.drr.size(); i++) {
                    String Str = Bimp.drr.get(i).substring(Bimp.drr.get(i).lastIndexOf("/") + 1,
                            Bimp.drr.get(i).lastIndexOf("."));
                    list.add(FileUtils.SDPATH + Str + ".JPEG");
                }
                String url = services.urlSendTalk;
                String file_path;
                OkHttpClient client = new OkHttpClient();
                MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
                for (int i = 0; i < list.size(); i++) {
                    file_path = list.get(i);
                    File file = new File(file_path);
                    RequestBody body = RequestBody.create(MediaType.parse("image/jpg"), file);
                    String fileName = file.getName();
                    requestBody.addFormDataPart("" + i, fileName, body);
                }
                String phone = getSharedPreferences("user_message", Context.MODE_PRIVATE).getString("phone", "");
                if (phone.length() != 11) {
                    Toast.makeText(SendTalkActivity.this, "数据异常", Toast.LENGTH_SHORT).show();
                }
                requestBody.addFormDataPart("phone", phone);
                requestBody.addFormDataPart("content", contentEditText.getText().toString());
                Request request = new Request.Builder().url(url).post(requestBody.build()).tag(SendTalkActivity.this).build();
                client.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("失败", e.getMessage());
                        FileUtils.deleteDir();
                        Bimp.bmp.clear();
                        Bimp.max = 0;
                        final Handler handler = new Handler(getMainLooper()) {
                            @Override
                            public void handleMessage(Message msg) {
                                if (msg.what == 2) {
                                    Toast.makeText(SendTalkActivity.this, "说说上传失败", Toast.LENGTH_SHORT).show();
                                    if(adapter!=null){
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                            }
                        };
                        Message message = new Message();
                        message.what = 2;
                        handler.sendMessage(message);

                        //adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.e("成功", response.body().string());
                        FileUtils.deleteDir();
                        Bimp.bmp.clear();
                        Bimp.max = 0;

                        final Handler handler = new Handler(getMainLooper()) {
                            @Override
                            public void handleMessage(Message msg) {
                                if (msg.what == 1) {
                                    Toast.makeText(SendTalkActivity.this, "说说上传成功", Toast.LENGTH_SHORT).show();
                                    if(adapter!=null){
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                            }
                        };
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                });
                // 高清的压缩图片全部就在 list 路径里面了
                // 高清的压缩过的 bmp 对象 都在 Bimp.bmp里面
                // 完成上传服务器后 .........
                //删除保存内容
                finish();
            }
        });

    }

    @OnClick(R.id.talk_ib_back)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.send_talk_open_pic_album_bt, R.id.send_talk_open_camera_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send_talk_open_pic_album_bt:
                Intent intent = new Intent(SendTalkActivity.this, TestPicActivity.class);
                startActivity(intent);
                break;
            case R.id.send_talk_open_camera_bt:
                photo();
                break;
        }
    }

    @SuppressLint("HandlerLeak")
    public class GridAdapter extends BaseAdapter {
        private LayoutInflater inflater; // 视图容器

        private int selectedPosition = -1;// 选中的位置

        private boolean shape;

        public boolean isShape() {
            return shape;
        }

        public void setShape(boolean shape) {
            this.shape = shape;
        }

        public GridAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public void update() {
            loading();
        }

        public int getCount() {
            if (Bimp.bmp.size() == 9) {
                return 9;
            } else {
                return Bimp.bmp.size() + 1;
            }
        }

        public Object getItem(int arg0) {

            return null;
        }

        public long getItemId(int arg0) {
            return arg0;
        }

        public void setSelectedPosition(int position) {
            selectedPosition = position;
        }

        public int getSelectedPosition() {
            return selectedPosition;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            final int coord = position;
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.talk_item_published_grida, parent, false);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView.findViewById(R.id.item_grida_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (position == Bimp.bmp.size()) {
                holder.image.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.send_talk_icon_addpic_unfocused));
            } else {
                holder.image.setImageBitmap(Bimp.bmp.get(position));
            }
            ;
            return convertView;
        }

        public class ViewHolder {
            public ImageView image;
        }

        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        adapter.notifyDataSetChanged();
                        break;
                }
                super.handleMessage(msg);
            }
        };

        public void loading() {
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        Log.e(TAG, "run: " + Bimp.max + "   " + Bimp.drr.size());
                        if (Bimp.max == Bimp.drr.size()) {
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                            break;
                        } else {
                            if (Bimp.max >= Bimp.drr.size()) {
                                return;
                            }
                            try {

                                String path = Bimp.drr.get(Bimp.max);
                                Bimp.max += 1;
                                Log.i("SendTalkActivity-Load:", path);
                                Bitmap bm = Bimp.revitionImageSize(path);
                                Bimp.bmp.add(bm);
                                String newStr = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
                                FileUtils.saveBitmap(bm, "" + newStr);

                                Message message = new Message();
                                message.what = 1;
                                handler.sendMessage(message);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).start();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        adapter.update();
    }

    public String getString(String s) {
        String path = null;
        if (s == null) return "";
        for (int i = s.length() - 1; i > 0; i++) {
            s.charAt(i);
        }
        return path;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void onRestart() {
        super.onRestart();
        adapter.update();
        Log.e(TAG, "onRestart: ");

    }

    public class PopupWindows extends PopupWindow {

        public PopupWindows(Context mContext, View parent) {

            View view = View.inflate(mContext, R.layout.send_talk_popwindow, null);
            view.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.send_talk_pop_windows_fade_ins));
            LinearLayout ll_popup = (LinearLayout) view.findViewById(R.id.send_talk_pop_window_linear_layout);
            ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.send_talk_pop_windows_push_bottom_in_2));

            setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            setBackgroundDrawable(new BitmapDrawable());
            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            update();
            //初始化控件
            Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);
            Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_Photo);
            Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);
            //绑定点击事件
            bt1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    photo();
                    //关闭弹窗
                    dismiss();
                }
            });
            bt2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //进入预览页
                    Intent intent = new Intent(SendTalkActivity.this, TestPicActivity.class);
                    startActivity(intent);
                    dismiss();
                }
            });
            bt3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dismiss();
                }
            });

        }
    }


    private String path = "";

    public void photo() {
        //开启相机
        if (Bimp.bmp.size() == 9) {
            Toast.makeText(SendTalkActivity.this, "最多只能选9张照片", Toast.LENGTH_SHORT).show();
            return;
        }


        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //创建文件
        //getExternalStorageDirectory() 得到SD 卡的根目录
        //getExternalCacheDir() 得到当前应用的缓存数据位置

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/myimage/");
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/myimage/", String.valueOf(System.currentTimeMillis()) + ".jpg");
        path = file.getPath();
        Uri imageUri;
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(SendTalkActivity.this, "com.example.wutai.wutaimoutain.fileprovider", file);
        } else {
            imageUri = Uri.fromFile(file);
        }
        //传递参数
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        //开始跳转确定回调标识
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("SendTalkActivity", "requestCode:" + requestCode + "  resultCode:" + resultCode);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (Bimp.drr.size() < 9 && resultCode == -1) {
                    Bimp.drr.add(path);
                    for (int i = 0; i < Bimp.drr.size(); i++) {
                        Log.e("SendTalkActivity", "onActivityResult: " + Bimp.drr.get(i));
                    }
                }
                break;
            case SHOWPICTURES:
                adapter.update();
        }
    }


}