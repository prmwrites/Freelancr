package com.grandroid.android.freelancr;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class InvoiceActivity extends SingleFragmentActivity {

    public static final String EXTRA_INVOICE_ID = "com.grandroid.android.freelancr.invoice_id";

    public static Intent newIntent(Context packageContext, UUID invoiceId) {
        Intent intent = new Intent(packageContext, InvoiceActivity.class);
        intent.putExtra(EXTRA_INVOICE_ID, invoiceId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new InvoiceFragment();
    }
}
