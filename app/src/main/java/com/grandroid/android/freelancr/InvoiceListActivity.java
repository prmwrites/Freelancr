package com.grandroid.android.freelancr;

import android.support.v4.app.Fragment;

/**
 * Created by Patrick on 7/14/2016.
 */
public class InvoiceListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new InvoiceListFragment();
    }
}