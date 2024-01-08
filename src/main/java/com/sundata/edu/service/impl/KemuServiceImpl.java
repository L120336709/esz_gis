package com.sundata.edu.service.impl;

import com.sundata.edu.service.KemuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * 成人高考标题Service业务层处理
 *
 * @author whj
 * @date 2021-10-25
 */
@Service
public class KemuServiceImpl  implements KemuService {

    @Autowired
    com.sundata.edu.dao.Kemu kemumapper;
    @Override
    public List<HashMap<Object, Object>> querySelect(String tableName, HashMap<Object, Object> map) {
        return kemumapper.querySelect(tableName,map);
    }



}

