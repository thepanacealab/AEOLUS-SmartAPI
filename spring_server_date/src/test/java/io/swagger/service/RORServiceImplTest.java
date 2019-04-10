package io.swagger.service;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import io.swagger.model.RORWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.swagger.model.ROR;
import io.swagger.repository.RorRepo;

@RunWith(MockitoJUnitRunner.class)
public class RORServiceImplTest {

    @InjectMocks
    RORServiceImpl rorServImpl;

    @Mock
    RorRepo rorRepo;

	/*@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }*/

    @Test
    public void getRORByCode() {
        int drugCode = 21;
        int outcomeCode = 22;
        ROR rorCode = new ROR();
        rorCode.id = 1;
        rorCode.setRor(1);
        rorCode.setCasecount(10);
        rorCode.setUpperCI(10);
        rorCode.setLowerCI(10);
        when(rorRepo.getRORByCode(drugCode,outcomeCode)).thenReturn(rorCode);
        RORWrapper rorList = rorServImpl.getRORByCode(drugCode, outcomeCode);
        assertTrue(rorList.getStatistics().size() > 0);
        ROR ror = rorList.getStatistics().get(0);
        assertSame(ror.id, rorCode.id);

    }

    @Test
    public void getRORByName() {
        String drugName = "anyDrug";
        String outcomeName = "anyOutcome";
        ROR rorName = new ROR();
        rorName.id = 2;
        rorName.setRor(1);
        rorName.setCasecount(10);
        rorName.setUpperCI(10);
        rorName.setLowerCI(10);
        when(rorRepo.getRORByName(drugName,outcomeName)).thenReturn(rorName);
        RORWrapper rorList = rorServImpl.getRORByName(drugName, outcomeName);
        assertTrue(rorList.getStatistics().size() > 0);
        ROR ror = rorList.getStatistics().get(0);
        assertSame(ror.id, rorName.id);

    }

    @Test
    public void getRORByDateCode() {
        int drugCode = 21;
        int outcomeCode = 22;
        int startDate = 20170101;
        int endDate = 20180101;
        ROR rorCode = new ROR();
        rorCode.id = 3;
        rorCode.setRor(1);
        rorCode.setCasecount(10);
        rorCode.setUpperCI(10);
        rorCode.setLowerCI(10);
        when(rorRepo.getRORByDateCode(drugCode,outcomeCode,startDate,endDate)).thenReturn(rorCode);
        RORWrapper rorList = rorServImpl.getRORByDateCode(drugCode, outcomeCode, startDate, endDate);
        assertTrue(rorList.getStatistics().size() > 0);
        ROR ror = rorList.getStatistics().get(0);
        assertSame(ror.id, rorCode.id);

    }

    @Test
    public void getRORByDateName() {
        String drugName = "anyDrug";
        String outcomeName = "anyOutcome";
        int startDate = 20170101;
        int endDate = 20180101;
        ROR rorName = new ROR();
        rorName.id = 2;
        rorName.setRor(1);
        rorName.setCasecount(10);
        rorName.setUpperCI(10);
        rorName.setLowerCI(10);
        when(rorRepo.getRORByDateName(drugName,outcomeName,startDate,endDate)).thenReturn(rorName);
        RORWrapper rorList = rorServImpl.getRORByDateName(drugName, outcomeName, startDate, endDate);
        assertTrue(rorList.getStatistics().size() > 0);
        ROR ror = rorList.getStatistics().get(0);
        assertSame(ror.id, rorName.id);

    }

}
