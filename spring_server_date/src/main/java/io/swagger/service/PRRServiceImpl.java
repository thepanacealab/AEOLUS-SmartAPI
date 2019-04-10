package io.swagger.service;

import io.swagger.model.PRR;
import io.swagger.model.PRRWrapper;
import io.swagger.repository.PrrRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class PRRServiceImpl {

    @Autowired
    private PrrRepo prrRepo;

    public PRRWrapper getPRRByCode(int drugCode, int outcomeCode) {

        PRR prrCode = prrRepo.getPRRByCode(drugCode, outcomeCode);
        List<PRR> prrList = new ArrayList<>();
        prrList.add(prrCode);

        PRRWrapper prrWrap = new PRRWrapper("https://schema.org", "MedicalEntity", "ProportionalReportingRatio", "http://purl.obolibrary.org/obo/OAE_0001563", "statistic to summarize the extent to which a particular adverse event is observed for individuals taking a specific a drug, compared to the frequency at which the same adverse event occurs for patients taking some other drug", prrList);
        return prrWrap;

    }

    public PRRWrapper getPRRByName(String drugName, String outcomeName) {

        PRR prr = prrRepo.getPRRByName(drugName, outcomeName);
        List<PRR> prrList = new ArrayList<>();
        prrList.add(prr);

        PRRWrapper prrWrap = new PRRWrapper("https://schema.org", "MedicalEntity", "ProportionalReportingRatio", "http://purl.obolibrary.org/obo/OAE_0001563", "statistic to summarize the extent to which a particular adverse event is observed for individuals taking a specific a drug, compared to the frequency at which the same adverse event occurs for patients taking some other drug", prrList);
        return prrWrap;
    }

    public PRRWrapper getPRRByDateCode(int drugCode, int outcomeCode,int startDate, int endDate) {

        PRR prrCode = prrRepo.getPRRByDateCode(drugCode, outcomeCode, startDate, endDate);
        List<PRR> prrList = new ArrayList<>();
        prrList.add(prrCode);
        PRRWrapper prrWrap = new PRRWrapper("https://schema.org", "MedicalEntity", "ProportionalReportingRatio", "http://purl.obolibrary.org/obo/OAE_0001563", "statistic to summarize the extent to which a particular adverse event is observed for individuals taking a specific a drug, compared to the frequency at which the same adverse event occurs for patients taking some other drug", prrList);
        return prrWrap;

    }

    public PRRWrapper getPRRByDateName(String drugName, String outcomeName, int startDate, int endDate) {

        PRR prr = prrRepo.getPRRByDateName(drugName, outcomeName, startDate, endDate);
        List<PRR> prrList = new ArrayList<>();
        prrList.add(prr);
        PRRWrapper prrWrap = new PRRWrapper("https://schema.org", "MedicalEntity", "ProportionalReportingRatio", "http://purl.obolibrary.org/obo/OAE_0001563", "statistic to summarize the extent to which a particular adverse event is observed for individuals taking a specific a drug, compared to the frequency at which the same adverse event occurs for patients taking some other drug", prrList);
        return prrWrap;
    }
}

