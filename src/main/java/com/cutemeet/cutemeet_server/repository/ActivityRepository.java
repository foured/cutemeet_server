package com.cutemeet.cutemeet_server.repository;

import com.cutemeet.cutemeet_server.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
