package com.zanpo.it.database.code;

import com.zanpo.it.database.code.entity.FileTemplate;
import com.zanpo.it.database.code.entity.GenCode;
import com.zanpo.it.utils.VelocityUtils;
import org.apache.velocity.VelocityContext;
import org.junit.jupiter.api.Test;

import java.util.Collections;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/4 23:14
 */
public class TestCodeGenerate {

    @Test
    public void testGenerateCode(){
        CodeGenerate codeGenerate = new CodeGenerate();
        VelocityContext ctx = new VelocityContext();
        ctx.put("name","hello world");
        GenCode genCode = new GenCode();
        genCode.setPath("com/zanpo/it");
        genCode.setFileTemplates(Collections.singletonList(new FileTemplate("test","test.java","/Volumes/Mac/IdeaPro/webpackage_parent/webpackage_domain/target/test.vm")));
        codeGenerate.generateCode("/Volumes/Mac/IdeaPro/webpackage_parent/webpackage_domain/target",Collections.singletonList(genCode),ctx);

    }

    @Test
    public void testVelocityReplace(){
        CodeGenerate codeGenerate = new CodeGenerate();
        VelocityContext ctx = new VelocityContext();
        ctx.put("name","hello world");
        String code = "test velocity replace: $name";
        String s = VelocityUtils.replace(ctx, code);
        System.out.println(s);
    }
}
