package com.zanpo.it;

import com.zanpo.it.exception.BaseException;

import java.io.File;

/**
 * 文件目录工具类
 *
 * @author cg
 * @date 2020/11/15 20:30
 */
public class FileUtils {

    public static File makeDirExist(File path) {
        if (path.isFile()) {
            throw new BaseException("CodeGenerate.makeDirExist:错误路径");
        }
        if (!path.exists()) {
            boolean mkdir = path.mkdirs();
            if (!mkdir) {
                throw new BaseException("CodeGenerate.makeDirExist:创建目录失败:" + path.getAbsolutePath());
            }
        }

        return path;
    }

    public static File makeDirExist(String path) {
        File file = new File(path);
        if (file.isFile()) {
            throw new BaseException("CodeGenerate.makeDirExist:错误路径");
        }
        if (!file.exists()) {
            boolean mkdir = file.mkdirs();
            if (!mkdir) {
                throw new BaseException("CodeGenerate.makeDirExist:创建目录失败:" + file.getAbsolutePath());
            }
        }

        return file;
    }
}
