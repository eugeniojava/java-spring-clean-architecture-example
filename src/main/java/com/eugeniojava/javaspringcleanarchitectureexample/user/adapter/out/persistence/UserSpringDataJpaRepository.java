package com.eugeniojava.javaspringcleanarchitectureexample.user.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserSpringDataJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    boolean existsByUsername(String username);
}
