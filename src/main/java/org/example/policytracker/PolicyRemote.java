package org.example.policytracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//Repository for performing Operations
@Repository
public interface PolicyRemote extends JpaRepository<Insurance, Integer> {
//    @Query(value = "select * from insurance where policy_type = :policy_type", nativeQuery = true)
    List<Insurance> findAllByPolicyType(String policyType);

//    @Query(value = "select * from insurance where premium_amount between ? and ?", nativeQuery = true)
    List<Insurance> findAllByPremiumAmountGreaterThanOrPremiumAmount(double start, double end);
}
