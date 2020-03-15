package edu.fzu.zhishe.reservesystem.service;

import edu.fzu.zhishe.reservesystem.generator.AdminUser;

/**
 * @author xjliang
 */
public interface AdminService {

    AdminUser findByNameAndPassword(String name, String password);
}
