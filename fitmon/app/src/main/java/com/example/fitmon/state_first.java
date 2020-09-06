package com.example.fitmon;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.telecom.TelecomManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class state_first extends Fragment {

    ImageButton share;

    public state_first() {
        // Required empty public constructor
    }

    SharedPreferences calDate;
    SharedPreferences.Editor calDateEdit;
    Button goal;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    Date currenTime = Calendar.getInstance().getTime();
    SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.KOREA);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.KOREA);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);

    String curYear = yearFormat.format(currenTime);
    String curMonth = monthFormat.format(currenTime);
    String curDay = dayFormat.format(currenTime);

    int curYearInt = Integer.parseInt(curYear);
    int curMonthInt = Integer.parseInt(curMonth);
    int curDayInt = Integer.parseInt(curDay);

    SharedPreferences record;
    SharedPreferences.Editor recordEdit;
    int wholeTime, upperTime, lowerTime, finYear, finMonth, finDay;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_state_first, container, false);


        TextView wholeT = (TextView) view.findViewById(R.id.wholeT);
        TextView upperT = (TextView) view.findViewById(R.id.upperT);
        TextView lowerT = (TextView) view.findViewById(R.id.lowerT);

        //운동 기록
        record = getActivity().getSharedPreferences("exe", 0);
        wholeTime = record.getInt("wholeTime", 0);
        upperTime = record.getInt("upperTime", 0);
        lowerTime = record.getInt("lowerTime", 0);
        finYear = record.getInt("finYear", -1);
        finMonth = record.getInt("finMonth", -1);
        finDay = record.getInt("finDay", -1);

        Calendar now = Calendar.getInstance();      //현재 날짜와 시간정보 저장
        int now_year = now.get(Calendar.YEAR);          //현재 년도
        int now_month = now.get(Calendar.MONTH) + 1;    //현재 달          //month는 0 to 11
        int now_day = now.get(Calendar.DAY_OF_MONTH);   //현재 날짜 저장

        if((now_year != finYear) || (now_month != finMonth) || (now_day != finDay)) {
            wholeTime = 0;
            upperTime = 0;
            lowerTime = 0;
        }
        if ((wholeTime / 60) != 0)
            wholeT.setText((wholeTime / 60) + "시간 " + (wholeTime % 60) + "분");
        else
            wholeT.setText((wholeTime % 60) + "분");
        if ((upperTime / 60) != 0)
            upperT.setText((upperTime / 60) + "시간 " + (upperTime % 60) + "분");
        else
            upperT.setText((upperTime % 60) + "분");
        if ((lowerTime / 60) != 0)
            lowerT.setText((lowerTime / 60) + "시간 " + (lowerTime % 60) + "분");
        else
            lowerT.setText((lowerTime % 60) + "분");
        record = getActivity().getSharedPreferences("exe", 0);    //stamp라는 그룹이름으로 객체를 가져옴. 없으면 자동생성
        recordEdit = record.edit();        //데이터를 저장하기 위한 edit 생성
        recordEdit.putInt("wholeTime", wholeTime);
        recordEdit.putInt("upperTime", upperTime);
        recordEdit.putInt("lowerTime", lowerTime);
        recordEdit.commit();

        //목표 날짜
        goal = (Button)view.findViewById(R.id.goalDate);

        final SharedPreferences goalBtn = getActivity().getSharedPreferences("goalD", 0);
        final int goalExYear = goalBtn.getInt("goalYear", -1);
        final int goalExMonth = goalBtn.getInt("goalMonth", -1);
        final int goalExDate = goalBtn.getInt("goalDate", -1);

        if(goalExYear != -1 && goalExMonth != -1 && goalExDate != -1)
        {
            goal.setText("목표 날짜 : " + goalExYear + "년 " + (goalExMonth) + "월 " + goalExDate + "일");
        }

        calDate = getActivity().getSharedPreferences("goalD", 0);
        calDateEdit = calDate.edit();

        this.InitializeListener();

        goal.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), callbackMethod, curYearInt, curMonthInt-1, curDayInt);
                dialog.show();
            }
        });

        share = (ImageButton)view.findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View screen = getActivity().getWindow().getDecorView();
                screen.buildDrawingCache();
                Bitmap c = screen.getDrawingCache();

                File storage = getActivity().getCacheDir();

                //저장할 파일 이름
                String fileName = "recordShare.jpg";

                //storage 에 파일 인스턴스를 생성합니다.
                File tempFile = new File(storage, fileName);

                try {
                    // 자동으로 빈 파일 생성.
                    tempFile.createNewFile();

                    // 파일을 쓸 수 있는 스트림.
                    FileOutputStream out = new FileOutputStream(tempFile);

                    // 스트림에 비트맵을 저장.
                    c.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    out.close();

                    //Toast.makeText(getApplicationContext(), "완료" + getCacheDir().toString(), Toast.LENGTH_LONG).show();

                }catch (IOException e) {
                    e.printStackTrace();
                    //Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_LONG).show();
                }

                File file = new File(getActivity().getCacheDir().toString());
                File[] files = file.listFiles();

                String path = getActivity().getCacheDir() + "/" + tempFile.getName();

                Bitmap bitmap = BitmapFactory.decodeFile(path);

                String bitmapPath = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "title", null); //이미지를 insert하고
                Uri bitmapUri = Uri.parse(bitmapPath);//경로를 통해서 Uri 생성

                Intent intent = new Intent(Intent.ACTION_SEND); //전송 인텐트 생성
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
                startActivity(intent);
            }
        });



        return view;
    }

    public void InitializeListener()
    {
        callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                monthOfYear++;
                goal.setText("목표 날짜 : " + year + "년 " + (monthOfYear) + "월 " + dayOfMonth + "일");
                calDateEdit.putInt("goalYear", year);
                calDateEdit.putInt("goalMonth", monthOfYear);
                calDateEdit.putInt("goalDate", dayOfMonth);
                calDateEdit.commit();
            }
        };
    }


}