package io.swagger.repository;

import io.swagger.model.ContingencyTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ContingencyTableRepo extends JpaRepository<ContingencyTable, Integer> {

    @Query(value = "SELECT 1 AS id, ct.count_a, ct.count_b, ct.count_c, ct.count_d FROM standard_drug_outcome_contingency_table ct WHERE ct.drug_concept_id = ?1 and ct.outcome_concept_id = ?2",
            countQuery = "SELECT count(*) from standard_drug_outcome_contingency_table ct",
            nativeQuery = true)
    ContingencyTable getContingencyTableByCode(int drugCode, int outcomeCode);

    @Query(value = "SELECT 1 AS id, a.count_a, a.count_b, a.count_c, a.count_d FROM standard_drug_outcome_contingency_table a INNER JOIN standard_case_outcome b ON a.outcome_concept_id = b.outcome_concept_id INNER JOIN standard_combined_drug_mapping c ON a.drug_concept_id = c.standard_concept_id WHERE c.lookup_value = ?1 AND b.pt = ?2",
            countQuery = "SELECT count(*) from standard_drug_outcome_contingency_table ct",
            nativeQuery = true)
    ContingencyTable getContingencyTableByName(String drugName, String outcomeName);

}
