package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] dataName = {"Cut Nyak Dien","Ki Hajar Dewantara","Moh Yamin","Patimura","R A Kartini","Sukarno"};
    private String[] Name;
    private String[] dataDesc;
    private TypedArray dataPhoto;

    private ArrayList<Hero> heroes;


    private HeroAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView =  findViewById(R.id.lv_list);
        adapter = new HeroAdapter(this);
        listView.setAdapter(adapter);
        prepare();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,heroes.get(i).getName(),Toast.LENGTH_SHORT).show();
            }
        });

            //List biasa
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,android.R.id.text1,dataName);
//
//        listView.setAdapter(adapter);
    }

    private void addItem(){
        heroes = new ArrayList<>();

        for(int i = 0; i< Name.length ; i++){
            Hero hero = new Hero();
            hero.setPhoto(dataPhoto.getResourceId(i,-1));
            hero.setName(Name[i]);
            hero.setDescription(dataDesc[i]);
            heroes.add(hero);
        }
        adapter.setHeroes(heroes);
    }


    private void prepare(){
        Name = getResources().getStringArray(R.array.data_name);
        dataDesc = getResources().getStringArray(R.array.data_description);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
    }
}
