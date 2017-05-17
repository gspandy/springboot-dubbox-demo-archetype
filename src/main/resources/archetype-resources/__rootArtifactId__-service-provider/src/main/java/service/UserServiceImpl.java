#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.dto.SearchFilter;
import ${package}.entity.User;
import ${package}.repository.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.Getter;

/**
 * 用户信息
 * Created by tom on 2017-03-07 13:25:01.
 */


@Service
@Getter
public class UserServiceImpl extends CURDServiceBase<User> implements UserService {

    @Autowired
    private UserDao dao;

}
