package com.sundata.edu.dao;

import java.util.List;
import com.sundata.edu.domain.Schoolpic;

/**
 * 学校图片表Mapper接口
 * 
 * @author ljg
 * @date 2024-02-22
 */
public interface SchoolpicMapper 
{
    /**
     * 查询学校图片表
     * 
     * @param id 学校图片表主键
     * @return 学校图片表
     */
    public Schoolpic selectSchoolpicById(Long id);

    /**
     * 查询学校图片表列表
     * 
     * @param schoolpic 学校图片表
     * @return 学校图片表集合
     */
    public List<Schoolpic> selectSchoolpicList(Schoolpic schoolpic);

    /**
     * 新增学校图片表
     * 
     * @param schoolpic 学校图片表
     * @return 结果
     */
    public int insertSchoolpic(Schoolpic schoolpic);

    /**
     * 修改学校图片表
     * 
     * @param schoolpic 学校图片表
     * @return 结果
     */
    public int updateSchoolpic(Schoolpic schoolpic);

    /**
     * 删除学校图片表
     * 
     * @param id 学校图片表主键
     * @return 结果
     */
    public int deleteSchoolpicById(Long id);

    /**
     * 批量删除学校图片表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSchoolpicByIds(String[] ids);
}
