package com.ncedu.cheetahtest.service.user;

import com.ncedu.cheetahtest.dao.resettoken.ResetTokenDao;
import com.ncedu.cheetahtest.dao.user.UserDao;
import com.ncedu.cheetahtest.entity.user.ResetToken;
import com.ncedu.cheetahtest.entity.user.User;
import com.ncedu.cheetahtest.entity.user.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

  private UserDao userDao;
  private ResetTokenDao resetTokenDao;

  @Autowired
  public UserServiceImpl(UserDao userDao, ResetTokenDao resetTokenDao) {
    this.userDao = userDao;
    this.resetTokenDao = resetTokenDao;
  }

  @Override
  @Transactional
  public User findUserByEmail(String email) {
    return userDao.findUserByEmail(email);
  }

  @Override
  @Transactional
  public void createPasswordResetTokenForUser(User user, String token) {
    ResetToken myToken = new ResetToken(token, user.getId(), new Date());

    ResetToken resetToken = resetTokenDao.findResetTokenByUserId(user.getId());

    if (resetToken == null) {
      resetTokenDao.createToken(myToken);

      log.info("Token has been successfully created: " + myToken);
    } else {
      resetTokenDao.saveToken(myToken);

      log.info("Token has been successfully updated: " + myToken);
    }
  }

  @Override
  @Transactional
  public ResetToken findByToken(String token) {
    return resetTokenDao.findResetTokenByToken(token);
  }

  @Override
  @Transactional
  public void makeTokenExpired(ResetToken resetToken) {
    resetTokenDao.makeTokenExpired(resetToken);
  }

  @Override
  @Transactional
  public User editUser(UserDto user) {
    return userDao.editUser(user);
  }

  @Override
  @Transactional
  public User changeUserStatus(long id, String status) {
    return userDao.changeUserStatus(id, status);
  }

  @Override
  public User findUserById(long id) {
    return userDao.findUserById(id);
  }

  @Override
  @Transactional
  public void setUserLastRequest(String email, Date date) {
    userDao.setUserLastRequest(email, date);
  }

}
