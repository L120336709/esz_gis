package com.sundata.edu.service;

import java.util.HashMap;
import java.util.List;

/**
 *  服务层
 *
 * @author whj
 * @date 2019-07-19 14:33:10
 */
public interface KemuService {

    public List<HashMap<Object,Object>> querySelect(String tableName, HashMap<Object,Object> map);
}

