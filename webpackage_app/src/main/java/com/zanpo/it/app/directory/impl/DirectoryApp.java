package com.zanpo.it.app.directory.impl;

import com.zanpo.it.appapi.directory.IDirectoryApp;
import com.zanpo.it.dto.directory.PathNodeOutputDto;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/11 21:02
 */
@Component
@SuppressWarnings("unchecked")
public class DirectoryApp implements IDirectoryApp {
    @Override
    public List<PathNodeOutputDto> getChildrenDirs(String path) {
        File file = new File(path);
        if(!file.isDirectory()){
            return Collections.EMPTY_LIST;
        }

        List<String> dirs = new ArrayList();
        File[] files = file.listFiles();
        for (File f : files) {
            if(f.isDirectory()){
                String name = f.getName();
                if(name.startsWith(".")){
                    continue;
                }
                dirs.add(name);
            }
        }
        Collections.sort(dirs, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });

        List<PathNodeOutputDto> result = new ArrayList<>(dirs.size());
        Random random = new Random();
        for(String item : dirs){
            PathNodeOutputDto pathNodeOutputDto = new PathNodeOutputDto();
            pathNodeOutputDto.setLabel(item);
            pathNodeOutputDto.setChildren(Collections.EMPTY_LIST);
            pathNodeOutputDto.setId(random.nextInt());
            result.add(pathNodeOutputDto);
        }
        return result;
    }
}
