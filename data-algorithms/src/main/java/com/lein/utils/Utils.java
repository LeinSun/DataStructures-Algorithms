package com.lein.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Lein
 * @Description contains common utils
 */

public class Utils {

  /**
   * 利用正则表达式判断字符串是否是数字
   * @param str
   * @return
   */
  public static boolean isNumeric(String str){
    String reg = "^[\\+\\-]?[\\d]+(\\.[\\d]+)?$";
    Pattern pattern = Pattern.compile(reg);
    Matcher isNum = pattern.matcher(str);
    return isNum.matches();
  }

}
