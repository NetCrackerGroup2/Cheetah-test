package com.ncedu.cheetahtest.controller.action;

import com.ncedu.cheetahtest.entity.action.*;
import com.ncedu.cheetahtest.service.action.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${frontend.ulr}")

public class ActionsController {
    private final ActionService actionService;

    @Autowired
    public ActionsController(ActionService actionService) {
        this.actionService = actionService;
    }

    @GetMapping("/actions/get-all")
    public List<Action> getAllActions() {
        return actionService.selectAllActions();
    }

    @PostMapping("/actions")
    public Action createAction(@RequestParam(name = "id") int idLibrary, @RequestBody Action actionDTO) {

        return actionService.createAction(idLibrary, actionDTO);

    }

    @GetMapping("/actions")
    public Action getActionsByTitle(@RequestParam("idAction") int idAction) {
        return actionService.getActionById(idAction);
    }


    @PutMapping("/actions")
    public Action editAction(@RequestBody Action actionDTO) {
        return actionService.editAction(actionDTO);
    }

    @PutMapping("actions/change-status")
    public Action changeStatus(@RequestBody ChangeActionStatusDTO changeActionStatusDTO) {
        return actionService.changeStatus(changeActionStatusDTO.getStatusToChange(),
                changeActionStatusDTO.getId());
    }

    @DeleteMapping("/actions")
    public ResponseEntity<ActionStatusResponse> deleteAction(
            @RequestHeader("Authorisation") String token,
            @RequestParam("idAction") int idAction) {
        actionService.deleteAction(token, idAction);
        return ResponseEntity.ok(new ActionStatusResponse("ActionDeletedSuccessfully"));
    }
}