package com.normurodov_nazar.ordinarydifferentialequations.Listeners;

public interface PageListener {
    void onPageSuccess();
    void onPageFail(String message);
    void onStartedLoading();
}
