package com.bignerdranch.andriod.newsapp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterAccountActivity extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        openHelper = new SQLiteDBHelper(this);
        final EditText _userid = (EditText) findViewById(R.id.reg_id);
        final EditText _userpass = (EditText) findViewById(R.id.reg_pass);
        Button _btnreg = (Button) findViewById(R.id.btn_reg);

        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = openHelper.getWritableDatabase();
                String userid = _userid.getText().toString();
                String pass = _userpass.getText().toString();

                InsertData(userid, pass);
                final AlertDialog.Builder builder = new AlertDialog.Builder(RegisterAccountActivity.this);
                builder.setTitle("Information");
                builder.setMessage("Your Account is Successfully Created.");
                builder.setPositiveButton("Okey", new DialogInterface.OnClickListener() {
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
    public void InsertData(String userid, String pass) {

        ContentValues values = new ContentValues();
        values.put(SQLiteDBHelper.COLUMN_USER,userid);
        values.put(SQLiteDBHelper.COLUMN_PASSWORD,pass);
        long id = db.insert(SQLiteDBHelper.TABLE_NAME,null,values);

    }

}

