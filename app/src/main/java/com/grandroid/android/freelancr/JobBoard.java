package com.grandroid.android.freelancr;

import android.content.Context;

/**
 * Created by Patrick on 7/14/2016.
 */
public class JobBoard {

    private static JobBoard sJobBoard;

    public static JobBoard get(Context context) {
        if (sJobBoard == null) {
            sJobBoard = new JobBoard(context);
        }
        return sJobBoard;
    }

    private JobBoard(Context context) {

    }
}
