package com.grandroid.android.freelancr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Patrick on 7/14/2016.
 */
public class InvoiceListFragment extends Fragment {
    private RecyclerView mInvoiceRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invoice_list, container, false);

        mInvoiceRecyclerView = (RecyclerView) view.findViewById(R.id.invoice_recycler_view);
        mInvoiceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
}