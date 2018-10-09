package com.example.wutai.wutaimoutain.bussness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.wutai.wutaimoutain.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    @BindView(R.id.bussness_tv_ticket)
    TextView bussnessTvTicket;
    @BindView(R.id.bussness_tv_jushi)
    TextView bussnessTvJushi;
    @BindView(R.id.bussness_tv_part_time_job)
    TextView bussnessTvPartTi;
    @BindView(R.id.bussness_tv_food)
    TextView bussnessTvFood;
    @BindView(R.id.bussness_tv_hotel)
    TextView bussnessTvHotel;
    private int mCurrentState;
    GestureDetector gestureDetector;
    @BindView(R.id.bussness_view_flipper)
    ViewFlipper bussnessViewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bussness_main);
        ButterKnife.bind(this);
        gestureDetector = new GestureDetector(this);
        bussnessViewFlipper.setAutoStart(true);
        bussnessViewFlipper.setFlipInterval(3000);
        bussnessViewFlipper.setOnTouchListener(this);
        mCurrentState = 0;
        bussnessViewFlipper.setLongClickable(true);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e2.getX() - e1.getX() > 20) {//左移
            bussnessViewFlipper.setInAnimation(this, R.anim.slide_right_in);
            bussnessViewFlipper.setInAnimation(this, R.anim.slide_right_out);
            bussnessViewFlipper.showNext();
        } else {

            bussnessViewFlipper.setInAnimation(this, R.anim.slide_left_in);
            bussnessViewFlipper.setOutAnimation(this, R.anim.slide_left_out);
            bussnessViewFlipper.showPrevious();
        }
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @OnClick({R.id.bussness_tv_food, R.id.bussness_tv_hotel, R.id.bussness_tv_ticket, R.id.bussness_tv_jushi, R.id.bussness_tv_part_time_job})
    public void onViewClicked(View view) {
        String data = "";
        switch (view.getId()) {
            case R.id.bussness_tv_food:
                data = "food";
                break;
            case R.id.bussness_tv_hotel:
                data = "hotel";
                break;
            case R.id.bussness_tv_ticket:
                data = "ticket";
                break;
            case R.id.bussness_tv_jushi:
                data = "jushi";
                break;
            case R.id.bussness_tv_part_time_job:
                data = "part_time_job";
                break;
        }
        Intent intent = new Intent(MainActivity.this, Bussness.class);
        intent.putExtra("name", data);
        startActivity(intent);
    }
}
