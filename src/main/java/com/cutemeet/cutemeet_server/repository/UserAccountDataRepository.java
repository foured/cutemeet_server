package com.cutemeet.cutemeet_server.repository;

import com.cutemeet.cutemeet_server.models.MyUserAccountData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountDataRepository extends JpaRepository<MyUserAccountData, Long> {
}
