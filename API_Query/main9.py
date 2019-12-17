#!/usr/bin/python
import psycopg2
from config import config
 
def connect():
    """ Connect to the PostgreSQL database server """
    conn = None
    try:
        # read connection parameters
        params = config()
 
        # connect to the PostgreSQL server
        print('Connecting to the PostgreSQL database...')
        conn = psycopg2.connect(**params)
      
        # create a cursor
        cur = conn.cursor()
        
   # execute a statement
        print('PostgreSQL database version:')
        cur.execute('SELECT version()')
 
        # display the PostgreSQL database server version
        db_version = cur.fetchone()
        print(db_version)

	import pandas as pd

	col_names =  ['DATE','DRUG','COUNT', 'OUTCOME', 'PRR', 'ROR']
	df  = pd.DataFrame(columns = col_names)

	param = {984232:36918942}

	for key, value in param.items():
		
		drug = key
		outcome = value
		mindate = 20010101
		dates = [20050101, 20050210, 20050310, 20050401, 20050501, 20050601, 20050701, 20050801, 20050901, 20051001, 20051101, 20051201, 20060101, 20060210, 20060310, 20060401, 20060501, 20060601, 20060701, 20060801, 20060901, 20061001, 20061101, 20061201,20070101, 20070210, 20070310, 20070401, 20070501, 20070601, 20070701, 20070801, 20070901, 20071001, 20071101, 20071201,20080101, 20080210, 20080310, 20080401, 20080501, 20080601, 20080701, 20080801, 20080901, 20081001, 20081101, 20081201, 20090101, 20090210, 20090310, 20090401, 20090501, 20090601, 20090701, 20090801, 20090901, 20091001, 20091101, 20091201,20100101, 20100210, 20100310, 20100401, 20100501, 20100601, 20100701, 20100801, 20100901, 20101001, 20101101, 20101201,20120101, 20120210, 20120310, 20120401, 20120501, 20120601, 20120701, 20120801, 20120901, 20121001, 20121101, 20121201, 20130701, 20130801, 20130901, 20131001, 20131101, 20131201, 20140101, 20140210, 20140310, 20140401, 20140501, 20140601, 20140701, 20140801, 20140901, 20141001, 20141101, 20141201,20150101, 20150210, 20150310, 20150401, 20150501, 20150601, 20150701, 20150801, 20150901, 20151001, 20151101, 20151201,20160101, 20160210, 20160310, 20160401, 20160501, 20160601, 20160701, 20160801, 20160901, 20161001, 20161101, 20161201, 20170101, 20170210, 20170310, 20170401, 20170501, 20170601, 20170701, 20170801, 20170901, 20171001, 20171101, 20171201,20180101, 20180210, 20180310, 20180401, 20180501, 20180601, 20180701, 20180801, 20180901, 20181001, 20181101, 20181201]

		for date in dates:
			maxdate = date
 
        		query ='SELECT count_a,round((count_a / (count_a + count_b)) / (count_c / (count_c + count_d)),10) as prr, round(((p.count_a / p.count_c) / (p.count_b / p.count_d)),10) as ror FROM (select 1 as id, (select count(*)*1.0 from faers.unique_all_casedemo where drugname_list IN (select distinct lookup_value from faers.standard_combined_drug_mapping where standard_concept_id = %s) and reac_pt_list IN (select distinct pt from faers.standard_case_outcome where outcome_concept_id = %s) and fda_dt between cast(%s as varchar) and cast(%s as varchar)) as count_a, (select count(*)*1.0 from faers.unique_all_casedemo where drugname_list IN (select distinct lookup_value from faers.standard_combined_drug_mapping where standard_concept_id = %s) and reac_pt_list NOT IN (select distinct pt from faers.standard_case_outcome where outcome_concept_id = %s) and fda_dt between cast(%s as varchar) and cast(%s as varchar)) as count_b, (select count(*)*1.0 from faers.unique_all_casedemo where drugname_list NOT IN (select distinct lookup_value from faers.standard_combined_drug_mapping where standard_concept_id = %s) and reac_pt_list IN (select distinct pt from faers.standard_case_outcome where outcome_concept_id = %s) and fda_dt between cast(%s as varchar) and cast(%s as varchar)) as count_c, (select count(*)*1.0 from faers.unique_all_casedemo where drugname_list NOT IN (select distinct lookup_value from faers.standard_combined_drug_mapping where standard_concept_id = %s)and reac_pt_list NOT IN (select DISTINCT pt from faers.standard_case_outcome where outcome_concept_id = %s) and fda_dt between cast(%s as varchar) and cast(%s as varchar)) as count_d) p' 
			cur.execute(query, (drug, outcome, mindate, maxdate, drug, outcome, mindate, maxdate, drug, outcome, mindate,maxdate, drug, outcome, mindate, maxdate)) 
			result = cur.fetchall() 

			for r in result:
				count = r[0]
				prr = r[1] 
				ror = r[1]  
				row = [date,drug,count, outcome, prr, ror]
				df.loc[len(df)] = row
	df.to_csv("/home/cynthiak/drug_outcome_date_analysis_9.csv", index=False)
       # close the communication with the PostgreSQL
        cur.close()
    
        return conn
    except (Exception, psycopg2.DatabaseError) as error:
        print(error)
    finally:
        if conn is not None:
            conn.close()
            print('Database connection closed.')
 
 
if __name__ == '__main__':
    connect()
