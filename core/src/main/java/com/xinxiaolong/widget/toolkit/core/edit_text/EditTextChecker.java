package com.xinxiaolong.widget.toolkit.core.edit_text;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaolong on 2018/3/9.
 * email：xinxiaolong123@foxmail.com
 */

public class EditTextChecker {

    private EditText editText;
    private List<Checker> checkerList=new ArrayList<>();

    public EditTextChecker(EditText editText){
        this.editText=editText;
        initEvent();
    }

    private void initEvent(){
        editText.addTextChangedListener(textWatcher);
    }


    public void addChecker(Checker checker){
        checkerList.add(checker);
    }


    TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
             String text=s.toString();
             for (Checker checker:checkerList){
                 boolean matches=checker.isMatches(text);
                 if(!checker.isMatches(text)){
                     showToast(checker.getHint());

                     break;
                 }
             }
        }
    };




    private void showToast(String text){
        Toast.makeText(editText.getContext(),text,Toast.LENGTH_SHORT).show();
    }
}
