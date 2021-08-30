package com.example.myexpensemanager;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListContents extends AppCompatActivity {
    DatabaseHelper dbh;
    ArrayList<User> userList;
    ListView listView;
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);

        dbh = new DatabaseHelper(this);

        userList = new ArrayList<>();
        Cursor data = dbh.viewData();
        int numRows = data.getCount();
        if (numRows == -1){
            Toast.makeText(ListContents.this, "Data not Inserted", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()){
                user = new User (data.getString(1),data.getString(2),data.getString(3),data.getString(4));
                userList.add(user);
            }
            Column_ListAdapter adapter = new Column_ListAdapter (this, R.layout.list_adapter_view,userList);
            listView = (ListView) findViewById(R.id.ListView);
            listView.setAdapter((ListAdapter) adapter);
        }
    }
}
