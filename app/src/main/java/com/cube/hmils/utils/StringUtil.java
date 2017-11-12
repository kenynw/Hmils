package com.cube.hmils.utils;

import com.github.promeg.pinyinhelper.Pinyin;

/**
 * Created by Carol on 2017/11/12.
 */

public class StringUtil {

    public static String getFirstLetter(String string) {
        char charLetter = Pinyin.toPinyin(string.charAt(0)).charAt(0);
        return Character.isLetter(charLetter) ? String.valueOf(charLetter) : "";
    }

}
