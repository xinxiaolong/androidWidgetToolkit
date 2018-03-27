package com.xinxiaolong.widget.toolkit.core.edit_text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xiaolong on 2018/3/9.
 * email：xinxiaolong123@foxmail.com
 *
 * 正则表达的验证器
 */

public class RegexChecker implements Checker{

    private RegexEnum regexEnum;
    private String hint;
    private Pattern p;

    public enum RegexEnum{
        //手机号码
        PHONE_NUM("^[0-9]{11,11}$"),
        //邮箱地址
        EMAIL("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"),
        //至少包含一个字母 长度为6-20位
        PASSWORD("^(?!(?:\\d*$))[A-Za-z0-9]{6,20}$");

        private final String value;

        RegexEnum(String value) {
            this.value = value;
        }
    }

    public RegexChecker(RegexEnum regexEnum,String hint){
        this.regexEnum=regexEnum;
        this.hint=hint;
        p=Pattern.compile(regexEnum.value);
    }

    public String getHint() {
        return hint;
    }

    @Override
    public boolean isMatches(String input) {
        Matcher m = p.matcher(input);
        return m.matches();
    }
}
