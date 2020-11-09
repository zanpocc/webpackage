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
import java.util.HashMap;

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
    public void testCodeGenerateEntity(){
        GenCode genCode = new GenCode();
        genCode.setPackageName("com.zanpo.it.entity");
        genCode.setFileTemplates(Collections.singletonList(new FileTemplate("${firstUppercaseName}.java","/Volumes/Mac/IdeaPro/webpackage_parent/webpackage_domain/src/main/resources/template/Entity.vm")));
        codeGenerateService.generateEntity("/Volumes/Mac/IdeaPro/webpackage_parent/webpackage_app/target",
                genCode,"myblog");
    }

    @Test
    public void testCodeGenerateDao(){
        GenCode genCode = new GenCode();
        genCode.setPackageName("com.zanpo.it.dao");
        genCode.setFileTemplates(Collections.singletonList(new FileTemplate("${firstUppercaseName}Dao.java","/Volumes/Mac/IdeaPro/webpackage_parent/webpackage_domain/src/main/resources/template/Dao.vm")));

        HashMap<Object, Object> extraMap = new HashMap<>();
        extraMap.put("entityPackage","com.zanpo.it.entity");
        codeGenerateService.generate("/Volumes/Mac/IdeaPro/webpackage_parent/webpackage_app/target",
                genCode,"myblog",extraMap);
    }

    @Test
    public void testCodeGenerateMapper(){
        GenCode genCode = new GenCode();
        genCode.setPackageName("com.zanpo.it.mapper");
        genCode.setFileTemplates(Collections.singletonList(new FileTemplate("${firstUppercaseName}Mapper.xml","/Volumes/Mac/IdeaPro/webpackage_parent/webpackage_domain/src/main/resources/template/Mapper.vm")));

        HashMap<Object, Object> extraMap = new HashMap<>();
        extraMap.put("entityPackage","com.zanpo.it.entity");
        extraMap.put("daoPackage","com.zanpo.it.dao");
        codeGenerateService.generate("/Volumes/Mac/IdeaPro/webpackage_parent/webpackage_app/target",
                genCode,"myblog",extraMap);
    }

}
