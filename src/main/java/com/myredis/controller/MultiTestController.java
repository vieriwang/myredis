package com.myredis.controller;

import com.myredis.service.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
@RequestMapping(value = "/multi")
public class MultiTestController {
    @Resource
    private StockService stockService;

    @GetMapping("/list")
    public void getList(HttpServletResponse response) throws Exception {
        PrintWriter writer = response.getWriter();
        response.setCharacterEncoding("utf-8");

        response.setContentType("application/json;charset=utf-8");
        writer.write("Hello World");
    }

    @GetMapping("/rob")
    public void getNum(HttpServletResponse response) throws Exception {
        stockService.cutNumber();

        PrintWriter writer = response.getWriter();
        response.setCharacterEncoding("utf-8");

        response.setContentType("application/json;charset=utf-8");
        writer.write("Set Finish");
    }

    @GetMapping("/rob2")
    public void getNum2(HttpServletResponse response) throws Exception {
        stockService.cutNumber2();

        PrintWriter writer = response.getWriter();
        response.setCharacterEncoding("utf-8");

        response.setContentType("application/json;charset=utf-8");
        writer.write("Set2 Finish");
    }
}
