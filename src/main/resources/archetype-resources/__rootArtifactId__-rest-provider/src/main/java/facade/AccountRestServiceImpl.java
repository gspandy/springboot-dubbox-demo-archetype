#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.facade;

import ${package}.dto.ListFilter;
import ${package}.dto.RestResult;
import ${package}.dto.SearchFilter;
import ${package}.dto.SignInDTO;
import ${package}.dto.SignUpDTO;
import ${package}.entity.User;
import ${package}.security.PermissionEnum;
import ${package}.security.credentials.PasswordHelper;
import ${package}.service.UserService;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.ApiOperation;
import lombok.Setter;

/**
 * User: baiqw
 * Date: 14-12-9
 * Time: 下午3:45
 */
@Service(protocol = "rest")
@Path("accounts")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({ContentType.APPLICATION_JSON_UTF_8})
@Setter
public class AccountRestServiceImpl implements AccountRestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountRestServiceImpl.class);

    @Autowired
    private UserService userService;

    @POST
    @Path("sign-in")
    @ApiOperation(value = "获取用户列表", notes = "")
    @Override
    public RestResult signIn(SignInDTO signIn, @Context HttpServletRequest request, @Context HttpServletResponse response) {
      String userName = signIn.getName();
      String password = signIn.getPassword();

      User user = userService.getUserByLoginName(userName);

      if(null == user){
          throw new UnauthorizedException("用户不存在");
      }

      PasswordHelper passwordHelper = new PasswordHelper();

      String encryptPassword = passwordHelper.encryptPassword(password);

      if (!encryptPassword.equals(user.getPassword())){
          throw new UnauthorizedException("密码错误");
      }

      UsernamePasswordToken token = new UsernamePasswordToken(userName, encryptPassword, false);

      Subject subject = SecurityUtils.getSubject();
      subject.login(token);

      if (user != null) {
          Session session = subject.getSession(true);
          session.setAttribute("userId", user.getId());
          session.setAttribute("enterpriseId", user.getEnterpriseId());

          new Thread(new Runnable() {
              @Override
              public void run() {
                  subject.isPermitted("+users+" + PermissionEnum.QUERY.getValue());
              }
          }).start();

      }

      return RestResult.OK(user);
    }

    @POST
    @Path("sign-out")
    @Override
    public RestResult signOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return RestResult.OK("成功退出");
    }

    @POST
    @Path("sign-up")
    @Override
    public RestResult signUp(SignUpDTO signUp) {
        return null;
    }
}
