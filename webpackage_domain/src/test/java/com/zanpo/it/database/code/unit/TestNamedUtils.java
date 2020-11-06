package com.zanpo.it.database.code.unit;

import com.zanpo.it.utils.NamedUtils;
import org.junit.Test;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/6 22:39
 */
public class TestNamedUtils {

    @Test
    public void testNamedUtils(){
        String tableName = "cluster_t";

        System.out.println(NamedUtils.wordsFirstLowercase(tableName,NamedUtils.UNDER_LINE));
        System.out.println(NamedUtils.wordsFirstUppercase(tableName,NamedUtils.UNDER_LINE));
    }
}
