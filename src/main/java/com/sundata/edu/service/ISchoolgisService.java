package com.sundata.edu.service;

import java.util.List;
import com.sundata.edu.domain.Schoolgis;

/**
 * 学校信息表Service接口
 * 
 * @author ljg
 * @date 2024-02-22
 */
public interface ISchoolgisService 
{
    /**
     * 查询学校信息表
     * 
     * @param id 学校信息表主键
     * @return 学校信息表
     */
    public Schoolgis selectSchoolgisById(Long id);

    String SchoolgisListImport(List<Schoolgis> SchoolgisList, boolean updateSupport);
    /**
     * 查询学校信息表列表
     * 
     * @param schoolgis 学校信息表
     * @return 学校信息表集合
     */
    public List<Schoolgis> selectSchoolgisList(Schoolgis schoolgis);

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
     * 批量删除学校信息表
     * 
     * @param ids 需要删除的学校信息表主键集合
     * @return 结果
     */
    public int deleteSchoolgisByIds(String ids);

    /**
     * 删除学校信息表信息
     * 
     * @param id 学校信息表主键
     * @return 结果
     */
    public int deleteSchoolgisById(Long id);
}
