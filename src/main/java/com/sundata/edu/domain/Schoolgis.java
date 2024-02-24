package com.sundata.edu.domain;

import com.sundata.edu.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 学校信息表对象 schoolgis
 * 
 * @author ljg
 * @date 2024-02-22
 */
public class Schoolgis
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 单位名称 */
    @Excel(name = "*学校名称")
    private String name;

    /** 展示值（名称或编号） */
//    @Excel(name = "展示值", readConverterExp = "名=称或编号")
    private String value;

    /** 单位编号 */
    //@Excel(name = "单位编号")
    private String bianhao;

    /** 学校类型 */
    @Excel(name = "*学校类型",combo = "幼儿园,小学教学点,完全小学,初级中学,完全中学,中职,高级中学,高职或大学,特教学校,九年一贯制,十二年一贯制")
    private String schooltype;

    /** 所属乡镇 */
    @Excel(name = "*所属乡镇")//,例:湖北省-恩施土家族苗族自治州-恩施市-板桥镇
    private String bsc;

    /** 所属教育局 */
    @Excel(name = "*所属教育局",combo = "恩施市教育局,利川市教育局,建始县教育局,巴东县教育局,宣恩县教育局,咸丰县教育局,来凤县教育局,鹤峰县教育局")
    private String jyj;

    /** 联系人 */
    @Excel(name = "联系人")
    private String people;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 经纬度 */
    @Excel(name = "*经纬度")//,示例=109.012345,30.032121
    private String coord;

    /** 单位地址 */
    @Excel(name = "*单位地址")
    private String address;

    /** 学校图片（多张）关联图片表 */
   // @Excel(name = "学校图片", readConverterExp = "多=张")
    private String picture;

    private String createpeople;
    private String updatepeople;
    private Date createtime;
    private Date updatetime;


    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setValue(String value) 
    {
        this.value = value;
    }

    public String getValue() 
    {
        return value;
    }
    public void setBianhao(String bianhao) 
    {
        this.bianhao = bianhao;
    }

    public String getBianhao() 
    {
        return bianhao;
    }
    public void setSchooltype(String schooltype) 
    {
        this.schooltype = schooltype;
    }

    public String getSchooltype() 
    {
        return schooltype;
    }
    public void setBsc(String bsc) 
    {
        this.bsc = bsc;
    }

    public String getBsc() 
    {
        return bsc;
    }
    public void setJyj(String jyj) 
    {
        this.jyj = jyj;
    }

    public String getJyj() 
    {
        return jyj;
    }
    public void setPeople(String people) 
    {
        this.people = people;
    }

    public String getPeople() 
    {
        return people;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setCoord(String coord) 
    {
        this.coord = coord;
    }

    public String getCoord() 
    {
        return coord;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setPicture(String picture) 
    {
        this.picture = picture;
    }

    public String getPicture() 
    {
        return picture;
    }
    public void setCreatepeople(String createpeople) 
    {
        this.createpeople = createpeople;
    }

    public String getCreatepeople() 
    {
        return createpeople;
    }
    public void setUpdatepeople(String updatepeople) 
    {
        this.updatepeople = updatepeople;
    }

    public String getUpdatepeople() 
    {
        return updatepeople;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("value", getValue())
            .append("bianhao", getBianhao())
            .append("schooltype", getSchooltype())
            .append("bsc", getBsc())
            .append("jyj", getJyj())
            .append("people", getPeople())
            .append("phone", getPhone())
            .append("coord", getCoord())
            .append("address", getAddress())
            .append("picture", getPicture())
            .append("createpeople", getCreatepeople())
            .append("updatepeople", getUpdatepeople())
            .toString();
    }
}
