package io.swagger.api;


import java.util.*;

import io.swagger.model.ContingencyTable;
import io.swagger.model.ContingencyTableWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContingencyTableDateCodesApiControllerIntegrationTest {

    @Autowired
    private ContingencyTableDateCodesApi api;

    @Test
    public void contingencyTableDateCodesTest() throws Exception {
        String drugCode = "drugCode_example";
        String outcomeCode = "outcomeCode_example";
        String startDate = "startDate_example";
        String endDate = "endDate_example";
        ResponseEntity<ContingencyTableWrapper> responseEntity = api.contingencyTableDateCodes(drugCode, outcomeCode, startDate, endDate);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}
