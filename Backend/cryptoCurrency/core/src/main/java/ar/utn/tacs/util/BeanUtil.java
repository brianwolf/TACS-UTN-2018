package ar.utn.tacs.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanUtil implements ApplicationContextAware{
	
	private static ApplicationContext context;
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName,Class<T> type) {
		 return (T)context.getBean(beanName);
	}
	
	public static <T> T getBean(Class<T> type) {
		String className = type.getSimpleName();
		className = className.substring(0, 1).toLowerCase()+className.substring(1);
		return (T)context.getBean(className,type);
	}


    public static ApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        context = ac;
    }

}
