package com.example.fitmon;

import androidx.appcompat.app.AppCompatActivity;
import android.hardware.Camera;
import android.hardware.camera2.CameraDevice;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import java.io.IOException;
import java.util.List;

//public class PlayActivity extends AppCompatActivity implements SurfaceHolder.Callback {
public class PlayActivity extends AppCompatActivity{

        private CameraDevice camera;
    private SurfaceView mCameraView;
    private SurfaceHolder mCameraHolder;
    private Camera mCamera;
    private Button mStart;
    private boolean recording = false;
    private MediaRecorder mediaRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

    }
}