import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import yu.study.bean.BeanOne;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/11/25 19:28
 */
public class BeanTestOne {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
		BeanOne beanOne = context.getBean(BeanOne.class);
		System.out.println("d");

	}
}
