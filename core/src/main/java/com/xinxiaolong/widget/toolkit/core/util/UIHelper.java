package com.xinxiaolong.widget.toolkit.core.util;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by xiaolong on 2017/7/22.
 * email：xinxiaolong123@foxmail.com
 */
public class UIHelper {
    /**
     * 弹出软键盘
     *
     * @param v
     */
    public static void showSoftInputMethod(EditText v) {
        v.setFocusable(true);
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) v
                .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(v, 0);
    }

    /**
     * 隐藏软键盘
     */
    public static void hideSoftInputMethod(View v) {
        v.setFocusable(true);
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) v
                .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public static void showShortToast(final Context context, final String content,int dur) {
        final Toast toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        toast.show();
        CountDownTimer cdt = new CountDownTimer(dur, dur) {

            @Override
            public void onTick(long arg0) {
            }

            @Override
            public void onFinish() {
                toast.cancel();
            }
        };

        cdt.start();
    }

    /**
     * EditText强制获取焦点
     * @param view
     */
    public static void getFocus(View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }



    private static int screenWidth, screenHeight;

    /**
     * 根据手机的分辨率从dp转换成px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从px(像素)转换成dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    /**
     * dip转px
     *
     * @param context
     * @param dp
     * @return
     */
    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * dip转px float
     *
     * @param context
     * @param dp
     * @return
     */
    public static float Dp2PxFloat(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (dp * scale + 0.5f);
    }

    /**
     * px 转 dip
     *
     * @param context
     * @param px
     * @return
     */
    public static int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }




    /**
     * 获取屏幕宽分辨率
     */
    public static int getDisplayWidth(Activity context) {
        if (screenWidth <= 0) {
            DisplayMetrics mDisplayMetrics = new DisplayMetrics();
            context.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
            screenWidth = mDisplayMetrics.widthPixels;
        }

        return screenWidth;
    }

    /**
     * 获取屏幕高分辨率
     */
    public static int getDisplayHeight(Activity context) {
        if (screenHeight <= 0) {
            DisplayMetrics mDisplayMetrics = new DisplayMetrics();
            context.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
            screenHeight = mDisplayMetrics.heightPixels;
        }

        return screenHeight;
    }



    /**
     * 获取DisplayMetrics
     *
     * @param activity
     * @return
     */
    public static DisplayMetrics getPhoneScreen(Activity activity) {
        DisplayMetrics   dm = new DisplayMetrics();

        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }



}
