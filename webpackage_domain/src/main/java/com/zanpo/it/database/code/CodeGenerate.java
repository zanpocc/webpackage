package com.zanpo.it.database.code;

import com.zanpo.it.database.code.entity.FileTemplate;
import com.zanpo.it.database.code.entity.GenCode;
import com.zanpo.it.exception.BaseException;
import com.zanpo.it.utils.VelocityUtils;
import org.apache.velocity.VelocityContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/4 22:17
 */
@Component
public class CodeGenerate {

    public void generateCode(String path, List<GenCode> genCodes, VelocityContext ctx) {
        File parentPath = new File(path);
        makeDirExist(parentPath);

        for (GenCode genCode : genCodes) {
            String realPath = parentPath + "/" + genCode.getPath();
            File file = new File(realPath);
            makeDirExist(file);
            List<FileTemplate> fileTemplates = genCode.getFileTemplates();
            for (FileTemplate fileTemplate : fileTemplates) {
                String genFile = makeDirExist(realPath + "/" + fileTemplate.getDirs()) + "/" + fileTemplate.getFile();
                String template = fileTemplate.getTemplate();
                try {
                    VelocityUtils.merge(ctx, template, genFile);
                } catch (IOException e) {
                    throw new BaseException("CodeGenerate.generateCode:" + e);
                }
            }
        }

    }

    private File makeDirExist(File parentPath) {
        if (parentPath.isFile()) {
            throw new BaseException("CodeGenerate.makeDirExist:错误路径");
        }
        if (!parentPath.exists()) {
            boolean mkdir = parentPath.mkdirs();
            if (!mkdir) {
                throw new BaseException("CodeGenerate.makeDirExist:创建目录失败:" + parentPath.getAbsolutePath());
            }
        }

        return parentPath;
    }

    private String makeDirExist(String path) {
        File file = new File(path);
        file = makeDirExist(file);
        return file.getAbsolutePath();
    }
}
