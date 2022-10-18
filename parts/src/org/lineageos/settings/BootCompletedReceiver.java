/*
 * Copyright (C) 2015 The CyanogenMod Project
 *               2017-2019 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.settings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import vendor.xiaomi.hardware.touchfeature.V1_0.ITouchFeature;

import org.lineageos.settings.dirac.DiracUtils;
import org.lineageos.settings.doze.DozeUtils;
import org.lineageos.settings.utils.FileUtils;

public class BootCompletedReceiver extends BroadcastReceiver {

    private static final boolean DEBUG = true;
    private static final String TAG = "XiaomiParts";

    public static final String SHARED_STYLUS = "shared_stylus";
    public static final String SHARED_KEYBOARD = "shared_keyboard";

    private ITouchFeature mTouchFeature;

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (DEBUG) Log.d(TAG, "Received boot completed intent");
        DiracUtils.initialize(context);
        DozeUtils.checkDozeService(context);
        SharedPreferences stylus_prefs = context.getSharedPreferences(SHARED_STYLUS, Context.MODE_PRIVATE);
        SharedPreferences keyboard_prefs = context.getSharedPreferences(SHARED_KEYBOARD, Context.MODE_PRIVATE);
        Log.d(TAG, "SHARED_STYLUS"+stylus_prefs.getInt(SHARED_STYLUS, 1));
        try {
            mTouchFeature = ITouchFeature.getService();
            mTouchFeature.setTouchMode(20, stylus_prefs.getInt(SHARED_STYLUS, 1));
        } catch (Exception e) {
        }
        Log.d(TAG, "SHARED_KEYBOARD"+keyboard_prefs.getInt(SHARED_KEYBOARD, 1));
        if (keyboard_prefs.getInt(SHARED_KEYBOARD, 1) == 1)
            FileUtils.writeLine("/sys/devices/platform/soc/soc:xiaomi_keyboard/xiaomi_keyboard_conn_status", "enable_keyboard");
        else
            FileUtils.writeLine("/sys/devices/platform/soc/soc:xiaomi_keyboard/xiaomi_keyboard_conn_status", "disable_keyboard");
    }
}
