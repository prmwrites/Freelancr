package com.grandroid.android.freelancr;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class InvoiceActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new InvoiceFragment();
    }
}
