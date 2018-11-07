package com.example.wutai.wutaimoutain.Talk;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wutai.wutaimoutain.MainActivity;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.common.showPhotoListActivity;
import com.example.wutai.wutaimoutain.mine.UserInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TalkAdapter extends RecyclerView.Adapter<TalkAdapter.ViewHold> {
    private static final String TAG = "TalkAdapter";
    private Context context;
    private int clickedPosition;
    public List<AllTalk.TalkArrBean> list;
    private LinearLayout sendCommentLayout;
    private Activity parentActivity;
    private SharedPreferences sharedPreferences;
    public TalkAdapter(List<AllTalk.TalkArrBean> list ,Activity activity,LinearLayout sendCommentLayout) {
        this.list = list;
        this.context = activity;
        this.sendCommentLayout = sendCommentLayout;
        this.parentActivity = activity;
        sharedPreferences = activity.getSharedPreferences("user_message", Context.MODE_PRIVATE);

    }

    @NonNull
    @Override
    public ViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.talk_main,parent,false);
        ViewHold hold = new ViewHold(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHold holder, final int position) {
        holder.userName.setText(list.get(position).getUserName());
        holder.talkTime.setText(convertTime(list.get(position).getSendTime()));
        holder.talkContent.setText(list.get(position).getContent());
        Drawable drawable = null;
        if((drawable = UserInfo.stream2Drawable(list.get(position).getUserPic()))!=null){
            holder.userPic.setImageDrawable(drawable);
        }
        else{
            holder.userPic.setImageResource(R.drawable.default_head_pic);
        }
        final List<String> tmp=list.get(position).getPicArr();
        GridViewAdapter adapter = new GridViewAdapter(tmp);
        if(tmp.size()<=3){
            holder.talkAllPic.setNumColumns(tmp.size());
        }
        else if(tmp.size() ==4){
            holder.talkAllPic.setNumColumns(2);
        }
        else{
            holder.talkAllPic.setNumColumns(3);
        }

        holder.talkAllPic.setAdapter(adapter);
        holder.talkAllPic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posi, long id) {
                showPhotoListActivity.newInstance(parent.getContext(),posi,list.get(position).getPicArr());
            }
        });
        holder.comments.setLayoutManager(new LinearLayoutManager(context));
        holder.comments.setAdapter(new CommentsAdapter(context,list.get(position).getCommArr()));
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedPreferences.getBoolean("isLogin",false)){
                    if(sendCommentLayout.getVisibility() == View.GONE){
                        clickedPosition = position;
                        sendCommentLayout.setVisibility(View.VISIBLE);
                        EditText editText = sendCommentLayout.findViewById(R.id.talk_et_comment_content);
                        editText.setFocusable(true);
                        editText.setFocusableInTouchMode(true);
                        editText.requestFocus();
                        InputMethodManager imm = (InputMethodManager) parentActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(editText,0);
                    }
                }
                else{
                    Toast.makeText(parentActivity,"请先登录",Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedPreferences.getBoolean("isLogin",false)){
                    if(sendCommentLayout.getVisibility() == View.GONE){
                        clickedPosition = position;
                        sendCommentLayout.setVisibility(View.VISIBLE);
                        EditText editText = sendCommentLayout.findViewById(R.id.talk_et_comment_content);
                        editText.setFocusable(true);
                        editText.setFocusableInTouchMode(true);
                        editText.requestFocus();
                        InputMethodManager imm = (InputMethodManager) parentActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(editText,0);
                    }
                }
                else{
                    Toast.makeText(parentActivity,"请先登录",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHold extends RecyclerView.ViewHolder {
        CircleImageView userPic;
        TextView userName;
        TextView talkTime;
        TextView talkContent;
        GridView talkAllPic;
        ImageView dianzan;
        ImageButton comment;
        ImageButton share;
        TextView commentButton;
        RecyclerView comments;
        public ViewHold(final View itemView) {
            super(itemView);
            userPic =itemView.findViewById(R.id.talk_user_pic_civ);
            userName=itemView.findViewById(R.id.talk_user_name_tv);
            talkTime=itemView.findViewById(R.id.talk_time_tv);
            talkContent=itemView.findViewById(R.id.talk_content_tv);
            talkAllPic=itemView.findViewById(R.id.talk_all_pic_grid_view);
            comments=itemView.findViewById(R.id.talk_all_content_recy_view);
            commentButton=itemView.findViewById(R.id.talk_content_bt);
            dianzan = itemView.findViewById(R.id.talk_bt_dianzan);
            comment = itemView.findViewById(R.id.talk_bt_review);
            share = itemView.findViewById(R.id.talk_bt_share);
            dianzan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dianzan.isSelected()){
                        dianzan.setSelected(false);
                        dianzan.setImageResource(R.drawable.ic_dianzan_not);
                    }
                    else{
                        dianzan.setSelected(true);
                        dianzan.setImageResource(R.drawable.ic_dianzan);
                    }

                }
            });

        }
    }
    public static String convertTime(String time){
        Calendar calendar =Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int mouth = calendar.get(Calendar.MONTH)+1;
        int day =calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        String tmp[]=time.split("[-: ]");
        String s="";
        if(year>Integer.parseInt(tmp[0])){
            return time.substring(0,10);
        }
        if(mouth>Integer.parseInt(tmp[1])){
            return time.substring(5,10);
        }
        if(day>Integer.parseInt(tmp[2])){
            return time.substring(5,10);
        }
        if(hour == Integer.parseInt(tmp[3])&&(minute-Integer.parseInt(tmp[4])<=5)){
            return "刚刚";
        }
        return tmp[3]+":"+tmp[4];
    }
    public int getClickedPosition(){
        return clickedPosition;
    }

}
