package com.bignerdranch.andriod.newsapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class viewcontent extends AppCompatActivity {

    SQLiteDBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcontent);

        ListView listView = (ListView) findViewById(R.id.listview);
        mydb = new SQLiteDBHelper(this);

        final ArrayList<String> thelist = new ArrayList<>();
        Cursor data = mydb.getListContent();
        if(data.getCount() ==0)
        {
            Toast.makeText(viewcontent.this,"Nothing in the db",Toast.LENGTH_SHORT).show();
        }else
        {
            while(data.moveToNext())
            {
                thelist.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,thelist);
                listView.setAdapter(listAdapter);

            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id)
          {

              Toast.makeText(viewcontent.this,"U clicked:"+ thelist.get(position),Toast.LENGTH_SHORT).show();
              Intent intent = new Intent(viewcontent.this,fullcontent.class);
              intent.putExtra("itemid",thelist.get(position));
              startActivity(intent);
          }

        }
        );

    }

}
