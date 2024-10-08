package com.reversosocial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reversosocial.models.entity.Event;
import com.reversosocial.models.entity.Subscription;
import com.reversosocial.models.entity.User;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
  Optional<Subscription> findByUserAndEvent(User user, Event event);

  Integer countByEvent(Event event);
}
