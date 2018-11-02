package com.example.wutai.wutaimoutain.mine;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wutai.wutaimoutain.MainActivity;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Services.services;
import com.example.wutai.wutaimoutain.common.ShowOnePictureActivity;
import com.google.gson.Gson;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class userCenterActivity extends AppCompatActivity {
    private static String TAG = "userCenterActivity";
    @BindView(R.id.mine_user_center_et_qianming)
    EditText mineUserCenterEtQianming;
    @BindView(R.id.mine_user_center_et_nickname)
    EditText mineUserCenterEtNickname;
    @BindView(R.id.mine_user_center_et_sex)
    EditText mineUserCenterEtSex;
    @BindView(R.id.mine_user_center_et_job)
    EditText mineUserCenterEtJob;
    @BindView(R.id.mine_user_center_et_dating_declaration)
    EditText mineUserCenterEtDatingDeclaration;
    @BindView(R.id.mine_user_center_bt_logout)
    Button mineUserCenterBtLogout;
    @BindView(R.id.mine_user_center_tv_user_pic)
    TextView mineUserCenterTvUserPic;
    private boolean isUpdateFinish;
    SharedPreferences sharedPreferences;
    Handler handler;
    AlertDialog alertDialog;
    private final int PHOTO_REQUEST_CAMERA = 11;
    private final int PHOTO_REQUEST_GALLERY = 22;
    private final int PHOTO_REQUEST_CUT = 33;
    private static final String FILENAME = "temp_photo.jpg";
    private File file;
    private Uri imageUri;
    private File cropFile;
    private Bitmap bitmap;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_user_center);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("user_message", MODE_PRIVATE);
        mineUserCenterTvUserPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str[] = {"查看当前头像", "拍摄头像", "从相册选取图片"};
                AlertDialog.Builder builder = new AlertDialog.Builder(userCenterActivity.this);
                builder.setItems(str, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (which == 0) {
                            if(!isUpdateFinish){
                                Toast.makeText(userCenterActivity.this,"数据更新中，请稍后",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Drawable drawable = UserInfo.stream2Drawable(sharedPreferences.getString("userPicture",""));
                                Bitmap bitmap = null;
                                if(drawable == null){
                                    bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.default_head_pic);
                                }
                                else{
                                    bitmap = ((BitmapDrawable)drawable).getBitmap();
                                }
                                Intent intent = new Intent(userCenterActivity.this,ShowOnePictureActivity.class);
                                intent.putExtra("bitmap",bitmap);
                                startActivity(intent);
                            }

                        }
                        else if (which == 1) {
                            if(ContextCompat.checkSelfPermission(userCenterActivity.this,Manifest.permission.CAMERA) !=PackageManager.PERMISSION_GRANTED){
                                ActivityCompat.requestPermissions(userCenterActivity.this,new String[]{Manifest.permission.CAMERA},1);
                            }
                            else{
                                openCamera();
                            }

                        } else if (which == 2) {
                            if(ContextCompat.checkSelfPermission(userCenterActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) !=PackageManager.PERMISSION_GRANTED){
                                ActivityCompat.requestPermissions(userCenterActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
                            }
                            else{
                                openAlbum();
                            }
                        }
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
            }
        });
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x0001) {
                    UserInfo userMessage = (UserInfo) msg.obj;
                    mineUserCenterEtQianming.setText(userMessage.getSignature());
                    mineUserCenterEtNickname.setText(userMessage.getName());
                    mineUserCenterEtSex.setText(userMessage.getSex());
                    mineUserCenterEtJob.setText(userMessage.getJob());
                    mineUserCenterEtDatingDeclaration.setText(userMessage.getIntroduction());
                }
                else if(msg.what == 5){
                    Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    finish();
                }
                else if(msg.what == 4){
                    Toast.makeText(getApplicationContext(),"保存失败",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    finish();
                }
            }
        };
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openCamera();
                }
                else{
                    Toast.makeText(userCenterActivity.this,"此功能需打开相机权限",Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if(grantResults.length>0&&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }
                else{
                    Toast.makeText(userCenterActivity.this,"此功能需打开读写内存权限",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void openCamera(){
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        file = new File(Environment.getExternalStorageDirectory(), FILENAME);
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(userCenterActivity.this, "com.example.wutai.wutaimoutain.fileprovider", file);
        } else {
            imageUri = Uri.fromFile(file);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
    }
    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,PHOTO_REQUEST_GALLERY);
    }

    @OnClick(R.id.mine_user_center_bt_logout)
    public void onViewClicked() {
        sharedPreferences.edit().clear().apply();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        isUpdateFinish = false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Simulate network access.
                String url = services.urlGetPersonInfo;
                OkHttpClient client = new OkHttpClient();
                MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
                requestBody.addFormDataPart("phone", sharedPreferences.getString("phone", ""));
                Request request = new Request.Builder().url(url).post(requestBody.build()).tag(userCenterActivity.this).build();
                client.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(TAG, "onResponse: " + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
                        UserInfo userInfo = new Gson().fromJson(result, UserInfo.class);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name", userInfo.getName());
                        editor.putString("sex", userInfo.getSex());
                        editor.putString("job", userInfo.getJob());
                        editor.putString("signature", userInfo.getSignature());
                        editor.putString("instruction", userInfo.getIntroduction());
                        editor.putString("userPicture",userInfo.getHead_pic_stream());
                        editor.apply();
                        Message message = handler.obtainMessage();
                        message.what = 1;
                        message.obj = userInfo;
                        handler.sendMessage(message);
                        isUpdateFinish = true;
                        Log.e(TAG, "onResponse: "+"获取信息成功" +"此时头像数据流的长度为："+userInfo.getHead_pic_stream().length());
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String signature = sharedPreferences.getString("signature", "");
        String name = sharedPreferences.getString("name", "");
        String sex = sharedPreferences.getString("sex", "");
        String job = sharedPreferences.getString("job", "");
        String instruction = sharedPreferences.getString("instruction", "");
        mineUserCenterEtQianming.setText(signature);
        mineUserCenterEtNickname.setText(name);
        mineUserCenterEtSex.setText(sex);
        mineUserCenterEtJob.setText(job);
        mineUserCenterEtDatingDeclaration.setText(instruction);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isUpdateFinish) {
            final SharedPreferences.Editor editor = sharedPreferences.edit();
            final String signature = mineUserCenterEtQianming.getText().toString();
            final String name = mineUserCenterEtNickname.getText().toString();
            final String sex = mineUserCenterEtSex.getText().toString();
            final String job = mineUserCenterEtJob.getText().toString();
            final String instruction = mineUserCenterEtDatingDeclaration.getText().toString();
            if (signature != null) {
                editor.putString("signature", signature);
            }
            if (name != null) {
                editor.putString("name", name);
            }
            if (sex != null) {
                editor.putString("sex", sex);
            }
            if (job != null) {
                editor.putString("job", job);
            }
            if (instruction != null) {
                editor.putString("instruction", instruction);
            }
            if(bitmap !=null){
                editor.putString("userPicture",UserInfo.bitmap2Stream(bitmap));
                Log.e(TAG, "onKeyDown: 有更改头像记录，更改后的头像数据流的大小为："+UserInfo.bitmap2Stream(bitmap).length() );
            }
            editor.apply();

            progressDialog = ProgressDialog.show(this,null,"数据保存中，请稍后",true,false);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    String url = services.urlSetPersonInfo;
                    OkHttpClient client = new OkHttpClient();
                    MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    requestBody.addFormDataPart("phone", sharedPreferences.getString("phone", ""));
                    requestBody.addFormDataPart("name", name);
                    requestBody.addFormDataPart("sex", sex);
                    requestBody.addFormDataPart("job", job);
                    requestBody.addFormDataPart("signature", signature);
                    requestBody.addFormDataPart("instruction", instruction);
                    requestBody.addFormDataPart("head_pic_stream",sharedPreferences.getString("userPicture",""));
                    Request request = new Request.Builder().url(url).post(requestBody.build()).tag(userCenterActivity.this).build();
                    client.newBuilder().readTimeout(50000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            handler.sendEmptyMessage(4);
                            Log.e(TAG, "onFailure: " + e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            handler.sendEmptyMessage(5);
                            Log.e(TAG, "onResponse: " + response.body().string());
                        }
                    });
                }
            }).start();
            return false;
        }
        else{
            return super.onKeyDown(keyCode, event);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "onActivityResult: "+resultCode );
        if (requestCode == PHOTO_REQUEST_CAMERA) {
            if (resultCode == RESULT_OK) {
                startUCrop(imageUri);
            }
        } else if (requestCode == PHOTO_REQUEST_GALLERY) {
            if(resultCode == RESULT_OK){
                if(Build.VERSION.SDK_INT>=19){
                    handleImageOnKitKat(data);
                }
                else{
                    handleImageBeforeKitKat(data);
                }
            }
            else{
                //没有从album获取到图片
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            if(resultCode == RESULT_OK){
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(UCrop.getOutput(data)));
                    saveBitmapToSharedPreferences(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            try {
                if(file.exists()){
                    file.delete();
                }
                if(cropFile.exists()){
                    cropFile.delete();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)){
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID+"="+id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }
            else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }
        }
        else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri,null);
        }
        else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        imageUri = FileProvider.getUriForFile(userCenterActivity.this, "com.example.wutai.wutaimoutain.fileprovider", new File(imagePath));
        startUCrop(imageUri);
    }
    private void handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        //String imagePath = getImagePath(uri,null);
        //imageUri = uri;
        startUCrop(uri);
    }

    private String getImagePath(Uri uri,String selection){
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path= cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }


    private void  saveBitmapToSharedPreferences(Bitmap bit) {
        // Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        //第一步:将Bitmap压缩至字节数组输出流ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String imageString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        //第三步:将String保持至SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userPicture", imageString);
        editor.apply();
    }
/*    public void getBitmapFromSharePreference(){
        SharedPreferences sharedPreferences1 = context.getSharedPreferences("Bitmap",Context.MODE_PRIVATE);
        String bt = sharedPreferences1.getString("bitmap", "");
        ByteArrayInputStream bis = new ByteArrayInputStream(android.util.Base64.encode(bt.getBytes(), android.util.Base64.DEFAULT));
        Drawable drawable =Drawable.createFromStream(bis,"");
    }*/

    private void startUCrop(Uri uri) {

        cropFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/test/crop_" + System.currentTimeMillis() + ".jpg");
        cropFile.getParentFile().mkdirs();
        try {
            cropFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "startUCrop: " + cropFile.getAbsolutePath() + "   " + cropFile.exists());
        Uri cropUri = Uri.fromFile(cropFile);
        UCrop uCrop = UCrop.of(uri, cropUri);
        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //是否隐藏底部容器，默认显示
        options.setHideBottomControls(false);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(this, R.color.colorPrimary));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(this, R.color.colorPrimary));
//        //是否能调整裁剪框
//        options.setShowCropFrame(true);
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        //UCrop配置
        //设置裁剪图片的宽高比，比如1:1  你自己随意
        uCrop.withAspectRatio(1, 1);
        uCrop.withMaxResultSize(250, 250);
        uCrop.withOptions(options);
        //uCrop.useSourceImageAspectRatio();
        uCrop.start(this, PHOTO_REQUEST_CUT);

    }
}
