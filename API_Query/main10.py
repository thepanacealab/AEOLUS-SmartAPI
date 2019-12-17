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
		mindate = 19990101
		dates = [20040801, 20040901, 20041001, 20041101, 20041201, 20050101, 20050210, 20050310, 20050401, 20050501, 20050601, 20050701, 20050801, 20050901, 20051001, 20051101, 20051201]

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
	df.to_csv("/home/cynthiak/drug_outcome_date_analysis_10.csv", index=False)
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
