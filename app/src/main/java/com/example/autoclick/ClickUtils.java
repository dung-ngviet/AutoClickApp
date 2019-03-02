package com.example.autoclick;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.io.InputStream;

public class ClickUtils {
    private static final String TAG = ClickUtils.class.getSimpleName();
    Mat img = new Mat(), templ = new Mat();
    int match_method = 5;

    public void clickFBLite(String [] args, Context context){

        setup(args, context);
        findFacebookLite();
    }

    private void findFacebookLite(){
        Mat result = new Mat();
        Mat img_display = new Mat();
        img.copyTo(img_display);
        int result_cols = img.cols() - templ.cols() + 1;
        int result_rows = img.rows() - templ.rows() + 1;
        result.create(result_rows, result_cols, CvType.CV_32FC1);

        Imgproc.matchTemplate(img, templ, result, match_method);

        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        Point matchLoc;

        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
        if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED) {
            matchLoc = mmr.minLoc;
        } else {
            matchLoc = mmr.maxLoc;
        }

        Imgproc.rectangle(img_display, matchLoc, new Point(matchLoc.x + templ.cols(), matchLoc.y + templ.rows()),
                new Scalar(0, 0, 0), 2, 8, 0);
        Imgproc.rectangle(result, matchLoc, new Point(matchLoc.x + templ.cols(), matchLoc.y + templ.rows()),
                new Scalar(0, 0, 0), 2, 8, 0);


        Log.d(TAG, "X = " + (matchLoc.x + templ.cols()/2));
        Log.d(TAG, "Y = " + (matchLoc.y + templ.rows()/2));
    }

    private void setup(String args[], Context context){
        Log.d(TAG, args[0]);
        Log.d(TAG, args[1]);
//        img = Imgcodecs.imread(args[0]);
//        templ = Imgcodecs.imread(args[1]);
        Utils.bitmapToMat(getBitmapFromAsset(context, "home.png"), img);
        Utils.bitmapToMat(getBitmapFromAsset(context, "magnify.png"), templ);

        Log.d(TAG, "Image X= " + img.cols() + " Y= " + img.rows());
        Log.d(TAG, "Temple X= " + templ.cols() + " Y= " + templ.rows());
        if (img.empty() && templ.empty()) {
            Log.d(TAG, "Can't read 2 of the images");
        } else if (img.empty() || templ.empty()) {
            Log.d(TAG, "Can't read one of the images");
        }
    }

    private Bitmap getBitmapFromAsset(Context context, String imageName){
        AssetManager assetManager = context.getAssets();
        InputStream instr = null;
        try {
            instr = assetManager.open(imageName);
        } catch (IOException e){
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(instr);
        return bitmap;
    }

}
