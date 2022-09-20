package com.sop.week5full;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class WordPublisher {
    protected Word words;
    @Autowired
    private RabbitTemplate rabbit;

    public WordPublisher() {
        this.words = new Word();
        this.words.goodWords.add("happy");
        this.words.goodWords.add("enjoy");
        this.words.goodWords.add("like");
        this.words.badWords.add("fuck");
        this.words.badWords.add("olo");
    }

    @RequestMapping(value = "/addBad/{word}", method = RequestMethod.GET)
    public ArrayList<String> addBadWord(@PathVariable("word") String s) {
        this.words.badWords.add(s);
        return this.words.badWords;
    }

    @RequestMapping(value = "/delBad/{word}", method = RequestMethod.GET)
    public ArrayList<String> deleteBadWord(@PathVariable("word") String s) {
        this.words.badWords.remove(s);
        return this.words.badWords;
    }

    @RequestMapping(value = "/addGood/{word}", method = RequestMethod.GET)
    public ArrayList<String> addGoodWord(@PathVariable("word") String s) {
        this.words.goodWords.add(s);
        return this.words.goodWords;
    }

    @RequestMapping(value = "/delGood/{word}", method = RequestMethod.GET)
    public ArrayList<String> deleteGoodWord(@PathVariable("word") String s) {
        this.words.goodWords.remove(s);
        return this.words.goodWords;
    }

    @RequestMapping(value = "/proof/{sentence}", method = RequestMethod.GET)
    public void proofSentence(@PathVariable("sentence") String s) {
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

    @RequestMapping(value = "/getSentence", method = RequestMethod.GET)
    public Sentence getSentence() {
        return (Sentence) (rabbit.convertSendAndReceive("Direct", "get", ""));
    }
}