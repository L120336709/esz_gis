package com.sundata.edu.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sundata.edu.domain.kemudomain;
import java.util.HashMap;
import java.util.List;
/**
 *  数据层
 *
 * @author whj
 * @date 2020-05-12 16:08:47
 */
public interface Kemu  extends BaseMapper<kemudomain> {

    List<HashMap<Object,Object>> querySelect(String tableName, HashMap<Object,Object> map);


}


