package com.ncedu.cheetahtest.controller.userprofile;

import com.ncedu.cheetahtest.entity.user.User;
import com.ncedu.cheetahtest.entity.user.UserDto;
import com.ncedu.cheetahtest.entity.user.UserStatus;
import com.ncedu.cheetahtest.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/user")
@Slf4j
@CrossOrigin(origins = "${frontend.ulr}")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PutMapping
  public User editUser(@RequestBody UserDto user) {
    return userService.editUser(user);
  }

  @PutMapping("/activate")
  public User doActive(@RequestBody long id) {
    return userService.changeUserStatus(id, UserStatus.ACTIVE.toString());
  }

  @PutMapping("/deactivate")
  public User doInactive(@RequestBody long id) {
    return userService.changeUserStatus(id, UserStatus.INACTIVE.toString());
  }

  @GetMapping("/{id}")
  public User searchUser(@PathParam("id") String id) {
    return userService.findUserById(Long.parseLong(id));
  }
}
