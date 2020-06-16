package com.bi.dbpedia.service.impl;

import com.bi.dbpedia.dao.UserMapper;
import com.bi.dbpedia.model.MyUser;
import com.bi.dbpedia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private List<User> userList;

    @Autowired
    private UserMapper userMapper;

    @PostConstruct
    public void initData() {
        String password = "123456";
        userList = new ArrayList<>();
        userList.add(new User("macro", password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin")));
        userList.add(new User("andy", password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
        userList.add(new User("mark", password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 过滤用户名
//        List<User> findUserList = userList.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
        List<MyUser> findUserList = userMapper.selectUserByUserName(username);
        if (!CollectionUtils.isEmpty(findUserList)) {
            MyUser myUser = findUserList.get(0);
            return new User(myUser.getUsername() ,myUser.getPassword(),AuthorityUtils.commaSeparatedStringToAuthorityList(myUser.getAuthorities()));
        } else {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
    }
}
