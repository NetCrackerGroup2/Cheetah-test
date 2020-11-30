package com.ncedu.cheetahtest.entity.dataset;

import lombok.Data;

import java.util.List;

@Data
public class PaginationDataset {
    private int totalElements;
    private  List<DataSet> dataSets;

}
