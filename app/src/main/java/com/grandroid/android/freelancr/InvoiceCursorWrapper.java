package com.grandroid.android.freelancr;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Patrick on 7/21/2016.
 */
public class InvoiceCursorWrapper extends CursorWrapper {
    public InvoiceCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Invoice getInvoice() {
        String uuidString = getString(getColumnIndex(InvoiceDbSchema.InvoiceTable.Cols.UUID));
        String customer = getString(getColumnIndex(InvoiceDbSchema.InvoiceTable.Cols.CUSTOMER));
        String owed = getString(getColumnIndex(InvoiceDbSchema.InvoiceTable.Cols.OWED));
        long date_received = getLong(getColumnIndex(InvoiceDbSchema.InvoiceTable.Cols.DATE_RECEIVED));
        long date_completed = getLong(getColumnIndex(InvoiceDbSchema.InvoiceTable.Cols.DATE_COMPLETED));
        int isFinished = getInt(getColumnIndex(InvoiceDbSchema.InvoiceTable.Cols.FINISHED));

        Invoice invoice = new Invoice(UUID.fromString(uuidString));
        invoice.setCustomer(customer);
        invoice.setOwed(owed);
        invoice.setDateReceived(new Date(date_received));
        invoice.setDateCompleted(new Date(date_completed));
        invoice.setFinished(isFinished != 0);

        return invoice;
    }
}
