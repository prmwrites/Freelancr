package com.grandroid.android.freelancr;

import android.icu.util.ChineseCalendar;
import android.os.Bundle;
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

import java.util.UUID;

/**
 * Created by Patrick on 7/13/2016.
 */
public class InvoiceFragment extends android.support.v4.app.Fragment {

    private Invoice mInvoice;
    private EditText mCustomerField;
    private EditText mAmountOwed;
    private Button mDateReceivedButton;
    private Button mDateCompletedButton;
    private CheckBox mPaidCheckbox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID invoiceId = (UUID) getActivity().getIntent().getSerializableExtra(InvoiceActivity.EXTRA_INVOICE_ID);
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
        mDateReceivedButton.setText(mInvoice.getDateReceived().toString());
        mDateReceivedButton.setEnabled(false);

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
}
