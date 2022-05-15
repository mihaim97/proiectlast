package com.ubb.ro.proiect1.service.user;

import com.ubb.ro.proiect1.dao.UserDAO;
import com.ubb.ro.proiect1.entity.User;
import com.ubb.ro.proiect1.util.SingleResultFromList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public User loadUser(String username) {
        return SingleResultFromList.getSingleResult(this.userDAO.searchByName(username));
    }
}
