package com.sop.week6.controller;

import com.sop.week6.pojo.Wizard;
import com.sop.week6.repository.WizardService;
import org.atmosphere.config.service.Get;
import org.atmosphere.config.service.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class WizardController {
   @Autowired
    private WizardService wizardService;

   @GetMapping("/wizards")
   public ResponseEntity<List<Wizard>> getModel() {
// public ResponseEntity<List<Wizard>> getModel() {
       return ResponseEntity.ok(this.wizardService.retrieveWizards());
   }

   @PostMapping("/addWizard")
   public ResponseEntity<Wizard> addWizard(@RequestBody Wizard wizard){
       wizard.set_id(null);
       return ResponseEntity.ok(this.wizardService.addWizard(wizard));
   }

   @PostMapping("/updateWizard")
   public ResponseEntity<Wizard> updateWizard(@RequestBody Wizard wizard){
       return ResponseEntity.ok(this.wizardService.updateWizard(wizard));
   }

   @PostMapping("/deleteWizard")
   public ResponseEntity<Boolean> deleteWizard(@RequestBody Wizard wizard){
       return ResponseEntity.ok(this.wizardService.deleteWizard(wizard.get_id()));
   }

}
