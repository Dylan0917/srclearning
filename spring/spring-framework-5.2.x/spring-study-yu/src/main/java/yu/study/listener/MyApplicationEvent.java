package yu.study.listener;

import org.springframework.context.ApplicationEvent;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/11/27 17:33
 */
public class MyApplicationEvent extends ApplicationEvent {
	private static final long serialVersionUID = -133973517499919843L;

	/**
	 * Create a new {@code ApplicationEvent}.
	 *
	 * @param source the object on which the event initially occurred or with
	 *               which the event is associated (never {@code null})
	 */
	public MyApplicationEvent(Object source) {
		super(source);
	}
}
