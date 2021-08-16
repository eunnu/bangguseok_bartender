package com.cocktail.repository;

import com.cocktail.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Slf4j
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void 유저_조회() throws Exception {
        // given
        Long userId = 1L;

        // when
        User user = userRepository.findById(userId);

        // then
        Assertions.assertNotNull(user);
        log.info(user.toString());
    }

}