//package com.zanpo.it.services.codegen;
//
//import com.zanpo.it.entity.database.code.FileTemplate;
//import com.zanpo.it.entity.database.table.TableEntity;
//import com.zanpo.it.repository.IDatabaseRepository;
//import com.zanpo.it.exception.BaseException;
//import com.zanpo.it.model.codegen.mybatis.MybatisCodeGenModelModel;
//import com.zanpo.it.utils.VelocityUtils;
//import org.apache.velocity.VelocityContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
///**
// * 添加类说明
// *
// * @author cg
// * @date 2020/11/4 22:17
// */
//@Component
//@SuppressWarnings("unchecked")
//public class CodeGenerateDomainService {
//
//    @Autowired
//    private IDatabaseRepository tableRepository;
//
//    public void generate(String path, MybatisCodeGenModelModel MybatisCodeGenModel, String schema, Map extraMap) {
//        String packageName = MybatisCodeGenModel.getPackageName();
//        // 从数据库中收集对应schema的所有表信息,map<tableName,ctx>
//        Map<String, VelocityContext> ctxMap = getVelocityCtx(schema,extraMap);
//        for (String key : ctxMap.keySet()) {
//            VelocityContext ctx = ctxMap.get(key);
//            // 对于每个都执行生成
//            List<FileTemplate> fileTemplates = MybatisCodeGenModel.getFileTemplates();
//            for (FileTemplate fileTemplate : fileTemplates) {
//                String template = fileTemplate.getTemplate();
//                String file = fileTemplate.getFile();
//                String generateCodeAbsPath = getGenerateCodeAbsPath(file, path,packageName);
//                setPackageToContext(ctx, packageName);
//                generateCodeAbsPath = VelocityUtils.replace(ctx, generateCodeAbsPath);
//                makeDirExist(generateCodeAbsPath.substring(0, generateCodeAbsPath.lastIndexOf("/")));
//                try {
//                    VelocityUtils.merge(ctx, template, generateCodeAbsPath);
//                } catch (IOException e) {
//                    throw new BaseException("CodeGenerateDomainService.generateEntity:" + e);
//                }
//            }
//        }
//    }
//
//
//    public void generateEntity(String path, MybatisCodeGenModelModel MybatisCodeGenModel, String schema) {
//        generate(path, MybatisCodeGenModel,schema,null);
//    }
//
//    private void setPackageToContext(VelocityContext ctx, String packageName) {
//        packageName = packageName.replaceAll("\\\\",".");
//        packageName = packageName.replaceAll("/",".");
//        ctx.put("packageName", packageName);
//    }
//
//    private String getGenerateCodeAbsPath(String file, String... paths) {
//        String tempPath = "";
//        for (String path : paths) {
//            path.replaceAll("\\.", "/");
//            path = path.replaceAll("\\\\", "/");
//            while (path.indexOf("//") >= 0) {
//                path = path.replaceAll("//", "/");
//            }
//            tempPath = tempPath + "/" + path;
//        }
//        String dir = tempPath.substring(1);
//        return dir + "/" + file;
//    }
//
//    private Map<String, VelocityContext> getVelocityCtx(String schema,Map<String,Object> extraMap) {
//        List<TableEntity> allTables = tableRepository.findAllTables(schema);
//        Map map = new HashMap(allTables.size());
//        for (TableEntity tableEntity : allTables) {
//            // 每张表一个context对象
//            VelocityContext ctx = obj2VelocityContext(tableEntity);
//            String tableName = tableEntity.getName();
//            map.put(tableName, ctx);
//            putCommonVelocityContext(ctx);
//            if(extraMap != null && !extraMap.isEmpty()){
//                putExtraVelocityContext(ctx,extraMap);
//            }
//        }
//
//        return map;
//    }
//
//    private void putExtraVelocityContext(VelocityContext ctx, Map<String, Object> extraMap) {
//        Set<String> keys = extraMap.keySet();
//        for (String key : keys) {
//            String s = (String) extraMap.get(key);
//            s = VelocityUtils.replace(ctx,s);
//            ctx.put(key,s);
//        }
//    }
//
//    private void putCommonVelocityContext(VelocityContext ctx) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        ctx.put("now",simpleDateFormat.format(new Date()));
//        ctx.put("user",System.getProperty("user.name"));
//    }
//
//    private VelocityContext obj2VelocityContext(Object obj) {
//        VelocityContext velocityContext = new VelocityContext();
//        Field[] fields = obj.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            field.setAccessible(true);
//            String key = field.getName();
//            Object value = null;
//            try {
//                value = field.get(obj);
//                velocityContext.put(key, value);
//            } catch (IllegalAccessException e) {
//                throw new BaseException("CodeGenerateDomainService.obj2VelocityContext");
//            }
//        }
//
//        return velocityContext;
//    }
//
//    public void generateCode(String path, List<MybatisCodeGenModelModel> mybatisCodeGenModels, VelocityContext ctx) {
//        File parentPath = new File(path);
//        makeDirExist(parentPath);
//
//        for (MybatisCodeGenModelModel MybatisCodeGenModel : mybatisCodeGenModels) {
//            String realPath = parentPath + "/" + MybatisCodeGenModel.getPackageName();
//            File file = new File(realPath);
//            makeDirExist(file);
//            List<FileTemplate> fileTemplates = MybatisCodeGenModel.getFileTemplates();
//            for (FileTemplate fileTemplate : fileTemplates) {
//                String genFile = makeDirExist(realPath) + "/" + fileTemplate.getFile();
//                // 命名也支持velocity引擎
//                genFile = VelocityUtils.replace(ctx, genFile);
//                String template = fileTemplate.getTemplate();
//                try {
//                    VelocityUtils.merge(ctx, template, genFile);
//                } catch (IOException e) {
//                    throw new BaseException("CodeGenerate.generateCode:" + e);
//                }
//            }
//        }
//
//    }
//
//    private File makeDirExist(File parentPath) {
//        if (parentPath.isFile()) {
//            throw new BaseException("CodeGenerate.makeDirExist:错误路径");
//        }
//        if (!parentPath.exists()) {
//            boolean mkdir = parentPath.mkdirs();
//            if (!mkdir) {
//                throw new BaseException("CodeGenerate.makeDirExist:创建目录失败:" + parentPath.getAbsolutePath());
//            }
//        }
//
//        return parentPath;
//    }
//
//    private String makeDirExist(String path) {
//        File file = new File(path);
//        file = makeDirExist(file);
//        return file.getAbsolutePath();
//    }
//}
