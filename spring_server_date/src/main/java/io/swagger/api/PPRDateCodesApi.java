/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.7).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.PRR;
import io.swagger.model.PRRWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-04-07T17:04:41.912Z[GMT]")
@Api(value = "PPRDateCodes", description = "the PPRDateCodes API")
public interface PPRDateCodesApi {

    @ApiOperation(value = "Uses drug and outcome codes in RxNorm and SNOMEDCT respectively and start and end date to search AEOLUS and return the PPR, 95% confidence intervals and case count within the specified date range.", nickname = "pprDateCodes", notes = "Returns the Proportional Reporting Ratio (PPR) for the drug/outcome coded query as well as the lower and upper 95% confidence intervals and the case count. Calculations are made following [Gavali, D. K., Kulkarni, K. S., Kumar, A. & Chakraborty, B. S. Therapeutic class-specific signal detection of bradycardia associated with propranolol hydrochloride. Indian Journal of Pharmacology 41, 162–166 (2009).](http://www.ijp-online.com/text.asp?2009/41/4/162/56068)", response = Object.class, responseContainer = "List", tags={ })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response", response = Object.class, responseContainer = "List") })
    @RequestMapping(value = "/PPRDateCodes",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PRRWrapper> pprDateCodes(@NotNull @ApiParam(value = "RxNorm drug code for search", required = true) @Valid @RequestParam(value = "drugCode", required = true) String drugCode, @NotNull @ApiParam(value = "SNOMEDCT outcome code for search", required = true) @Valid @RequestParam(value = "outcomeCode", required = true) String outcomeCode, @NotNull @ApiParam(value = "Start date for range search", required = true) @Valid @RequestParam(value = "startDate", required = true) String startDate, @NotNull @ApiParam(value = "End date for range search", required = true) @Valid @RequestParam(value = "endDate", required = true) String endDate);

}
