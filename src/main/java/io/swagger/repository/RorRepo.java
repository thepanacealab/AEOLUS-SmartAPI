package io.swagger.repository;

import io.swagger.model.ROR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RorRepo extends JpaRepository<ROR, Integer>{

    @Query(value = "SELECT 1 AS id, r.ror, r.case_count, r.ror_95_percent_upper_confidence_limit, r.ror_95_percent_lower_confidence_limit FROM faers.standard_drug_outcome_statistics r WHERE r.drug_concept_id = ?1 and r.outcome_concept_id = ?2",
            countQuery = "SELECT count(*) from faers.standard_drug_outcome_statistics r",
            nativeQuery = true)
    ROR getRORByCode(int drugCode, int outcomeCode);

    @Query(value = "SELECT 1 AS id, a.ror, a.case_count, a.ror_95_percent_upper_confidence_limit, a.ror_95_percent_lower_confidence_limit FROM faers.standard_drug_outcome_statistics a where a.drug_concept_id IN (select distinct standard_concept_id from faers.standard_combined_drug_mapping where lookup_value = ?1) and a.outcome_concept_id IN (select distinct outcome_concept_id from faers.standard_case_outcome where pt = ?2)",
            countQuery = "SELECT count(*) from faers.standard_drug_outcome_statistics p",
            nativeQuery = true)
    ROR getRORByName(String drugName, String outcomeName);

    @Query(value = "SELECT 1 AS id, round(((p.count_a / p.count_c) / (p.count_b / p.count_d)),5) as ror, p.count_a as case_count, round(exp((ln((p.count_a / p.count_c) / (p.count_b / p.count_d)))+1.96*sqrt((1.0/p.count_a)+(1.0/p.count_b)+(1.0/p.count_c)+(1.0/p.count_d))),5) as ror_95_percent_upper_confidence_limit, round(exp((ln((p.count_a / p.count_c) / (p.count_b / p.count_d)))-1.96*sqrt((1.0/p.count_a)+(1.0/p.count_b)+(1.0/p.count_c)+(1.0/p.count_d))),5) as ror_95_percent_lower_confidence_limit FROM (select 1 as id, (select count(*)*1.0 from faers.unique_all_casedemo where drugname_list IN (select distinct lookup_value from faers.standard_combined_drug_mapping where standard_concept_id = ?1) and reac_pt_list IN (select distinct pt from faers.standard_case_outcome where outcome_concept_id = ?2) and fda_dt between cast(?3 as varchar) and cast(?4 as varchar)) as count_a, (select count(*)*1.0 from faers.unique_all_casedemo where drugname_list IN (select distinct lookup_value from faers.standard_combined_drug_mapping where standard_concept_id = ?1) and reac_pt_list NOT IN (select distinct pt from faers.standard_case_outcome where outcome_concept_id = ?2) and fda_dt between cast(?3 as varchar) and cast(?4 as varchar)) as count_b, (select count(*)*1.0 from faers.unique_all_casedemo where drugname_list NOT IN (select distinct lookup_value from faers.standard_combined_drug_mapping where standard_concept_id = ?1) and reac_pt_list IN (select distinct pt from faers.standard_case_outcome where outcome_concept_id = ?2) and fda_dt between cast(?3 as varchar) and cast(?4 as varchar)) as count_c, (select count(*)*1.0 from faers.unique_all_casedemo where drugname_list NOT IN (select distinct lookup_value from faers.standard_combined_drug_mapping where standard_concept_id = ?1)and reac_pt_list NOT IN (select DISTINCT pt from faers.standard_case_outcome where outcome_concept_id = ?2) and fda_dt between cast(?3 as varchar) and cast(?4 as varchar)) as count_d) p", 
            countQuery = "SELECT count(*) from faers.standard_drug_outcome_statistics r",
            nativeQuery = true)
    ROR getRORByDateCode(int drugCode, int outcomeCode, int startDate, int endDate);

      @Query(value = "SELECT 1 AS id, round(((p.count_a / p.count_c) / (p.count_b / p.count_d)),5) as ror, p.count_a as case_count, round(exp((ln((p.count_a / p.count_c) / (p.count_b / p.count_d)))+1.96*sqrt((1.0/p.count_a)+(1.0/p.count_b)+(1.0/p.count_c)+(1.0/p.count_d))),5) as ror_95_percent_upper_confidence_limit, round(exp((ln((p.count_a / p.count_c) / (p.count_b / p.count_d)))-1.96*sqrt((1.0/p.count_a)+(1.0/p.count_b)+(1.0/p.count_c)+(1.0/p.count_d))),5) as ror_95_percent_lower_confidence_limit FROM (select 1 as id, (select count(*)*1.0 from faers.unique_all_casedemo where drugname_list IN (select distinct lookup_value from faers.standard_combined_drug_mapping where standard_concept_id = (select distinct standard_concept_id from faers.standard_combined_drug_mapping where lookup_value = ?1)) and reac_pt_list = UPPER(?2) and fda_dt between cast(?3 as varchar) and cast(?4 as varchar)) as count_a, (select count(*)*1.0 from faers.unique_all_casedemo where drugname_list IN (select distinct lookup_value from faers.standard_combined_drug_mapping where standard_concept_id = (select distinct standard_concept_id from faers.standard_combined_drug_mapping where lookup_value = ?1)) and reac_pt_list <> UPPER(?2) and fda_dt between cast(?3 as varchar) and cast(?4 as varchar)) as count_b, (select count(*)*1.0 from faers.unique_all_casedemo where drugname_list NOT IN (select distinct lookup_value from faers.standard_combined_drug_mapping where standard_concept_id = (select distinct standard_concept_id from faers.standard_combined_drug_mapping where lookup_value = ?1)) and reac_pt_list = UPPER(?2) and fda_dt between cast(?3 as varchar) and cast(?4 as varchar)) as count_c, (select count(*)*1.0 from faers.unique_all_casedemo where drugname_list NOT IN (select distinct lookup_value from faers.standard_combined_drug_mapping where standard_concept_id = (select distinct standard_concept_id from faers.standard_combined_drug_mapping where lookup_value = ?1)) and reac_pt_list <> UPPER(?2) and fda_dt between cast(?3 as varchar) and cast(?4 as varchar)) as count_d) p", 
            countQuery = "SELECT count(*) from standard.standard_drug_outcome_statistics p",
            nativeQuery = true)
    ROR getRORByDateName(String drugName, String outcomeName, int startDate, int endDate);
}

