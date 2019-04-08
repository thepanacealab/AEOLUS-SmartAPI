package io.swagger.api;


import java.util.*;

import io.swagger.model.PRR;
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
public class PPRNamesApiControllerIntegrationTest {

    @Autowired
    private PPRNamesApi api;

    @Test
    public void pprNamesTest() throws Exception {
        String drugName = "drugName_example";
        String outcomeName = "outcomeName_example";
        ResponseEntity<List<PRR>> responseEntity = api.pprNames(drugName, outcomeName);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
