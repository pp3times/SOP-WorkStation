package com.jeremy.week3;

import org.springframework.web.bind.annotation.*;

@RestController
public class MathService {
    @RequestMapping(value = "/add/{x}/{y}", method = RequestMethod.GET)
    public double add(@PathVariable("x") double x, @PathVariable("y") double y) {
        return x+y;
    }

    @RequestMapping(value = "/minus/{x}/{y}", method = RequestMethod.GET)
    public double minus(@PathVariable("x") double x, @PathVariable("y") double y){
        return x-y;
    }

    @RequestMapping(value = "/multiply", method = RequestMethod.GET)
    public double multiply(@RequestParam("num1") double x, @RequestParam("num2") double y) {
        return x*y;
    }

    @RequestMapping(value = "/divide", method = RequestMethod.GET)
    public double divide(@RequestParam("num1") double x, @RequestParam("num2") double y){
        return x/y;
    }
}
