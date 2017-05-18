#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.facade;

import ${package}.converter.Converter;
import ${package}.dto.UserDTO;
import ${package}.entity.User;

/**
 * 用户信息 接口
 * <p>
 * Created by tom on 2017-03-07 13:25:01.
 */
 public interface UserRestService extends CURDRestService<UserDTO>,Converter<UserDTO, User> {

 }
