package com.cd.razorpay.common.enums;

public enum PaymentEvent {
    AUTHORIZE_ATTEMPT,
    CAPTURE_ATTEMPT,
    AUTHORIZE_SUCCESS,
    AUTHORIZE_FAIL,
    CAPTURE_SUCCESS,
    CAPTURE_FAILURE,
    REFUND_COMPLETE,
    REFUND_INIT,
    SETTLE,
    CANCEL,
    CAPTURE_TIMEOUT
}
