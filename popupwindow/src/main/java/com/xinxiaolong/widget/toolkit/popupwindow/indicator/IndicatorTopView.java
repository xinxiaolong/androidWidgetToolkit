package com.xinxiaolong.widget.toolkit.popupwindow.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.xinxiaolong.widget.toolkit.core.util.UIHelper;
import com.xinxiaolong.widget.toolkit.popupwindow.R;

/**
 * Created by xiaolong on 2017/7/22.
 * email：xinxiaolong123@foxmail.com
 */

public class IndicatorTopView extends View{
    private int height;
    private int with;
    private Path path;
    private Paint paint;
    private int color;
    private Context mContext;

    public IndicatorTopView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IndicatorTopView(Context context) {
        this(context,null);
    }
    public IndicatorTopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        color=getResources().getColor(R.color.color_c1);
        initPait();
    }

    private void initPait() {
        paint=new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        path=new Path();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height=getMeasuredHeight();
        with=getMeasuredHeight();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        path.moveTo(0,height);
        path.lineTo(with/2, UIHelper.Dp2Px(mContext,5));
        path.lineTo(height,with);
        path.close();
        canvas.drawPath(path,paint);
    }
}
