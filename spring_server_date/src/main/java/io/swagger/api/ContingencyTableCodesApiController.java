package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.ContingencyTable;
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
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-03-04T11:51:52.090Z[GMT]")
@Controller
public class ContingencyTableCodesApiController implements ContingencyTableCodesApi {

    private static final Logger log = LoggerFactory.getLogger(ContingencyTableCodesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    ContingencyTableServiceImpl contService;

    @org.springframework.beans.factory.annotation.Autowired
    public ContingencyTableCodesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ContingencyTableWrapper> contingencyTableCodes(@NotNull @ApiParam(value = "RxNorm drug code for search", required = true) @Valid @RequestParam(value = "drugCode", required = true) String drugCode,@NotNull @ApiParam(value = "SNOMEDCT outcome code for search", required = true) @Valid @RequestParam(value = "outcomeCode", required = true) String outcomeCode) {
        String accept = request.getHeader("Accept");
        int DrugCode = Integer.parseInt(drugCode);
        int OutcomeCode = Integer.parseInt(outcomeCode);
        ContingencyTableWrapper contingencyTable = contService.getContingencyTableByCode(DrugCode, OutcomeCode);
        if (!contingencyTable.getOutcome().isEmpty()) {
            return ResponseEntity.ok().body(contingencyTable);
        }else{
            return new ResponseEntity("Record not found for drug/outcome values", HttpStatus.NOT_FOUND);
        }
    }

}
