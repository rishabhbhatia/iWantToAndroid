package com.statiate.iwantto.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.Display;

import com.statiate.iwantto.models.GoalSelector;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Rishabh Bhatia on 23/10/16.
 */

public class iWantUtils {


    public static int generateRandomNumber(int min, int max)
    {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static String generateRandomString(int length)
    {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public static int getDominantColor(Bitmap bitmap)
    {
        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, 1, 1, true);
        final int color = newBitmap.getPixel(0, 0);
        newBitmap.recycle();
        return color;
    }

    public static int getScreenHeight(Activity activity)
    {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    public static int getScreenWidth(Activity activity)
    {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static int getScreenPixels(Context context, int dps)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dps * scale + 0.5f);
    }

    public static GoalSelector generateRandomGoalSelector()
    {
        GoalSelector goalSelector = new GoalSelector();
        goalSelector.setGoalCount(generateRandomNumber(0,100));
        goalSelector.setName(generateRandomString(6));
        goalSelector.setImageUrl("https://unsplash.it/"+generateRandomNumber(550, 750)+"/"+generateRandomNumber(300,400)+"/?random");

        return goalSelector;
    }

    public static ArrayList<GoalSelector> generateRandomGoalSelectors(int count)
    {
        ArrayList<GoalSelector> goalSelectors = new ArrayList<>();

        for(int i=0; i<count; i++)
        {
            goalSelectors.add(generateRandomGoalSelector());
        }

        return goalSelectors;
    }

}
