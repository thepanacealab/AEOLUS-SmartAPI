package io.swagger.repository;

import io.swagger.model.PRR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrrRepo extends JpaRepository<PRR, Integer>{
    @Query(value = "SELECT 1 AS id, p.prr, p.case_count, p.prr_95_percent_upper_confidence_limit, p.prr_95_percent_lower_confidence_limit FROM standard_drug_outcome_statistics p WHERE p.drug_concept_id = ?1 and p.outcome_concept_id = ?2",
            countQuery = "SELECT count(*) from standard_drug_outcome_statistics p",
            nativeQuery = true)
    PRR getPRRByCode(int drugCode, int outcomeCode);

    @Query(value = "SELECT 1 AS id, a.prr, a.case_count, a.prr_95_percent_upper_confidence_limit, a.prr_95_percent_lower_confidence_limit FROM standard_drug_outcome_statistics a INNER JOIN standard_case_outcome b ON a.outcome_concept_id = b.outcome_concept_id INNER JOIN standard_combined_drug_mapping c ON a.drug_concept_id = c.standard_concept_id WHERE c.lookup_value = ?1 AND b.pt = ?2",
            countQuery = "SELECT count(*) from standard_drug_outcome_statistics p",
            nativeQuery = true)
    PRR getPRRByName(String drugName, String outcomeName);
}
