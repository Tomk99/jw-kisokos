package com.tomk99.jwkisokos.repository;

import com.tomk99.jwkisokos.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
