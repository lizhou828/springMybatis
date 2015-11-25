import com.liz.domain.User;
import com.liz.service.IUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Frank
 * Date: 15-4-30
 * Time: 上午12:05
 * To change this template use File | Settings | File Templates.
 */
public class TestSpringMybatis {
    @Test
    public void test(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        IUserService userService = (IUserService)ac.getBean("userService");
        List<User> userList = userService.list();
        System.out.println(userList.size());
        for(User user:userList){
            System.out.println(user);
        }

    }

    @Test
    public void testDelete(){
             ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
             IUserService us = (IUserService) ac.getBean("userService");
             us.deleteById(1);
         }
}
