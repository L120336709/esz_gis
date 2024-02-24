package com.sundata.edu.domain;

import com.sundata.edu.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 学校图片表对象 schoolpic
 * 
 * @author ljg
 * @date 2024-02-22
 */
public class Schoolpic
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 图片名称（展示名称） */
    @Excel(name = "图片名称", readConverterExp = "展=示名称")
    private String picname;

    /** 图片存储地址 */
    @Excel(name = "图片存储地址")
    private String picadd;

    /** 上传人 */
    @Excel(name = "上传人")
    private String createpeople;

    private Date createtime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPicname(String picname) 
    {
        this.picname = picname;
    }

    public String getPicname() 
    {
        return picname;
    }
    public void setPicadd(String picadd) 
    {
        this.picadd = picadd;
    }

    public String getPicadd() 
    {
        return picadd;
    }
    public void setCreatepeople(String createpeople) 
    {
        this.createpeople = createpeople;
    }

    public String getCreatepeople() 
    {
        return createpeople;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("picname", getPicname())
            .append("picadd", getPicadd())
            .append("createpeople", getCreatepeople())
            .toString();
    }
}
