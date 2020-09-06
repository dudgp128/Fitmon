package com.example.fitmon;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Scanner;


/**
 * A simple {@link Fragment} subclass.
 */
public class hard2 extends Fragment {

    int linkID[];
    int favorID[];
    int unfavorID[];
    Button linkButton[] = new Button[15];
    ImageButton favorButton[] = new ImageButton[15];
    ImageButton unfavorButton[] = new ImageButton[15];
    String link[] = new String[15];
    String linkTitle[] = new String[15];

    public hard2() {
        // Required empty public constructor
    }


    public String loadFavorite(){
        String s = "";
        try{
            InputStream in = getActivity().openFileInput("favoriteExercise.txt");
            byte[] txt = new byte[in.available()];
            in.read(txt);
            s = new String(txt);
            in.close();
        }catch (IOException e) { }
        return s;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_hard2, container, false);

        linkID = new int[]{R.id.go6, R.id.go7, R.id.go8, R.id.go9, R.id.go10};

        unfavorID = new int[]{R.id.m_1, R.id.m_2, R.id.m_3, R.id.m_4, R.id.m_5};

        favorID = new int[]{R.id.like_m_1, R.id.like_m_2, R.id.like_m_3, R.id.like_m_4, R.id.like_m_5};

        // 링크
        InputStream is = getResources().openRawResource(R.raw.hard);
        InputStream is_text = getResources().openRawResource(R.raw.hard_title);
        Scanner s = new Scanner(is,"UTF-8");
        int n = 0;

        while (s.hasNextLine()) {
            link[n] = s.nextLine();
            n++;
        }
        n=0;
        s = new Scanner(is_text,"UTF-8");
        while (s.hasNextLine()) {
            linkTitle[n] = s.nextLine();
            n++;
        }

        // 링크 달기
        for(int i=0; i< linkID.length; i++)
        {
            final int index;
            final int linkindex;
            index = i;
            linkindex = 5+i;

            linkButton[index] = (Button)view.findViewById(linkID[index]);
            linkButton[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), webActivity.class);
                    intent.putExtra("goLink", link[linkindex]);

                    Calendar now = Calendar.getInstance();      //현재 날짜와 시간정보 저장
                    int year = now.get(Calendar.YEAR);          //현재 년도
                    int month = now.get(Calendar.MONTH) + 1;    //현재 달          //month는 0 to 11
                    int day = now.get(Calendar.DAY_OF_MONTH);   //현재 날짜 저장
                    int hour = now.get(Calendar.HOUR_OF_DAY);
                    int min = now.get(Calendar.MINUTE);
                    int sec = now.get(Calendar.SECOND);

                    intent.putExtra("mode", 2);
                    intent.putExtra("year", year);
                    intent.putExtra("month", month);
                    intent.putExtra("day", day);
                    intent.putExtra("hour", hour);
                    intent.putExtra("min", min);
                    intent.putExtra("sec", sec);

                    startActivityForResult(intent, 0);
                }
            });
        }

        // 찜 해제
        for(int i=0; i< unfavorID.length; i++) {
            final int index;
            final int linkindex;
            index = i;
            linkindex = 10+i;

            favorButton[index] = (ImageButton) view.findViewById(favorID[index]);
            favorButton[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String f = loadFavorite();

                    try {
                        FileOutputStream out = getActivity().openFileOutput("favoriteExercise.txt", Context.MODE_PRIVATE);
                        f = f.replace(link[linkindex]+"$" + linkTitle[linkindex] + "\n", "");
                        //Toast.makeText(getApplicationContext(),f,Toast.LENGTH_SHORT).show();
                        out.write(f.getBytes());
                        out.close();
                    } catch (IOException e) {
                    }

                    favorButton[index].setVisibility(View.INVISIBLE);
                    unfavorButton[index].setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity().getApplicationContext(), "찜이 해제되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // 찜하기
        for(int i=0; i< unfavorID.length; i++)
        {
            final int index;
            final int linkindex;
            index = i;
            linkindex = 5+i;

            unfavorButton[index] = (ImageButton)view.findViewById(unfavorID[index]);

            if(loadFavorite().contains(link[linkindex])){
                unfavorButton[index].setVisibility(View.INVISIBLE);
                favorButton[index].setVisibility(View.VISIBLE);
            }

            unfavorButton[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String f = loadFavorite();

                    try{
                        FileOutputStream out = getActivity().openFileOutput("favoriteExercise.txt", Context.MODE_PRIVATE);
                        f += link[linkindex]+"$" + linkTitle[linkindex] + "\n";
                        //Toast.makeText(getApplicationContext(),f,Toast.LENGTH_SHORT).show();
                        out.write(f.getBytes());
                        out.close();
                    }catch (IOException e){}

                    unfavorButton[index].setVisibility(View.INVISIBLE);
                    favorButton[index].setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity().getApplicationContext(),"찜 되었습니다",Toast.LENGTH_SHORT).show();

                }
            });
        }


        return view;
    }


}
