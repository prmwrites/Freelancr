package com.grandroid.android.freelancr;

import java.util.Currency;

/**
 * Created by Patrick on 7/20/2016.
 */
public class InvoiceDbSchema {
    public static final class InvoiceTable {
        public static final String NAME = "invoices";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String CUSTOMER = "customer";
            public static final String OWED = "owed";
            public static final String DATE_RECEIVED = "date_received";
            public static final String DATE_COMPLETED = "date_completed";
            public static final String FINISHED = "finished";
        }
    }
}
