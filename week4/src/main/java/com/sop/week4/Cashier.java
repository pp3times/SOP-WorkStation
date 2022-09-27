package com.sop.week4;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cashier {
    @RequestMapping(value = "/getChange/{n}", method = RequestMethod.GET)
    public Change getChange(@PathVariable("n") int n) {
        Change change = new Change();
        change.setB1000(n/1000);
        n = n % 1000;
        change.setqB500(n/500);
        n = n % 500;
        change.setB100(n/100);
        n = n % 100;
        change.setB20(n/20);
        n = n % 20;
        change.setB10(n/10);
        n = n % 10;
        change.setB5(n/5);
        n = n % 5;
        change.setB1(n);
        return change;
    }
}
