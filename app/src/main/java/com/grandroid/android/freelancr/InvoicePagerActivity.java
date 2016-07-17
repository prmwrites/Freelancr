package com.grandroid.android.freelancr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * Created by Patrick on 7/17/2016.
 */
public class InvoicePagerActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private List<Invoice> mInvoices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_pager);

        mInvoices = JobBoard.get(this).getInvoices();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Invoice invoice = mInvoices.get(position);
                return InvoiceFragment.newInstance(invoice.getId());
            }

            @Override
            public int getCount() {
                return mInvoices.size();
            }
        });
    }
}