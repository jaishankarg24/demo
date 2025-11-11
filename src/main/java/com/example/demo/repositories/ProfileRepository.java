package com.example.demo.repositories;

import com.example.demo.dtos.UserSummary;
import com.example.demo.entities.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

    @EntityGraph(attributePaths = "user")
    List<Profile> findByLoyaltyPointsGreaterThan(int loyaltyPoints);

    //@EntityGraph(attributePaths = "user")
    //List<Profile> findByLoyaltyPointsGreaterThanOrderByUserEmail(int loyaltyPoints);

    /*
    @Query("select p from Profile p where p.loyaltyPoints > :loyaltyPoints")
    @EntityGraph(attributePaths = "user")
    List<Profile> findLoyaltyProfiles(@Param("loyaltyPoints") int loyaltyPoints);
    */

    @Query("select p.id as id, p.user.email as email from Profile p where p.loyaltyPoints > :loyaltyPoints order by p.user.email")
    @EntityGraph(attributePaths = "user")
    List<UserSummary> findLoyaltyProfiles(@Param("loyaltyPoints") int loyaltyPoints);

}
