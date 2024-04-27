package com.walker.part.controller;

import com.walker.part.service.IExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Walker
 * @since 2024-04-21 09:52:39
 */
@RestController
@RequestMapping("/experience")
@RequiredArgsConstructor
public class ExperienceController {

    private final IExperienceService experienceService;



}
