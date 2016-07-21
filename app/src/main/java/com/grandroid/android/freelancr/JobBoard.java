package com.grandroid.android.freelancr;

import android.content.ContentValues;
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
    }

    public void addInvoice(Invoice c) {
        ContentValues values = getContentValues(c);

        mDatabase.insert(InvoiceDbSchema.InvoiceTable.NAME, null, values);
    }

    public List<Invoice> getInvoices() {
        return new ArrayList<>();
    }

    public Invoice getInvoice(UUID id) {
        return null;
    }

    public void updateInvoice(Invoice invoice) {
        String uuidString = invoice.getId().toString();
        ContentValues values = getContentValues(invoice);

        mDatabase.update(InvoiceDbSchema.InvoiceTable.NAME, values, InvoiceDbSchema.InvoiceTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    private static ContentValues getContentValues(Invoice invoice) {
        ContentValues values = new ContentValues();
        values.put(InvoiceDbSchema.InvoiceTable.Cols.UUID, invoice.getId().toString());
        values.put(InvoiceDbSchema.InvoiceTable.Cols.CUSTOMER, invoice.getCustomer());
        values.put(InvoiceDbSchema.InvoiceTable.Cols.OWED, invoice.getOwed());
        values.put(InvoiceDbSchema.InvoiceTable.Cols.DATE_RECEIVED, invoice.getDateReceived().getTime());
        values.put(InvoiceDbSchema.InvoiceTable.Cols.DATE_COMPLETED, invoice.getDateCompleted().getTime());
        values.put(InvoiceDbSchema.InvoiceTable.Cols.FINISHED, invoice.isFinished() ? 1 : 0);

    return values;
    }
}