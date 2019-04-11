package io.swagger.api;


import io.swagger.model.RORWrapper;
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
public class RORNamesApiControllerIntegrationTest {

    @Autowired
    private RORNamesApi api;

    @Test
    public void rorNamesTest() throws Exception {
        String drugName = "drugName_example";
        String outcomeName = "outcomeName_example";
        ResponseEntity<RORWrapper> responseEntity = api.rorNames(drugName, outcomeName);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
