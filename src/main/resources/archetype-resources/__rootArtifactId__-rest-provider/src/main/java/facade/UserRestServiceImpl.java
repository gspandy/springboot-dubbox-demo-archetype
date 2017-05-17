#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.facade;

import ${package}.converter.UserDataConverter;
import ${package}.dto.UserDTO;
import ${package}.entity.User;
import ${package}.service.UserService;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import lombok.Getter;

/**
 * 用户信息
 * Created by tom on 2017-03-07 13:25:01.
 */
@Service(protocol = "rest")
@Path("users")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({ContentType.APPLICATION_JSON_UTF_8})
@Api(value = "users", description = "用户信息接口")
@Getter
public class UserRestServiceImpl extends ConverterRestServiceBase<UserDTO, User> implements UserRestService {

    @Autowired
    private UserService service;

    @Autowired
    private UserDataConverter dataConverter;

}
