package com.jeremy.week3;

import org.springframework.web.bind.annotation.*;

@RestController
public class GeneratePasswordService {
    @RequestMapping(value = "/{username}.generate", method = RequestMethod.GET)
    public String generate(@PathVariable("username") String username) {
        double randomPassword = Math.random()*10000000 ;
        return "Hi," + username + "\n" + "Your new password is " + (int)randomPassword + ".";
    }
}
