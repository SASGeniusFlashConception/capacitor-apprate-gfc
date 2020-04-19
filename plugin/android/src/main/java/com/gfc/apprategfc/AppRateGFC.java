package com.gfc.apprategfc;

import androidx.appcompat.app.AlertDialog;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.gfc.apprategfc.capacitorapprategfc.R;

import java.net.HttpURLConnection;
import java.net.URL;


@NativePlugin(
        permissions = {
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET
        }
)
public class
AppRateGFC extends Plugin {
    private static String APP_PACKAGE_ID;
    private static String APP_RATE_TITLE;
    private static String APP_RATE_MESSAGE;
    private static String APP_RATE_URL_STORE;

    private static String POSITIVE_BUTTON_TEXT;
    private static Integer POSITIVE_BUTTON_COLOR_INT = 0;

    private static String NETRAL_BUTTON_TEXT;
    private static Integer NETRAL_BUTTON_COLOR_INT = 0;

    private static String NEGATIVE_BUTTON_TEXT;
    private static Integer NEGATIVE_BUTTON_COLOR_INT = 0;



    private static int THEME_DIALOG = R.style.AlertDialogThemeLight;

    private static int STATUT_BTN_POSITIVE = 0;
    private static int STATUT_BTN_NETRAL = 1;
    private static int STATUT_BTN_NEGATIVE = 2;

    private static boolean DONT_SHOW_AGAIN = true;

    private static boolean IS_INIT = false;

    private static boolean ICON;


    private static int DAYS_UNTIL_PROMPT;//Min number of days (Min nombre jour avant affichage )
    private static int LAUNCHES_UNTIL_PROMPT;//Min number of launches (Min ouverture app avant affichage)


    @PluginMethod()
    public void init(PluginCall call) {

        try {

            // Theme de la popup (ligth = 0 | dark = 1)
            Boolean darkMode = call.getBoolean("darkMode", false);

            if (darkMode) {
                THEME_DIALOG = R.style.AlertDialogThemeDark;
            }
            else {
                THEME_DIALOG = R.style.AlertDialogThemeLight;
            }

            // Nom de l'application
            // App Name
            String APP_NAME = call.getString("appName", null);
            if ("".equals(APP_NAME) || APP_NAME == null) {
                call.error("Must provide app name");
            }

            // Id du package de l'application
            APP_PACKAGE_ID = call.getString("appPackageId", null);
            if ("".equals(APP_PACKAGE_ID) || APP_PACKAGE_ID == null) {
                call.error("Must provide a valid app package id");
            }

            // Url du store
            APP_RATE_URL_STORE = call.getString("storeUrl", null);
            if ("".equals(APP_RATE_URL_STORE) || APP_RATE_URL_STORE == null) {
                call.error("Must provide a valid stre url");
            }
            // Titre de la popup de notification
            APP_RATE_TITLE = call.getString("titleAppRate", null);
            if ("".equals(APP_RATE_TITLE) || APP_RATE_TITLE == null) {
                call.error("Must provide title");
            }

            // Texte de la popup de notification
            APP_RATE_MESSAGE = call.getString("messageAppRate", null);
            if ("".equals(APP_RATE_MESSAGE) || APP_RATE_MESSAGE == null) {
                call.error("Must provide message");
            }

            // Nombre de jour avant ouverture de la popup
            LAUNCHES_UNTIL_PROMPT = call.getInt("untilPrompt", null);
            if ("".equals(APP_RATE_MESSAGE) || APP_RATE_MESSAGE == null) {
                call.error("Must provide until Prompt");
            }

            // Nombre d'ouverture de l'application avant ouverture de la popup
            DAYS_UNTIL_PROMPT = call.getInt("daysUntilPrompt", null);
            if ("".equals(APP_RATE_MESSAGE) || APP_RATE_MESSAGE == null) {
                call.error("Must provide days Until Prompt");
            }

            // Texte du bouton positif
            POSITIVE_BUTTON_TEXT = call.getObject("positifButtonStyle").getString("buttonActionText", null);
            if ("".equals(POSITIVE_BUTTON_TEXT) || POSITIVE_BUTTON_TEXT == null) {
                call.error("Must provide buttonActionText for positif Button");
            }
            // Couleur du bouton positif
            String POSITIVE_BUTTON_COLOR = call.getObject("positifButtonStyle").getString("buttonActionColor", null);
            if(POSITIVE_BUTTON_COLOR != null)
            {
                try {
                    POSITIVE_BUTTON_COLOR_INT = Color.parseColor(POSITIVE_BUTTON_COLOR);
                } catch (Exception ex) {
                    call.error("Must provide Must provide a valid color for positif Button");
                }
            }

            // Texte du bouton neutre
            NETRAL_BUTTON_TEXT = call.getObject("netralButtonStyle").getString("buttonActionText", null);
            if ("".equals(NETRAL_BUTTON_TEXT) || NETRAL_BUTTON_TEXT == null) {
                call.error("Must provide Must provide buttonActionText for netral Button");
            }
            // Couleur du bouton neutre
            String NETRAL_BUTTON_COLOR = call.getObject("netralButtonStyle").getString("buttonActionColor", null);
            if(NETRAL_BUTTON_COLOR != null)
            {
                try {
                    NETRAL_BUTTON_COLOR_INT = Color.parseColor(NETRAL_BUTTON_COLOR);
                } catch (Exception ex) {
                    call.error("Must provide Must provide a valid color for netral Button");
                }
            }

            // Texte du bouton negatif
            NEGATIVE_BUTTON_TEXT = call.getObject("negatifButtonStyle").getString("buttonActionText", null);
            if ("".equals(NEGATIVE_BUTTON_TEXT) || NEGATIVE_BUTTON_TEXT == null) {
                call.error("Must provide Must provide buttonActionText for negatif Button");
            }
            // Couleur du bouton negatif
            String NEGATIVE_BUTTON_COLOR = call.getObject("negatifButtonStyle").getString("buttonActionColor", null);
            if(NEGATIVE_BUTTON_COLOR != null)
            {
                try {
                    NEGATIVE_BUTTON_COLOR_INT = Color.parseColor(NEGATIVE_BUTTON_COLOR);
                } catch (Exception ex) {
                    call.error("Must provide Must provide a valid color for negatif Button");
                }
            }


            // Icone du Dialog alert
            ICON = call.getBoolean("addAppIcon", false);


            assert APP_NAME != null;
            APP_RATE_TITLE = APP_RATE_TITLE.replace("%@", APP_NAME);
            APP_RATE_MESSAGE = APP_RATE_MESSAGE.replace("%@", APP_NAME);


            // lancement de la popup
            app_launched(getContext(), call, true);
            JSObject ret = new JSObject();
            IS_INIT = true;
            ret.put("appRateInit", IS_INIT);
            call.resolve(ret);
        } catch (Exception ex) {
            call.error(ex.getMessage(), ex);
        }


    }

    @PluginMethod()
    public void showDirectly(PluginCall call) {
        if(IS_INIT)
        {
            app_launched(getContext(), call, false);
            call.success();
        }
        else {
            call.error("AppRate i not initialized !");
        }
    }


    @PluginMethod()
    public void checkAppStatus(PluginCall call) {
        // Id du package de l'application
        APP_PACKAGE_ID = call.getString("appPackageId");
        if ("".equals(APP_PACKAGE_ID) || APP_PACKAGE_ID == null) {
            call.error("Must provide a valid app package id");
        }
        APP_RATE_URL_STORE = call.getString("storeUrl", null);
        if ("".equals(APP_RATE_URL_STORE) || APP_RATE_URL_STORE == null) {
            call.error("Must provide a valid stre url");
        }
        boolean isLive = isAppLiveOnPlayStore(APP_PACKAGE_ID, APP_RATE_URL_STORE, call);
        if (isLive) {
            JSObject ret = new JSObject();
            boolean STATUT_APP_IS_PUBLISH = true;
            ret.put("appOnPlay", true);
            notifyListeners("appIsPublishEvent", ret);
        } else {
            JSObject ret = new JSObject();
            ret.put("appOnPlay", false);
            notifyListeners("appIsPublishEvent", ret);
        }

        call.success();

    }

    @PluginMethod()
    private void app_launched(Context mContext, PluginCall call, Boolean useUntil) {
        SharedPreferences prefs = mContext.getSharedPreferences("apprater", 0);
        if (prefs.getBoolean("dontshowagain", false)) {
            JSObject ret = new JSObject();
            ret.put("dontshowagain", DONT_SHOW_AGAIN);
            notifyListeners("isAllReadyShow", ret);
            return;
        }

        SharedPreferences.Editor editor = prefs.edit();

        // Increment launch counter
        long launch_count = prefs.getLong("launch_count", 0) + 1;
        editor.putLong("launch_count", launch_count);

        // Get date of first launch
        long date_firstLaunch = prefs.getLong("date_firstlaunch", 0);
        if (date_firstLaunch == 0) {
            date_firstLaunch = System.currentTimeMillis();
            editor.putLong("date_firstlaunch", date_firstLaunch);
        }

        if(useUntil)
        {
            // Wait at least n days before opening
            if (launch_count >= LAUNCHES_UNTIL_PROMPT) {
                if (System.currentTimeMillis() >= date_firstLaunch +
                        (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)) {
                    showRateDialog(mContext, editor, call);
                }
            }

        }
        else {
            showRateDialog(mContext, editor, call);
        }
        editor.apply();
    }


    @PluginMethod()
    private void showRateDialog(final Context mContext, final SharedPreferences.Editor editor, final PluginCall call) {

        AlertDialog materialAlertDialogBuilder = new AlertDialog.Builder(mContext, THEME_DIALOG)
                .setTitle(APP_RATE_TITLE)
                .setMessage(APP_RATE_MESSAGE)
                .setCancelable(false)
                .setPositiveButton(POSITIVE_BUTTON_TEXT, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean isLive = false;
                        if(APP_RATE_URL_STORE.equals(""))
                        {
                            isLive = true;
                        }
                        else {
                            isLive = isAppLiveOnPlayStore(APP_PACKAGE_ID, APP_RATE_URL_STORE, call);
                        }
                        if (isLive) {
                            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(APP_RATE_URL_STORE + APP_PACKAGE_ID)));
                            if (editor != null) {
                                editor.putBoolean("dontshowagain", DONT_SHOW_AGAIN);
                                editor.commit();
                            }
                            JSObject ret = new JSObject();
                            ret.put("rateStatut", STATUT_BTN_POSITIVE);
                            notifyListeners("positifRateEvent", ret);
                            dialog.dismiss();
                        } else {
                            JSObject ret = new JSObject();
                            ret.put("appOnPlay", false);
                            notifyListeners("appIsPublishEvent", ret);
                        }
                    }
                })
                .setNeutralButton(NETRAL_BUTTON_TEXT, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        JSObject ret = new JSObject();
                        ret.put("rateStatut", STATUT_BTN_NETRAL);
                        notifyListeners("netralRateEvent", ret);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(NEGATIVE_BUTTON_TEXT, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (editor != null) {
                            editor.putBoolean("dontshowagain", true);
                            editor.commit();
                        }
                        JSObject ret = new JSObject();
                        ret.put("rateStatut", STATUT_BTN_NEGATIVE);
                        notifyListeners("negativeRateEvent", ret);
                        dialog.dismiss();
                    }

                }).create();

        if (ICON) {
            materialAlertDialogBuilder.setIcon(R.mipmap.ic_launcher_round);
        }

        if (POSITIVE_BUTTON_COLOR_INT > 0) {
            materialAlertDialogBuilder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(POSITIVE_BUTTON_COLOR_INT);
        }

        if (NETRAL_BUTTON_COLOR_INT > 0) {
            materialAlertDialogBuilder.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(NETRAL_BUTTON_COLOR_INT);
        }

        if (NEGATIVE_BUTTON_COLOR_INT > 0) {
            materialAlertDialogBuilder.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(NEGATIVE_BUTTON_COLOR_INT);
        }
        materialAlertDialogBuilder.show();
    }

    public boolean isAppLiveOnPlayStore(String appId, String storeUrl, PluginCall call) {

        final String GOOGLE_PLAY_MARKET = "market://details?id=";
        final String GOOGLE_PLAY_URL = "https://play.google.com/store/apps/details?id=";
        final String APPLE_STORE_MARKET = "itms-apps://itunes.apple.com/app/id";
        final String APPLE_STORE_URL = "https://itunes.apple.com/en/lookup?bundleId=";
        final String AMAZON_STORE_ANDROID_URL = "https://www.amazon.com/gp/mas/dl/android?p=";
        final String AMAZON_STORE_MARKET_ANDROID = "amzn://apps/android?p=";
        final String HUAWEI_APP_GALLERY = "appmarket://details?id=";

        if(storeUrl.equals(GOOGLE_PLAY_MARKET))
        {
            storeUrl = GOOGLE_PLAY_URL;
        }

        if(storeUrl.equals(APPLE_STORE_MARKET))
        {
            storeUrl = APPLE_STORE_URL;
        }

        if(storeUrl.equals(AMAZON_STORE_MARKET_ANDROID))
        {
            storeUrl = AMAZON_STORE_ANDROID_URL;
        }
        if(storeUrl.equals(HUAWEI_APP_GALLERY))
        {
            call.error("We can't checking HUAWEI_APP_GALLERY...");
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) (new URL(storeUrl + appId))
                    .openConnection();
            conn.setUseCaches(false);
            conn.connect();
            int status = conn.getResponseCode();
            conn.disconnect();
            return status == 200;
        } catch (Exception e) {
            call.error(e.getMessage(), e);
        }
        return false;
    }


}
