package com.grandroid.android.freelancr;

import android.app.Activity;
import android.content.Intent;
import android.icu.util.ChineseCalendar;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Patrick on 7/13/2016.
 */
public class InvoiceFragment extends android.support.v4.app.Fragment {

    private static final String ARG_INVOICE_ID = "invoice_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_DATE2 = "DialogDate";

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_DATE2 = 1;

    private Invoice mInvoice;
    private EditText mCustomerField;
    private EditText mAmountOwed;
    private Button mDateReceivedButton;
    private Button mDateCompletedButton;
    private CheckBox mFinishedCheckBox;

    public static InvoiceFragment newInstance(UUID invoiceId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_INVOICE_ID, invoiceId);

        InvoiceFragment fragment = new InvoiceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        UUID invoiceId = (UUID) getArguments().getSerializable(ARG_INVOICE_ID);
        mInvoice = JobBoard.get(getActivity()).getInvoice(invoiceId);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_invoice_list_item, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_delete_invoice:
                UUID invoiceId = mInvoice.getId();
                JobBoard.get(getActivity()).deleteInvoice(invoiceId);
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        JobBoard.get(getActivity()).updateInvoice(mInvoice);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_invoice, container, false);

        mCustomerField = (EditText) v.findViewById(R.id.customer_name);
        mCustomerField.setText(mInvoice.getCustomer());
        mCustomerField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This space left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mInvoice.setCustomer(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mAmountOwed = (EditText) v.findViewById(R.id.amount_owed);
        mAmountOwed.setText(mInvoice.getOwed());
        mAmountOwed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This space left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mInvoice.setOwed(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mDateReceivedButton = (Button) v.findViewById(R.id.date_received);
        updateDate();
        mDateReceivedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mInvoice.getDateReceived());
                dialog.setTargetFragment(InvoiceFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mDateCompletedButton = (Button) v.findViewById(R.id.date_completed);;
        updateDate();
        mDateCompletedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mInvoice.getDateCompleted());
                dialog.setTargetFragment(InvoiceFragment.this, REQUEST_DATE2);
                dialog.show(manager, DIALOG_DATE2);
            }
        });

        mFinishedCheckBox = (CheckBox) v.findViewById(R.id.received_payment);
        mFinishedCheckBox.setChecked(mInvoice.isFinished());
        mFinishedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mInvoice.setFinished(isChecked);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mInvoice.setDateReceived(date);
            updateDate();
        }
    }

    private void updateDate() {
        mDateReceivedButton.setText(mInvoice.getDateReceived().toString());
    }
}