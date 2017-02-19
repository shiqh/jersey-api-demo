package cn.sqh.dao.mapper;

import cn.sqh.model.User;

/**
 * 用户管理
 * Created by Administrator on 2017/2/17.
 */
public interface UserMapper {

    int deleteByPrimaryKey(String userId) throws Exception;

    int insert(User user) throws Exception;

    User selectByPrimaryKey(String userId) throws Exception;

    int updateByPrimaryKeySelective(User user) throws Exception;

}