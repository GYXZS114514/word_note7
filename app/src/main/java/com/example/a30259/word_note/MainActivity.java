package com.example.a30259.word_note;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    private String[] data = {"a","b","c","ddddd","ddddd","ddddd","ddddd","ddddd","ddddd","ddddd","ddddd","ddddd","ddddd","ddddd","ddddd"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final List<String> list = new ArrayList<String>() ;
        list.add("aaa");
        list.add("bbb");

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list);
        final ListView listview = (ListView)findViewById(R.id.list_view);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = list.get(i);
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.help:
                Toast.makeText(this,"你点击了帮助",Toast.LENGTH_LONG).show();
                break;
            case R.id.add:
                // Toast.makeText(this,"you have click remove",Toast.LENGTH_LONG).show();
                // break;
               // Intent intent = new Intent(MainActivity.this,huansuan.class);
              //  startActivity(intent);
                break;
            case R.id.delete:
                // Toast.makeText(this,"you have click remove",Toast.LENGTH_LONG).show();
                // break;
                // Intent intent = new Intent(MainActivity.this,huansuan.class);
                //  startActivity(intent);
                break;


            default:
                break;
        }
        return true;
    }


}

