package com.xinxiaolong.widget.toolkit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xinxiaolong.widget.toolkit.core.edit_text.EditTextChecker;
import com.xinxiaolong.widget.toolkit.core.edit_text.RegexChecker;
import com.xinxiaolong.widget.toolkit.popupwindow.MoreOperatePopWin;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    MoreOperatePopWin moreOperatePopWin;
    @BindView(R.id.tv_pop)
    TextView tvPop;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initPopWin();
        initEvent();
        webView.loadUrl("file:///android_asset/logcat.html");
    }


    private void initEvent() {
        moreOperatePopWin.setOnItemClickCallBack(new MoreOperatePopWin.OnItemClickCallBack() {
            @Override
            public void onClick(String item) {
                Toast.makeText(getBaseContext(), item, Toast.LENGTH_SHORT).show();
            }
        });

        tvPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreOperatePopWin.show(v);
            }
        });

        EditTextChecker editTextChecker = new EditTextChecker(editText);
        editTextChecker.addChecker(new RegexChecker(RegexChecker.RegexEnum.PHONE_NUM, "请输入手机格式"));
    }

    public void initPopWin() {
        moreOperatePopWin = new MoreOperatePopWin(this);
        moreOperatePopWin.setMoreItemList(new String[]{"评论", "删除", "复制", "屏蔽"});
    }
}
