package com.jrd.itmas_client.ui;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Created by jakub on 19.06.16.
 */
public class RawCommandGenerator {
    public static String generateRawCommand(String[] args) {
        StringJoiner sj = new StringJoiner(" ");
        Arrays.asList(args).forEach(s -> sj.add(s));
        return sj.toString();
    }
}
