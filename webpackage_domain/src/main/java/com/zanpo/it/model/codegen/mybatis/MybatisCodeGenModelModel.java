package com.zanpo.it.model.codegen.mybatis;

import com.zanpo.it.FileUtils;
import com.zanpo.it.entity.database.table.TableEntity;
import com.zanpo.it.exception.BaseException;
import com.zanpo.it.model.codegen.AbsCodeGenModel;
import com.zanpo.it.repository.IDatabaseRepository;
import com.zanpo.it.utils.SpringUtils;
import com.zanpo.it.utils.VelocityUtils;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.velocity.VelocityContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Mybatis代码生成模型，充血模型
 *
 * @author cg
 * @date 2020/11/4 22:24
 */
@Data
public class MybatisCodeGenModelModel extends AbsCodeGenModel {
    /**
     * 代码生成的包路径，包命名
     */
    private String packageName;
    /**
     * 数据库schema
     */
    private String schema;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private IDatabaseRepository tableRepository = SpringUtils.getBean(IDatabaseRepository.class);


    /**
     * 代码生成
     *
     * @return 是否成功
     */
    public boolean generate(){
        // 从数据库中收集对应schema的所有表信息,map<tableName,ctx>
        Map<String, VelocityContext> ctxMap = getVelocityCtx(schema,extraParams);
        for (String key : ctxMap.keySet()) {
            VelocityContext ctx = ctxMap.get(key);
            // 对于每个都执行生成
            String generateCodeAbsPath = getGenerateCodeAbsPath(fileName, codeGenPath,packageName);
            generateCodeAbsPath = VelocityUtils.replace(ctx, generateCodeAbsPath);
            FileUtils.makeDirExist(generateCodeAbsPath.substring(0, generateCodeAbsPath.lastIndexOf("/")));
            try {
                VelocityUtils.merge(ctx, templateFilePath, generateCodeAbsPath);
            } catch (IOException e) {
                throw new BaseException("500","CodeGenerateDomainService.generate:" + e);
            }
        }
        return true;
    }

    private Map<String, VelocityContext> getVelocityCtx(String schema,Map<String,Object> extraMap) {
        List<TableEntity> allTables = tableRepository.findAllTables(schema);
        Map map = new HashMap(allTables.size());
        for (TableEntity tableEntity : allTables) {
            // 每张表一个context对象
            VelocityContext ctx = VelocityUtils.obj2VelocityContext(tableEntity);
            String tableName = tableEntity.getName();
            map.put(tableName, ctx);
            if(extraMap != null && !extraMap.isEmpty()){
                // 将拓展属性放入VelocityContext中
                putExtraParamsToVelocityContext(ctx,extraMap);
            }
        }

        return map;
    }

    private void putExtraParamsToVelocityContext(VelocityContext ctx, Map<String, Object> extraMap) {
        Set<String> keys = extraMap.keySet();
        for (String key : keys) {
            String s = (String) extraMap.get(key);
            s = VelocityUtils.replace(ctx,s);
            ctx.put(key,s);
        }
    }

    private String getGenerateCodeAbsPath(String file, String... paths) {
        String tempPath = "";
        for (String path : paths) {
            path.replaceAll("\\.", "/");
            path = path.replaceAll("\\\\", "/");
            while (path.indexOf("//") >= 0) {
                path = path.replaceAll("//", "/");
            }
            tempPath = tempPath + "/" + path;
        }
        String dir = tempPath.substring(1);
        return dir + "/" + file;
    }

}
