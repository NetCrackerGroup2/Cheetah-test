package com.ncedu.cheetahtest.entity.action;

import lombok.Data;

import java.util.List;

@Data
public class PaginationAction {
    private List<Action> list;
    private int totalElements;

}
