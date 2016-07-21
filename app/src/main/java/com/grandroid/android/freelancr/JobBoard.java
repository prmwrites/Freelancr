package com.grandroid.android.freelancr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Patrick on 7/14/2016.
 */
public class JobBoard {

    private static JobBoard sJobBoard;
    private List<Invoice> mInvoices;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static JobBoard get(Context context) {
        if (sJobBoard == null) {
            sJobBoard = new JobBoard(context);
        }
        return sJobBoard;
    }

    private JobBoard(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new InvoiceBaseHelper(mContext).getWritableDatabase();
        mInvoices = new ArrayList<>();
    }

    public void addInvoice(Invoice c) {
        mInvoices.add(c);
    }

    public List<Invoice> getInvoices() {
        return mInvoices;
    }

    public Invoice getInvoice(UUID id) {
        for (Invoice invoice : mInvoices) {
            if (invoice.getId().equals(id)) {
                return invoice;
            }
        }
        return null;
    }
}
