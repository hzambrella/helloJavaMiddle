package proxy;

import static org.junit.Assert.*;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import net.sf.cglib.proxy.Proxy;

import org.junit.Ignore;
import org.junit.Test;

public class TestTargetProxyCglib {
	/**
	 * 测试拦截器实现的子类代理
	 * 
	 */
	@Test
	@Ignore
	public void test1() {
		TargetBusiness target = new TargetBusiness();
		TargetProxyCglibInterceptor proxyCglib = new TargetProxyCglibInterceptor(
				target);
		
		// 动态地返回代理对象TargetBusiness，父类引用指向子类对象。
		Enhancer en = new Enhancer();
		en.setSuperclass(TargetBusiness.class);
		en.setCallback(proxyCglib);
		TargetBusiness tb=(TargetBusiness) en.create();
		//TargetBusiness tb = (TargetBussiness) proxyCglib.createProxy();
		tb.business();
		tb.business2();
	}

	/**
	 * 测试过滤器实现的子类代理
	 */
	@Test
	public void test2() {
		
		TargetBusiness target = new TargetBusiness();
		// 工具类
		Enhancer enhancer = new Enhancer();
		// 设置父类
		enhancer.setSuperclass(TargetBusiness.class);

		//设置处理装置
		
		// 拦截器
		Callback interceptor = (Callback) new TargetProxyCglibInterceptor(
				target);
		// 代表不进行代理
		Callback noopCb = (Callback) NoOp.INSTANCE;
		// 锁定器
		Callback fixed = (Callback) new TargetCglibFixed();

		Callback[] cbarray = new Callback[] { interceptor, noopCb, fixed };
		enhancer.setCallbacks(cbarray);

		// 过滤器
		TargetCglibCallbackFilter filter = new TargetCglibCallbackFilter();
		// 设置过滤器
		enhancer.setCallbackFilter(filter);
		// 动态生成代理子类
		TargetBusiness targetProxy = (TargetBusiness) enhancer.create();
		System.out.println("=====拦截器=====");
		targetProxy.business();
		System.out.println("=====NoOp.INSTANCE=====");
		targetProxy.business2();
		System.out.println("====锁定器======");
		targetProxy.business3();
		System.out.println("====过滤器中没设置时，这个程序是采用的拦截器，因为return 0======");
		targetProxy.business4();

	}

}
