package com.refctoringapp.mynewapp;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.applovin.sdk.AppLovinMediationProvider;
import com.applovin.sdk.AppLovinSdk;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.onesignal.OneSignal;

import java.util.Map;

public class mynewapp_Application extends Application {

    Context context_mynewapp;

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder mynewapp_builder = new StrictMode.VmPolicy.Builder();
            mynewapp_builder.detectFileUriExposure();
            StrictMode.setVmPolicy(mynewapp_builder.build());
        }
        context_mynewapp = this;
        AppLovinSdk.getInstance(this).setMediationProvider(AppLovinMediationProvider.MAX);
        AppLovinSdk.initializeSdk(this, appLovinSdkConfiguration -> {

        });


        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(this);
        OneSignal.setAppId(mynewapp_Utils.PREF_mynewapp_ONE_SIGNAL_APP_ID);
        OneSignal.setExternalUserId(mynewapp_Utils.generatemynewappClickID(getApplicationContext()));

        String afDevKey_mynewapp = mynewapp_Utils.PREF_mynewapp_DEV_KEY;
        AppsFlyerLib appsflyer_mynewapp = AppsFlyerLib.getInstance();
        appsflyer_mynewapp.setMinTimeBetweenSessions(0);

        appsflyer_mynewapp.init(afDevKey_mynewapp, new AppsFlyerConversionListener() {
            @Override
            public void onConversionDataSuccess(Map<String, Object> map) {
                String mynewapp_campaign = (String) map.get("campaign");
                mynewapp_Utils.setCampaignmynewapp(context_mynewapp, mynewapp_campaign);
            }

            @Override
            public void onConversionDataFail(String s) {

            }

            @Override
            public void onAppOpenAttribution(Map<String, String> map) {

            }

            @Override
            public void onAttributionFailure(String s) {

            }
        }, this);
        appsflyer_mynewapp.start(this);

        mynewapp_Utils.generatemynewappClickID(getApplicationContext());

        try {
            FirebaseApp.initializeApp(this);
            FirebaseAnalytics.getInstance(context_mynewapp)
                    .getAppInstanceId()
                    .addOnCompleteListener(task -> {
                        mynewapp_Utils.setmynewappFirebaseInstanceID(context_mynewapp, task.getResult());
                    });
        } catch (Exception e_mynewapp) {
            e_mynewapp.printStackTrace();
        }
    }

}
