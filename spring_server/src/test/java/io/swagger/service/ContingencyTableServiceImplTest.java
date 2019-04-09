package io.swagger.service;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.swagger.model.ContingencyTable;
import io.swagger.repository.ContingencyTableRepo;

@RunWith(MockitoJUnitRunner.class)
public class ContingencyTableServiceImplTest {

    @InjectMocks
    ContingencyTableServiceImpl conTabServImpl;

    @Mock
    ContingencyTableRepo contRepo;

    /*@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }*/

    @Test
    public void getContingencyTableByCode() {
        int drugCode = 21;
        int outcomeCode = 22;
        ContingencyTable ctCode = new ContingencyTable();
        ctCode.id = 1;
        ctCode.setA(10);
        ctCode.setB(10);
        ctCode.setC(10);
        ctCode.setD(10);
        when(contRepo.getContingencyTableByCode(drugCode,outcomeCode)).thenReturn(ctCode);
        List<ContingencyTable> conTabList = conTabServImpl.getContingencyTableByCode(drugCode, outcomeCode);
        assertTrue(conTabList.size() > 0);
        ContingencyTable ctab = conTabList.get(0);
        assertSame(ctab.id, ctCode.id);

    }

    @Test
    public void getContingencyTableByName() {
        String drugName = "anyDrug";
        String outcomeName = "anyOutcome";
        ContingencyTable ctName = new ContingencyTable();
        ctName.id = 2;
        ctName.setA(10);
        ctName.setB(10);
        ctName.setC(10);
        ctName.setD(10);
        when(contRepo.getContingencyTableByName(drugName,outcomeName)).thenReturn(ctName);
        List<ContingencyTable> conTabList = conTabServImpl.getContingencyTableByName(drugName, outcomeName);
        assertTrue(conTabList.size() > 0);
        ContingencyTable ctab = conTabList.get(0);
        assertSame(ctab.id, ctName.id);

    }

    @Test
    public void getContingencyTableByCodeDate() {
        int drugCode = 21;
        int outcomeCode = 22;
        int startDate = 20170101;
        int endDate = 20180101;
        ContingencyTable ctCode = new ContingencyTable();
        ctCode.id = 3;
        ctCode.setA(10);
        ctCode.setB(10);
        ctCode.setC(10);
        ctCode.setD(10);
        when(contRepo.getContingencyTableByCodeDate(drugCode,outcomeCode,startDate,endDate)).thenReturn(ctCode);
        List<ContingencyTable> conTabList = conTabServImpl.getContingencyTableByCodeDate(drugCode, outcomeCode, startDate, endDate);
        assertTrue(conTabList.size() > 0);
        ContingencyTable ctab = conTabList.get(0);
        assertSame(ctab.id, ctCode.id);
    }

    @Test
    public void getContingencyTableByNameDate() {
        String drugName = "anyDrug";
        String outcomeName = "anyOutcome";
        int startDate = 20170101;
        int endDate = 20180101;
        ContingencyTable ctName = new ContingencyTable();
        ctName.id = 4;
        ctName.setA(10);
        ctName.setB(10);
        ctName.setC(10);
        ctName.setD(10);
        when(contRepo.getContingencyTableByNameDate(drugName,outcomeName,startDate,endDate)).thenReturn(ctName);
        List<ContingencyTable> conTabList = conTabServImpl.getContingencyTableByNameDate(drugName, outcomeName,startDate,endDate);
        assertTrue(conTabList.size() > 0);
        ContingencyTable ctab = conTabList.get(0);
        assertSame(ctab.id, ctName.id);

    }
}
