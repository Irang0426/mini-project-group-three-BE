package com.miniproject.miniprojectgroupthree.constant;

import lombok.Getter;

public enum ScheduleType {
    REST("연차"),
    WORK("당직");

    @Getter
    private String description;

    ScheduleType(String description) {
        this.description = description;
    }
}
