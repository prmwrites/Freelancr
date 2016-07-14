package com.grandroid.android.freelancr;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Patrick on 7/14/2016.
 */
public class JobBoard {

    private static JobBoard sJobBoard;
    private List<Invoice> mInvoices;

    public static JobBoard get(Context context) {
        if (sJobBoard == null) {
            sJobBoard = new JobBoard(context);
        }
        return sJobBoard;
    }

    private JobBoard(Context context) {
        mInvoices = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Invoice invoice = new Invoice();
            invoice.setCustomer("Customer #" + i);
            invoice.setFinished(i % 2 == 0); // Every other one
            mInvoices.add(invoice);
        }
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
