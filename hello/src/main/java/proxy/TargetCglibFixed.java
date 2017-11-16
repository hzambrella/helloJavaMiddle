package proxy;

import net.sf.cglib.proxy.FixedValue;

/**
 * 子类代理之锁定器 表示锁定方法返回值，无论被代理类的方法返回什么值，回调方法都返回固定值。
 * 
 * @author 50448
 * 
 */
public class TargetCglibFixed implements FixedValue {

	public Object loadObject() throws Exception {
		System.out.println("锁定结果");
		return null;
	}

}
