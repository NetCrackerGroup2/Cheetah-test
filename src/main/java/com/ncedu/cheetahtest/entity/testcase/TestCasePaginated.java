package com.ncedu.cheetahtest.entity.testcase;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class TestCasePaginated {
    @NonNull private List<TestCase> testCaseList;
    @NonNull private int totalElements;
}
