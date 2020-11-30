package com.ncedu.cheetahtest.dao.compactprior;

public class CompActPriorConsts {
    public static final String CREATE_COMP_ACT_PRIOR = "INSERT INTO comp_act_prior (comp_id,action_id,priority) VALUES (?,?,?)";
    public static final String DELETE_BY_ID_COMPOUND = "DELETE FROM comp_act_prior WHERE comp_id = ?";
    public static final String DELETE_BY_ID = "DELETE FROM comp_act_prior WHERE id = ?";

}
