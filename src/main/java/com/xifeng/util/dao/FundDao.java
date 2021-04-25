package com.xifeng.util.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xifeng.util.dao.model.FundDetail;
import com.xifeng.util.dao.model.FundStock;

@Mapper
public interface FundDao {

	
	@Select("select fund_no from t_fund where type=1 and mark=0 limit ${start},10")
	List<String> queryUnmarkWithPage(@Param("start")int start);
	
	@Update("update t_fund set mark=0")
	void resetMark();
	
	@Update("update t_fund set mark=1 where fund_no=#{fundNo}")
	void markExecuted(@Param("fundNo")String fundNo);
	
	@Update("update t_fund set type=2 where fund_no=#{fundNo}")
	void markClosed(@Param("fundNo")String fundNo);
	
	@Insert({"INSERT INTO t_fund_detail (",
		"fund_no,total_money,manager,manage_company,open_date,net_worth_estimation_time,",
		"net_worth_estimation_value,net_value_of_unit_time,net_value_of_unit,",
		"cumulative_net_value,last_day_increase,last_month_increase,three_month_increase,",
		"six_month_increase,last_year_increase,three_year_increase,total_increase,stock_last_time",
		") VALUES (",
		"#{fundNo},#{totalMoney},#{manager},#{manageCompany},#{openDate},",
		"#{netWorthEstimationTime},#{netWorthEstimationValue},#{netValueOfUnitTime},",
		"#{netValueOfUnit},#{cumulativeNetValue},#{lastDayIncrease},#{lastMonthIncrease},",
		"#{threeMonthIncrease},#{sixMonthIncrease},#{lastYearIncrease},#{threeYearIncrease},#{totalIncrease},#{stockLastTime} )",
	})
	void saveFundDetail(FundDetail fund);
	
	@Insert({"INSERT INTO t_fund_stock (",
		"fund_no,stock_code,stock_name,position_ratio,daily_rise_and_fall",
		") VALUES (",
		"#{fundNo},#{stockCode},#{stockName},#{positionRatio},#{dailyRiseAndFall})",
	})
	void saveFundStock(FundStock stock);
	
	@Insert({
		"<script>",
		"INSERT INTO t_fund_stock (",
		"fund_no,stock_code,stock_name,position_ratio,daily_rise_and_fall",
		") VALUES ",
		"<foreach  collection='list' item='item' separator=','>",
    	"(#{item.fundNo},#{item.stockCode},#{item.stockName},#{item.positionRatio},#{item.dailyRiseAndFall})",
    	"</foreach>",
		"</script>",
	})
	void saveBatchFundStock(@Param("list")List<FundStock> stockList);
}
