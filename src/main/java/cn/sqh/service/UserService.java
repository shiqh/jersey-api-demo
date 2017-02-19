package cn.sqh.service;


import cn.sqh.model.User;

/**
 * 用户服务
 * Created by Administrator on 2017/2/17.
 */
public interface UserService {

    Boolean addUser(User user) throws Exception;

    User getUserByUserId(String userId)throws Exception;

}
