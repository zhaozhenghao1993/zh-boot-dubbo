package net.zhenghao.zh.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring Context 工具类
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2018年1月5日 上午9:06:44
 * SpringContextUtils.java
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
	
	public static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtils.applicationContext = applicationContext;
	}
	
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}
	
	/**
	 * 获取类型为requireType的对象
	 * 如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException）
	 * @param name bean注册名
	 * @param requiredType 返回对象类型
	 * @return Object 返回requiredType类型对象
	 */
	public static <T> T getBean(String name, Class<T> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}
	
	/**
	 * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
	 * @param name
	 * @return
	 */
	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	/**
	 * 判断以给定名字注册的bean定义是一个singleton(单例)还是一个prototype。
	 * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
	 * @param name
	 * @return
	 */
	public static boolean isSingleton(String name) {
		return applicationContext.isSingleton(name);
	}

	
	/**
	 * 获取注册对象的类型
	 * @param name
	 * @return 注册对象的类型
	 */
	public static Class<? extends Object> getType(String name) {
		return applicationContext.getType(name);
	}
}
/**
 * 一、这个接口有什么用？
 * 当一个类实现了这个接口（ApplicationContextAware）之后，这个类就可以方便获得ApplicationContext中的所有bean。
 * 换句话说，就是这个类可以直接获取spring配置文件中，所有有引用到的bean对象。
 * 
 * 二、怎么用？
 * 例如我有一个方法类AppUtil，这个方法类中需要使用到的ApplicationContext中的某个bean（companyService）。
 * 1、因为spring要建立属于自己的容器，就必须要加载自己的配置文件。这个时候，需要注册ContextLoaderListener或者这个类的子类。
 * 在web.xml加上以下的信息：
 * <listener>
 * 		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
 * </listener>
 * 当然，这样子的话只会读取默认路径下的application.xml配置文件的。如果需要读取特定路径下的配置文件。需要在web.xml中
 * 添加如下信息。可以参考我的示例，指定配置文件，如下：
 * <context-param>
 * 		<param-name>contextConfigLocation</param-name>
 * 		<param-value>classpath:conf/app-context.xml</param-value>
 * </context-param>
 * 注意：<param-name>contextConfigLocation</param-name>是不能改变的。
 * 
 * 2、方法类AppUtil的处理
 * 方法类AppUtil实现ApplicationContextAware接口：
 * public class AppUtil implements ApplicationContextAware
 * 为方法类AppUtil增加一个静态的成员ApplicationContext类型的对象。以后方法类AppUtil获取ApplicationContext，就是通过读取这个
 * 成员变量的。具体如下所示：
 * private static ApplicationContext appContext;
 * 实现ApplicationContextAware接口的默认方法：
 *  public void setApplicationContext(ApplicationContext paramApplicationContext) throws BeansException{
 *  	appContext = paramApplicationContext;
 *  }
 *  
 *  3、在spring的配置文件中，注册方法类AppUtil
 *  严格上来说，方法类AppUtil是一个bean,而且从步骤2中我们不难发现，之所以方法类AppUtil能够灵活自如地获取ApplicationContext
 *  就是因为spring能够为我们自动地执行了setApplicationContext。但是，spring不会无缘无故地为某个类执行它的方法的，所以，就很有必要
 *  通过注册方法类AppUtil的方式告知spring有这样子一个类的存在。
 *  其实，方法很简单，就是将方法类AppUtil作为一个普通的bean在spring的配置文件中进行注册：
 *  <bean id="appUtil" class="com.htsoft.core.util.AppUtil"/>
 *  
 *  4、使用静态的成员ApplicationContext类型的对象，appContext，来调用其他bean。在方法类AppUtil中增加如下方法：
 *  public static Object getBean(String paramString){ 
 *  	return appContext.getBean(paramString);
 *  }
 *  那么，在方法类AppUtil中就能够灵活地调用其他任何一个bean了，例如：
 *  CompanyService localCompanyService = (CompanyService)getBean("companyService");
 *  注：配置文件中关于companyService的内容：
 *  <bean id="companyService" class="com.kaiwii.service.system.impl.CompanyServiceImpl">
 *  	<constructor-arg index="0" ref="companyDao"/>
 *  </bean>
 */

