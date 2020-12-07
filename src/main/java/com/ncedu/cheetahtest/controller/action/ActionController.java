package com.ncedu.cheetahtest.controller.action;

import com.ncedu.cheetahtest.entity.action.Action;
import com.ncedu.cheetahtest.entity.action.ActionDescriptionDTO;
import com.ncedu.cheetahtest.entity.action.PaginationAction;
import com.ncedu.cheetahtest.service.action.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library/actions")
@CrossOrigin(origins = "${frontend.ulr}")
public class ActionController {
    private final ActionService actionService;
    @Autowired
    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }
    @GetMapping
    public PaginationAction getActionsByTitleLike(@RequestParam("title") String title,
                                                  @RequestParam("size") int size,
                                                  @RequestParam("page") int page){
        return actionService.getActionsByTitle(title,size,page);
    }

    @PutMapping("/{idAction}")
    public Action editActionDescription(@RequestBody ActionDescriptionDTO actionDescriptionDTO,
                                        @PathVariable int idAction){
        return actionService.editActionDescription(actionDescriptionDTO.getDescription(),idAction);
    }
    @GetMapping("/all")
    public List<Action> getAllActionsByTitleLike(@RequestParam("title") String title){
        return actionService.getAllByTitleLike(title);
    }
    @GetMapping("/search-by-type")
    public PaginationAction findByType(@RequestParam("type") String type,
                                       @RequestParam("size") int size,
                                       @RequestParam("page") int page){
        return actionService.geActionsByType(type, size, page);
    }

    @GetMapping("/{id}")
    public Action getAllActionsByTitleLike(@PathVariable int id){
        return actionService.getActionById(id);
    }

}
