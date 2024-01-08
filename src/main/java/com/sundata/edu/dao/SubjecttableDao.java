package com.sundata.edu.dao;

import com.sundata.edu.domain.Subjecttable;
import com.sundata.edu.framework.core.Mapper;

import java.util.List;

/**
 *  数据层
 * 
 * @author whj
 * @date 2021-10-28 10:22:25
 */
public interface SubjecttableDao extends Mapper<Subjecttable> {

    List<Subjecttable> selectSubjecttable(Subjecttable subjecttable);

	
}