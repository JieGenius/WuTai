package com.example.wutai.wutaimoutain.common;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.wutai.wutaimoutain.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class OnePicPreview extends AppCompatActivity {

    @BindView(R.id.com_one_pic_photo_view)
    PhotoView comOnePicPhotoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_pic_preview);
        ButterKnife.bind(this);

        int reId = getIntent().getIntExtra("reId",0);
        if(reId!=0){
            comOnePicPhotoView.setImageResource(reId);
        }
        else{
            Bitmap bitmap = (Bitmap) getIntent().getExtras().get("bitmap");
            comOnePicPhotoView.setImageBitmap(bitmap);
        }
        comOnePicPhotoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                finish();
            }

            @Override
            public void onOutsidePhotoTap() {
                finish();
            }
        });



    }
}
