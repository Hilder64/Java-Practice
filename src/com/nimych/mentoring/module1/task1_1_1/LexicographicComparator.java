package com.nimych.mentoring.module1.task1_1_1;

import java.util.Comparator;

public class LexicographicComparator implements Comparator<String> {

    @Override
    public int compare(String str1, String str2) {
        for (int i = 0; i < str1.length() && i < str2.length(); i++) {
            if ((int) str1.charAt(i) != (int) str2.charAt(i)) {
                return (int) str1.charAt(i) - (int) str2.charAt(i);
            }
        }
        return (str1.length() - str2.length());
    }
}
