package edu.fzu.zhishe.reservesystem.service.impl;

import static org.junit.Assert.*;

import edu.fzu.zhishe.reservesystem.generator.AdminUser;
import edu.fzu.zhishe.reservesystem.service.AdminService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminServiceImplTest {

    @Autowired
    private AdminService adminService;

    @org.junit.Test
    public void findByNameAndPassword() {
        AdminUser admin = adminService.findByNameAndPassword("admin", "123");
        System.out.println(admin);
    }
}
