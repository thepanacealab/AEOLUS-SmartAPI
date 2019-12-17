package io.swagger.repository;

import io.swagger.model.ContingencyTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ContingencyTableRepo extends JpaRepository<ContingencyTable, Integer> {

    @Query(value = "SELECT 1 AS id, ct.count_a, ct.count_b, ct.count_c, ct.count_d FROM faers.standard_drug_outcome_contingency_table ct WHERE ct.drug_concept_id = ?1 and ct.outcome_concept_id = ?2",
            countQuery = "SELECT count(*) from faers.standard_drug_outcome_contingency_table ct",
            nativeQuery = true)
    ContingencyTable getContingencyTableByCode(int drugCode, int outcomeCode);

    @Query(value = "SELECT 1 AS id, a.count_a, a.count_b, a.count_c, a.count_d FROM faers.standard_drug_outcome_contingency_table a where a.drug_concept_id IN (select distinct standard_concept_id from faers.standard_combined_drug_mapping where lookup_value = ?1) and a.outcome_concept_id IN (select distinct outcome_concept_id from faers.standard_case_outcome where pt = ?2)",
            countQuery = "SELECT count(*) from faers.standard_drug_outcome_contingency_table ct",
            nativeQuery = true)
    ContingencyTable getContingencyTableByName(String drugName, String outcomeName);

    @Query(value = "select 1 as id, (select count(*) from faers.unique_all_casedemo where drugname_list IN (select distinct lookup_value from faers.standard_combined_drug_mapping where standard_concept_id = ?1) and reac_pt_list IN (select distinct pt from faers.standard_case_outcome where outcome_concept_id = ?2) and fda_dt between cast(?3 as varchar) and cast(?4 as varchar)) as count_a, (select count(*) from faers.unique_all_casedemo where drugname_list IN (select distinct lookup_value from faers.standard_combined_drug_mapping where standard_concept_id = ?1) and reac_pt_list NOT IN (select distinct pt from faers.standard_case_outcome where outcome_concept_id = ?2) and fda_dt between cast(?3 as varchar) and cast(?4 as varchar)) as count_b, (select count(*) from faers.unique_all_casedemo where drugname_list NOT IN (select distinct lookup_value from faers.standard_combined_drug_mapping where standard_concept_id = ?1) and reac_pt_list IN (select distinct pt from faers.standard_case_outcome where outcome_concept_id = ?2) and fda_dt between cast(?3 as varchar) and cast(?4 as varchar)) as count_c, (select count(*) from faers.unique_all_casedemo where drugname_list NOT IN (select distinct lookup_value from faers.standard_combined_drug_mapping where standard_concept_id = ?1)and reac_pt_list NOT IN (select DISTINCT pt from faers.standard_case_outcome where outcome_concept_id = ?2) and fda_dt between cast(?3 as varchar) and cast(?4 as varchar)) as count_d", 
            countQuery = "SELECT count(*) from standard.standard_drug_outcome_count ct",
            nativeQuery = true)
    ContingencyTable getContingencyTableByCodeDate(int drugCode, int outcomeCode, int startDate, int endDate);

    @Query(value = "select 1 as id, (select count(*) from faers.unique_all_casedemo where drugname_list IN (select distinct lookup_value from faers.standard_combined_drug_mapping where standard_concept_id = (select distinct standard_concept_id from faers.standard_combined_drug_mapping where lookup_value = ?1)) and reac_pt_list = UPPER(?2) and fda_dt between cast(?3 as varchar) and cast(?4 as varchar)) as count_a, (select count(*) from faers.unique_all_casedemo where drugname_list IN (select distinct lookup_value from faers.standard_combined_drug_mapping where standard_concept_id = (select distinct standard_concept_id from faers.standard_combined_drug_mapping where lookup_value = ?1)) and reac_pt_list <> UPPER(?2) and fda_dt between cast(?3 as varchar) and cast(?4 as varchar)) as count_b, (select count(*) from faers.unique_all_casedemo where drugname_list NOT IN (select distinct lookup_value from faers.standard_combined_drug_mapping where standard_concept_id = (select distinct standard_concept_id from faers.standard_combined_drug_mapping where lookup_value = ?1)) and reac_pt_list = UPPER(?2) and fda_dt between cast(?3 as varchar) and cast(?4 as varchar)) as count_c, (select count(*) from faers.unique_all_casedemo where drugname_list NOT IN (select distinct lookup_value from faers.standard_combined_drug_mapping where standard_concept_id = (select distinct standard_concept_id from faers.standard_combined_drug_mapping where lookup_value = ?1)) and reac_pt_list <> UPPER(?2) and fda_dt between cast(?3 as varchar) and cast(?4 as varchar)) as count_d",
            countQuery = "SELECT count(*) from standard.standard_drug_outcome_count ct",
            nativeQuery = true)
    ContingencyTable getContingencyTableByNameDate(String drugName, String outcomeName, int startDate, int endDate);

}

