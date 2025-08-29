package org.example.policytracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Repository for performing Operations
@Repository
public interface PolicyRemote extends JpaRepository<Insurance, Integer> {

}
