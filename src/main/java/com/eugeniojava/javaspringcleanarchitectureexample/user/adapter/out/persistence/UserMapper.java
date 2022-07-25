package com.eugeniojava.javaspringcleanarchitectureexample.user.adapter.out.persistence;

import com.eugeniojava.javaspringcleanarchitectureexample.user.domain.user.User;
import com.eugeniojava.javaspringcleanarchitectureexample.user.domain.user.UserFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class UserMapper {
    private final UserFactory userFactory;

    UserJpaEntity mapToJpaEntity(User user) {
        return new UserJpaEntity(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getCreatedAt()
        );
    }

    User mapToDomainEntity(UserJpaEntity userJpaEntity) {
        return userFactory.create(
            userJpaEntity.getId(),
            userJpaEntity.getUsername(),
            userJpaEntity.getPassword(),
            userJpaEntity.getCreatedAt()
        );
    }
}
