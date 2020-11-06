package com.zanpo.it.utils;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/6 22:20
 */

public final class NamedUtils {

    public static final String UNDER_LINE = "_";

    /**
     * 命名转大驼峰
     *
     * @param str 字符串
     * @return 大驼峰
     */
    public static String wordUppercase(String str){
        char[] chars = str.toLowerCase().toCharArray();
        chars[0] = (char) (chars[0] - 32);
        return new String(chars);
    }

    /**
     * 转小驼峰
     *
     * @param tableName 表名
     * @return 驼峰
     */
    public static String wordsFirstLowercase(String tableName,String split){
        String[] s = tableName.split(split);
        // 最少两个才能转
        if(s.length  < 2){
            return tableName.toLowerCase();
        }

        String result = s[0].toLowerCase();
        for (int i = 1; i < s.length; i++) {
            String humpName = wordUppercase(s[i].toLowerCase());
            result += humpName;
        }

        return result;
    }

    /**
     * 表名转大驼峰
     *
     * @param tableName 表名
     * @return 驼峰
     */
    public static String wordsFirstUppercase(String tableName,String split){
        String[] s = tableName.split(split);
        // 最少两个才能转
        if(s.length  < 2){
            return wordUppercase(tableName);
        }
        String result = "";
        for (int i = 0; i < s.length; i++) {
            String humpName = wordUppercase(s[i].toLowerCase());
            result += humpName;
        }

        return result;
    }
}
