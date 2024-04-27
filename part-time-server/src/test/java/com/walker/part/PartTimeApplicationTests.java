package com.walker.part;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.walker.part.entity.AdminInfo;
import com.walker.part.entity.UserInfo;
import com.walker.part.service.IAdminInfoService;
import com.walker.part.service.IUserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@SpringBootTest
class PartTimeApplicationTests {

    @Autowired
    private IAdminInfoService adminInfoService;

    @Test
    void test2(){
        AdminInfo adminInfo = adminInfoService.getBaseMapper().selectOne(new LambdaQueryWrapper<AdminInfo>()
                .eq(AdminInfo::getName, "walker")
                .eq(AdminInfo::getPassword, "123456")
        );
        System.out.println("adminInfo = " + adminInfo);
    }

    @Test
    void test1(){
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setName("walker");
        adminInfo.setAvatar("http://192.168.43.104:8417/api/files/1781897948829806594.png");
        adminInfo.setPassword("123456");
        adminInfo.setIntroduction("超级管理员");
        adminInfoService.saveForm(adminInfo);
    }

    @Test
    void contextLoads() {
    }

}
