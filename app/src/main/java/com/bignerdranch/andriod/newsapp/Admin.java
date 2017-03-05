package com.bignerdranch.andriod.newsapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        TextView txtname = (TextView) findViewById(R.id.txt_success_name);
        Button _btnlogout = (Button) findViewById(R.id.btn_logout);
        Button _insert = (Button) findViewById(R.id.btn_insert);
        Intent intent = getIntent();

        String loginName = intent.getStringExtra("user");
        txtname.setText("Welcome admin, " + loginName);



        _btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Admin.this);
                builder.setTitle("Info");
                builder.setMessage("Do you want to logout ??");
                builder.setPositiveButton("Take me away!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(Admin.this, LogInActivity.class);
                        startActivity(intent);

                        finish();
                    }
                });
                builder.setNegativeButton("Not now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        _insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Admin.this,insertcontent.class);
                startActivity(intent);
            }
        });
    }
}
