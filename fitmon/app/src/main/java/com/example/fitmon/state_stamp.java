package com.example.fitmon;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.fragment.app.Fragment;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class state_stamp extends Fragment {


    public state_stamp() {
        // Required empty public constructor
    }

    SharedPreferences count;
    SharedPreferences.Editor edit;

    SharedPreferences date;
    SharedPreferences.Editor caledit;

    int ex_stamp;
    int stamp_count;
    boolean check_btn = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_state_stamp, container, false);

        //여기에 스탬프 코드
        final SharedPreferences goalBtn = getActivity().getSharedPreferences("goalD", 0);
        final int goalExYear = goalBtn.getInt("goalYear", 0);
        final int goalExMonth = goalBtn.getInt("goalMonth", 0);
        final int goalExDate = goalBtn.getInt("goalDate", 0);

        Button stamp_button = (Button) view.findViewById(R.id.stamp_button);
        final ImageButton first_stamp=(ImageButton)view.findViewById(R.id.first_stamp);
        final ImageButton stamp02=(ImageButton)view.findViewById(R.id.stamp02);
        final ImageButton stamp03=(ImageButton)view.findViewById(R.id.stamp03);
        final ImageButton stamp04=(ImageButton)view.findViewById(R.id.stamp04);
        final ImageButton stamp05=(ImageButton)view.findViewById(R.id.stamp05);
        final ImageButton stamp06=(ImageButton)view.findViewById(R.id.stamp06);
        final ImageButton stamp07=(ImageButton)view.findViewById(R.id.stamp07);
        final ImageButton stamp08=(ImageButton)view.findViewById(R.id.stamp08);
        final ImageButton stamp09=(ImageButton)view.findViewById(R.id.stamp09);
        final ImageButton stamp10=(ImageButton)view.findViewById(R.id.stamp10);
        final ImageButton stamp11=(ImageButton)view.findViewById(R.id.stamp11);
        final ImageButton stamp12=(ImageButton)view.findViewById(R.id.stamp12);
        final ImageButton stamp13=(ImageButton)view.findViewById(R.id.stamp13);
        final ImageButton stamp14=(ImageButton)view.findViewById(R.id.stamp14);
        final ImageButton stamp15=(ImageButton)view.findViewById(R.id.stamp15);
        final ImageButton stamp16=(ImageButton)view.findViewById(R.id.stamp16);
        final ImageButton stamp17=(ImageButton)view.findViewById(R.id.stamp17);
        final ImageButton stamp18=(ImageButton)view.findViewById(R.id.stamp18);
        final ImageButton stamp19=(ImageButton)view.findViewById(R.id.stamp19);
        final ImageButton stamp20=(ImageButton)view.findViewById(R.id.stamp20);
        final ImageButton stamp21=(ImageButton)view.findViewById(R.id.stamp21);
        final ImageButton stamp22=(ImageButton)view.findViewById(R.id.stamp22);
        final ImageButton stamp23=(ImageButton)view.findViewById(R.id.stamp23);
        final ImageButton stamp24=(ImageButton)view.findViewById(R.id.stamp24);
        final ImageButton stamp25=(ImageButton)view.findViewById(R.id.stamp25);

        //my_stamp 개수 불러오기
        final SharedPreferences stamp = getActivity().getSharedPreferences("stamp", 0); //stamp라는 그룹이름으로 객체를 가져옴. 없으면 자동생성
        stamp_count = stamp.getInt("save_stamp", 0);                    //save_stamp로 검색하여 stamp_count에 불러옴. 없으면 0으로 대체
        ex_stamp = stamp_count;

        //이전 스탬프 저장 날짜 불러오기
        final SharedPreferences cal = getActivity().getSharedPreferences("cal", 0);
        final int save_year = cal.getInt("ex_year", 0);
        final int save_month = cal.getInt("ex_month", 0);
        final int save_day = cal.getInt("ex_day", 0);

        switch (stamp_count){
            case 0:
                break;
            case 1:
                first_stamp.setVisibility(View.VISIBLE);
                break;
            case 2:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                break;
            case 3:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                break;
            case 4:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                break;
            case 5:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                break;
            case 6:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                stamp06.setVisibility(View.VISIBLE);
                break;
            case 7:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                stamp06.setVisibility(View.VISIBLE);
                stamp07.setVisibility(View.VISIBLE);
                break;
            case 8:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                stamp06.setVisibility(View.VISIBLE);
                stamp07.setVisibility(View.VISIBLE);
                stamp08.setVisibility(View.VISIBLE);
                break;
            case 9:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                stamp06.setVisibility(View.VISIBLE);
                stamp07.setVisibility(View.VISIBLE);
                stamp08.setVisibility(View.VISIBLE);
                stamp09.setVisibility(View.VISIBLE);
                break;
            case 10:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                stamp06.setVisibility(View.VISIBLE);
                stamp07.setVisibility(View.VISIBLE);
                stamp08.setVisibility(View.VISIBLE);
                stamp09.setVisibility(View.VISIBLE);
                stamp10.setVisibility(View.VISIBLE);
                break;
            case 11:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                stamp06.setVisibility(View.VISIBLE);
                stamp07.setVisibility(View.VISIBLE);
                stamp08.setVisibility(View.VISIBLE);
                stamp09.setVisibility(View.VISIBLE);
                stamp10.setVisibility(View.VISIBLE);
                stamp11.setVisibility(View.VISIBLE);
                break;
            case 12:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                stamp06.setVisibility(View.VISIBLE);
                stamp07.setVisibility(View.VISIBLE);
                stamp08.setVisibility(View.VISIBLE);
                stamp09.setVisibility(View.VISIBLE);
                stamp10.setVisibility(View.VISIBLE);
                stamp11.setVisibility(View.VISIBLE);
                stamp12.setVisibility(View.VISIBLE);
                break;
            case 13:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                stamp06.setVisibility(View.VISIBLE);
                stamp07.setVisibility(View.VISIBLE);
                stamp08.setVisibility(View.VISIBLE);
                stamp09.setVisibility(View.VISIBLE);
                stamp10.setVisibility(View.VISIBLE);
                stamp11.setVisibility(View.VISIBLE);
                stamp12.setVisibility(View.VISIBLE);
                stamp13.setVisibility(View.VISIBLE);
                break;
            case 14:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                stamp06.setVisibility(View.VISIBLE);
                stamp07.setVisibility(View.VISIBLE);
                stamp08.setVisibility(View.VISIBLE);
                stamp09.setVisibility(View.VISIBLE);
                stamp10.setVisibility(View.VISIBLE);
                stamp11.setVisibility(View.VISIBLE);
                stamp12.setVisibility(View.VISIBLE);
                stamp13.setVisibility(View.VISIBLE);
                stamp14.setVisibility(View.VISIBLE);
                break;
            case 15:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                stamp06.setVisibility(View.VISIBLE);
                stamp07.setVisibility(View.VISIBLE);
                stamp08.setVisibility(View.VISIBLE);
                stamp09.setVisibility(View.VISIBLE);
                stamp10.setVisibility(View.VISIBLE);
                stamp11.setVisibility(View.VISIBLE);
                stamp12.setVisibility(View.VISIBLE);
                stamp13.setVisibility(View.VISIBLE);
                stamp14.setVisibility(View.VISIBLE);
                stamp15.setVisibility(View.VISIBLE);
                break;
            case 16:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                stamp06.setVisibility(View.VISIBLE);
                stamp07.setVisibility(View.VISIBLE);
                stamp08.setVisibility(View.VISIBLE);
                stamp09.setVisibility(View.VISIBLE);
                stamp10.setVisibility(View.VISIBLE);
                stamp11.setVisibility(View.VISIBLE);
                stamp12.setVisibility(View.VISIBLE);
                stamp13.setVisibility(View.VISIBLE);
                stamp14.setVisibility(View.VISIBLE);
                stamp15.setVisibility(View.VISIBLE);
                stamp16.setVisibility(View.VISIBLE);
                break;
            case 17:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                stamp06.setVisibility(View.VISIBLE);
                stamp07.setVisibility(View.VISIBLE);
                stamp08.setVisibility(View.VISIBLE);
                stamp09.setVisibility(View.VISIBLE);
                stamp10.setVisibility(View.VISIBLE);
                stamp11.setVisibility(View.VISIBLE);
                stamp12.setVisibility(View.VISIBLE);
                stamp13.setVisibility(View.VISIBLE);
                stamp14.setVisibility(View.VISIBLE);
                stamp15.setVisibility(View.VISIBLE);
                stamp16.setVisibility(View.VISIBLE);
                stamp17.setVisibility(View.VISIBLE);
                break;
            case 18:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                stamp06.setVisibility(View.VISIBLE);
                stamp07.setVisibility(View.VISIBLE);
                stamp08.setVisibility(View.VISIBLE);
                stamp09.setVisibility(View.VISIBLE);
                stamp10.setVisibility(View.VISIBLE);
                stamp11.setVisibility(View.VISIBLE);
                stamp12.setVisibility(View.VISIBLE);
                stamp13.setVisibility(View.VISIBLE);
                stamp14.setVisibility(View.VISIBLE);
                stamp15.setVisibility(View.VISIBLE);
                stamp16.setVisibility(View.VISIBLE);
                stamp17.setVisibility(View.VISIBLE);
                stamp18.setVisibility(View.VISIBLE);
                break;
            case 19:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                stamp06.setVisibility(View.VISIBLE);
                stamp07.setVisibility(View.VISIBLE);
                stamp08.setVisibility(View.VISIBLE);
                stamp09.setVisibility(View.VISIBLE);
                stamp10.setVisibility(View.VISIBLE);
                stamp11.setVisibility(View.VISIBLE);
                stamp12.setVisibility(View.VISIBLE);
                stamp13.setVisibility(View.VISIBLE);
                stamp14.setVisibility(View.VISIBLE);
                stamp15.setVisibility(View.VISIBLE);
                stamp16.setVisibility(View.VISIBLE);
                stamp17.setVisibility(View.VISIBLE);
                stamp18.setVisibility(View.VISIBLE);
                stamp19.setVisibility(View.VISIBLE);
                break;
            case 20:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                stamp06.setVisibility(View.VISIBLE);
                stamp07.setVisibility(View.VISIBLE);
                stamp08.setVisibility(View.VISIBLE);
                stamp09.setVisibility(View.VISIBLE);
                stamp10.setVisibility(View.VISIBLE);
                stamp11.setVisibility(View.VISIBLE);
                stamp12.setVisibility(View.VISIBLE);
                stamp13.setVisibility(View.VISIBLE);
                stamp14.setVisibility(View.VISIBLE);
                stamp15.setVisibility(View.VISIBLE);
                stamp16.setVisibility(View.VISIBLE);
                stamp17.setVisibility(View.VISIBLE);
                stamp18.setVisibility(View.VISIBLE);
                stamp19.setVisibility(View.VISIBLE);
                stamp20.setVisibility(View.VISIBLE);
                break;
            case 21:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                stamp06.setVisibility(View.VISIBLE);
                stamp07.setVisibility(View.VISIBLE);
                stamp08.setVisibility(View.VISIBLE);
                stamp09.setVisibility(View.VISIBLE);
                stamp10.setVisibility(View.VISIBLE);
                stamp11.setVisibility(View.VISIBLE);
                stamp12.setVisibility(View.VISIBLE);
                stamp13.setVisibility(View.VISIBLE);
                stamp14.setVisibility(View.VISIBLE);
                stamp15.setVisibility(View.VISIBLE);
                stamp16.setVisibility(View.VISIBLE);
                stamp17.setVisibility(View.VISIBLE);
                stamp18.setVisibility(View.VISIBLE);
                stamp19.setVisibility(View.VISIBLE);
                stamp20.setVisibility(View.VISIBLE);
                stamp21.setVisibility(View.VISIBLE);
                break;
            case 22:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                stamp06.setVisibility(View.VISIBLE);
                stamp07.setVisibility(View.VISIBLE);
                stamp08.setVisibility(View.VISIBLE);
                stamp09.setVisibility(View.VISIBLE);
                stamp10.setVisibility(View.VISIBLE);
                stamp11.setVisibility(View.VISIBLE);
                stamp12.setVisibility(View.VISIBLE);
                stamp13.setVisibility(View.VISIBLE);
                stamp14.setVisibility(View.VISIBLE);
                stamp15.setVisibility(View.VISIBLE);
                stamp16.setVisibility(View.VISIBLE);
                stamp17.setVisibility(View.VISIBLE);
                stamp18.setVisibility(View.VISIBLE);
                stamp19.setVisibility(View.VISIBLE);
                stamp20.setVisibility(View.VISIBLE);
                stamp21.setVisibility(View.VISIBLE);
                stamp22.setVisibility(View.VISIBLE);
                break;
            case 23:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                stamp06.setVisibility(View.VISIBLE);
                stamp07.setVisibility(View.VISIBLE);
                stamp08.setVisibility(View.VISIBLE);
                stamp09.setVisibility(View.VISIBLE);
                stamp10.setVisibility(View.VISIBLE);
                stamp11.setVisibility(View.VISIBLE);
                stamp12.setVisibility(View.VISIBLE);
                stamp13.setVisibility(View.VISIBLE);
                stamp14.setVisibility(View.VISIBLE);
                stamp15.setVisibility(View.VISIBLE);
                stamp16.setVisibility(View.VISIBLE);
                stamp17.setVisibility(View.VISIBLE);
                stamp18.setVisibility(View.VISIBLE);
                stamp19.setVisibility(View.VISIBLE);
                stamp20.setVisibility(View.VISIBLE);
                stamp21.setVisibility(View.VISIBLE);
                stamp22.setVisibility(View.VISIBLE);
                stamp23.setVisibility(View.VISIBLE);
                break;
            case 24:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                stamp06.setVisibility(View.VISIBLE);
                stamp07.setVisibility(View.VISIBLE);
                stamp08.setVisibility(View.VISIBLE);
                stamp09.setVisibility(View.VISIBLE);
                stamp10.setVisibility(View.VISIBLE);
                stamp11.setVisibility(View.VISIBLE);
                stamp12.setVisibility(View.VISIBLE);
                stamp13.setVisibility(View.VISIBLE);
                stamp14.setVisibility(View.VISIBLE);
                stamp15.setVisibility(View.VISIBLE);
                stamp16.setVisibility(View.VISIBLE);
                stamp17.setVisibility(View.VISIBLE);
                stamp18.setVisibility(View.VISIBLE);
                stamp19.setVisibility(View.VISIBLE);
                stamp20.setVisibility(View.VISIBLE);
                stamp21.setVisibility(View.VISIBLE);
                stamp22.setVisibility(View.VISIBLE);
                stamp23.setVisibility(View.VISIBLE);
                stamp24.setVisibility(View.VISIBLE);
                break;
            case 25:
                first_stamp.setVisibility(View.VISIBLE);
                stamp02.setVisibility(View.VISIBLE);
                stamp03.setVisibility(View.VISIBLE);
                stamp04.setVisibility(View.VISIBLE);
                stamp05.setVisibility(View.VISIBLE);
                stamp06.setVisibility(View.VISIBLE);
                stamp07.setVisibility(View.VISIBLE);
                stamp08.setVisibility(View.VISIBLE);
                stamp09.setVisibility(View.VISIBLE);
                stamp10.setVisibility(View.VISIBLE);
                stamp11.setVisibility(View.VISIBLE);
                stamp12.setVisibility(View.VISIBLE);
                stamp13.setVisibility(View.VISIBLE);
                stamp14.setVisibility(View.VISIBLE);
                stamp15.setVisibility(View.VISIBLE);
                stamp16.setVisibility(View.VISIBLE);
                stamp17.setVisibility(View.VISIBLE);
                stamp18.setVisibility(View.VISIBLE);
                stamp19.setVisibility(View.VISIBLE);
                stamp20.setVisibility(View.VISIBLE);
                stamp21.setVisibility(View.VISIBLE);
                stamp22.setVisibility(View.VISIBLE);
                stamp23.setVisibility(View.VISIBLE);
                stamp24.setVisibility(View.VISIBLE);
                stamp25.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }

        stamp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //first_stamp.setVisibility(View.VISIBLE);

                Calendar now = Calendar.getInstance();      //현재 날짜와 시간정보 저장
                int year = now.get(Calendar.YEAR);          //현재 년도
                int month = now.get(Calendar.MONTH) + 1;    //현재 달          //month는 0 to 11
                int day = now.get(Calendar.DAY_OF_MONTH);   //현재 날짜 저장

                if(((save_year < year) || ((save_year == year) && (save_month < month)) || ((save_year == year) && (save_month == month) && (save_day < day))) && (check_btn == true)) {
                    ex_stamp++;
                    check_btn = false;
                }
                //plus = ex_stamp / 26;
                switch (ex_stamp){
                    case 1:
                        first_stamp.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        stamp02.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        stamp03.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        stamp04.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        stamp05.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        stamp06.setVisibility(View.VISIBLE);
                        break;
                    case 7:
                        stamp07.setVisibility(View.VISIBLE);
                        break;
                    case 8:
                        stamp08.setVisibility(View.VISIBLE);
                        break;
                    case 9:
                        stamp09.setVisibility(View.VISIBLE);
                        break;
                    case 10:
                        stamp10.setVisibility(View.VISIBLE);
                        break;
                    case 11:
                        stamp11.setVisibility(View.VISIBLE);
                        break;
                    case 12:
                        stamp12.setVisibility(View.VISIBLE);
                        break;
                    case 13:
                        stamp13.setVisibility(View.VISIBLE);
                        break;
                    case 14:
                        stamp14.setVisibility(View.VISIBLE);
                        break;
                    case 15:
                        stamp15.setVisibility(View.VISIBLE);
                        break;
                    case 16:
                        stamp16.setVisibility(View.VISIBLE);
                        break;
                    case 17:
                        stamp17.setVisibility(View.VISIBLE);
                        break;
                    case 18:
                        stamp18.setVisibility(View.VISIBLE);
                        break;
                    case 19:
                        stamp19.setVisibility(View.VISIBLE);
                        break;
                    case 20:
                        stamp20.setVisibility(View.VISIBLE);
                        break;
                    case 21:
                        stamp21.setVisibility(View.VISIBLE);
                        break;
                    case 22:
                        stamp22.setVisibility(View.VISIBLE);
                        break;
                    case 23:
                        stamp23.setVisibility(View.VISIBLE);
                        break;
                    case 24:
                        stamp24.setVisibility(View.VISIBLE);
                        break;
                    case 25:
                        stamp25.setVisibility(View.VISIBLE);
                        break;
                }

                count = getActivity().getSharedPreferences("stamp", 0);    //stamp라는 그룹이름으로 객체를 가져옴. 없으면 자동생성
                edit = count.edit();        //데이터를 저장하기 위한 edit 생성
                edit.putInt("save_stamp", ex_stamp);    //ex_stamp 값을 save_stamp에 저장
                edit.commit();


                date = getActivity().getSharedPreferences("cal", 0);
                caledit = date.edit();
                if (ex_stamp != stamp_count) {
                    caledit.putInt("ex_year", year);
                    caledit.putInt("ex_month", month);
                    caledit.putInt("ex_day", day);
                }
                caledit.commit();
            }
        });

        return view;
    }

}