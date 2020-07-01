package com.fanyitai.text.demo.util;

public class ExceptionLogUtils {

    public static String E2String(Throwable e) {
        StringBuilder sb = new StringBuilder();
        if (e!=null){
            sb.append(e.toString());
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                sb.append("\n\tat:");
                sb.append(stackTraceElement);
            }
        }
        return sb.toString();
    }
}
