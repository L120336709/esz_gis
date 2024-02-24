package com.sundata.edu.controller.h5;

import java.util.Date;
import java.util.List;

import com.sundata.edu.domain.Schoolgis;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.service.ISchoolgisService;
import com.sundata.edu.util.ExcelUtil;
import com.sundata.edu.util.file.FileUploadUtils;
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
import com.sundata.edu.domain.Schoolpic;
import com.sundata.edu.service.ISchoolpicService;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

/**
 * 学校图片表Controller
 * 
 * @author ljg
 * @date 2024-02-22
 */
@Controller
@RequestMapping("/gis/schoolpic")
public class SchoolpicController extends BaseController
{
    private String prefix = "gis/schoolpic";

    @Autowired
    private ISchoolpicService schoolpicService;

    @RequiresPermissions("gis:schoolpic:view")
    @GetMapping()
    public String schoolpic()
    {
        return prefix + "/schoolpic";
    }

    @Autowired
    private ISchoolgisService schoolgisService;

    @PostMapping("/common/upload")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file, String filetype, String taskId)
    {
        try
        {

            UserinfoVo userinfo = (UserinfoVo) SecurityUtils.getSubject().getPrincipal();

            //图片file转base64
            BASE64Encoder encoder = new BASE64Encoder();
            String file64 = "data:image/png;base64," + encoder.encode(file.getBytes());

            // 上传文件路径
            //String filePath = RuoYiConfig.getUploadPath();
            //File directory = new File("");//设定为当前文件夹
            //filePath=directory.getAbsolutePath()+"/gongzzh/WEB-INF/classes/static/img/picture";

            //String newprofile = "F:/05-SDProject/WorkSpace/01_work_project/esz/esz_gis/src/main/resources/static/";
            String newprofile =  "/data/project/gis/apache-tomcat-8.5.95/esz_gis/WEB-INF/classes/static/";

            String filePath=newprofile+"img/1/"+taskId+"/"+userinfo.getUserId();

            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);

            //String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            String url ="/img"+fileName.split("img")[1];

            Schoolpic schoolpic=new Schoolpic();


            schoolpic.setCreatepeople(userinfo.getRealName());
            //jzrdUploadfiles.setBase64(file64);
            schoolpic.setPicadd(url);
            schoolpic.setPicname(file.getOriginalFilename());
            schoolpic.setCreatetime(new Date());

            schoolpicService.insertSchoolpic(schoolpic);


            ajax.put("fileName",  file.getOriginalFilename());
            ajax.put("url", schoolpic.getPicadd());
            //  ajax.put("url", schoolpic.getId());
            ajax.put("base64", file64);
            ajax.put("bytes", file.getSize());
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 查询学校图片表列表
     */
    @RequiresPermissions("gis:schoolpic:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Schoolpic schoolpic)
    {
        startPage();
        List<Schoolpic> list = schoolpicService.selectSchoolpicList(schoolpic);
        return getDataTable(list);
    }

    /**
     * 导出学校图片表列表
     */
    @RequiresPermissions("gis:schoolpic:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Schoolpic schoolpic)
    {
        List<Schoolpic> list = schoolpicService.selectSchoolpicList(schoolpic);
        ExcelUtil<Schoolpic> util = new ExcelUtil<Schoolpic>(Schoolpic.class);
        return util.exportExcel(list, "学校图片表数据");
    }

    /**
     * 新增学校图片表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存学校图片表
     */
    @RequiresPermissions("gis:schoolpic:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Schoolpic schoolpic)
    {
        return toAjax(schoolpicService.insertSchoolpic(schoolpic));
    }

    /**
     * 修改学校图片表
     */
    @RequiresPermissions("gis:schoolpic:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Schoolpic schoolpic = schoolpicService.selectSchoolpicById(id);
        mmap.put("schoolpic", schoolpic);
        return prefix + "/edit";
    }

    /**
     * 修改保存学校图片表
     */
    @RequiresPermissions("gis:schoolpic:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Schoolpic schoolpic)
    {
        return toAjax(schoolpicService.updateSchoolpic(schoolpic));
    }

    /**
     * 删除学校图片表
     */
    @RequiresPermissions("gis:schoolpic:remove")
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(schoolpicService.deleteSchoolpicByIds(ids));
    }
}
