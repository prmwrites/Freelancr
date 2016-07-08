package com.grandroid.android.freelancr;

import java.util.Currency;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Patrick on 7/8/2016.
 */
public class Invoice {
    private UUID mId;
    private String mCustomer;
    private Currency mOwed;
    private Date mDateReceived;
    private Date mDateCompleted;
    private boolean mFinished;

    public Invoice() {
        this(UUID.randomUUID());
    }

    public Invoice(UUID id) {
        mId = id;
        mDateReceived = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getCustomer() {
        return mCustomer;
    }

    public void setCustomer(String customer) {
        mCustomer = customer;
    }

    public Currency getOwed() {
        return mOwed;
    }

    public void setOwed(Currency owed) {
        mOwed = owed;
    }

    public Date getDateReceived() {
        return mDateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        mDateReceived = dateReceived;
    }

    public Date getDateCompleted() {
        return mDateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        mDateCompleted = dateCompleted;
    }

    public boolean isFinished() {
        return mFinished;
    }

    public void setFinished(boolean finished) {
        mFinished = finished;
    }
}