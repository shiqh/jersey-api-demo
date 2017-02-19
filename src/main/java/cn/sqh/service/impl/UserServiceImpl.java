package cn.sqh.service.impl;

import cn.sqh.dao.mapper.UserMapper;
import cn.sqh.model.User;
import cn.sqh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * 用户服务实现
 * Created by Administrator on 2017/2/17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public Boolean addUser(User user) throws Exception {
        Assert.notNull(user, "存储对象不能为空");
        Assert.hasText(user.getId(), "用户ID不能为空");
        try {
            return userMapper.insert(user) > 0;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public User getUserByUserId(String userId) throws Exception {
        Assert.hasText(userId, "用户ID不能为空");
        try {
            return userMapper.selectByPrimaryKey(userId);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
