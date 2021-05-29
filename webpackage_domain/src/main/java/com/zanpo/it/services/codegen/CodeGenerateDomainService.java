package com.zanpo.it.services.codegen;

import com.zanpo.it.repository.IDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/4 22:17
 */
@Component
@SuppressWarnings("unchecked")
public class CodeGenerateDomainService {

    @Autowired
    private IDatabaseRepository databaseRepository;


}
