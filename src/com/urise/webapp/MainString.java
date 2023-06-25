package com.urise.webapp;

public class MainString {
    public static void main(String[] args) {
        String[] strArr = new String[]{"1", "2", "3", "4", "5"};
        StringBuffer sb = new StringBuffer();
        for (String str: strArr
             ) {
            sb.append(str).append(", ");
        }
        System.out.println(sb.toString());

        String str1 = "abc";
        String str3 = "c";
        String str2 = ("ab" + str3).intern();
        System.out.println(str1 == str2);
    }
}
