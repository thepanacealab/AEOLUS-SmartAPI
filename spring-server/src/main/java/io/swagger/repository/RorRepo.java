package io.swagger.repository;

import io.swagger.model.ROR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RorRepo extends JpaRepository<ROR, Integer>{

    @Query(value = "SELECT 1 AS id, r.ror, r.case_count, r.ror_95_percent_upper_confidence_limit, r.ror_95_percent_lower_confidence_limit FROM standard_drug_outcome_statistics r WHERE r.drug_concept_id = ?1 and r.outcome_concept_id = ?2",
            countQuery = "SELECT count(*) from standard_drug_outcome_statistics r",
            nativeQuery = true)
    ROR getRORByCode(int drugCode, int outcomeCode);

    @Query(value = "SELECT 1 AS id, a.ror, a.case_count, a.ror_95_percent_upper_confidence_limit, a.ror_95_percent_lower_confidence_limit FROM standard_drug_outcome_statistics a INNER JOIN standard_case_outcome b ON a.outcome_concept_id = b.outcome_concept_id INNER JOIN standard_combined_drug_mapping c ON a.drug_concept_id = c.standard_concept_id WHERE c.lookup_value = ?1 AND b.pt = ?2",
            countQuery = "SELECT count(*) from standard_drug_outcome_statistics p",
            nativeQuery = true)
    ROR getRORByName(String drugName, String outcomeName);
}
