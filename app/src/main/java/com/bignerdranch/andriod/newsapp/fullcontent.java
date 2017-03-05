package com.bignerdranch.andriod.newsapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class fullcontent extends AppCompatActivity {
    SQLiteOpenHelper dbhelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullcontent);

        dbhelper = new SQLiteDBHelper(this);
        db = dbhelper.getReadableDatabase();

        TextView title = (TextView)findViewById(R.id.viewid);
        TextView content = (TextView)findViewById(R.id.viewcontent);
        TextView writer = (TextView)findViewById(R.id.viewwriter);
        Intent intent = getIntent();
        String idname = intent.getExtras().getString("itemid");
        cursor = db.rawQuery("SELECT * FROM "+SQLiteDBHelper.TABLE2_NAME+" WHERE "+SQLiteDBHelper.COLUMN2_TITLE+"=?",new String[]{idname});
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                String _content = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN2_CONTENT));
                String _writer = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN2_WRITER));

                title.setText("Title:" + idname);
                content.setText(_content);
                writer.setText("Writer:"+ _writer);
            } else {
                Toast.makeText(fullcontent.this, "Something Wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
