package com.ncedu.cheetahtest.entity.compound;

import lombok.Data;

import java.util.List;

@Data
public class PaginationCompound {
    private List<Compound> compounds;
    private int totalCompounds;
}
