package io.swagger.repository;

import io.swagger.model.PRR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrrRepo extends JpaRepository<PRR, Integer>{
    @Query(value = "SELECT 1 AS id, p.prr, p.case_count, p.prr_95_percent_upper_confidence_limit, p.prr_95_percent_lower_confidence_limit FROM standard_drug_outcome_statistics p WHERE p.drug_concept_id = ?1 and p.outcome_concept_id = ?2",
            countQuery = "SELECT count(*) from standard_drug_outcome_statistics p",
            nativeQuery = true)
    PRR getPRRByCode(int drugCode, int outcomeCode);

    @Query(value = "SELECT 1 AS id, a.prr, a.case_count, a.prr_95_percent_upper_confidence_limit, a.prr_95_percent_lower_confidence_limit FROM standard_drug_outcome_statistics a where a.drug_concept_id IN (select distinct standard_concept_id from standard_combined_drug_mapping where lookup_value = ?1) and a.outcome_concept_id IN (select distinct outcome_concept_id from standard_case_outcome where pt = ?2)",
            countQuery = "SELECT count(*) from standard_drug_outcome_statistics p",
            nativeQuery = true)
    PRR getPRRByName(String drugName, String outcomeName);

    @Query(value = "SELECT 1 AS id, p.count_a as case_count, round((p.count_a / (p.count_a + p.count_b)) / (p.count_c / (p.count_c + p.count_d)),5) as prr, round(exp(ln((p.count_a / (p.count_a + p.count_b)) / (p.count_c / (p.count_c + p.count_d)))+1.96*sqrt((1.0/p.count_a)-(1.0/(p.count_a+p.count_b))+(1.0/p.count_c)+(1.0/(p.count_c+p.count_d)))),5) as prr_95_percent_upper_confidence_limit, round(exp(ln((p.count_a / (p.count_a + p.count_b)) / (p.count_c / (p.count_c + p.count_d)))-1.96*sqrt((1.0/p.count_a)-(1.0/(p.count_a+p.count_b))+(1.0/p.count_c)+(1.0/(p.count_c+p.count_d)))),5) as prr_95_percent_lower_confidence_limit FROM (SELECT (SELECT SUM(drug_outcome_pair_count) FROM standard_drug_outcome_count_date where drug_concept_id = ?1 and outcome_concept_id = ?2 and fda_dt between ?3 and ?4) AS count_a, (SELECT SUM(drug_outcome_pair_count) from standard_drug_outcome_count_date where drug_concept_id = ?1 and outcome_concept_id <> ?2 and fda_dt between ?3 and ?4) AS count_b, (SELECT SUM(drug_outcome_pair_count) FROM standard_drug_outcome_count_date WHERE drug_concept_id <> ?1 and outcome_concept_id = ?2 and fda_dt between ?3 and ?4) AS count_c, (SELECT SUM(drug_outcome_pair_count) FROM standard_drug_outcome_count_date WHERE drug_concept_id <> ?1 and outcome_concept_id <> ?2 and fda_dt between ?3 and ?4) as count_d) p",
            countQuery = "SELECT count(*) from standard_drug_outcome_statistics p",
            nativeQuery = true)
    PRR getPRRByDateCode(int drugCode, int outcomeCode, int startDate, int endDate);

    @Query(value = "SELECT 1 AS id, p.count_a as case_count, round((p.count_a / (p.count_a + p.count_b)) / (p.count_c / (p.count_c + p.count_d)),5) as prr, round(exp(ln((p.count_a / (p.count_a + p.count_b)) / (p.count_c / (p.count_c + p.count_d)))+1.96*sqrt((1.0/p.count_a)-(1.0/(p.count_a+p.count_b))+(1.0/p.count_c)+(1.0/(p.count_c+p.count_d)))),5) AS prr_95_percent_upper_confidence_limit, round(exp(ln((p.count_a / (p.count_a + p.count_b)) / (p.count_c / (p.count_c + p.count_d)))-1.96*sqrt((1.0/p.count_a)-(1.0/(p.count_a+p.count_b))+(1.0/p.count_c)+(1.0/(p.count_c+p.count_d)))),5) as prr_95_percent_lower_confidence_limit FROM (SELECT (SELECT SUM(drug_outcome_pair_count) FROM standard_drug_outcome_count_date WHERE drug_concept_id IN (select distinct standard_concept_id FROM standard_combined_drug_mapping WHERE lookup_value = ?1) and outcome_concept_id IN (SELECT DISTINCT outcome_concept_id FROM standard_case_outcome WHERE pt = ?2) and fda_dt between ?3 and ?4) AS count_a,(SELECT SUM(drug_outcome_pair_count) FROM standard_drug_outcome_count_date WHERE drug_concept_id NOT IN (SELECT DISTINCT standard_concept_id FROM standard_combined_drug_mapping WHERE lookup_value = ?1) and outcome_concept_id IN (SELECT DISTINCT outcome_concept_id FROM standard_case_outcome WHERE pt = ?2) and fda_dt between ?3 and ?4) as count_b,(SELECT SUM(drug_outcome_pair_count) FROM standard_drug_outcome_count_date WHERE drug_concept_id IN (select distinct standard_concept_id FROM standard_combined_drug_mapping WHERE lookup_value = ?1) and outcome_concept_id NOT IN (select distinct outcome_concept_id from standard_case_outcome where pt = ?2) and fda_dt between ?3 and ?4) as count_c,(select sum(drug_outcome_pair_count) from standard_drug_outcome_count_date where drug_concept_id NOT IN (select distinct standard_concept_id FROM standard_combined_drug_mapping WHERE lookup_value = ?1) and outcome_concept_id NOT IN (select distinct outcome_concept_id from standard_case_outcome where pt = ?2) and fda_dt between ?3 and ?4) as count_d) p",
            countQuery = "SELECT count(*) from standard_drug_outcome_statistics p",
            nativeQuery = true)
    PRR getPRRByDateName(String drugName, String outcomeName, int startDate, int endDate);
}

