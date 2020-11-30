package com.ncedu.cheetahtest.controller.actscenario;

import com.ncedu.cheetahtest.entity.actscenario.ActScenario;
import com.ncedu.cheetahtest.entity.actscenario.ActScenarioResponseStatus;
import com.ncedu.cheetahtest.entity.actscenario.PaginationActScenario;
import com.ncedu.cheetahtest.service.actscenario.ActScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "${frontend.ulr}")
@RequestMapping("/api")
public class ActScenarioController {
    private final ActScenarioService actScenarioService;

    @Autowired
    public ActScenarioController(ActScenarioService actScenarioService) {
        this.actScenarioService = actScenarioService;
    }


    @PostMapping("/test-scenarios/act-scenarios")
    public ActScenario createActScenario(@RequestBody ActScenario actScenario){
        return actScenarioService.createActScenario(actScenario);
    }

    @GetMapping("/test-scenarios/act-scenarios")
    public PaginationActScenario findByTitlePaginated(@RequestParam("title") String title,
                                                 @RequestParam("size") int size,
                                                 @RequestParam("page") int page){
        return actScenarioService.findByTitleLike(title,size,page);
    }
    @GetMapping("/test-scenarios/{idTestScenario}/act-scenarios")
    public PaginationActScenario findByTitleInTestScenPaginated(@RequestParam("title") String title,
                                             @RequestParam("size") int size,
                                             @RequestParam("page") int page,
                                             @PathVariable int idTestScenario){
        return actScenarioService.findByTitleInTestScenario(title,idTestScenario,size,page);
    }
    //same as without /all but doesn`t have pagination
    @GetMapping("/test-scenarios/act-scenarios/all")
    public List<ActScenario> findAllByTitlePaginated(@RequestParam("title") String title){
        return actScenarioService.findAllByTitleLike(title);
    }
    @GetMapping("/test-scenarios/{idTestScenario}/act-scenarios/all")
    public List<ActScenario> findAllByTitle(@RequestParam("title") String title,
                                                     @PathVariable int idTestScenario){
        return actScenarioService.findAllByTitleInTestScenario(title, idTestScenario);
    }
    @PutMapping("/test-scenarios/act-scenarios/{idActScen}")
    public ActScenario editActScenario(@RequestBody ActScenario actScenario, @PathVariable int idActScen){
        return actScenarioService.editActScenario(actScenario,idActScen);
    }
    @DeleteMapping("/test-scenarios/act-scenarios/{idActScen}")
    public ActScenarioResponseStatus deleteActScenario(@PathVariable int idActScen){
        actScenarioService.deleteActScenario(idActScen);
        return new ActScenarioResponseStatus("Success");
    }
}
