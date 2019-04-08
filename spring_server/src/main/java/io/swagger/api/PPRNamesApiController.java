package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.PRR;
import io.swagger.service.PRRServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-03-04T11:51:52.090Z[GMT]")
@Controller
public class PPRNamesApiController implements PPRNamesApi {

    private static final Logger log = LoggerFactory.getLogger(PPRNamesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    PRRServiceImpl prrService;

    @org.springframework.beans.factory.annotation.Autowired
    public PPRNamesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<PRR>> pprNames(@NotNull @ApiParam(value = "RxNorm drug name for search", required = true) @Valid @RequestParam(value = "drugName", required = true) String drugName,@NotNull @ApiParam(value = "SNOMEDCT outcome name for search", required = true) @Valid @RequestParam(value = "outcomeName", required = true) String outcomeName) {
        String accept = request.getHeader("Accept");
        String DrugName = drugName.toUpperCase();
        String OutcomeName = outcomeName.toUpperCase();
        List<PRR> prr = prrService.getPRRByName(DrugName, OutcomeName);
        if (!prr.isEmpty()) {
            return ResponseEntity.ok().body(prr);
        }else{
            return new ResponseEntity<List<PRR>>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

}
