package edu.fzu.zhishe.reservesystem.service.impl;

import edu.fzu.zhishe.reservesystem.generator.AdminUser;
import edu.fzu.zhishe.reservesystem.generator.AdminUserDao;
import edu.fzu.zhishe.reservesystem.generator.AdminUserExample;
import edu.fzu.zhishe.reservesystem.service.AdminService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author xjliang
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminUserDao adminUserDao;

    @Override
    public AdminUser findByNameAndPassword(String name, String password) {
        AdminUserExample adminUserExample = new AdminUserExample();
        adminUserExample.createCriteria()
            .andUserNameEqualTo(name)
            .andUserPswEqualTo(password);
        return adminUserDao.selectByExample(adminUserExample).get(0);
    }
}
