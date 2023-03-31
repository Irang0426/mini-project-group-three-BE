package com.miniproject.miniprojectgroupthree.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule extends BaseWriterEntity{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private User user;

    @Setter
    private ScheduleType scheduleType;

    @Setter
    private LocalDate startDate;

    @Setter
    private LocalDate endDate;

    public static Schedule of(User user, ScheduleType scheduleType, LocalDate startDate, LocalDate endDate) {
        return new Schedule(user, scheduleType, startDate, endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return id.equals(schedule.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
