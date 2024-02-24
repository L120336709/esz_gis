package com.sundata.edu.dao;

import java.util.List;

import com.sundata.edu.domain.Schoolgis;
import org.apache.ibatis.annotations.Param;

/**
 * 学校信息表Mapper接口
 * 
 * @author ljg
 * @date 2024-02-22
 */
public interface SchoolgisMapper 
{
    /**
     * 查询学校信息表
     * 
     * @param id 学校信息表主键
     * @return 学校信息表
     */
    public Schoolgis selectSchoolgisById(Long id);

    /**
     * 查询学校信息表列表
     * 
     * @param schoolgis 学校信息表
     * @return 学校信息表集合
     */
    public List<Schoolgis> selectSchoolgisList(Schoolgis schoolgis);

    List<Schoolgis> findSelectSchoolgis(@Param("list") List<String> name);
    /**
     * 批量插入数据
     * @param schoolgis
     * @return
     */
    int insertSchoolgisList(@Param("list")List<Schoolgis> schoolgis);
    /**
     * 批量更新数据
     * @param schoolgis
     * @return
     */
    int updateSchoolgisList(@Param("list")List<Schoolgis> schoolgis);

    /**
     * 新增学校信息表
     * 
     * @param schoolgis 学校信息表
     * @return 结果
     */
    public int insertSchoolgis(Schoolgis schoolgis);

    /**
     * 修改学校信息表
     * 
     * @param schoolgis 学校信息表
     * @return 结果
     */
    public int updateSchoolgis(Schoolgis schoolgis);

    /**
     * 删除学校信息表
     * 
     * @param id 学校信息表主键
     * @return 结果
     */
    public int deleteSchoolgisById(Long id);

    /**
     * 批量删除学校信息表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSchoolgisByIds(String[] ids);
}
