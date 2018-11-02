package com.example.wutai.wutaimoutain.common;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.wutai.wutaimoutain.R;

public class ShowOnePictureActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_a_picture_layout);
        Bitmap bitmap = (Bitmap) getIntent().getExtras().get("bitmap");
        imageView = findViewById(R.id.show_a_picture_activity_iv);
        imageView.setImageBitmap(bitmap);
    }
}
