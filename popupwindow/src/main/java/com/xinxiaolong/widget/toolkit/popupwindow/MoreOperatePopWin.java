package com.xinxiaolong.widget.toolkit.popupwindow;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.xinxiaolong.widget.toolkit.popupwindow.indicator.IndicatorPopWindow;

import java.util.List;

/**
 * Created by xiaolong on 2017/7/22.
 * email：xinxiaolong123@foxmail.com
 */

public class MoreOperatePopWin extends IndicatorPopWindow{

    Context mContext;

    String[] moreItemList;
    public MoreOperatePopWin(Context context) {
        super(context);
        this.mContext = context;
    }

    public void setMoreItemList(String[] moreItemList){
        this.moreItemList=moreItemList;
        addChildView();
    }


    private void addChildView() {
        removeAllChildView();
        for (int i = 0; i < moreItemList.length; i++) {
            View itemView = View.inflate(mContext, R.layout.item_more_operate_popwin_view, null);
            TextView tv = (TextView) itemView.findViewById(R.id.tv_pop);
            View lineView=itemView.findViewById(R.id.view_line);
            tv.setOnClickListener(itemOnclickListener);
            tv.setText(moreItemList[i]);
            addChildView(itemView);
            if(i==moreItemList.length-1){
                lineView.setVisibility(View.GONE);
            }
        }
    }

    public View.OnClickListener itemOnclickListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(onItemClickCallBack!=null){
                TextView tv=(TextView)v;
                onItemClickCallBack.onClick(tv.getText().toString());
            }
            dismiss();
        }
    };

    OnItemClickCallBack onItemClickCallBack;
    public interface OnItemClickCallBack{
        void onClick(String item);
    }

    public void setOnItemClickCallBack(OnItemClickCallBack onItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack;
    }
}
