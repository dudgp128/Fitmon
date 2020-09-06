package com.example.fitmon;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstExerciseFragment extends Fragment {


    public FirstExerciseFragment() {
        // Required empty public constructor
    }

    private Activity activity;

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            activity = (Activity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_exercise, container, false);

        Button btn = (Button) view.findViewById(R.id.testexer);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(activity, LevelActivity.class);
                activity.startActivity((intentLoadNewActivity));
                //activity.finish();
            }
        });

        return view;
        //return inflater.inflate(R.layout.fragment_first_exercise, container, false);
    }

}
