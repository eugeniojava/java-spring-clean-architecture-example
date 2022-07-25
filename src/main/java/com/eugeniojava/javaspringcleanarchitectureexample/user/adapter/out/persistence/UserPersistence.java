package com.eugeniojava.javaspringcleanarchitectureexample.user.adapter.out.persistence;

import com.eugeniojava.javaspringcleanarchitectureexample.common.annotation.PersistenceAdapter;
import com.eugeniojava.javaspringcleanarchitectureexample.user.application.port.out.ExistsUserByUsernamePort;
import com.eugeniojava.javaspringcleanarchitectureexample.user.application.port.out.SaveUserPort;
import com.eugeniojava.javaspringcleanarchitectureexample.user.domain.user.User;
import lombok.AllArgsConstructor;

@PersistenceAdapter
@AllArgsConstructor
class UserPersistence implements ExistsUserByUsernamePort, SaveUserPort {
    private final UserMapper userMapper;
    private final UserSpringDataJpaRepository userRepository;

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User save(User user) {
        var userJpaEntity = userRepository.save(userMapper.mapToJpaEntity(user));
        return userMapper.mapToDomainEntity(userJpaEntity);
    }
}
