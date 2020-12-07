package com.ncedu.cheetahtest.entity.project;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Project {
    private int id;
    private String title;
    private String link;
    private String status;
    private Timestamp createDate;

    public Project(int id, String title, String link, String status, Timestamp createDate) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.status = status;
        this.createDate = createDate;
    }
}
