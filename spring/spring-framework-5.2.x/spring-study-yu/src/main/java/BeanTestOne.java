import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import yu.study.bean.BeanOne;
import yu.study.ext.ClassPathXmlApplicationContextExt;
import yu.study.listener.MainConfig;
import yu.study.listener.MyApplicationEvent;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/11/25 19:28
 */
public class BeanTestOne {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContextExt("classpath:spring.xml");
		BeanOne beanOne = context.getBean(BeanOne.class);
		System.out.println("d");

		AnnotationConfigApplicationContext contextL = new AnnotationConfigApplicationContext(MainConfig.class);
		contextL.publishEvent(new MyApplicationEvent("想涨工资"));

	}
}
