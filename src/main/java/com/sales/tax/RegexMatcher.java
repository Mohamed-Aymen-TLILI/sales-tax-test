package com.sales.tax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcher {
    public static String extractValue(String input, String pattern, String groupName) {
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        return matcher.matches() ? matcher.group(groupName) : "";
    }
}
