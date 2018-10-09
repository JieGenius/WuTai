package com.example.wutai.wutaimoutain.allsimiaos;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MoveDBFile {
    private static String dbPath = "data/data/com.example.wutai.wutaimoutain/wutaishan.db";
    private static String pathSrc = "data/data/com.example.wutai.wutaimoutain";

    public static boolean move(Context context){
        File dbFile = new File(dbPath);
        if(dbFile.exists()){
            Log.e("xxxx","文件存在");
            return true;
        }
        else{
            File path = new File(pathSrc);
            if(!path.exists()){
                path.mkdir();
            }
            try {
                AssetManager am = context.getAssets();
                InputStream inputStream = am.open("wutaishan.db");
                FileOutputStream outputStream = new FileOutputStream(dbPath);
                byte[] buffer = new byte[1024];
                int count=0;
                while((count =inputStream.read(buffer))>0){
                    outputStream.write(buffer,0,count);
                }
                inputStream.close();
                outputStream.flush();
                outputStream.close();
                Log.e("xxxx","移动成功");
                return true;
            }
            catch (IOException e){
                e.printStackTrace();
                return false;
            }
        }
    }

}
