/**
* Project Name:MyUtil <br>
* File Name:FlowLog.java <br>
* Package Name:com.xifeng.util.pojo <br>
* @author xiezbmf
* Date:2018-05-28 18:46:01 <br>
* Copyright (c) 2017, 深圳市彩付宝科技有限公司 All Rights Reserved.
*/
package com.xifeng.util.pojo;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;
/**
* ClassName:FlowLog <br>
* Description: TODO
* @author xiezbmf
* @Date:2018-05-28 18:46:01 <br>
* @version 
* @since JDK 1.8 
*/
@Alias("flowLog")
public class FlowLog{
    Integer id;
    String serialNo;
    String funcFlag;
    String userId;
    String outDeposite;
    String outName;
    String outMerchantId;
    String outMerchantCode;
    String inDeposite;
    String inMerchantCode;
    String inMerchantId;
    String inName;
    BigDecimal tradeAmount;
    BigDecimal tradeHandFee;
    String tradeDate;
    String tradeTime;
    String bankFrontSerial;
    String flowId;
    String remark;
    Date createTime;

    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public String getSerialNo(){
        return this.serialNo;
    }
    public void setSerialNo(String serialNo){
        this.serialNo = serialNo;
    }
    public String getFuncFlag(){
        return this.funcFlag;
    }
    public void setFuncFlag(String funcFlag){
        this.funcFlag = funcFlag;
    }
    public String getUserId(){
        return this.userId;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getOutDeposite(){
        return this.outDeposite;
    }
    public void setOutDeposite(String outDeposite){
        this.outDeposite = outDeposite;
    }
    public String getOutName(){
        return this.outName;
    }
    public void setOutName(String outName){
        this.outName = outName;
    }
    public String getOutMerchantId(){
        return this.outMerchantId;
    }
    public void setOutMerchantId(String outMerchantId){
        this.outMerchantId = outMerchantId;
    }
    public String getOutMerchantCode(){
        return this.outMerchantCode;
    }
    public void setOutMerchantCode(String outMerchantCode){
        this.outMerchantCode = outMerchantCode;
    }
    public String getInDeposite(){
        return this.inDeposite;
    }
    public void setInDeposite(String inDeposite){
        this.inDeposite = inDeposite;
    }
    public String getInMerchantCode(){
        return this.inMerchantCode;
    }
    public void setInMerchantCode(String inMerchantCode){
        this.inMerchantCode = inMerchantCode;
    }
    public String getInMerchantId(){
        return this.inMerchantId;
    }
    public void setInMerchantId(String inMerchantId){
        this.inMerchantId = inMerchantId;
    }
    public String getInName(){
        return this.inName;
    }
    public void setInName(String inName){
        this.inName = inName;
    }
    public BigDecimal getTradeAmount(){
        return this.tradeAmount;
    }
    public void setTradeAmount(BigDecimal tradeAmount){
        this.tradeAmount = tradeAmount;
    }
    public BigDecimal getTradeHandFee(){
        return this.tradeHandFee;
    }
    public void setTradeHandFee(BigDecimal tradeHandFee){
        this.tradeHandFee = tradeHandFee;
    }
    public String getTradeDate(){
        return this.tradeDate;
    }
    public void setTradeDate(String tradeDate){
        this.tradeDate = tradeDate;
    }
    public String getTradeTime(){
        return this.tradeTime;
    }
    public void setTradeTime(String tradeTime){
        this.tradeTime = tradeTime;
    }
    public String getBankFrontSerial(){
        return this.bankFrontSerial;
    }
    public void setBankFrontSerial(String bankFrontSerial){
        this.bankFrontSerial = bankFrontSerial;
    }
    public String getFlowId(){
        return this.flowId;
    }
    public void setFlowId(String flowId){
        this.flowId = flowId;
    }
    public String getRemark(){
        return this.remark;
    }
    public void setRemark(String remark){
        this.remark = remark;
    }
    public Date getCreateTime(){
        return this.createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
}