package com.example.wutai.wutaimoutain.mine;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wutai.wutaimoutain.R;

import jp.wasabeef.richeditor.RichEditor;

public class YoujiEditor extends AppCompatActivity {
    private RichEditor richEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_youji_editor);
        richEditor = findViewById(R.id.mine_youji_rich_editor);
        richEditor.setEditorHeight(200);
        richEditor.setEditorFontSize(22);
        richEditor.setEditorFontColor(Color.RED);
        richEditor.setPadding(10, 10, 10, 10);
        richEditor.setPlaceholder("Insert text here...");
    }
}
