package com.miewone.DeuNotice.Domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeuPostRepository extends JpaRepository<DeuPost,Long> {
    @Query("select I.chatId from DeuPost I")
    List<Long> findbyAllId();

    @Query("select I.department from DeuPost I")
    List<String> findAllByDepartment();
}
