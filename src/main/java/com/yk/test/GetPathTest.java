package com.yk.test;

import java.io.File;
import java.io.IOException;

/**
 * Created by dylanyang on 12/1/15.
 */
public class GetPathTest {
    public static void main(String[] args) throws IOException {
        File directory = new File("..");
        System.out.println(directory.getCanonicalPath());
        System.out.println(directory.getAbsolutePath());
        System.out.println(directory.getPath());
        System.out.println(System.getProperty("user.dir"));
                //得到的是C:/
                //得到的是C:/test/..

    }
}
