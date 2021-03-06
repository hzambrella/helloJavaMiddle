package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**动态代理
 * 测试见TestTargetProxyDynamic
 * @author 50448
 *
 */
//动态代理关键1InvocationHandler
public class TargetProxyDynamic implements InvocationHandler  {
	private TargetInterface target;

	public TargetProxyDynamic(TargetInterface target){
		this.target=target;		
	}
	
	public void business2(){
		System.out.println("doing business2");
	}
	
	//动态代理关键2 method.invoke
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("pefore do business");
		method.invoke(this.target, args);//调用代理业务
		System.out.println("after do business");
		return null;
	}

}
