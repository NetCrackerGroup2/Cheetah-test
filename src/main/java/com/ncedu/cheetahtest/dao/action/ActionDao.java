package com.ncedu.cheetahtest.dao.action;


import com.ncedu.cheetahtest.entity.action.Action;

import java.util.List;

public interface ActionDao {

    List<Action> selectActionsByTitleLike(String title,int limit, int offset);
    int getTotalElements(String title);
    List<Action> getActionsInCompound(int idCompound, int limit, int offset);
    List<Action> getAllActionsInComp(int idComp);
    int getTotalActionsInComp(int idCompound);
    int getTotalActionsByType(String type);
    Action getActionByTitle(String title);
    Action getActionById(int id);
    Action editActionDesc(String description, int id);
    List<Action> selectAllActionsByTitleLike(String title);
    List<Action> getActionsByType(String type, int limit, int offset);


}
