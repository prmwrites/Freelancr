package com.grandroid.android.freelancr;

import android.app.Activity;
import android.content.Intent;
import android.icu.util.ChineseCalendar;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
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

    private static final int REQUEST_DATE = 0;

    private Invoice mInvoice;
    private EditText mCustomerField;
    private EditText mAmountOwed;
    private Button mDateReceivedButton;
    private Button mDateCompletedButton;
    private CheckBox mPaidCheckbox;

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

        UUID invoiceId = (UUID) getArguments().getSerializable(ARG_INVOICE_ID);
        mInvoice = JobBoard.get(getActivity()).getInvoice(invoiceId);
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

        mCustomerField = (EditText) v.findViewById(R.id.amount_owed);
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
        mDateCompletedButton.setEnabled(false);

        mPaidCheckbox = (CheckBox) v.findViewById(R.id.received_payment);
        mPaidCheckbox.setChecked(mInvoice.isFinished());
        mPaidCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
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
