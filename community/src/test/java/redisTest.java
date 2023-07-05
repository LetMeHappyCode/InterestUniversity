import com.interest.community.CommunityApplication;
import com.interest.community.service.impl.MainService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = {CommunityApplication.class})
@RunWith(SpringRunner.class)
@SpringBootConfiguration
public class redisTest {

    @Resource
    private MainService mainService;

    @Value("${ceshi.name}")
    String name;

    @Value("${server.port}")
    String port;
    @Test
    public void test(){
        mainService.test1();
    }
    
}
