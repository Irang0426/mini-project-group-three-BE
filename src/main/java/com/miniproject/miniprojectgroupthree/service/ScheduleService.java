package com.miniproject.miniprojectgroupthree.service;

import com.miniproject.miniprojectgroupthree.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
}
