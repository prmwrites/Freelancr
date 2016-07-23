package com.grandroid.android.freelancr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Patrick on 7/20/2016.
 */
public class InvoiceBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 2;
    private static final String DATABASE_NAME = "invoiceBase.db";

    public InvoiceBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + InvoiceDbSchema.InvoiceTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                InvoiceDbSchema.InvoiceTable.Cols.UUID + ", " +
                InvoiceDbSchema.InvoiceTable.Cols.CUSTOMER + ", " +
                InvoiceDbSchema.InvoiceTable.Cols.OWED + ", " +
                InvoiceDbSchema.InvoiceTable.Cols.PHONE + ", " +
                InvoiceDbSchema.InvoiceTable.Cols.EMAIL + ", " +
                InvoiceDbSchema.InvoiceTable.Cols.DATE_RECEIVED + ", " +
                InvoiceDbSchema.InvoiceTable.Cols.DATE_COMPLETED + ", " +
                InvoiceDbSchema.InvoiceTable.Cols.FINISHED +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}