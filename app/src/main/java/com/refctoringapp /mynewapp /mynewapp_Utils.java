package com.refctoringapp.mynewapp;

import static android.content.Context.MODE_PRIVATE;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.sdk.AppLovinSdkUtils;
import com.appsflyer.AppsFlyerLib;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class mynewapp_Utils {

    public static final String PREF_mynewapp_SETTINGS_NAME = "refctoringapp";
    public static final String PREF_mynewapp_DEV_KEY = "2czkX4PzkQ6nw2iaf3y8zJ";
    public static final String PREF_mynewapp_ONE_SIGNAL_APP_ID = "4a3fecdf-5f0f-4916-a7ff-5e2e31a0f490";
    public static final String PREF_mynewapp_PREF_KEY_CAMPAIGN = "campaign";
    public static final String PREF_mynewapp_USER_UUID = "user_uuid";
    public static final String PREF_mynewapp_GPS_ADID = "gps_adid";
    public static final String PREF_mynewapp_END_POINT = "end_point";
    public static final String PREF_mynewapp_REMOTE_CONFIG_CLOUD_POINT = "cloud_point";
    public static final String PREF_mynewapp_FIREBASE_INSTANCE_ID = "firebase_instance_id";
    public static String mynewapp_interstitial_ads = "26295fd389f9b6a2";
    public static String mynewapp_banner_ads = "2bd9da18253e80da";
    public static MaxInterstitialAd mInterstitialAd_mynewapp;
    public static MaxAdView mBannerAd_mynewapp;
    private static int loadAttempt_mynewapp;


    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return (connectivityManager != null && connectivityManager.getActiveNetworkInfo() != null) && connectivityManager
                .getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public static void setClickIDFormynewapp(Context context, String value_of_mynewapp) {
        if (context != null) {
            SharedPreferences mynewapp_preferences = context.getSharedPreferences(PREF_mynewapp_SETTINGS_NAME,
                    MODE_PRIVATE);
            Editor editormynewapp = mynewapp_preferences.edit();
            editormynewapp.putString(PREF_mynewapp_USER_UUID, value_of_mynewapp);
            editormynewapp.apply();
        }
    }

    public static String getClickIDmynewapp(Context context) {
        SharedPreferences mynewapp_preferences = context.getSharedPreferences(PREF_mynewapp_SETTINGS_NAME,
                MODE_PRIVATE);
        return mynewapp_preferences.getString(PREF_mynewapp_USER_UUID, "");
    }

    public static void setCampaignmynewapp(Context context, String InternalFlow_value) {
        if (context != null) {
            SharedPreferences mynewapp_preferences = context.getSharedPreferences(PREF_mynewapp_SETTINGS_NAME, MODE_PRIVATE);
            Editor editormynewapp = mynewapp_preferences.edit();
            editormynewapp.putString(PREF_mynewapp_PREF_KEY_CAMPAIGN, InternalFlow_value);
            editormynewapp.apply();
        }
    }

    public static String getCampaignmynewapp(Context context) {
        SharedPreferences mynewapp_preferences = context.getSharedPreferences(PREF_mynewapp_SETTINGS_NAME, MODE_PRIVATE);
        return mynewapp_preferences.getString(PREF_mynewapp_PREF_KEY_CAMPAIGN, "");
    }

    public static void setmynewappFirebaseInstanceID(Context context, String value) {
        if (context != null) {
            SharedPreferences mynewapp_preferences = context.getSharedPreferences(PREF_mynewapp_SETTINGS_NAME, MODE_PRIVATE);
            Editor editormynewapp = mynewapp_preferences.edit();
            editormynewapp.putString(PREF_mynewapp_FIREBASE_INSTANCE_ID, value);
            editormynewapp.apply();
        }
    }

    public static String getmynewappFirebaseInstanceID(Context context) {
        SharedPreferences mynewapp_preferences = context.getSharedPreferences(PREF_mynewapp_SETTINGS_NAME, MODE_PRIVATE);
        return mynewapp_preferences.getString(PREF_mynewapp_FIREBASE_INSTANCE_ID, "");
    }

    public static void setmynewappCloudPointValue(Context context, String value) {
        if (context != null) {
            SharedPreferences mynewapp_preferences = context.getSharedPreferences(PREF_mynewapp_SETTINGS_NAME,
                    MODE_PRIVATE);
            Editor editormynewapp = mynewapp_preferences.edit();
            editormynewapp.putString(PREF_mynewapp_END_POINT, value);
            editormynewapp.apply();
        }
    }

    public static String getmynewappCloudPointValue(Context context) {
        SharedPreferences mynewapp_preferences = context.getSharedPreferences(PREF_mynewapp_SETTINGS_NAME,
                MODE_PRIVATE);
        return mynewapp_preferences.getString(PREF_mynewapp_END_POINT, "");
    }

    public static void setmynewappGPSADID(Context context, String InternalFlow_value) {
        if (context != null) {
            SharedPreferences mynewapp_preferences = context.getSharedPreferences(PREF_mynewapp_SETTINGS_NAME, MODE_PRIVATE);
            Editor editormynewapp = mynewapp_preferences.edit();
            editormynewapp.putString(PREF_mynewapp_GPS_ADID, InternalFlow_value);
            editormynewapp.apply();
        }
    }

    public static String getmynewappGPSADID(Context context) {
        SharedPreferences mynewapp_preferences = context.getSharedPreferences(PREF_mynewapp_SETTINGS_NAME, MODE_PRIVATE);
        return mynewapp_preferences.getString(PREF_mynewapp_GPS_ADID, "");
    }

    public static String generatemynewappClickID(Context context) {
        String md5uuid_mynewapp = getClickIDmynewapp(context);
        if (md5uuid_mynewapp == null || md5uuid_mynewapp.isEmpty()) {
            String mynewappguid = "";
            final String mynewappuniqueID = UUID.randomUUID().toString();
            Date date = new Date();
            long timeMilli_mynewapp = date.getTime();
            mynewappguid = mynewappuniqueID + timeMilli_mynewapp;
            md5uuid_mynewapp = mynewapp_md5(mynewappguid);
            setClickIDFormynewapp(context, md5uuid_mynewapp);
        }
        return md5uuid_mynewapp;
    }

    private static String mynewapp_md5(String str_value) {
        try {
            MessageDigest digestmynewapp = MessageDigest.getInstance("MD5");
            digestmynewapp.update(str_value.getBytes());
            byte[] messageDigestmynewapp = digestmynewapp.digest();
            StringBuffer strBuffermynewapp = new StringBuffer();
            for (int i = 0; i < messageDigestmynewapp.length; i++)
                strBuffermynewapp.append(Integer.toHexString(0xFF & messageDigestmynewapp[i]));
            return strBuffermynewapp.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String getmynewappMcc(Context context) {
        try {
            TelephonyManager telephonyManager_mynewapp = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (telephonyManager_mynewapp == null) {
                return "";
            }
            String mCCMccCodemynewapp = telephonyManager_mynewapp.getNetworkOperator();
            String mccCode_mynewapp = "";
            if (TextUtils.isEmpty(mCCMccCodemynewapp)) {
                return "";
            }
            final int MCC_CODE_LENGTH = 3;
            if (mCCMccCodemynewapp.length() >= MCC_CODE_LENGTH) {
                mccCode_mynewapp = mCCMccCodemynewapp.substring(0, MCC_CODE_LENGTH);
            }

            return mccCode_mynewapp;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getmynewappMnc(Context context) {
        try {
            TelephonyManager telephonyManager_mynewapp = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (telephonyManager_mynewapp == null) {
                return "";
            }
            String mCCMncCodemynewapp = telephonyManager_mynewapp.getNetworkOperator();
            String mncCode_mynewapp = "";
            if (TextUtils.isEmpty(mCCMncCodemynewapp)) {
                return "";
            }

            final int MNC_CODE_LENGTH = 3;

            if (mCCMncCodemynewapp.length() > MNC_CODE_LENGTH) {
                mncCode_mynewapp = mCCMncCodemynewapp.substring(MNC_CODE_LENGTH);
            }
            return mncCode_mynewapp;
        } catch (Exception e) {
            return "";
        }
    }

    public static String generatemynewappPremiumLink(Context context) {
        String mynewappLinkUrl = "";
        try {
            mynewappLinkUrl = getmynewappCloudPointValue(context)
                    + "?naming=" + URLEncoder.encode(getCampaignmynewapp(context), "utf-8")
                    + "&adid=" + getmynewappGPSADID(context)
                    + "&firebase_instance_id=" + getmynewappFirebaseInstanceID(context)
                    + "&afid=" + AppsFlyerLib.getInstance().getAppsFlyerUID(context)
                    + "&package=" + context.getPackageName()
                    + "&mnc=" + getmynewappMnc(context)
                    + "&mcc=" + getmynewappMcc(context)
                    + "&click_id=" + getClickIDmynewapp(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mynewappLinkUrl;
    }

    public static void setLoadmynewappInterstitialAd(Activity activity) {
        try {
            if (mInterstitialAd_mynewapp == null) {
                mInterstitialAd_mynewapp = new MaxInterstitialAd(mynewapp_Utils.mynewapp_interstitial_ads, activity);
                mInterstitialAd_mynewapp.setListener(new MaxAdListener() {
                    @Override
                    public void onAdLoaded(MaxAd maxAd) {
                        loadAttempt_mynewapp = 0;
                    }

                    @Override
                    public void onAdDisplayed(MaxAd maxAd) {

                    }

                    @Override
                    public void onAdHidden(MaxAd maxAd) {

                    }

                    @Override
                    public void onAdClicked(MaxAd maxAd) {

                    }

                    @Override
                    public void onAdLoadFailed(String s, MaxError maxError) {
                        loadAttempt_mynewapp++;
                        long delayMillis_mynewapp = TimeUnit.SECONDS.toMillis((long) Math.pow(2, Math.min(6, loadAttempt_mynewapp)));
                        new Handler().postDelayed(() -> mInterstitialAd_mynewapp.loadAd(), delayMillis_mynewapp);
                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd maxAd, MaxError maxError) {
                        mInterstitialAd_mynewapp.loadAd();

                    }
                });
                mInterstitialAd_mynewapp.loadAd();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showmynewappInterstitialAd() {
        try {
            if (mInterstitialAd_mynewapp != null && mInterstitialAd_mynewapp.isReady()) {
                mInterstitialAd_mynewapp.showAd();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showmynewappBannerAds(RelativeLayout adContainer, Activity activity) {

        mBannerAd_mynewapp = new MaxAdView(mynewapp_Utils.mynewapp_banner_ads, activity);
        int widthmynewapp = ViewGroup.LayoutParams.MATCH_PARENT;

        int heightDp = MaxAdFormat.BANNER.getAdaptiveSize(activity).getHeight();
        int heightPx = AppLovinSdkUtils.dpToPx(activity, heightDp);

        mBannerAd_mynewapp.setLayoutParams(new RelativeLayout.LayoutParams(widthmynewapp, heightPx));
        mBannerAd_mynewapp.setExtraParameter("adaptive_banner", "true");
        adContainer.addView(mBannerAd_mynewapp);

        mBannerAd_mynewapp.startAutoRefresh();
        mBannerAd_mynewapp.setListener(new MaxAdViewAdListener() {
            @Override
            public void onAdExpanded(MaxAd ad) {

            }

            @Override
            public void onAdCollapsed(MaxAd ad) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {
            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {

            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
            }
        });

        mBannerAd_mynewapp.loadAd();

    }

}
