package com.grandroid.android.freelancr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Patrick on 7/17/2016.
 */
public class InvoicePagerActivity extends AppCompatActivity {

    private static final String EXTRA_INVOICE_ID = "com.grandroid.android.freelancr.invoice_id";

    private ViewPager mViewPager;
    private List<Invoice> mInvoices;

    public static Intent newIntent(Context packageContext, UUID invoiceId) {
        Intent intent = new Intent(packageContext, InvoicePagerActivity.class);
        intent.putExtra(EXTRA_INVOICE_ID, invoiceId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_pager);

        UUID invoiceId = (UUID) getIntent().getSerializableExtra(EXTRA_INVOICE_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_invoice_pager_view_pager);

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