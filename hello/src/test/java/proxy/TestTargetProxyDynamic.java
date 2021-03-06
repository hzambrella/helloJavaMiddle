package proxy;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.junit.Before;
import org.junit.Test;
/**动态代理测试类
 * 
 * @author 50448
 *
 */
public class TestTargetProxyDynamic {
	private TargetInterface target;

	@Before
	public void setUp() {
		this.target = new TargetBusiness();
	}

	@Test
	public void test() {
		//动态代理关键3 处理器 InvocationHandler
		 InvocationHandler myInterceptor = new TargetProxyDynamic(this.target);
		 //动态代理关键4 实例化Proxy
		TargetInterface proxyObj = (TargetInterface) Proxy.newProxyInstance(
				target.getClass().getClassLoader(), target.getClass()
						.getInterfaces(), myInterceptor);
		proxyObj.business();
		//proxyObj.business2();
	}

}
