package com.zyyglxt.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class IndustrialDevelopSciAchiDO extends IndustrialDevelopSciAchiDOKey {
    public String getIndustrialDevelopName() {
        return industrialDevelopName;
    }

    public void setIndustrialDevelopName(String industrialDevelopName) {
        this.industrialDevelopName = industrialDevelopName;
    }

    public String getIndustrialDevelopLeader() {
        return industrialDevelopLeader;
    }

    public void setIndustrialDevelopLeader(String industrialDevelopLeader) {
        this.industrialDevelopLeader = industrialDevelopLeader;
    }

    public Integer getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(Integer visitNum) {
        this.visitNum = visitNum;
    }

    public String getIndustrialDevelopSource() {
        return industrialDevelopSource;
    }

    public void setIndustrialDevelopSource(String industrialDevelopSource) {
        this.industrialDevelopSource = industrialDevelopSource;
    }

    public String getIndustrialDevelopAuthor() {
        return industrialDevelopAuthor;
    }

    public void setIndustrialDevelopAuthor(String industrialDevelopAuthor) {
        this.industrialDevelopAuthor = industrialDevelopAuthor;
    }

    public String getIndustrialDevelopStatus() {
        return industrialDevelopStatus;
    }

    public void setIndustrialDevelopStatus(String industrialDevelopStatus) {
        this.industrialDevelopStatus = industrialDevelopStatus;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getItemcreateat() {
        return itemcreateat;
    }

    public void setItemcreateat(Date itemcreateat) {
        this.itemcreateat = itemcreateat;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getItemupdateat() {
        return itemupdateat;
    }

    public void setItemupdateat(Date itemupdateat) {
        this.itemupdateat = itemupdateat;
    }

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_industrial_develop_sci_achi.INDUSTRIAL_DEVELOP_NAME
     *
     * @mbg.generated Wed Oct 28 16:25:52 CST 2020
     */
    private String industrialDevelopName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_industrial_develop_sci_achi.INDUSTRIAL_DEVELOP_LEADER
     *
     * @mbg.generated Wed Oct 28 16:25:52 CST 2020
     */
    private String industrialDevelopLeader;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_industrial_develop_sci_achi.VISIT_NUM
     *
     * @mbg.generated Wed Oct 28 16:25:52 CST 2020
     */
    private Integer visitNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_industrial_develop_sci_achi.INDUSTRIAL_DEVELOP_SOURCE
     *
     * @mbg.generated Wed Oct 28 16:25:52 CST 2020
     */
    private String industrialDevelopSource;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_industrial_develop_sci_achi.INDUSTRIAL_DEVELOP_AUTHOR
     *
     * @mbg.generated Wed Oct 28 16:25:52 CST 2020
     */
    private String industrialDevelopAuthor;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_industrial_develop_sci_achi.INDUSTRIAL_DEVELOP_STATUS
     *
     * @mbg.generated Wed Oct 28 16:25:52 CST 2020
     */
    private String industrialDevelopStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_industrial_develop_sci_achi.CREATER
     *
     * @mbg.generated Wed Oct 28 16:25:52 CST 2020
     */
    private String creater;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_industrial_develop_sci_achi.itemCreateAt
     *
     * @mbg.generated Wed Oct 28 16:25:52 CST 2020
     */
    private Date itemcreateat;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_industrial_develop_sci_achi.UPDATER
     *
     * @mbg.generated Wed Oct 28 16:25:52 CST 2020
     */
    private String updater;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_industrial_develop_sci_achi.itemUpdateAt
     *
     * @mbg.generated Wed Oct 28 16:25:52 CST 2020
     */
    private Date itemupdateat;

}