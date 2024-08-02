package com.github._1mag1n33dev.HubHaven.Utils;

public class StringUtils {
    public static String createIndent(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }
}
