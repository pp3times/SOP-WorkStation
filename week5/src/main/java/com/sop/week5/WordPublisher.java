package com.sop.week5;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class WordPublisher
{
    protected Word words;
    @Autowired
    private RabbitTemplate rabbit;

    public WordPublisher() {
        this.words = new Word();
        this.words.goodWords.add("happy");
        this.words.goodWords.add("enjoy");
        this.words.goodWords.add("life");
        this.words.badWords.add("fuck");
        this.words.badWords.add("olo");
    }

    @RequestMapping(value = "/addBad/{s}", method = RequestMethod.GET)
    public ArrayList<String> addBadWord(@PathVariable("s") String s) {
        this.words.badWords.add(s);
        return this.words.badWords;
    }

    @RequestMapping(value = "/delBad/{s}", method = RequestMethod.GET)
    public ArrayList<String> deleteBadWord(@PathVariable("s") String s) {
        this.words.badWords.remove(s);
        return this.words.badWords;
    }

    @RequestMapping(value = "/addGood/{s}", method = RequestMethod.GET)
    public ArrayList<String> addGoodWord(@PathVariable("s") String s) {
        this.words.goodWords.add(s);
        return this.words.goodWords;
    }

    @RequestMapping(value = "/delGood/{s}", method = RequestMethod.GET)
    public ArrayList<String> deleteGoodWord(@PathVariable("s") String s) {
        this.words.goodWords.remove(s);
        return this.words.goodWords;
    }

    @RequestMapping(value = "/proof/{s}", method = RequestMethod.GET)
    public void proofSentence(@PathVariable("s") String s){
        boolean sg = false, sb = false;

        for(String x: this.words.goodWords) {
            sg = Arrays.asList(s.split(" ")).contains(x) || sg;
//            System.out.println(sg);
        }
        for(String y: this.words.badWords) {
            sb = Arrays.asList(s.split(" ")).contains(y) || sb;
//            System.out.println(sb);
        }

        if(sg && sb) {
            rabbit.convertAndSend("Fanout", "", s);
//            System.out.println("Fanout");
        } else if (sg) {
            rabbit.convertAndSend("Direct", "good", s);
//            System.out.println("Good");
        } else if (sb) {
            rabbit.convertAndSend("Direct", "bad", s);
//            System.out.println("Bad");
        }
    }
}
