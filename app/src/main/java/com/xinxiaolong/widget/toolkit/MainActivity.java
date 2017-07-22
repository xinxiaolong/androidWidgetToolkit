package com.xinxiaolong.widget.toolkit;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xinxiaolong.widget.toolkit.popupwindow.MoreOperatePopWin;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    MoreOperatePopWin moreOperatePopWin;

    @BindView(R.id.tv_pop)
    TextView tvPop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initPopWin();
        initEvent();
    }


    private void initEvent(){
        moreOperatePopWin.setOnItemClickCallBack(new MoreOperatePopWin.OnItemClickCallBack() {
            @Override
            public void onClick(String item) {
                Toast.makeText(getBaseContext(),item,Toast.LENGTH_SHORT).show();
            }
        });

        tvPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreOperatePopWin.show(v);
            }
        });
    }

    public void initPopWin(){
        moreOperatePopWin=new MoreOperatePopWin(this);
        moreOperatePopWin.setMoreItemList(new String[]{"评论","删除","复制","屏蔽"});
    }
}
