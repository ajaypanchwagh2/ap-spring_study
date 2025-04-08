package com.example.Testing.utils;

public class StringUtils {

    public static boolean isPalindrome(String input) {
        int i = 0, j = input.length() - 1;
        while (i <= j) {
            if (input.charAt(i) != input.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static String removeSpaces(String input) {
        StringBuilder sb = new StringBuilder();
        String[] st = input.split(" ");

        for (int i=0;i< st.length ; i++)
        {
            sb.append(st[i]);
        }

        System.out.println(sb);



        return sb.toString();
    }
}
