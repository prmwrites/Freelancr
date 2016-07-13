package com.grandroid.android.freelancr;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

/**
 * Created by Patrick on 7/13/2016.
 */
public class InvoiceFragment extends android.support.v4.app.Fragment {

    private Invoice mInvoice;
    private EditText mCustomerField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInvoice = new Invoice();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_invoice, container, false);

        mCustomerField = (EditText) v.findViewById(R.id.customer_name);
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
            public void afterTextChanged(Editable editable) {
                // This one too
            }
        });
        return v;
    }
}
