package com.staxrt.tutorial.repository;

import com.staxrt.tutorial.model.NotificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationTokenRepository extends JpaRepository<NotificationToken, Integer> {

}
