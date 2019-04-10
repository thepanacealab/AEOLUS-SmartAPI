package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.ROR;
import io.swagger.model.RORWrapper;
import io.swagger.service.RORServiceImpl;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-04-07T17:04:41.912Z[GMT]")
@Controller
public class RORDateNamesApiController implements RORDateNamesApi {

    private static final Logger log = LoggerFactory.getLogger(RORDateNamesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    RORServiceImpl rorService;

    @org.springframework.beans.factory.annotation.Autowired
    public RORDateNamesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<RORWrapper> rorDateNames(@NotNull @ApiParam(value = "RxNorm drug name for search", required = true) @Valid @RequestParam(value = "drugName", required = true) String drugName, @NotNull @ApiParam(value = "SNOMEDCT outcome name for search", required = true) @Valid @RequestParam(value = "outcomeName", required = true) String outcomeName, @NotNull @ApiParam(value = "Start date for range search", required = true) @Valid @RequestParam(value = "startDate", required = true) String startDate, @NotNull @ApiParam(value = "End date for range search", required = true) @Valid @RequestParam(value = "endDate", required = true) String endDate) {
        String accept = request.getHeader("Accept");
        String DrugName = drugName.toUpperCase();
        String OutcomeName = outcomeName.toUpperCase();
        int StartDate = Integer.parseInt(startDate);
        int EndDate = Integer.parseInt(endDate);
        RORWrapper ror = rorService.getRORByDateName(DrugName, OutcomeName, StartDate, EndDate);
        if (!ror.getStatistics().isEmpty()) {
            return ResponseEntity.ok().body(ror);
        }else{
            return new ResponseEntity("Record not found for drug/outcome values", HttpStatus.NOT_FOUND);
        }
    }

}
