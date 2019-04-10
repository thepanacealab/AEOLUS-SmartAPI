package io.swagger.service;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import io.swagger.model.PRRWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.swagger.model.PRR;
import io.swagger.repository.PrrRepo;

@RunWith(MockitoJUnitRunner.class)
public class PRRServiceImplTest {

    @InjectMocks
    PRRServiceImpl prrServImpl;

    @Mock
    PrrRepo prrRepo;

	/*@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }*/

    @Test
    public void getPRRByCode() {
        int drugCode = 21;
        int outcomeCode = 22;
        PRR prrCode = new PRR();
        prrCode.id = 1;
        prrCode.setPrr(1);
        prrCode.setCasecount(10);
        prrCode.setUpperCI(3);
        prrCode.setLowerCI(2);
        when(prrRepo.getPRRByCode(drugCode,outcomeCode)).thenReturn(prrCode);
        PRRWrapper prrList = prrServImpl.getPRRByCode(drugCode, outcomeCode);
        assertTrue(prrList.getStatistics().size() > 0);
        PRR prr = prrList.getStatistics().get(0);
        assertSame(prr.id, prrCode.id);

    }

    @Test
    public void getPRRByName() {
        String drugName = "anyDrug";
        String outcomeName = "anyOutcome";
        PRR prrName = new PRR();
        prrName.id = 2;
        prrName.setPrr(1);
        prrName.setCasecount(10);
        prrName.setUpperCI(3);
        prrName.setLowerCI(2);
        when(prrRepo.getPRRByName(drugName,outcomeName)).thenReturn(prrName);
        PRRWrapper prrList = prrServImpl.getPRRByName(drugName, outcomeName);
        assertTrue(prrList.getStatistics().size() > 0);
        PRR prr = prrList.getStatistics().get(0);
        assertSame(prr.id, prrName.id);

    }

    @Test
    public void getPRRByDateCode() {
        int drugCode = 21;
        int outcomeCode = 22;
        int startDate = 20170101;
        int endDate = 20180101;
        PRR prrCode = new PRR();
        prrCode.id = 3;
        prrCode.setPrr(1);
        prrCode.setCasecount(10);
        prrCode.setUpperCI(3);
        prrCode.setLowerCI(2);
        when(prrRepo.getPRRByDateCode(drugCode,outcomeCode,startDate,endDate)).thenReturn(prrCode);
        PRRWrapper prrList = prrServImpl.getPRRByDateCode(drugCode, outcomeCode,startDate,endDate);
        assertTrue(prrList.getStatistics().size() > 0);
        PRR prr = prrList.getStatistics().get(0);
        assertSame(prr.id, prrCode.id);

    }

    @Test
    public void getPRRByDateName() {
        String drugName = "anyDrug";
        String outcomeName = "anyOutcome";
        int startDate = 20170101;
        int endDate = 20180101;
        PRR prrName = new PRR();
        prrName.id = 4;
        prrName.setPrr(1);
        prrName.setCasecount(10);
        prrName.setUpperCI(3);
        prrName.setLowerCI(2);
        when(prrRepo.getPRRByDateName(drugName,outcomeName,startDate,endDate)).thenReturn(prrName);
        PRRWrapper prrList = prrServImpl.getPRRByDateName(drugName, outcomeName,startDate,endDate);
        assertTrue(prrList.getStatistics().size() > 0);
        PRR prr = prrList.getStatistics().get(0);
        assertSame(prr.id, prrName.id);

    }
}