#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.entity.User;

/**
 * 用户信息
 * Created by tom on 2017-03-07 13:25:01.
 */
public interface UserService extends CURDService<User> {

}
