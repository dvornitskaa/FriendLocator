package oksana.dvornitska.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import oksana.dvornitska.services.UserService;
import oksana.dvornitska.services.interfaces.UserServiceI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceTest {
    @Autowired
    UserServiceI userService;
    @Test
    void test1(){
        Assertions.assertTrue(true);
    }
    @Test
    void test2(){
        Assertions.assertTrue(1==2);
    }
}
