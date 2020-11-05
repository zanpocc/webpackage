package com.zanpo.it.database.code.framework;

import com.zanpo.it.AppApplication;
import com.zanpo.it.appapi.database.IDataBaseApp;
import com.zanpo.it.database.code.entity.FileTemplate;
import com.zanpo.it.database.code.entity.GenCode;
import com.zanpo.it.database.code.service.CodeGenerateService;
import com.zanpo.it.dto.database.DataSourceInputDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/5 23:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppApplication.class)
public class TestCodeGenerate {
    @Autowired
    private CodeGenerateService codeGenerateService;

    @Autowired
    private IDataBaseApp dataBaseApp;

    @Before
    public void before(){
        dataBaseApp.createDataSource(new DataSourceInputDto());
    }



    @Test
    public void testCodeGenerate(){
        GenCode genCode = new GenCode();
        genCode.setPackageName("com/huawei/it");
        genCode.setFileTemplates(Collections.singletonList(new FileTemplate("entity/$name","$name.java","/Volumes/Mac/IdeaPro/webpackage_parent/webpackage_domain/target/test.vm")));
        codeGenerateService.generateEntity("/Volumes/Mac/IdeaPro/webpackage_parent/webpackage_domain/target",
                genCode,"myblog");
    }

}
