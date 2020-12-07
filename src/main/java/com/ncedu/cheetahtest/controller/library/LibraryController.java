package com.ncedu.cheetahtest.controller.library;

import com.ncedu.cheetahtest.entity.action.Action;
import com.ncedu.cheetahtest.entity.action.PaginationAction;
import com.ncedu.cheetahtest.entity.compound.Compound;
import com.ncedu.cheetahtest.entity.compound.CompoundCreationBody;
import com.ncedu.cheetahtest.entity.compound.CompoundStatusResponse;
import com.ncedu.cheetahtest.entity.compound.PaginationCompound;
import com.ncedu.cheetahtest.service.action.ActionService;
import com.ncedu.cheetahtest.service.compound.CompoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
@CrossOrigin(origins = "${frontend.ulr}")

public class LibraryController {
    private final CompoundService compoundService;
    private final ActionService actionService;

    @Autowired
    public LibraryController(CompoundService compoundService, ActionService actionService) {
        this.compoundService = compoundService;
        this.actionService = actionService;
    }

    @PostMapping
    public Compound createCompound(@RequestBody CompoundCreationBody compoundCreationBody) {
        return compoundService.createCompound(compoundCreationBody.getCompound(), compoundCreationBody.getActions());
    }

    @GetMapping
    public PaginationCompound findCompoundsByTitleLike(@RequestParam("title") String title,
                                                       @RequestParam("size") int size,
                                                       @RequestParam("page") int page) {
        return compoundService.getCompoundsByTitleLike(title, size, page);
    }

    @PutMapping("/{idCompound}")
    public Compound editCompound(@PathVariable int idCompound,
                                 @RequestBody CompoundCreationBody compoundCreationBody) {
        return compoundService.editCompound(compoundCreationBody.getCompound(), idCompound, compoundCreationBody.getActions());
    }

    @PutMapping("/{idCompound}/edit-properties")
    public Compound editOnlyProperties(@PathVariable int idCompound,
                                       @RequestBody Compound compound) {
        return compoundService.editCompoundProperties(compound, idCompound);
    }

    @GetMapping("/{idCompound}/actions")
    public PaginationAction getActionsInCompound(@PathVariable int idCompound,
                                                 @RequestParam("size") int size,
                                                 @RequestParam("page") int page) {
        return actionService.getActionsInCompound(idCompound, size, page);
    }


    @GetMapping("/{idCompound}/all-actions")
    public List<Action> getActionsInCompound(@PathVariable int idCompound) {
        return actionService.getActionsInCompoundNotPaginated(idCompound);
    }

    @DeleteMapping("/{idCompound}")
    public CompoundStatusResponse deleteCompound(@PathVariable int idCompound) {
        compoundService.deleteCompound(idCompound);
        return new CompoundStatusResponse("Success");
    }


}
