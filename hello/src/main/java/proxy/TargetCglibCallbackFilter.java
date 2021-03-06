package proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackFilter;

/**
 * 子类代理 方法过滤器
 * 
 * 
 * 过滤方法 返回的值为数字，代表了Callback数组中的索引位置，要到用的Callback
 */

public class TargetCglibCallbackFilter implements CallbackFilter {

	public int accept(Method method) {
		if(method.getName().equals("business1")){  
            System.out.println("filter method1 ==0 奇怪现象 过滤器不是全部打印这条信息");  
            return 0;  
        }  
        if(method.getName().equals("business2")){  
            System.out.println("filter method2 ==1 奇怪现象 过滤器不是全部打印这条信息");  
            return 1;  
        }  
        if(method.getName().equals("business3")){  
            System.out.println("filter method3 ==2 奇怪现象 过滤器不是全部打印这条信息");  
            return 2;  
        }  
		return 0;
	}
}
