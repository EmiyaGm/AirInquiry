package com.example.AirInquiry.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 读取文本
 * Created by yxd on 2015/5/18.
 */
public class TextReader {

    public String readText(InputStream is, String encoding) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, encoding));
        StringBuilder builder = new StringBuilder(10240);
        String line;
        while((line = reader.readLine()) != null) {
            if (builder.length() > 0) builder.append('\n');
            builder.append(line);
        }
        reader.close();
        return builder.toString();
    }
}
