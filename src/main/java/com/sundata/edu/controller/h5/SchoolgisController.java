package com.sundata.edu.controller.h5;

import java.util.List;

import com.sundata.edu.domain.Userinfo;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.util.ExcelUtil;
import com.sundata.edu.vo.UserinfoVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sundata.edu.domain.Schoolgis;
import com.sundata.edu.service.ISchoolgisService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 学校信息表Controller
 * 
 * @author ljg
 * @date 2024-02-22
 */
@Controller
@RequestMapping("/gis/schoolgis")
public class SchoolgisController extends BaseController
{
    private String prefix = "gis/schoolgis";

    @Autowired
    private ISchoolgisService schoolgisService;

    @RequiresPermissions("gis:schoolgis:view")
    @GetMapping()
    public String schoolgis()
    {
        return prefix + "/schoolgis";
    }

    /**
     * 查询学校信息表列表
     */
    @RequiresPermissions("gis:schoolgis:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Schoolgis schoolgis)
    {
        startPage();
        List<Schoolgis> list = schoolgisService.selectSchoolgisList(schoolgis);
        return getDataTable(list);
    }

    @PostMapping("/getSchool")
    @ResponseBody
    public UserinfoVo getSchool(){
        UserinfoVo userinfo = (UserinfoVo) SecurityUtils.getSubject().getPrincipal();
        return userinfo;
    }

    @PostMapping("/getSchoolgis")
    @ResponseBody
    public List<Schoolgis> getSchoolgis(Schoolgis schoolgis){
        List<Schoolgis> list = schoolgisService.selectSchoolgisList(schoolgis);
        return list;
    }

    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<Schoolgis> util = new ExcelUtil<Schoolgis>(Schoolgis.class);
        List<Schoolgis> SchoolgisList = util.importExcel(file.getInputStream());
        String message = schoolgisService.SchoolgisListImport(SchoolgisList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 下载教师资格证考场管理导入模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<Schoolgis> util = new ExcelUtil<Schoolgis>(Schoolgis.class);

        return util.importTemplateExcel("学校GIS信息");
    }


    /**
     * 导出学校信息表列表
     */
    @RequiresPermissions("gis:schoolgis:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Schoolgis schoolgis)
    {
        List<Schoolgis> list = schoolgisService.selectSchoolgisList(schoolgis);
        ExcelUtil<Schoolgis> util = new ExcelUtil<Schoolgis>(Schoolgis.class);
        return util.exportExcel(list, "学校信息表数据");
    }

    /**
     * 新增学校信息表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存学校信息表
     */
    @RequiresPermissions("gis:schoolgis:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Schoolgis schoolgis)
    {
        return toAjax(schoolgisService.insertSchoolgis(schoolgis));
    }

    /**
     * 修改学校信息表
     */
    @RequiresPermissions("gis:schoolgis:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Schoolgis schoolgis = schoolgisService.selectSchoolgisById(id);
        mmap.put("schoolgis", schoolgis);
        return prefix + "/edit";
    }



    /**
     * 修改保存学校信息表
     */
    @RequiresPermissions("gis:schoolgis:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Schoolgis schoolgis)
    {
        return toAjax(schoolgisService.updateSchoolgis(schoolgis));
    }

    /**
     * 删除学校信息表
     */
    @RequiresPermissions("gis:schoolgis:remove")
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(schoolgisService.deleteSchoolgisByIds(ids));
    }
}
