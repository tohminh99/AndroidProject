package com.bignerdranch.andriod.newsapp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class insertcontent extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertcontent);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        openHelper = new SQLiteDBHelper(this);

        final EditText _title = (EditText) findViewById(R.id.edt_title);
        final EditText _content = (EditText) findViewById(R.id.edt_content);
        final EditText _writer = (EditText) findViewById(R.id.edt_writer);
        Button _btninsert = (Button) findViewById(R.id.btn_insert);

        _btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                db = openHelper.getWritableDatabase();
                String title = _title.getText().toString();
                String content = _content.getText().toString();
                String writer = _writer.getText().toString();

                InsertData(title,content,writer);
                final AlertDialog.Builder builder = new AlertDialog.Builder(insertcontent.this);
                builder.setTitle("Information");
                builder.setMessage("Content successfully insert.");
                builder.setPositiveButton("Okey", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //Finishing the dialog and removing Activity from stack.
                        dialogInterface.dismiss();
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });


    }
    public void InsertData(String title, String content,String writer) {

        ContentValues values = new ContentValues();
        values.put(SQLiteDBHelper.COLUMN2_TITLE,title);
        values.put(SQLiteDBHelper.COLUMN2_CONTENT,content);
        values.put(SQLiteDBHelper.COLUMN2_WRITER,writer);
        long id = db.insert(SQLiteDBHelper.TABLE2_NAME,null,values);

    }

}
