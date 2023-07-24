package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Switch aSwitch ;
    TextView tv_result;
    CameraManager cameramanager;
    String cameraid, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aSwitch = findViewById(R.id.switch1);
        tv_result = findViewById(R.id.textView2);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean status) {
                // Flashlight Code -->
                torch(status);
            }
        });
    }

    private void torch(boolean status) {
        try {
            cameramanager= (CameraManager) getSystemService(CAMERA_SERVICE);
            cameraid = cameramanager.getCameraIdList()[0];

            result = status ? "ON" : "OFF";
            tv_result.setText(result);

            cameramanager.setTorchMode(cameraid,status);
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
    }
}