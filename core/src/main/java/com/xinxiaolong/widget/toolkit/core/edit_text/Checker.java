package com.xinxiaolong.widget.toolkit.core.edit_text;

/**
 * Created by xiaolong on 2018/3/9.
 * email：xinxiaolong123@foxmail.com
 */

public interface Checker {

    boolean isMatches(String input);

    String getHint();
}
