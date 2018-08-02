package com.freeintelligence.robotclient.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.widget.EditText;


import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.app.App;


/**
 * Name 赋睿智能
 * Date 2018/4/26
 * Time 11:36
 */

public class SpannableUtlis  {

    String str;
    EditText editText;

    public SpannableUtlis(String str, EditText editText) {
        this.str = str;
        this.editText = editText;
        SpannableString name =  new SpannableString(str);
        AbsoluteSizeSpan names = new AbsoluteSizeSpan(12, true);
        editText.setHintTextColor(App.getContext().getResources().getColor(R.color.colorGrey));
        name.setSpan(names, 0, name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText.setHint(new SpannedString(name));

    }





}
