package io.swagger.service;

import io.swagger.model.ROR;
import io.swagger.model.RORWrapper;
import io.swagger.repository.RorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class RORServiceImpl {
    @Autowired
    private RorRepo rorRepo;

    public RORWrapper getRORByCode(int drugCode, int outcomeCode) {

        ROR ror = rorRepo.getRORByCode(drugCode, outcomeCode);
        List<ROR> rorList = new ArrayList<>();
        rorList.add(ror);
        RORWrapper rorWrap = new RORWrapper("https://schema.org", "MedicalEntity", "ReportingOddsRatio", "http://purl.obolibrary.org/obo/STATO_0000182", "The odds ratio is a ratio of describing the strength of association or non-independence between two binary data values by forming the ratio of the odds for the first group and the odds for the second group.", rorList);
        return rorWrap;
    }

    public RORWrapper getRORByName(String drugName, String outcomeName) {

        ROR rorName = rorRepo.getRORByName(drugName, outcomeName);
        List<ROR> rorListName = new ArrayList<>();
        rorListName.add(rorName);
        RORWrapper rorWrap = new RORWrapper("https://schema.org", "MedicalEntity", "ReportingOddsRatio", "http://purl.obolibrary.org/obo/STATO_0000182", "The odds ratio is a ratio of describing the strength of association or non-independence between two binary data values by forming the ratio of the odds for the first group and the odds for the second group.", rorListName);
        return rorWrap;
    }

    public RORWrapper getRORByDateCode(int drugCode, int outcomeCode, int startDate, int endDate) {

        ROR ror = rorRepo.getRORByDateCode(drugCode, outcomeCode, startDate, endDate);
        List<ROR> rorList = new ArrayList<>();
        rorList.add(ror);
        RORWrapper rorWrap = new RORWrapper("https://schema.org", "MedicalEntity", "ReportingOddsRatio", "http://purl.obolibrary.org/obo/STATO_0000182", "The odds ratio is a ratio of describing the strength of association or non-independence between two binary data values by forming the ratio of the odds for the first group and the odds for the second group.", rorList);
        return rorWrap;
    }

    public RORWrapper getRORByDateName(String drugName, String outcomeName, int startDate, int endDate) {

        ROR rorName = rorRepo.getRORByDateName(drugName, outcomeName, startDate, endDate);
        List<ROR> rorListName = new ArrayList<>();
        rorListName.add(rorName);
        RORWrapper rorWrap = new RORWrapper("https://schema.org", "MedicalEntity", "ReportingOddsRatio", "http://purl.obolibrary.org/obo/STATO_0000182", "The odds ratio is a ratio of describing the strength of association or non-independence between two binary data values by forming the ratio of the odds for the first group and the odds for the second group.", rorListName);
        return rorWrap;
    }
}

