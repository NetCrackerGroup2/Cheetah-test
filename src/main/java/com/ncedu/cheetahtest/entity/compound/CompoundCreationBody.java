package com.ncedu.cheetahtest.entity.compound;

import com.ncedu.cheetahtest.entity.action.Action;
import lombok.Data;

import java.util.List;

@Data
public class CompoundCreationBody {
    private Compound compound;
    private List<Action> actions;
}
