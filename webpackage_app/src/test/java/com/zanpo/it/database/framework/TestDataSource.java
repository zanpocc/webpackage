package com.zanpo.it.database.framework;

import com.zanpo.it.AppApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/11 12:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppApplication.class)
public class TestDataSource {

    @Autowired
    private DataSource  dataSource;

    @Test
    public void testGetDataSource(){
        System.out.println(dataSource);
    }
}
