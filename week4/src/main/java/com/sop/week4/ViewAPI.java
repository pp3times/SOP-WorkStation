package com.sop.week4;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ViewAPI {
    @RequestMapping(value = "/{n1}+{n2}", method = RequestMethod.GET)
    public double calPlus(@PathVariable("n1") double n1, @PathVariable("n2") double n2) {
        return n1+n2;
    }
    @RequestMapping(value = "/names", method = RequestMethod.GET)
    public View.MyData getName() {
        View.MyData d = new View.MyData();
        d.getName().add("Alex");
        d.getName().add("Peter");
        d.getName().add("John");
        return d;
    }
    @RequestMapping(value = "test1", method = RequestMethod.POST)
    public String testPost1(@RequestBody MultiValueMap<String, String> n) {
        Map<String, String> d = n.toSingleValueMap();
        double out = Double.parseDouble(d.get("n1")) - Double.parseDouble(d.get("n2"));
        return out+"";
    }
}
