package com.xinxiaolong.widget.toolkit.popupwindow.indicator;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.xinxiaolong.widget.toolkit.popupwindow.R;

/**
 * Created by xiaolong on 2017/7/22.
 * email：xinxiaolong123@foxmail.com
 */

public class IndicatorPopWindow extends PopupWindow{
    Context mContext;
    View contentView;
    LinearLayout ll_content;
    IndicatorTopView indicatorTopView;
    IndicatorBottomView indicatorBottomView;
    int popupWidth;
    int popupHeight;

    public IndicatorPopWindow(Context context) {
        mContext = context;
        init();
        initContentView();
    }

    private void init() {
        ColorDrawable cd = new ColorDrawable(0x000000);
        setBackgroundDrawable(cd);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setAnimationStyle(R.style.hide_display_stype);
        setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
    }

    public void initContentView() {
        contentView = View.inflate(mContext, R.layout.indicator_popwin_content_layout, null);
        setContentView(contentView);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        indicatorTopView = (IndicatorTopView) contentView.findViewById(R.id.triangle_view);
        indicatorBottomView=(IndicatorBottomView)contentView.findViewById(R.id.reverse_triangle_view);
        ll_content=(LinearLayout)contentView.findViewById(R.id.ll_content);
        popupWidth = contentView.getMeasuredWidth();
        popupHeight = contentView.getMeasuredHeight();
    }

    public void removeAllChildView(){
        ll_content.removeAllViews();
    }

    public void addChildView(View childView){
        ll_content.addView(childView);
        contentView.measure(0, 0);
        popupWidth = contentView.getMeasuredWidth();
        popupHeight = contentView.getMeasuredHeight();
    }

    /**
     *  期望显示在targetView的中心位置
     * @param targetView
     */
    public void show(View targetView) {
        backgroundAlpha(0.6f);
        //pop的中心点和targetView中心点对其，计算pop的开始X,Y位置
        showAtLocation(targetView, Gravity.NO_GRAVITY, getShowStartX(targetView), getShowStartY(targetView));
        moveIndicator(targetView);
    }

    /**
     * 根据目标View计算显示的X坐标
     *
     * @param targetView
     * @return
     */
    private int getShowStartX(View targetView) {
        int targetX =getLocationOnScreenX(targetView);
        //targetView的中心点X坐标
        int targetCenterX = targetX + targetView.getWidth() / 2;
        //pop的中心点和targetView中心点对其，计算pop的开始X位置
        return targetCenterX - popupWidth / 2;
    }

    /**
     * 根据目标View计算显示的Y坐标
     *
     * @param targetView
     * @return
     */
    private int getShowStartY(View targetView) {
        int showStartY;
        int targetY = getLocationOnScreenY(targetView);

        //对于Y轴的判断，稍微复杂一些。默认显示在view的下方。当下方显示不下，需要转换成上方。
        if (canShowDrop(targetView)) {
            showStartY = targetY + targetView.getHeight();
        } else {
            showStartY = targetY - popupHeight;
        }
        return showStartY;
    }

    private boolean canShowDrop(View targetView){
        int targetY = getLocationOnScreenY(targetView);
        int showDropEndY = targetY + targetView.getHeight() + popupHeight;
        //是否可以显示在下方不被遮盖
        boolean canshowDrop = showDropEndY <= getScreenHeight();
        return canshowDrop;
    }


    private int getLocationOnScreenX(View targetView){
        int[] location = new int[2];
        targetView.getLocationOnScreen(location);
        int targetX = location[0];
        return targetX;
    }

    private int getLocationOnScreenY(View targetView){
        int[] location = new int[2];
        targetView.getLocationOnScreen(location);
        int targetY = location[1];
        return targetY;
    }

    /**
     * 移动指示标,将指标的中心对准targetView的中心
     *
     * @param targetView
     */
    private void moveIndicator(View targetView) {
        int startX = getShowStartX(targetView);
        int moveToX = popupWidth / 2;
        if (startX < 0) {
            moveToX = moveToX + startX;
        } else if (startX + popupWidth > getScreenWidth()) {
            moveToX = moveToX + startX + popupWidth - getScreenWidth();
        }

        View triangleView=getTriangleView(targetView);
        triangleView.setVisibility(View.VISIBLE);
        AbsoluteLayout.LayoutParams layoutParams = (AbsoluteLayout.LayoutParams) triangleView.getLayoutParams();
        layoutParams.x = moveToX - triangleView.getMeasuredWidth() / 2;
        triangleView.setLayoutParams(layoutParams);
    }


    private View getTriangleView(View targetView){
        if(canShowDrop(targetView)){
            indicatorBottomView.setVisibility(View.GONE);
            return indicatorTopView;
        }
        indicatorTopView.setVisibility(View.GONE);
        return indicatorBottomView;
    }

    private int getScreenHeight() {
        WindowManager wm = ((Activity) mContext).getWindowManager();
        return wm.getDefaultDisplay().getHeight();
    }

    private int getScreenWidth() {
        WindowManager wm = ((Activity) mContext).getWindowManager();
        return wm.getDefaultDisplay().getWidth();
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        ((Activity) mContext).getWindow().setAttributes(lp);
    }
}
