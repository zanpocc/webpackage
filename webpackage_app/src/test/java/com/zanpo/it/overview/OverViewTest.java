package com.zanpo.it.overview;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/28 21:39
 */
public class OverViewTest {

    //
    // os.name:Mac OS X
    // os.version:10.14.5
    // user.name:cg
    @Test
    public void testJavaProperty(){
        Properties properties = System.getProperties();
        Set<Object> keys = properties.keySet();
        for(Object key : keys){
            System.out.println(String.format("%s:%s",key,properties.getProperty(key+"")));
        }
    }

    // PATH:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Applications/VMware Fusion.app/Contents/Public:/usr/local/go/bin:/opt/X11/bin:/Applications/Wireshark.app/Contents/MacOS
    // PWD:当前目录
    @Test
    public void testJavaEnv(){
        Map<String, String> properties = System.getenv();
        Set<String> keys = properties.keySet();
        for(String key : keys){
            System.out.println(String.format("%s:%s",key,properties.get(key)));
        }
    }
}
