package io.swagger.api;


import io.swagger.model.PRRWrapper;
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
public class PPRCodesApiControllerIntegrationTest {

    @Autowired
    private PPRCodesApi api;

    @Test
    public void pprCodesTest() throws Exception {
        String drugCode = "drugCode_example";
        String outcomeCode = "outcomeCode_example";
        ResponseEntity<PRRWrapper> responseEntity = api.pprCodes(drugCode, outcomeCode);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}
