package com.sundata.edu.service.impl;

import java.util.List;

import com.sundata.edu.dao.SchoolpicMapper;
import com.sundata.edu.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sundata.edu.domain.Schoolpic;
import com.sundata.edu.service.ISchoolpicService;

/**
 * 学校图片表Service业务层处理
 * 
 * @author ljg
 * @date 2024-02-22
 */
@Service
public class SchoolpicServiceImpl implements ISchoolpicService 
{
    @Autowired
    private SchoolpicMapper schoolpicMapper;

    /**
     * 查询学校图片表
     * 
     * @param id 学校图片表主键
     * @return 学校图片表
     */
    @Override
    public Schoolpic selectSchoolpicById(Long id)
    {
        return schoolpicMapper.selectSchoolpicById(id);
    }

    /**
     * 查询学校图片表列表
     * 
     * @param schoolpic 学校图片表
     * @return 学校图片表
     */
    @Override
    public List<Schoolpic> selectSchoolpicList(Schoolpic schoolpic)
    {
        return schoolpicMapper.selectSchoolpicList(schoolpic);
    }

    /**
     * 新增学校图片表
     * 
     * @param schoolpic 学校图片表
     * @return 结果
     */
    @Override
    public int insertSchoolpic(Schoolpic schoolpic)
    {
        return schoolpicMapper.insertSchoolpic(schoolpic);
    }

    /**
     * 修改学校图片表
     * 
     * @param schoolpic 学校图片表
     * @return 结果
     */
    @Override
    public int updateSchoolpic(Schoolpic schoolpic)
    {
        return schoolpicMapper.updateSchoolpic(schoolpic);
    }

    /**
     * 批量删除学校图片表
     * 
     * @param ids 需要删除的学校图片表主键
     * @return 结果
     */
    @Override
    public int deleteSchoolpicByIds(String ids)
    {
        return schoolpicMapper.deleteSchoolpicByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学校图片表信息
     * 
     * @param id 学校图片表主键
     * @return 结果
     */
    @Override
    public int deleteSchoolpicById(Long id)
    {
        return schoolpicMapper.deleteSchoolpicById(id);
    }
}
