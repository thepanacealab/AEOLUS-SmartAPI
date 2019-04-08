package io.swagger.api;


import java.util.*;

import io.swagger.model.ContingencyTable;
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
public class ContingencyTableCodesApiControllerIntegrationTest {

    @Autowired
    private ContingencyTableCodesApi api;

    @Test
    public void contingencyTableCodesTest() throws Exception {
        String drugCode = "drugCode_example";
        String outcomeCode = "outcomeCode_example";
        ResponseEntity<List<ContingencyTable>> responseEntity = api.contingencyTableCodes(drugCode, outcomeCode);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
