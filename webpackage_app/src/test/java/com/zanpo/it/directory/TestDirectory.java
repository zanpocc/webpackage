package com.zanpo.it.directory;

import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/11 21:03
 */
public class TestDirectory {

    @Test
    public void testListDirs(){
        String path = "/";

        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files) {
            if(f.isDirectory()){
                String name = f.getName();
                if(name.startsWith(".")){
                    continue;
                }
                System.out.println(name);
            }
        }
    }

}
