package com.sundata.edu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.sundata.edu.dao.SchoolgisMapper;
import com.sundata.edu.framework.exception.ServiceException;
import com.sundata.edu.util.Convert;
import com.sundata.edu.util.StringUtils;
import com.sundata.edu.vo.UserinfoVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sundata.edu.domain.Schoolgis;
import com.sundata.edu.service.ISchoolgisService;

/**
 * 学校信息表Service业务层处理
 * 
 * @author ljg
 * @date 2024-02-22
 */
@Service
public class SchoolgisServiceImpl implements ISchoolgisService 
{
    @Autowired
    private SchoolgisMapper schoolgisMapper;

    /**
     * 导入
     * @param schoolgisList
     * @param updateSupport
     * @return
     */
    public String SchoolgisListImport(List<Schoolgis>  schoolgisList, boolean updateSupport){
        if (StringUtils.isNull(schoolgisList) || schoolgisList.size() == 0)
        {
            throw new ServiceException("导入学校GIS数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        List<Schoolgis> schoolgisListinsert=new ArrayList<>();
        List<Schoolgis> schoolgisListupdate=new ArrayList<>();
        List<String> Names=new ArrayList<>();

        for (Schoolgis Schoolgis : schoolgisList)
        {
            Names.add(Schoolgis.getName()+"");
        }

        //查询已经存在的数据
        List<Schoolgis> schoolgiss=schoolgisMapper.findSelectSchoolgis(Names);

        UserinfoVo userinfo = (UserinfoVo) SecurityUtils.getSubject().getPrincipal();
        //把存在的和不存在的数据分别保存，进行插入和更新
        for(Schoolgis schoolgisnew : schoolgisList){
            //判断是否存在，存在放入exaPostgraduatelistupdate，不存在放入exaPostgraduatelistinsert
            boolean i=false;//用于判断是否已经放入存在的集合

            for (Schoolgis schoolgisold:schoolgiss){
                if(schoolgisnew.getName().equals(schoolgisold.getName())){
                    schoolgisnew.setId(schoolgisold.getId());
                    schoolgisnew.setValue(schoolgisnew.getName());
                    schoolgisnew.setUpdatepeople(userinfo.getUserId());
                    schoolgisnew.setUpdatetime(new Date());

                    schoolgisnew.setCreatepeople(schoolgisold.getCreatepeople());
                    schoolgisnew.setCreatetime(schoolgisold.getCreatetime());
                    schoolgisListupdate.add(schoolgisnew);
                    i=true;
                }
            }
            if(i==false){

                schoolgisnew.setValue(schoolgisnew.getName());
                schoolgisnew.setCreatepeople(userinfo.getUserId());
                schoolgisnew.setCreatetime(new Date());
                schoolgisListinsert.add(schoolgisnew);
            }
        }
        //分别对新增数据和更新数据进行操作
        if(schoolgisListinsert.size()>0){
            schoolgisMapper.insertSchoolgisList(schoolgisListinsert);
            successNum=successNum+schoolgisListinsert.size();
            for(Schoolgis mes : schoolgisListinsert){
                successMsg.append("<br/>" +  mes.getName() + " 学校GIS信息 导入成功");
            }

        }
        if(schoolgisListupdate.size()>0){
            if(updateSupport){
                schoolgisMapper.updateSchoolgisList(schoolgisListupdate);
                successNum=successNum+schoolgisListupdate.size();
                for(Schoolgis mes : schoolgisListupdate){
                    successMsg.append("<br/>" + mes.getName() + "学校GIS信息 " + " 更新成功");
                }
            }else {
                failureNum=failureNum+schoolgisListupdate.size();
                for(Schoolgis mes : schoolgisListupdate){
                    failureMsg.append("<br/>" + mes.getName() + "学校GIS信息 "  + " 已存在");
                }

            }
        }

        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString(),new Throwable());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    /**
     * 查询学校信息表
     * 
     * @param id 学校信息表主键
     * @return 学校信息表
     */
    @Override
    public Schoolgis selectSchoolgisById(Long id)
    {
        return schoolgisMapper.selectSchoolgisById(id);
    }

    /**
     * 查询学校信息表列表
     * 
     * @param schoolgis 学校信息表
     * @return 学校信息表
     */
    @Override
    public List<Schoolgis> selectSchoolgisList(Schoolgis schoolgis)
    {
        return schoolgisMapper.selectSchoolgisList(schoolgis);
    }

    /**
     * 新增学校信息表
     * 
     * @param schoolgis 学校信息表
     * @return 结果
     */
    @Override
    public int insertSchoolgis(Schoolgis schoolgis)
    {
        return schoolgisMapper.insertSchoolgis(schoolgis);
    }

    /**
     * 修改学校信息表
     * 
     * @param schoolgis 学校信息表
     * @return 结果
     */
    @Override
    public int updateSchoolgis(Schoolgis schoolgis)
    {
        return schoolgisMapper.updateSchoolgis(schoolgis);
    }

    /**
     * 批量删除学校信息表
     * 
     * @param ids 需要删除的学校信息表主键
     * @return 结果
     */
    @Override
    public int deleteSchoolgisByIds(String ids)
    {
        return schoolgisMapper.deleteSchoolgisByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学校信息表信息
     * 
     * @param id 学校信息表主键
     * @return 结果
     */
    @Override
    public int deleteSchoolgisById(Long id)
    {
        return schoolgisMapper.deleteSchoolgisById(id);
    }
}
