import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootConfiguration
public class redisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("name","yhx");

        String name1 = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name1);
    }
}
