/**
* Project Name:MyUtil <br>
* File Name:FlowLogDao.java <br>
* Package Name:com.xifeng.util.dao <br>
* @author xiezbmf
* Date:2018-05-28 18:46:01 <br>
* Copyright (c) 2017, 深圳市彩付宝科技有限公司 All Rights Reserved.
*/
package com.xifeng.util.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xifeng.util.pojo.FlowLog;
/**
* ClassName:FlowLogDao <br>
* Description: TODO
* @author xiezbmf
* @Date:2018-05-28 18:46:01 <br>
* @version 
* @since JDK 1.8 
*/
@Repository
public interface FlowLogDao{
    int saveFlowLog(FlowLog flowLog);

    int updateFlowLogById(FlowLog flowLog);

    int deleteFlowLogById(@Param("id")String id);

    List<FlowLog> queryFlowLogByParams(Map<String,String> params);

}