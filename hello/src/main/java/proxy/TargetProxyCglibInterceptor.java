package proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 子类代理 的拦截器  不需要接口TargetInterface
 * 测试见TargetProxyCglib
 * @author 50448
 * 
 */
// 关键1 MethodInterceptor
public class TargetProxyCglibInterceptor implements MethodInterceptor{
	private TargetBusiness target;

	public TargetProxyCglibInterceptor(TargetBusiness target) {
		this.target = target;

	}

/*	//关键2  返回代理对象
	public Object createProxy() {
		// 1.工具类
		Enhancer en = new Enhancer();
		// 2.设置父类
		en.setSuperclass(target.getClass());
		// 3.设置回调函数
		en.setCallback(this);
		// 4.动态创建被代理类的子类(代理对象)
		return en.create();
	}*/

	//关键3 接口MethodInterceptor实现 定义拦截器
	public Object intercept(Object object, Method method, Object[] arg2,
			MethodProxy proxy) throws Throwable {
		System.out.println("pefore do business："+object.getClass().getName());
		method.invoke(target,arg2);
		System.out.println("after do business："+proxy.getClass().getName());
		return null;
	}

}
