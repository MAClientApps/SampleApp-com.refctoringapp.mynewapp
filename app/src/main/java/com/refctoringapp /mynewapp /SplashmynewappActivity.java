package com.refctoringapp.mynewapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdRevenueListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.concurrent.TimeUnit;

public class SplashmynewappActivity extends AppCompatActivity implements MaxAdListener, MaxAdRevenueListener {

    MaxInterstitialAd interstitialAd_mynewapp;
    int noof_refctoringapp_ads_attemp = 0;
    private FirebaseRemoteConfig mFireRemoteConfigrefctoringapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashmynewapp);
        retrievemynewappAdvertiseID();
        loadrefctoringappAds();
        initViewmynewapp();
    }


    private void retrievemynewappAdvertiseID() {
        GoogleApiAvailability gAPIAvailabilityrefctoringapp = GoogleApiAvailability.getInstance();
        int resultCode = gAPIAvailabilityrefctoringapp.isGooglePlayServicesAvailable(this);

        if (resultCode == ConnectionResult.SUCCESS) {
            new Thread(() -> {
                try {
                    AdvertisingIdClient.Info adInfomynewapp = AdvertisingIdClient.getAdvertisingIdInfo(SplashmynewappActivity.this);
                    String gpsId_refctoringapp = adInfomynewapp.getId();
                    mynewapp_Utils.setmynewappGPSADID(SplashmynewappActivity.this, gpsId_refctoringapp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public void initViewmynewapp() {
        FirebaseAnalytics.getInstance(this).setUserId(mynewapp_Utils.getClickIDmynewapp(this));
        FirebaseCrashlytics.getInstance().setUserId(mynewapp_Utils.getClickIDmynewapp(this));

        mFireRemoteConfigrefctoringapp = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettingsrefctoringapp = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(21600)
                .build();
        mFireRemoteConfigrefctoringapp.setConfigSettingsAsync(configSettingsrefctoringapp);
        mFireRemoteConfigrefctoringapp.fetchAndActivate()
                .addOnCanceledListener(() -> {
                    if (interstitialAd_mynewapp.isReady()) {
                        interstitialAd_mynewapp.showAd();
                    } else {
                        gotorefctoringappHome();
                    }
                })
                .addOnFailureListener(this, task -> {
                    if (interstitialAd_mynewapp.isReady()) {
                        interstitialAd_mynewapp.showAd();
                    } else {
                        gotorefctoringappHome();
                    }

                })
                .addOnCompleteListener(this, task -> {
                    String endPrefctoringapp = mFireRemoteConfigrefctoringapp.getString(mynewapp_Utils.PREF_mynewapp_REMOTE_CONFIG_CLOUD_POINT);
                    if (endPrefctoringapp != null && !endPrefctoringapp.isEmpty()) {
                        if (mFireRemoteConfigrefctoringapp.getString(mynewapp_Utils.PREF_mynewapp_REMOTE_CONFIG_CLOUD_POINT)
                                .startsWith("http")) {
                            mynewapp_Utils.setmynewappCloudPointValue(SplashmynewappActivity.this,
                                    mFireRemoteConfigrefctoringapp.getString(mynewapp_Utils.PREF_mynewapp_REMOTE_CONFIG_CLOUD_POINT));
                        } else {
                            mynewapp_Utils.setmynewappCloudPointValue(SplashmynewappActivity.this,
                                    "https://" + mFireRemoteConfigrefctoringapp.getString(mynewapp_Utils.PREF_mynewapp_REMOTE_CONFIG_CLOUD_POINT));
                        }
                        checkrefctoringappInternet();
                    } else {
                        if (interstitialAd_mynewapp.isReady()) {
                            interstitialAd_mynewapp.showAd();
                        } else {
                            gotorefctoringappHome();
                        }
                    }
                });
    }


    public void checkrefctoringappInternet() {
        if (!mynewapp_Utils.isNetworkConnected(SplashmynewappActivity.this)) {
            checkrefctoringappConnectionDialog(SplashmynewappActivity.this);
        } else {
            gotomynewapp();
        }
    }

    public void checkrefctoringappConnectionDialog(Context context) {
        AlertDialog.Builder buildermynewapp = new AlertDialog.Builder(context);
        buildermynewapp.setTitle(R.string.refctoringappdialog_title);
        buildermynewapp.setMessage(R.string.refctoringapp_no_internet);
        buildermynewapp.setCancelable(false);
        buildermynewapp.setPositiveButton(R.string.mynewapp_try_again, (dialog, which) -> retryrefctoringappConnection());
        buildermynewapp.show();
    }

    private void retryrefctoringappConnection() {
        new Handler(Looper.getMainLooper()).postDelayed(this::checkrefctoringappInternet, 1000);
    }

    public void gotomynewapp() {
        startActivity(new Intent(SplashmynewappActivity.this, mynewapp_Activity.class));
        finish();
    }


    public void loadrefctoringappAds() {
        interstitialAd_mynewapp = new MaxInterstitialAd(mynewapp_Utils.mynewapp_interstitial_ads, this);
        interstitialAd_mynewapp.setListener(this);
        interstitialAd_mynewapp.setRevenueListener(this);
        interstitialAd_mynewapp.loadAd();
    }

    public void gotorefctoringappHome() {
        startActivity(new Intent(SplashmynewappActivity.this, WebmynewappActivity.class));
        finish();
    }

    @Override
    public void onAdLoaded(MaxAd maxAd) {
    }

    @Override
    public void onAdDisplayed(MaxAd maxAd) {

    }

    @Override
    public void onAdHidden(MaxAd maxAd) {
        gotorefctoringappHome();
    }

    @Override
    public void onAdClicked(MaxAd maxAd) {

    }

    @Override
    public void onAdLoadFailed(String s, MaxError maxError) {
        noof_refctoringapp_ads_attemp++;
        long delayMillis = TimeUnit.SECONDS.toMillis((long) Math.pow(2, Math.min(6, noof_refctoringapp_ads_attemp)));
        new Handler().postDelayed(() -> interstitialAd_mynewapp.loadAd(), delayMillis);

    }

    @Override
    public void onAdDisplayFailed(MaxAd maxAd, MaxError maxError) {
        interstitialAd_mynewapp.loadAd();
    }

    @Override
    public void onAdRevenuePaid(MaxAd maxAd) {

    }
}