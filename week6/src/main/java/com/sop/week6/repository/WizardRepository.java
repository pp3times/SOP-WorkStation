package com.sop.week6.repository;

import com.sop.week6.pojo.Wizard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WizardRepository extends MongoRepository<Wizard, String> {

}
