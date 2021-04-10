select * from fund.t_fund_detail;
select fund_no,total_money,
case when (total_money like '%--%') = 1 then
0.00
else
replace(replace(SUBSTRING_INDEX(total_money,'（',1),'基金规模：',''),'亿元','') end total_money_value,
case when (last_month_increase like '%--%') = 1 then
0.00
else
replace(last_month_increase,'%','') end last_month_increase_value,
case when (three_month_increase like '%--%') = 1 then
0.00
else
replace(three_month_increase,'%','') end three_month_increase_value,
case when (six_month_increase like '%--%') = 1 then
0.00
else
replace(six_month_increase,'%','') end six_month_increase_value
 from fund.t_fund_detail;
 
 set sql_safe_updates=0;
 
 update fund.t_fund_detail a set 
 total_money_value = (case when (total_money like '%--%') = 1 then
0.00
else
replace(replace(SUBSTRING_INDEX(total_money,'（',1),'基金规模：',''),'亿元','') end ),
last_month_increase_value = (case when (last_month_increase like '%--%') = 1 then
0.00
else
replace(last_month_increase,'%','') end),
three_month_increase_value=(case when (three_month_increase like '%--%') = 1 then
0.00
else
replace(three_month_increase,'%','') end),
six_month_increase_value=(case when (six_month_increase like '%--%') = 1 then
0.00
else
replace(six_month_increase,'%','') end);


select * from fund.t_fund_stock;

update t_fund_stock set position_ratio_value=(case when (position_ratio like '%--%') = 1 then
0.00
else
replace(position_ratio,'%','') end);

select b.fund_no,b.name,a.total_money_value,a.last_month_increase_value,c.stock_name,
c.position_ratio_value,a.total_money_value*c.position_ratio_value/100  stock_money 
from t_fund_detail a,t_fund b,t_fund_stock c
where a.fund_no=b.fund_no
and b.fund_no = c.fund_no
order by stock_code;

select stock_name,count(1) cnt,sum(stock_money) stock_money,sum(total_money_value) total_money_value
from(
select b.fund_no,b.name,a.total_money_value,a.last_month_increase_value,c.stock_name,
c.position_ratio_value,a.total_money_value*c.position_ratio_value/100  stock_money 
from t_fund_detail a,t_fund b,t_fund_stock c
where a.fund_no=b.fund_no
and b.fund_no = c.fund_no) a
group by a.stock_name;
