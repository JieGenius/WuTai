package com.example.wutai.wutaimoutain.ShareWithOther;
import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import com.example.wutai.wutaimoutain.Adapter.UpLoadImgAdpter;
import com.example.wutai.wutaimoutain.BaiDuMap.GetPositionActivity;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Utils.MyLogUtils;
import com.example.wutai.wutaimoutain.Utils.Uri2PathUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShareWithiOhterHomeActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;

    private File outputImage;
    private String imagePath;
    private Uri mUri,imageUri;
    private static final int MY_PERMISSION_CAMERAL = 1;
    private GridView gridView1;
    private UpLoadImgAdpter adpter;
    private Button postShare,cancelPost;
    String[] permission = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private ArrayList<String> list;
    private PhotoPopwindow mPhotoPopwindow;
    List<String> permissionList = new ArrayList<>();

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case 110:
                    if (ContextCompat.checkSelfPermission(ShareWithiOhterHomeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    }
                    if (ContextCompat.checkSelfPermission(ShareWithiOhterHomeActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        permissionList.add(Manifest.permission.CAMERA);
                    }
                    if (ContextCompat.checkSelfPermission(ShareWithiOhterHomeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                    }
                    if (!permissionList.isEmpty()) {
                        String [] permissions = permissionList.toArray(new String[permissionList.size()]);
                        ActivityCompat.requestPermissions(ShareWithiOhterHomeActivity.this, permissions, 1);
                    } else {
                        takePhoto();
                    }
                    MyLogUtils.e("点击了拍照");
//                    Toast.makeText(ShareWithiOhterHomeActivity.this,"点击了拍照",Toast.LENGTH_SHORT).show();
                    break;
                case 111:
                    if (ContextCompat.checkSelfPermission(ShareWithiOhterHomeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    }
                    if (ContextCompat.checkSelfPermission(ShareWithiOhterHomeActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        permissionList.add(Manifest.permission.CAMERA);
                    }
                    if (ContextCompat.checkSelfPermission(ShareWithiOhterHomeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                    }
                    if (!permissionList.isEmpty()) {
                        String [] permissions = permissionList.toArray(new String[permissionList.size()]);
                        ActivityCompat.requestPermissions(ShareWithiOhterHomeActivity.this, permissions, 1);
                    } else {
                        openAlbum();
                    }
                    MyLogUtils.e("点解了从相册选择照片");
                default:
                    break;

            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_withi_ohter_home);
        initView();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.post_share:
                MyLogUtils.e("点击了PostShare");

                break;
            case R.id.cancel_post:
                MyLogUtils.e("点击了cancelPost");
                //这里不能传入上下文 只能穿Activity 否则 报错
                dialog(ShareWithiOhterHomeActivity.this);
                break;
            default:
                break;

        }
    }

    public void initView(){
        postShare = (Button)findViewById(R.id.post_share);
        postShare.setOnClickListener(this);
        cancelPost = (Button)findViewById(R.id.cancel_post);
        cancelPost.setOnClickListener(this);


        list = new ArrayList<>();
        gridView1 = (GridView)findViewById(R.id.gridView1);
        mPhotoPopwindow = new PhotoPopwindow(ShareWithiOhterHomeActivity.this, mHandler);
        adpter =  new UpLoadImgAdpter(ShareWithiOhterHomeActivity.this,list);
        gridView1.setAdapter(adpter);
        gridView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(position != parent.getChildCount()-1){
                    dialog(ShareWithiOhterHomeActivity.this,position);
                }
                else {

                }
                return true;
            }
        });

        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position != parent.getChildCount()-1){
                    //预览功能

                }
                else {
                    if(mPhotoPopwindow != null){
                        if(!mPhotoPopwindow.isShowing()){
                            mPhotoPopwindow.showAtLocation(findViewById(R.id.gridView1),
                                    Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
                        }else{
                            mPhotoPopwindow.dismiss();
                        }
                    }
                }
            }
        });
    }

    public void takePhoto(){
        // 创建File对象，用于存储拍照后的图片
        File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT < 24) {
            imageUri = Uri.fromFile(outputImage);
        } else {
            imageUri = FileProvider.getUriForFile(ShareWithiOhterHomeActivity.this, "com.example.wutai.wutaimoutain.fileprovider", outputImage);
        }
        // 启动相机程序
        MyLogUtils.e("启动前的imageUri：" + imageUri);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO);
        MyLogUtils.e("图片路径 :" + getExternalCacheDir()+"/output_image.jpg");
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
//        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        startActivityForResult(intent, CHOOSE_PHOTO); // 打开相册
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                //申请多个权限
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    takePhoto();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
                //申请单个权限
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    openAlbum();
//                } else {
//                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
//                }
//                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                MyLogUtils.e("请求拍照ok");
                if (resultCode == RESULT_OK) {
                    try {
                        MyLogUtils.e("OnActivityResult 刚进去得到的 imgUri ：" + imagePath);
                        // 将拍摄的照片显示出来
                        String imagePath = getExternalCacheDir() + "/output_image.jpg";
                        MyLogUtils.e("OnActivityResult 之后得到的 imagePath ：" + imagePath);
                        if(imagePath == null || imagePath.equals("")){
                            MyLogUtils.e("OnActivityResult 路径为空 ");
                        }
                        else {
                            addImage(imagePath);
                            MyLogUtils.e("OnActivityResult 路径 : " + imagePath);
                        }
//                        getImagePath()
//                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
//                        .setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    // 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4及以上系统使用这个方法处理图片
                        String imagePath = null;
                        imagePath = handleImageOnKitKat(data);
                        addImage(imagePath);
//                        Uri uri = data.getData();
//                        MyLogUtils.e("选择图片的 : imageUri:" + uri);
//                        imagePath = Uri2PathUtils.getFilePathByUri(getApplicationContext(),uri);
                        MyLogUtils.e("imagePath:" + imagePath);
                    } else {
                        // 4.4以下系统使用这个方法处理图片
                    }
                }
                break;
            default:
                break;
        }
    }


    public void addImage(String pathImage){
        list.add(pathImage);
        adpter.notifyDataSetChanged();
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private String handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        MyLogUtils.e("handleImageOnKitKat: uri is " + uri);
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        MyLogUtils.e("郭林得到的 imagePaht：" + imagePath);
//        displayImage(imagePath); // 根据图片路径显示图片
        return imagePath;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {

//            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
//            .setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    protected void dialog(Context context, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确认取消发布?");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                list.remove(position);
                adpter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    protected void dialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.Theme_AppCompat_Light_Dialog_Alert);
        builder.setMessage("确认取消发布?");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}
