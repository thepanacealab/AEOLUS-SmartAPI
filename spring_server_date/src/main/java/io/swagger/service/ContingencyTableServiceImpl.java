package io.swagger.service;

import io.swagger.model.ContingencyTable;
import io.swagger.model.ContingencyTableWrapper;
import io.swagger.repository.ContingencyTableRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ContingencyTableServiceImpl {

    @Autowired
    private ContingencyTableRepo contRepo;

    public ContingencyTableWrapper getContingencyTableByCode(int drugCode, int outcomeCode) {
        ContingencyTable ctCode = contRepo.getContingencyTableByCode(drugCode, outcomeCode);
        List<ContingencyTable> contListCode = new ArrayList<>();
        contListCode.add(ctCode);

        ContingencyTableWrapper ctWrapper = new ContingencyTableWrapper("https://schema.org", "MedicalEntity", "Contingency Table", "http://purl.obolibrary.org/obo/STATO_0000138", "A 2x2 contingency table built for 2 dichotomous variables (i.e. 2 categorical variables, each with only 2 possible outcomes).", contListCode);

        return ctWrapper;
    }

    public ContingencyTableWrapper getContingencyTableByName(String drugName, String outcomeName) {
        ContingencyTable ctName = contRepo.getContingencyTableByName(drugName, outcomeName);
        List<ContingencyTable> contListName = new ArrayList<>();
        contListName.add(ctName);

        ContingencyTableWrapper ctWrapper = new ContingencyTableWrapper("https://schema.org", "MedicalEntity", "Contingency Table", "http://purl.obolibrary.org/obo/STATO_0000138", "A 2x2 contingency table built for 2 dichotomous variables (i.e. 2 categorical variables, each with only 2 possible outcomes).", contListName);

        return ctWrapper;
    }

    public ContingencyTableWrapper getContingencyTableByCodeDate(int drugCode, int outcomeCode, int startDate, int endDate) {
        ContingencyTable ctCode = contRepo.getContingencyTableByCodeDate(drugCode, outcomeCode, startDate, endDate);
        List<ContingencyTable> contListCode = new ArrayList<>();
        contListCode.add(ctCode);

        ContingencyTableWrapper ctWrapper = new ContingencyTableWrapper("https://schema.org", "MedicalEntity", "Contingency Table", "http://purl.obolibrary.org/obo/STATO_0000138", "A 2x2 contingency table built for 2 dichotomous variables (i.e. 2 categorical variables, each with only 2 possible outcomes).", contListCode);

        return ctWrapper;
    }

    public ContingencyTableWrapper getContingencyTableByNameDate(String drugName, String outcomeName, int startDate, int endDate) {
        ContingencyTable ctName = contRepo.getContingencyTableByNameDate(drugName, outcomeName, startDate, endDate);
        List<ContingencyTable> contListName = new ArrayList<>();
        contListName.add(ctName);

        ContingencyTableWrapper ctWrapper = new ContingencyTableWrapper("https://schema.org", "MedicalEntity", "Contingency Table", "http://purl.obolibrary.org/obo/STATO_0000138", "A 2x2 contingency table built for 2 dichotomous variables (i.e. 2 categorical variables, each with only 2 possible outcomes).", contListName);

        return ctWrapper;
    }


}
