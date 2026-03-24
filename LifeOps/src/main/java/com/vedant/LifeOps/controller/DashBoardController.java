package com.vedant.LifeOps.controller;


import com.vedant.LifeOps.dto.DashBoardResponse;
import com.vedant.LifeOps.service.DashBoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {

    private final DashBoardService dashBoardService;


    public DashBoardController(DashBoardService dashBoardService) {
        this.dashBoardService = dashBoardService;
    }

    @GetMapping
    public DashBoardResponse getDashBoard(){
        return dashBoardService.getDashboard();
    }
}
