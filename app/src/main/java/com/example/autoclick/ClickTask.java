package com.example.autoclick;

import android.content.Context;
import android.util.Log;

import com.jaredrummler.android.shell.CommandResult;
import com.jaredrummler.android.shell.Shell;

public class ClickTask {
    private static final String TAG = "ClickTask";
    public static final String ACTION_CLICK_BY_COORDINATE = "action_coordiante";

    public static void excute(Context context, String action){
        if (ACTION_CLICK_BY_COORDINATE.equals(action)){
            clickByCoordinate(context);
        }
    }

    private static void clickByCoordinate(Context context) {
        CommandResult result = Shell.SU.run("input tap 300 1500");
        if (result.isSuccessful()){
            Log.d(TAG, result.getStdout());
        } else
        {
            Log.d(TAG, result.getStderr());
        }
    }
}
