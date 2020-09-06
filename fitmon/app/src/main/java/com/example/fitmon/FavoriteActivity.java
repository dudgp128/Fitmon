package com.example.fitmon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity{


   // ArrayList<String> favorTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        String f = loadFavorite();
        String FavorList[] = f.split("\n");

        final ArrayList<String> favorLink = new ArrayList<>();
        final ArrayList<String> favorTitle = new ArrayList<>();

        for(int i=0; i<FavorList.length; i++){
            String tmp[] = FavorList[i].split("\\$");

            if(!tmp[0].equals("")){
                favorLink.add(tmp[0]);
                favorTitle.add(tmp[1]);
                //Toast.makeText(getApplicationContext(), tmp[1], Toast.LENGTH_LONG).show();
            }
        }

        ListView list = (ListView) findViewById(R.id.listV);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, favorTitle);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(favorLink.get(arg2)));
                    startActivity(intent);
                }
            });

    }

    public String loadFavorite(){
        String s = "";
        try{
            InputStream in = openFileInput("favoriteExercise.txt");
            byte[] txt = new byte[in.available()];
            in.read(txt);
            s = new String(txt);
            in.close();
        }catch (IOException e) { }
        return s;
    }

}
