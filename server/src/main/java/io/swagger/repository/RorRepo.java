package io.swagger.repository;

import io.swagger.model.ROR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RorRepo extends JpaRepository<ROR, Integer>{

    @Query(value = "SELECT 1 AS id, r.ror, r.case_count, r.ror_95_percent_upper_confidence_limit, r.ror_95_percent_lower_confidence_limit FROM standard_drug_outcome_statistics r WHERE r.drug_concept_id = ?1 and r.outcome_concept_id = ?2",
            countQuery = "SELECT count(*) from standard_drug_outcome_statistics r",
            nativeQuery = true)
    ROR getRORByCode(int drugCode, int outcomeCode);

    @Query(value = "SELECT 1 AS id, a.ror, a.case_count, a.ror_95_percent_upper_confidence_limit, a.ror_95_percent_lower_confidence_limit FROM standard_drug_outcome_statistics a where a.drug_concept_id IN (select distinct standard_concept_id from standard_combined_drug_mapping where lookup_value = ?1) and a.outcome_concept_id IN (select distinct outcome_concept_id from standard_case_outcome where pt = ?2)",
            countQuery = "SELECT count(*) from standard_drug_outcome_statistics p",
            nativeQuery = true)
    ROR getRORByName(String drugName, String outcomeName);

    @Query(value = "SELECT 1 AS id, round(((p.count_a / p.count_c) / (p.count_b / p.count_d)),5) as ror, p.count_a as case_count, round(exp((ln((p.count_a / p.count_c) / (p.count_b / p.count_d)))+1.96*sqrt((1.0/p.count_a)+(1.0/p.count_b)+(1.0/p.count_c)+(1.0/p.count_d))),5) as ror_95_percent_upper_confidence_limit, round(exp((ln((p.count_a / p.count_c) / (p.count_b / p.count_d)))-1.96*sqrt((1.0/p.count_a)+(1.0/p.count_b)+(1.0/p.count_c)+(1.0/p.count_d))),5) as ror_95_percent_lower_confidence_limit FROM (SELECT (SELECT SUM(drug_outcome_pair_count)FROM standard_drug_outcome_count_date WHERE drug_concept_id = ?1 and outcome_concept_id = ?2 and fda_dt between ?3 and ?4) as count_a, (SELECT SUM(drug_outcome_pair_count) FROM standard_drug_outcome_count_date where drug_concept_id = ?1 and outcome_concept_id <> ?2 and fda_dt between ?3 and ?4) as count_b,(select sum(drug_outcome_pair_count) from standard_drug_outcome_count_date where drug_concept_id <> ?1 and outcome_concept_id = ?2 and fda_dt between ?3 and ?4) as count_c, (select sum(drug_outcome_pair_count) from standard_drug_outcome_count_datewhere drug_concept_id <> ?1 and outcome_concept_id <> ?2 and fda_dt between ?3 and ?4) as count_d) p",
            countQuery = "SELECT count(*) from standard_drug_outcome_statistics r",
            nativeQuery = true)
    ROR getRORByDateCode(int drugCode, int outcomeCode, int startDate, int endDate);

    @Query(value = "SELECT 1 AS id, round(((p.count_a / p.count_c) / (p.count_b / p.count_d)),5) as ror, p.count_a as case_count, round(exp((ln((p.count_a / p.count_c) / (p.count_b / p.count_d)))+1.96*sqrt((1.0/p.count_a)+(1.0/p.count_b)+(1.0/p.count_c)+(1.0/p.count_d))),5) as ror_95_percent_upper_confidence_limit, round(exp((ln((p.count_a / p.count_c) / (p.count_b / p.count_d)))-1.96*sqrt((1.0/p.count_a)+(1.0/p.count_b)+(1.0/p.count_c)+(1.0/p.count_d))),5) as ror_95_percent_lower_confidence_limit FROM (SELECT (SELECT SUM(drug_outcome_pair_count) FROM standard_drug_outcome_count_date where drug_concept_id IN (select distinct standard_concept_id from standard_combined_drug_mapping where lookup_value = ?1) and outcome_concept_id IN (select distinct outcome_concept_id from standard_case_outcome where pt = ?2) and fda_dt between ?3 and ?4) as count_a, (SELECT SUM(drug_outcome_pair_count) from standard_drug_outcome_count_date where drug_concept_id IN (select distinct standard_concept_id from standard_combined_drug_mapping where lookup_value = ?1) and outcome_concept_id NOT IN (select distinct outcome_concept_id from standard_case_outcome where pt = ?2) and fda_dt between ?3 and ?4) as count_b, (SELECT SUM(drug_outcome_pair_count) from standard_drug_outcome_count_date where drug_concept_id NOT IN (select distinct standard_concept_id from standard_combined_drug_mapping where lookup_value = ?1) and outcome_concept_id IN (select distinct outcome_concept_id from standard_case_outcome where pt = ?2) and fda_dt between ?3 and ?4) as count_c, (select sum(drug_outcome_pair_count) from standard_drug_outcome_count_date where drug_concept_id NOT IN (select distinct standard_concept_id from standard_combined_drug_mapping where lookup_value = ?1) and outcome_concept_id NOT IN (select distinct outcome_concept_id from standard_case_outcome where pt = ?2) and fda_dt between ?3 and ?4) as count_d) p",
            countQuery = "SELECT count(*) from standard_drug_outcome_statistics p",
            nativeQuery = true)
    ROR getRORByDateName(String drugName, String outcomeName, int startDate, int endDate);
}

