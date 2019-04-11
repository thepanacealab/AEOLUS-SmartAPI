package io.swagger.repository;

import io.swagger.model.ContingencyTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ContingencyTableRepo extends JpaRepository<ContingencyTable, Integer> {

    @Query(value = "SELECT 1 AS id, ct.count_a, ct.count_b, ct.count_c, ct.count_d FROM standard_drug_outcome_contingency_table ct WHERE ct.drug_concept_id = ?1 and ct.outcome_concept_id = ?2",
            countQuery = "SELECT count(*) from standard_drug_outcome_contingency_table ct",
            nativeQuery = true)
    ContingencyTable getContingencyTableByCode(int drugCode, int outcomeCode);

    @Query(value = "SELECT 1 AS id, a.count_a, a.count_b, a.count_c, a.count_d FROM standard_drug_outcome_contingency_table a where a.drug_concept_id IN (select distinct standard_concept_id from standard_combined_drug_mapping where lookup_value = ?1) and a.outcome_concept_id IN (select distinct outcome_concept_id from standard_case_outcome where pt = ?2)",
            countQuery = "SELECT count(*) from standard_drug_outcome_contingency_table ct",
            nativeQuery = true)
    ContingencyTable getContingencyTableByName(String drugName, String outcomeName);

    @Query(value = "SELECT 1 AS id, ( SELECT SUM(drug_outcome_pair_count)FROM standard_drug_outcome_count_date WHERE drug_concept_id = ?1 and outcome_concept_id = ?2 and fda_dt between ?3 and ?4) as count_a, (SELECT SUM(drug_outcome_pair_count) FROM standard_drug_outcome_count_date where drug_concept_id = ?1 and outcome_concept_id <> ?2 and fda_dt between ?3 and ?4) as count_b,(select sum(drug_outcome_pair_count) from standard_drug_outcome_count_date where drug_concept_id <> ?1 and outcome_concept_id = ?2 and fda_dt between ?3 and ?4) as count_c, (select sum(drug_outcome_pair_count) from standard_drug_outcome_count_datewhere drug_concept_id <> ?1 and outcome_concept_id <> ?2 and fda_dt between ?3 and ?4) as count_d",
            countQuery = "SELECT count(*) from standard_drug_outcome_count_date ct",
            nativeQuery = true)
    ContingencyTable getContingencyTableByCodeDate(int drugCode, int outcomeCode, int startDate, int endDate);

    @Query(value = "SELECT 1 AS id,(SELECT SUM(drug_outcome_pair_count) FROM standard_drug_outcome_count_date where drug_concept_id IN (select distinct standard_concept_id from standard_combined_drug_mapping where lookup_value = ?1) and outcome_concept_id IN (select distinct outcome_concept_id from standard_case_outcome where pt = ?2) and fda_dt between ?3 and ?4) as count_a, (SELECT SUM(drug_outcome_pair_count) from standard_drug_outcome_count_date where drug_concept_id IN (select distinct standard_concept_id from standard_combined_drug_mapping where lookup_value = ?1) and outcome_concept_id NOT IN (select distinct outcome_concept_id from standard_case_outcome where pt = ?2) and fda_dt between ?3 and ?4) as count_b, (SELECT SUM(drug_outcome_pair_count) from standard_drug_outcome_count_date where drug_concept_id NOT IN (select distinct standard_concept_id from standard_combined_drug_mapping where lookup_value = ?1) and outcome_concept_id IN (select distinct outcome_concept_id from standard_case_outcome where pt = ?2) and fda_dt between ?3 and ?4) as count_c, (select sum(drug_outcome_pair_count) from standard_drug_outcome_count_date where drug_concept_id NOT IN (select distinct standard_concept_id from standard_combined_drug_mapping where lookup_value = ?1) and outcome_concept_id NOT IN (select distinct outcome_concept_id from standard_case_outcome where pt = ?2) and fda_dt between ?3 and ?4) as count_d",
            countQuery = "SELECT count(*) from standard_drug_outcome_count_date ct",
            nativeQuery = true)
    ContingencyTable getContingencyTableByNameDate(String drugName, String outcomeName, int startDate, int endDate);

}

