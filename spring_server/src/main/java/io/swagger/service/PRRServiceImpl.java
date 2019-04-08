package io.swagger.service;

import io.swagger.model.PRR;
import io.swagger.repository.PrrRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class PRRServiceImpl {

    @Autowired
    private PrrRepo prrRepo;

    public List<PRR> getPRRByCode(int drugCode, int outcomeCode) {

        PRR prrCode = prrRepo.getPRRByCode(drugCode, outcomeCode);
        List<PRR> prrList = new ArrayList<>();
        prrList.add(prrCode);
        prrCode.toString();
        return prrList;

    }

    public List<PRR> getPRRByName(String drugName, String outcomeName) {

        PRR prr = prrRepo.getPRRByName(drugName, outcomeName);
        List<PRR> prrList = new ArrayList<>();
        prrList.add(prr);
        prr.toString();
        return prrList;
    }

    public List<PRR> getPRRByDateCode(int drugCode, int outcomeCode,int startDate, int endDate) {

        PRR prrCode = prrRepo.getPRRByDateCode(drugCode, outcomeCode, startDate, endDate);
        List<PRR> prrList = new ArrayList<>();
        prrList.add(prrCode);
        prrCode.toString();
        return prrList;

    }

    public List<PRR> getPRRByDateName(String drugName, String outcomeName, int startDate, int endDate) {

        PRR prr = prrRepo.getPRRByDateName(drugName, outcomeName, startDate, endDate);
        List<PRR> prrList = new ArrayList<>();
        prrList.add(prr);
        prr.toString();
        return prrList;
    }
}

