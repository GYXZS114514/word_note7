package com.example.a30259.word_note;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a30259.word_note.MyDatabaseHelper;
import com.example.a30259.word_note.R;

import java.util.*;

public class MainActivity extends AppCompatActivity {
    final List<String> list = new ArrayList<String>();
    final List<String> list1 = new ArrayList<String>();
    final List<String> list2 = new ArrayList<String>();

    final List<String> result = new ArrayList<String>();
    final List<String> result1 = new ArrayList<String>();
    final List<String> result2 = new ArrayList<String>();



    public EditText editText1;
    public EditText editText2;
    public EditText editText3;
    public EditText editText_sear;
    public int a;//position
    public int sign = 0;//sign of search
    private MyDatabaseHelper dbhelper;
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.add);
            dbHelper = new MyDatabaseHelper(this,"person8.db",null,1);
            editText1 = (EditText)this.findViewById(R.id.yw);
            editText2 = (EditText)this.findViewById(R.id.zw);
            //editText1= (EditText)rightFragment.getActivity().findViewById(R.id.yw);
            editText3 = (EditText)this.findViewById(R.id.lj);




            Button button = (Button) findViewById(R.id.add);
            dbHelper = new MyDatabaseHelper(this,"person8.db",null,1);

            //editText = (EditText) this.findViewById(R.id.edittext);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();

                    String zw = editText2.getText()+"";
                    String lj = editText3.getText()+"";
                    String yw = editText1.getText()+"";


                    values.put("yw",yw);
                    values.put("zw",zw);
                    values.put("lj",lj);
                    db.insert("word",null,values);
                    Toast.makeText(MainActivity.this,"Success,please check after restart app!",Toast.LENGTH_SHORT).show();

                    list.add(yw);
                    list1.add(zw);
                    list2.add(lj);


                }
            });





























        }else{

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  final List<String> list = new ArrayList<String>();
       // final List<String> list1 = new ArrayList<String>();
      //  final List<String> list2 = new ArrayList<String>();
            ArrayAdapter<String> adapter= new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list);
            final ListView listview = (ListView)findViewById(R.id.list_view);
            listview.setAdapter(adapter);


        RightFragment rightFragment=(RightFragment)getSupportFragmentManager().findFragmentById(R.id.right_fragment);

            LeftFragment leftFragment=(LeftFragment)getSupportFragmentManager().findFragmentById(R.id.left_fragment);
            editText_sear = (EditText)leftFragment.getActivity().findViewById(R.id.search);
        editText3 = (EditText) this.findViewById(R.id.lj);
        editText2 = (EditText)rightFragment.getActivity().findViewById(R.id.zw);
       // editText2 = (EditText) this.findViewById(R.id.zw);
        editText1 = (EditText) this.findViewById(R.id.yw);
        dbhelper = new MyDatabaseHelper(MainActivity.this,"person8.db",null,1);

       SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("word",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String yw = cursor.getString(cursor.getColumnIndex("yw"));
                list.add(yw);
                String zw = cursor.getString(cursor.getColumnIndex("zw"));
                list1.add(zw);
                String lj = cursor.getString(cursor.getColumnIndex("lj"));
                list2.add(lj);

            }while(cursor.moveToNext());
            cursor.close();

        }
        ArrayAdapter<String> adapter1= new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list);
        final ListView listview1 = (ListView)findViewById(R.id.list_view);
        listview.setAdapter(adapter1);


        Button button = (Button) findViewById(R.id.update);
        Button button2 = (Button) findViewById(R.id.delete);
            Button button3 = (Button) findViewById(R.id.search1);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               SQLiteDatabase db=dbhelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                String zw = editText2.getText()+"";
                String lj = editText3.getText()+"";
                values.put("zw",zw);
                values.put("lj",lj);
                String yw = list.get(a)+"";
                db.update("word",values,"yw = ?",new String[]{yw});
                Toast.makeText(MainActivity.this,"success!Please check after restart app!",Toast.LENGTH_SHORT).show();

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SQLiteDatabase db=dbhelper.getWritableDatabase();
                String yw = list.get(a)+"";
                db.delete("word","yw = ?",new String[] {yw});
                list.remove(a);
                list1.remove(a);
                list2.remove(a);
            }
        });
            button3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    result.clear();
                    result1.clear();
                    result2.clear();
                    sign = 1;
                    String input = editText_sear.getText()+"";
                    int a = list.size();
                    for(int i =0,j = 0;i<a;i++){
                        if(bfFind(list.get(j)+"",input, 0) == -1){
                                j++;continue;
                        }else{
                            result.add(list.get(j));
                            result1.add(list1.get(j));
                            result2.add(list2.get(j));
                            j++;
                        }
                    }

                    ArrayAdapter<String> adapter1= new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,result);

                    final ListView listview1 = (ListView)findViewById(R.id.list_view);
                    listview.setAdapter(adapter1);




                }
            });

            Button f5 = (Button) findViewById(R.id.f5);


            f5.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ArrayAdapter<String> adapter1= new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list);
                    final ListView listview1 = (ListView)findViewById(R.id.list_view);
                    listview.setAdapter(adapter1);
                    sign = 0;

                }
            });









        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(sign == 0){
                String s = list.get(i);
                String x = list1.get(i);
                String y = list2.get(i);
                a=i;
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
                editText2.setText(x+"");
                editText3.setText(y+"");
                } else if(sign == 1){
                    String m = result1.get(i);
                    String n = result2.get(i);
                    editText2.setText(m+"");
                    editText3.setText(n+"");


                }




            }
        });
    }
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


               // Intent intent = new Intent(MainActivity.this,ADDword.class);
               // startActivity(intent);
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

    public static int bfFind(String S, String T, int pos) {
        char[] arr1 = S.toCharArray();
        char[] arr2 = T.toCharArray();
        int i = pos;
        int j = 0;
        while(i < arr1.length && j < arr2.length) {
            if(arr1[i] == arr2[j]) {
                i++;
                j++;
            }
            else {
                i = i - j + 1;
                j = 0;
            }
        }
        if(j == arr2.length) return i - j;
        else return -1;
    }




}

