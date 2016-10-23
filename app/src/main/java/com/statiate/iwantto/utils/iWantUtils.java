package com.statiate.iwantto.utils;

import java.util.Random;

/**
 * Created by Rishabh Bhatia on 23/10/16.
 */

public class iWantUtils {


    public static int generateRandomNumber(int min, int max)
    {
        return new Random().nextInt((max - min) + 1) + min;
    }
}
