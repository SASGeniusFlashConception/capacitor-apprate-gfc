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
public class AppRateGFC extends Plugin {
    private static int IS_INIT = 0;
    private static String APP_NAME;// App Name
    private static String APP_PACKAGE_ID;// Package Name
    private static String APP_RATE_TITLE;
    private static String APP_RATE_MESSAGE;

    private static String POSITIVE_BUTTON_TEXT;
    private static String POSITIVE_BUTTON_COLOR;

    private static String NETRAL_BUTTON_TEXT;
    private static String NETRAL_BUTTON_COLOR;

    private static String NEGATIVE_BUTTON_TEXT;
    private static String NEGATIVE_BUTTON_COLOR;

    private static int THEME_DIALOG;

    private static int THEME_LIGHT = 0;
    private static int THEME_DARK = 1;

    private static boolean STATUT_APP_IS_PUBLISH = true;
    private static boolean STATUT_APP_IS_NOT_PUBLISH = false;

    private static int STATUT_BTN_POSITIVE = 0;
    private static int STATUT_BTN_NETRAL = 1;
    private static int STATUT_BTN_NEGATIVE = 2;

    private static boolean DONT_SHOW_AGAIN = true;


    private static int DAYS_UNTIL_PROMPT;//Min number of days (Min nombre jour avant affichage )
    private static int LAUNCHES_UNTIL_PROMPT;//Min number of launches (Min ouverture app avant affichage)


    @PluginMethod()
    public void init(PluginCall call) {

        // Theme de la popup (ligth = 0 | dark = 1)
        Integer themeDialog = call.getInt("theme");

        if (themeDialog == THEME_LIGHT) {
            THEME_DIALOG = R.style.AppCompatAlertDialogLightStyle;
        } else if (themeDialog == THEME_DARK) {
            THEME_DIALOG = R.style.AppCompatAlertDialogDarkStyle;
        }
        else {
            THEME_DIALOG = R.style.AppCompatAlertDialogLightStyle;
        }

        // Nom de l'application
        APP_NAME = call.getString("appName");

        // Id du package de l'application
        APP_PACKAGE_ID = call.getString("appPackageId");

        // Titre de la popup de notification
        APP_RATE_TITLE = call.getString("titleAppRate", "Failed TITLE for %@%");
        APP_RATE_TITLE = APP_RATE_TITLE.replace("%@%", APP_NAME);

        // Texte de la popup de notification
        APP_RATE_MESSAGE = call.getString("messageAppRate", "Failed MESSAGE for %@%");
        APP_RATE_MESSAGE = APP_RATE_MESSAGE.replace("%@%", APP_NAME);

        // Nombre de jour avant ouverture de la popup
        LAUNCHES_UNTIL_PROMPT = call.getInt("untilPrompt", 3);

        // Nombre d'ouverture de l'application avant ouverture de la popup
        DAYS_UNTIL_PROMPT = call.getInt("daysUntilPrompt", 3);

        // Texte du bouton positif
        POSITIVE_BUTTON_TEXT = call.getObject("positifBtnStyle").getString("text", "Yes, Sure");
        // Couleur du bouton positif
        POSITIVE_BUTTON_COLOR = call.getObject("negatifBtnStyle").getString("color", "#000000");

        // Texte du bouton neutre
        NETRAL_BUTTON_TEXT = call.getObject("netralBtnStyle").getString("text", "Remind me Later");
        // Couleur du bouton neutre
        NETRAL_BUTTON_COLOR = call.getObject("netralBtnStyle").getString("color", "#000000");

        // Texte du bouton negatif
        NEGATIVE_BUTTON_TEXT = call.getObject("negatifBtnStyle").getString("text", "Don't Ask Again");
        // Couleur du bouton negatif
        NEGATIVE_BUTTON_COLOR = call.getObject("negatifBtnStyle").getString("color", "#000000");


        try {
            // lancement de la popup
            app_launched(getContext(), call);
            JSObject ret = new JSObject();
            ret.put("appRateInit", IS_INIT);
            call.resolve(ret);
        } catch (Exception ex) {
            call.reject(ex.getMessage(), ex);
        }


    }

    @PluginMethod()
    public void checkAppStatus(PluginCall call) {
        // Id du package de l'application
        APP_PACKAGE_ID = call.getString("appPackageId");
        boolean isLive = isAppLiveOnPlayStore(APP_PACKAGE_ID, call);
        if (isLive) {
            JSObject ret = new JSObject();
            ret.put("appOnPlay", STATUT_APP_IS_PUBLISH);
            notifyListeners("appIsPublishEvent", ret);
        } else {
            JSObject ret = new JSObject();
            ret.put("appOnPlay", STATUT_APP_IS_NOT_PUBLISH);
            notifyListeners("appIsPublishEvent", ret);
        }

        call.resolve();

    }

    @PluginMethod()
    public void app_launched(Context mContext, PluginCall call) {
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
        Long date_firstLaunch = prefs.getLong("date_firstlaunch", 0);
        if (date_firstLaunch == 0) {
            date_firstLaunch = System.currentTimeMillis();
            editor.putLong("date_firstlaunch", date_firstLaunch);
        }

        // Wait at least n days before opening
        if (launch_count >= LAUNCHES_UNTIL_PROMPT) {
            if (System.currentTimeMillis() >= date_firstLaunch +
                    (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)) {
                showRateDialog(mContext, editor, call);
            }
        }
        editor.apply();
    }


    @PluginMethod()
    public void showRateDialog(final Context mContext, final SharedPreferences.Editor editor, final PluginCall call) {
        AlertDialog materialAlertDialogBuilder = new AlertDialog.Builder(mContext, THEME_DIALOG)
                .setTitle(APP_RATE_TITLE)
                .setMessage(APP_RATE_MESSAGE)
                .setPositiveButton(POSITIVE_BUTTON_TEXT, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean isLive = isAppLiveOnPlayStore(APP_PACKAGE_ID, call);
                        if (isLive) {
                            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + APP_PACKAGE_ID)));
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
                            ret.put("appOnPlay", STATUT_APP_IS_NOT_PUBLISH);
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

        materialAlertDialogBuilder.show();
        materialAlertDialogBuilder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor(POSITIVE_BUTTON_COLOR));
        materialAlertDialogBuilder.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.parseColor(NETRAL_BUTTON_COLOR));
        materialAlertDialogBuilder.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor(NEGATIVE_BUTTON_COLOR));
    }

    public boolean isAppLiveOnPlayStore(String appid, PluginCall call) {

        try {
            HttpURLConnection conn = (HttpURLConnection) (new URL("https://play.google.com/store/apps/details?id=" + appid))
                    .openConnection();
            conn.setUseCaches(false);
            conn.connect();
            int status = conn.getResponseCode();
            conn.disconnect();
            return status == 200;
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
        return false;
    }


}
