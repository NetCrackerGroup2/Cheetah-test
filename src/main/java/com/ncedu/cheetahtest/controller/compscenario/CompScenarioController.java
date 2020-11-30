package com.ncedu.cheetahtest.controller.compscenario;

import com.ncedu.cheetahtest.entity.actscenario.ActScenario;
import com.ncedu.cheetahtest.entity.compscenario.CompScenario;
import com.ncedu.cheetahtest.entity.compscenario.CompScenarioCreationDTO;
import com.ncedu.cheetahtest.entity.compscenario.CompScenarioResponseStatus;
import com.ncedu.cheetahtest.entity.compscenario.PaginationCompScenario;
import com.ncedu.cheetahtest.service.compscenario.CompScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${frontend.ulr}")
public class CompScenarioController {
    private final CompScenarioService compScenarioService;

    @Autowired
    public CompScenarioController(CompScenarioService compScenarioService) {
        this.compScenarioService = compScenarioService;
    }

    @PostMapping("/test-scenarios/comp-scenarios")
    public CompScenario createCompScenario(@RequestBody CompScenarioCreationDTO compScenarioCreationDTO){
        return compScenarioService.createCompScenario(compScenarioCreationDTO);
    }

    @PutMapping("/test-scenarios/comp-scenarios/{idCompScen}")
    public CompScenario editCompScenario(@RequestBody CompScenarioCreationDTO compScenarioCreationDTO,
                                         @PathVariable int idCompScen){
        return compScenarioService.editCompScenario(compScenarioCreationDTO,idCompScen);
    }


    @DeleteMapping("/test-scenarios/comp-scenarios/{idCompScen}")
    public CompScenarioResponseStatus deleteCompScenario(@PathVariable int idCompScen){
        compScenarioService.deleteCompScenario(idCompScen);
        return new CompScenarioResponseStatus("Success");
    }


    @GetMapping("/test-scenarios/comp-scenarios")
    public PaginationCompScenario findAllByTitlePaginated(@RequestParam("title") String title,
                                                          @RequestParam("size") int size,
                                                          @RequestParam("page") int page){
        return compScenarioService.findByTitleLike(title,size,page);
    }
    @GetMapping("/test-scenarios/{idTestScenario}/comp-scenarios")
    public PaginationCompScenario findByTitlePaginated(@RequestParam("title") String title,
                                             @RequestParam("size") int size,
                                             @RequestParam("page") int page,
                                             @PathVariable int idTestScenario){
        return compScenarioService.findByTitleInTestScenario(title,idTestScenario,size,page);
    }
    //same as without /all but doesn't have pagination
    @GetMapping("/test-scenarios/comp-scenarios/all")
    public List<CompScenario> findAllByTitlePaginated(@RequestParam("title") String title){
        return compScenarioService.findAllByTitleLike(title);
    }
    @GetMapping("/test-scenarios/{idTestScenario}/comp-scenarios/all")
    public List<CompScenario> findAllByTitlePaginated(@RequestParam("title") String title,
                                                     @PathVariable int idTestScenario){
        return compScenarioService.findAllByTitleInTestScenario(title, idTestScenario);
    }
    @GetMapping("/test-scenarios/comp-scenarios/{idCompScen}/act-scenarios")
    public List<ActScenario> getAllActScenInCompScen(@PathVariable int idCompScen){
        return compScenarioService.getAllActionScenarioInComp(idCompScen);
    }
}
