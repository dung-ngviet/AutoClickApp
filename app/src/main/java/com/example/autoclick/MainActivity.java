package com.example.autoclick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.opencv.android.OpenCVLoader;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    static{
        if (!OpenCVLoader.initDebug()){
            Log.d(TAG, "OpenCV load fail");
        } else{
            Log.d(TAG, "OpenCV load successful");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickLite();
    }

    public void clickLite(){
        ClickUtils clickUtils = new ClickUtils();
        String [] args ={"file://android_asset/home.png", "file://android_asset/manify.png"};
        clickUtils.clickFBLite(args, this);
    }
}
