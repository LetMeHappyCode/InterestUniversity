import com.interest.community.aliyun.oss.util.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootConfiguration
public class redisTest {
    @Resource
    private LoginService loginService;

    @Test
    public void testRedis(){
        loginService.loginFail("yhx");
    }
//    @Autowired
//    private RedisTemplate redisTemplate;
//    @Test
//    public void testRedis(){
//        redisTemplate.opsForValue().set("name","yhx");
//
//        String name1 = (String) redisTemplate.opsForValue().get("name");
//        System.out.println(name1);
//    }
}
