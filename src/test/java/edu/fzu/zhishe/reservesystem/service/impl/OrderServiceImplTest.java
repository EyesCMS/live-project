package edu.fzu.zhishe.reservesystem.service.impl;

import static org.junit.Assert.*;

import edu.fzu.zhishe.reservesystem.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author liang on 3/17/2020.
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void exportJackFile() {
        orderService.exportJackFile();
    }
}
