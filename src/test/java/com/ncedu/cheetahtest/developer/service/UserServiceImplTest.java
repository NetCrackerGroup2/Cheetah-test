package com.ncedu.cheetahtest.developer.service;

import com.ncedu.cheetahtest.dao.user.UserDao;
import com.ncedu.cheetahtest.entity.user.User;
import com.ncedu.cheetahtest.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {
  @Autowired
  private UserService userService;
  @MockBean
  private UserDao userDao;

  @Test
  void shouldFindDeveloperByEmail() {
//    User user = new User(1, "netCracker2@gmail.com", "1234", "NetCracker", "ADMIN", "ACTIVE", 43);
//    doReturn(user).when(userDao).findUserByEmail("netCracker2@gmail.com");
//    userService.findUserByEmail("netCracker2@gmail.com");
  }

  @Test
  void shouldCreatePasswordResetTokenForUser() {

  }
}