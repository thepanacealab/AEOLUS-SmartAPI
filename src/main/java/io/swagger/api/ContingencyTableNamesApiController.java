package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.ContingencyTableWrapper;
import io.swagger.service.ContingencyTableServiceImpl;
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

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-03-04T11:51:52.090Z[GMT]")
@Controller
public class ContingencyTableNamesApiController implements ContingencyTableNamesApi {

    private static final Logger log = LoggerFactory.getLogger(ContingencyTableNamesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    ContingencyTableServiceImpl contService;

    @Autowired
    public ContingencyTableNamesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ContingencyTableWrapper> contingencyTableNames(@NotNull @ApiParam(value = "RxNorm-standard drug name for search", required = true) @Valid @RequestParam(value = "drugName", required = true) String drugName, @NotNull @ApiParam(value = "SNOMEDCT-standard outcome name for search", required = true) @Valid @RequestParam(value = "outcomeName", required = true) String outcomeName) {
        String accept = request.getHeader("Accept");
        String DrugName = drugName.toUpperCase();
        String OutcomeName = outcomeName.toUpperCase();
        ContingencyTableWrapper contingencyTable = contService.getContingencyTableByName(DrugName, OutcomeName);
        if (!contingencyTable.getOutcome().isEmpty()) {
            return ResponseEntity.ok().body(contingencyTable);
        }else{
            return new ResponseEntity("Record not found for drug/outcome values", HttpStatus.NOT_FOUND);
        }
    }

}
