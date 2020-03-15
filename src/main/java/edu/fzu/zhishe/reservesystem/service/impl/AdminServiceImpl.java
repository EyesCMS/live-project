package edu.fzu.zhishe.reservesystem.service.impl;

import static javax.swing.UIManager.get;

import edu.fzu.zhishe.reservesystem.generator.AdminUser;
import edu.fzu.zhishe.reservesystem.generator.AdminUserDao;
import edu.fzu.zhishe.reservesystem.generator.AdminUserExample;
import edu.fzu.zhishe.reservesystem.service.AdminService;
import java.util.List;
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
        List<AdminUser> adminUsers = adminUserDao.selectByExample(adminUserExample);
        return adminUsers.isEmpty() ? null : adminUsers.get(0);
    }
}
