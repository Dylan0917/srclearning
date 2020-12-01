package yu.study.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/11/27 16:11
 */
@Component
public class MyListener implements ApplicationListener<ApplicationEvent> {
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("收到的事件 " + event.toString());
	}
}
