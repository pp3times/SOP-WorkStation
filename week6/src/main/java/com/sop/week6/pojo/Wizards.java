package com.sop.week6.pojo;

import java.util.ArrayList;
import java.util.List;

public class Wizards {
    private List<Wizard> model;

   public Wizards() {
       this.model = new ArrayList<>();
   }

    public List<Wizard> getModel() {
        return model;
    }

    public void setModel(List<Wizard> model) {
        this.model = model;
    }
}
