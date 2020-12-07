package com.ncedu.cheetahtest.entity.testscenario;

import lombok.Data;

@Data
public class ItemDTO {
    private int id;
    private String title;
    private int priority;
    private String description;
    private ItemKind kind;
}
