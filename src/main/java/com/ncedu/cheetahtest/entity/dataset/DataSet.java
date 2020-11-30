package com.ncedu.cheetahtest.entity.dataset;

import lombok.Data;

@Data
public class DataSet {
    private int id;
    private String title;
    private String description;
    private int idTestCase;
}
