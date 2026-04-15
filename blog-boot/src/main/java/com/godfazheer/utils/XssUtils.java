package com.godfazheer.utils;

import java.util.regex.Pattern;

public class XssUtils {

    private static final Pattern SCRIPT_PATTERN = Pattern.compile("<script[^>]*>[\\s\\S]*?</script>", Pattern.CASE_INSENSITIVE);
    private static final Pattern EVENT_PATTERN = Pattern.compile("\\s*(on\\w+)\\s*=\\s*['\"][^'\"]*['\"]", Pattern.CASE_INSENSITIVE);
    private static final Pattern IFRAME_PATTERN = Pattern.compile("<iframe[^>]*>[\\s\\S]*?</iframe>", Pattern.CASE_INSENSITIVE);
    private static final Pattern OBJECT_PATTERN = Pattern.compile("<object[^>]*>[\\s\\S]*?</object>", Pattern.CASE_INSENSITIVE);
    private static final Pattern EMBED_PATTERN = Pattern.compile("<embed[^>]*>", Pattern.CASE_INSENSITIVE);

    public static String clean(String value) {
        if (value == null || value.isBlank()) {
            return value;
        }
        String result = SCRIPT_PATTERN.matcher(value).replaceAll("");
        result = IFRAME_PATTERN.matcher(result).replaceAll("");
        result = OBJECT_PATTERN.matcher(result).replaceAll("");
        result = EMBED_PATTERN.matcher(result).replaceAll("");
        result = EVENT_PATTERN.matcher(result).replaceAll("");
        return result;
    }
}
