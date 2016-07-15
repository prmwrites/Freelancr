package com.grandroid.android.freelancr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Patrick on 7/14/2016.
 */
public class InvoiceListFragment extends Fragment {
    private RecyclerView mInvoiceRecyclerView;
    private InvoiceAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invoice_list, container, false);

        mInvoiceRecyclerView = (RecyclerView) view.findViewById(R.id.invoice_recycler_view);
        mInvoiceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        JobBoard jobBoard = JobBoard.get(getActivity());
        List<Invoice> invoices = jobBoard.getInvoices();

        mAdapter = new InvoiceAdapter(invoices);
        mInvoiceRecyclerView.setAdapter(mAdapter);
    }

    private class InvoiceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Invoice mInvoice;
        private TextView mCustomerTextView;
        private TextView mDateTextView;
        private CheckBox mFinishedCheckBox;

        public InvoiceHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mCustomerTextView = (TextView) itemView.findViewById(R.id.list_item_invoice_customer_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_invoice_date_text_view);
            mFinishedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_invoice_finished_check_box);
        }

        public void bindInvoice(Invoice invoice) {
            mInvoice = invoice;
            mCustomerTextView.setText(mInvoice.getCustomer());
            mDateTextView.setText(mInvoice.getDateReceived().toString());
            mFinishedCheckBox.setChecked(mInvoice.isFinished());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), mInvoice.getCustomer() + " clicked!", Toast.LENGTH_SHORT).show();
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
            View view = layoutInflater.inflate(R.layout.list_item_invoice, parent, false);
            return new InvoiceHolder(view);
        }

        @Override
        public void onBindViewHolder(InvoiceHolder holder, int position) {
            Invoice invoice = mInvoices.get(position);
            holder.bindInvoice(invoice);
        }

        @Override
        public int getItemCount() {
            return mInvoices.size();
        }
    }
}