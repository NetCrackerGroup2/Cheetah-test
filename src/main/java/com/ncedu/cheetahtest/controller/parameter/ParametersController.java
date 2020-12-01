package com.ncedu.cheetahtest.controller.parameter;

import com.ncedu.cheetahtest.dao.parameters.ResponseParameter;
import com.ncedu.cheetahtest.entity.parameter.PaginationParameter;
import com.ncedu.cheetahtest.entity.parameter.Parameter;
import com.ncedu.cheetahtest.service.parameters.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "${frontend.ulr}")
@RequestMapping("/api/parameters")
public class ParametersController {
    private final ParameterService parameterService;

    @Autowired
    public ParametersController(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @PostMapping
    public Parameter createParameter(@RequestBody Parameter parameter) {
        return parameterService.createParameter(parameter);
    }
    @GetMapping
    public PaginationParameter findParameterByType(@PathVariable int idDataSet,
                                                   @RequestParam("type") String type,
                                                   @RequestParam("size") int size,
                                                   @RequestParam("page") int page){
        return parameterService.findByType(type,idDataSet,page,size);
    }
    @PutMapping
    public Parameter editParameter(@RequestBody Parameter parameter,
                                   @RequestParam("id") int id){
        return parameterService.editParameter(parameter,id);
    }
    @DeleteMapping
    public ResponseParameter deleteParameter(@RequestParam("id") int id){
        parameterService.deleteParameter(id);
        return new ResponseParameter("Success");
    }
}
