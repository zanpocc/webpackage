//package com.zanpo.it.model.software;
//
//import org.apache.velocity.runtime.Runtime;
//
//import java.io.IOException;
//import java.io.InputStream;
//
///**
// * 添加类说明
// *
// * @author cg
// * @date 2020/11/15 23:24
// */
//public class SoftwareModel {
//
//    public static void main(String[] args) throws IOException {
//        Process ls = Runtime.getRuntime().exec("mysqld");
//        InputStream inputStream = ls.getInputStream();
//        byte[] buff = new byte[4096];
//        int read = inputStream.read(buff);
//        System.out.println(new String(buff));
//    }
//}
