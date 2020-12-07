package com.ncedu.cheetahtest.entity.testscenario;

import lombok.Data;

import java.util.List;

@Data
public class PaginationItems {
    private int totalElements;
    private List<ItemDTO> itemsFromTestScenario;
}
