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

	param = {1501700:35809076}

	for key, value in param.items():
		
		drug = key
		outcome = value
		mindate = 20100101
		dates = [20130701, 20130801, 20130901, 20131001, 20131101, 20131201, 20140101, 20140210, 20140310, 20140401, 20140501, 20140601, 20140701, 20140801, 20140901, 20141001, 20141101, 20141201,20150101, 20150210, 20150310, 20150401, 20150501, 20150601, 20150701, 20150801, 20150901, 20151001, 20151101, 20151201,20160101, 20160210, 20160310, 20160401, 20160501, 20160601, 20160701, 20160801, 20160901, 20161001, 20161101, 20161201, 20170101, 20170210, 20170310, 20170401, 20170501, 20170601, 20170701, 20170801, 20170901, 20171001, 20171101, 20171201,20180101, 20180210, 20180310, 20180401, 20180501, 20180601, 20180701, 20180801, 20180901, 20181001, 20181101, 20181201]

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
	df.to_csv("/home/cynthiak/drug_outcome_date_analysis_8.csv", index=False)
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
