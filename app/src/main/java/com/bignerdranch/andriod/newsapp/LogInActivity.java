package com.bignerdranch.andriod.newsapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase db;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //To hide AppBar for fullscreen.
        ActionBar ab = getSupportActionBar();
        ab.hide();

        //Referencing UserEmail, Password EditText and TextView for SignUp Now
        final EditText _txtid = (EditText) findViewById(R.id.txt_id);
        final EditText _txtpass = (EditText) findViewById(R.id.txt_pass);
        Button _btnlogin = (Button) findViewById(R.id.btn_login);
        TextView _btnreg = (TextView) findViewById(R.id.btn_register);

        //Opening SQLite Pipeline
        dbhelper = new SQLiteDBHelper(this);
        db = dbhelper.getReadableDatabase();

        _btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = _txtid.getText().toString();
                String pass = _txtpass.getText().toString();

                cursor = db.rawQuery("SELECT * FROM "+SQLiteDBHelper.TABLE_NAME+" WHERE "+SQLiteDBHelper.COLUMN_USER+"=? AND "+SQLiteDBHelper.COLUMN_PASSWORD+"=?",new String[] {id,pass});
                if (cursor != null) {
                    if(cursor.getCount() > 0) {

                        cursor.moveToFirst();
                        //Retrieving User after successfull login and passing to LoginSucessActivity
                        String _username = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_USER));
                        if (_username.equals("admin"))
                        {
                            Toast.makeText(LogInActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LogInActivity.this,Admin.class);
                            intent.putExtra("user",_username);
                            startActivity(intent);
                            finish();
                        }
                         else
                        {
                        Toast.makeText(LogInActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LogInActivity.this,LoginSuccessActivity.class);
                        intent.putExtra("user",_username);
                        startActivity(intent);
                        finish();
                        }

                    }
                    else {

                        //I am showing Alert Dialog Box here for alerting user about wrong credentials
                        final AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this);
                        builder.setTitle("Alert");
                        builder.setMessage("Username or Password is wrong.");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();

                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                        //-------Alert Dialog Code Snippet End Here
                    }
                    }
            }
        });
            // Intent For Opening RegisterAccountActivity
            _btnreg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(LogInActivity.this,RegisterAccountActivity.class);
                    startActivity(intent);
                }
            });

        }
}
