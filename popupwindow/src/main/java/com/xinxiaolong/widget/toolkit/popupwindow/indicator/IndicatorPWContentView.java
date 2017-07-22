package com.xinxiaolong.widget.toolkit.popupwindow.indicator;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by xiaolong on 2017/7/22.
 * email：xinxiaolong123@foxmail.com
 */

public class IndicatorPWContentView extends LinearLayout {

    public IndicatorPWContentView(Context context) {
        super(context);
    }

    public IndicatorPWContentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(MeasureSpec.UNSPECIFIED, heightMeasureSpec);
    }
}
