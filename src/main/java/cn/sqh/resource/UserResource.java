package cn.sqh.resource;

import cn.sqh.constants.DemoApiConstants;
import cn.sqh.model.User;
import cn.sqh.service.UserService;
import cn.sqh.utils.UUIDUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

/**
 * 用户管理接口
 * Created by Administrator on 2017/2/17.
 */
@Path(DemoApiConstants.PathRoute.ROUTE_USER)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Autowired
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postUser(User user) {
        JSONObject info = new JSONObject();
        try {
            user.setId(UUIDUtil.getUUID());
            if (userService.addUser(user)) {
                info.put("userId", user.getId());
                return Response.ok().entity(info.toJSONString()).build();
            }
            info.put("info", "添加失败");
            return Response.status(Response.Status.BAD_REQUEST).entity(info.toJSONString()).build();
        } catch (IllegalArgumentException ie) {
            JSONObject errorInfo = new JSONObject();
            errorInfo.put("errorInfo", ie.getMessage());
            info.put("message", errorInfo);
            return Response.status(Response.Status.BAD_REQUEST).entity(info.toJSONString()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("{userId}")
    public Response getUser(@PathParam("userId") @NotBlank String userId) {
        try {
            User user = userService.getUserByUserId(userId);
            JSONObject info = new JSONObject();
            JSONObject userInfo = new JSONObject();
            if (user != null) {
                userInfo.put("userId", user.getId());
                userInfo.put("name", user.getName());
                userInfo.put("age", user.getAge());
            } else {
                userInfo.put("userId", "");
                userInfo.put("name", "");
                userInfo.put("age", "");
            }
            info.put("user", userInfo);
            return Response.ok().entity(info.toJSONString()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("users")
    public Response getUser(@QueryParam("id") String id,
                            @QueryParam("name") String name,
                            @QueryParam("age") String ageStr) {
        try {
            User query = new User();
            query.setId(id);
            query.setName(name);
            if (StringUtils.isNotBlank(ageStr)) {
                query.setAge(Integer.parseInt(ageStr));
            }

            List<User> users = userService.getUsers(query);
            JSONObject info = new JSONObject();
            JSONArray userInfos = new JSONArray();
            if (!CollectionUtils.isEmpty(users)) {
                for (User user : users) {
                    JSONObject userInfo = new JSONObject();
                    userInfo.put("userId", user.getId());
                    userInfo.put("name", user.getName());
                    userInfo.put("age", user.getAge());
                    userInfos.add(userInfo);
                }
                info.put("userInfos", userInfos);
            } else {
                info.put("userInfos", new JSONArray());
            }
            return Response.ok().entity(info.toJSONString()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

}
