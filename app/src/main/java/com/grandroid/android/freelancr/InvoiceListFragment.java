package com.grandroid.android.freelancr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

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

    private class InvoiceHolder extends RecyclerView.ViewHolder {
        public TextView mCustomerTextView;

        public InvoiceHolder(View itemView) {
            super(itemView);

            mCustomerTextView = (TextView) itemView;
        }
    }

    private class InvoiceAdapter extends RecyclerView.Adapter<InvoiceHolder> {

        private List<Invoice> mInvoices;

        public InvoiceAdapter(List<Invoice> invoices) {
            mInvoices = invoices;
        }

        @Override
        public InvoiceHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new InvoiceHolder(view);
        }

        @Override
        public void onBindViewHolder(InvoiceHolder holder, int position) {
            Invoice invoice = mInvoices.get(position);
            holder.mCustomerTextView.setText(invoice.getCustomer());
        }

        @Override
        public int getItemCount() {
            return mInvoices.size();
        }
    }
}