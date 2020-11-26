package yu.study.ext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/11/26 17:48
 */
public class ClassPathXmlApplicationContextExt extends ClassPathXmlApplicationContext {
	public ClassPathXmlApplicationContextExt() {
	}

	public ClassPathXmlApplicationContextExt(ApplicationContext parent) {
		super(parent);
	}

	public ClassPathXmlApplicationContextExt(String configLocation) throws BeansException {
		super(configLocation);
	}

	public ClassPathXmlApplicationContextExt(String... configLocations) throws BeansException {
		super(configLocations);
	}

	public ClassPathXmlApplicationContextExt(String[] configLocations, ApplicationContext parent) throws BeansException {
		super(configLocations, parent);
	}

	public ClassPathXmlApplicationContextExt(String[] configLocations, boolean refresh) throws BeansException {
		super(configLocations, refresh);
	}

	public ClassPathXmlApplicationContextExt(String[] configLocations, boolean refresh, ApplicationContext parent) throws BeansException {
		super(configLocations, refresh, parent);
	}

	public ClassPathXmlApplicationContextExt(String path, Class<?> clazz) throws BeansException {
		super(path, clazz);
	}

	public ClassPathXmlApplicationContextExt(String[] paths, Class<?> clazz) throws BeansException {
		super(paths, clazz);
	}

	public ClassPathXmlApplicationContextExt(String[] paths, Class<?> clazz, ApplicationContext parent) throws BeansException {
		super(paths, clazz, parent);
	}


	@Override
	protected void initPropertySources() {
		System.out.println("---自定义initPropertySources-------------------");;
	}
}
