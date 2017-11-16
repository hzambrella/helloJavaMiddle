package proxy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**静态代理测试类
 * 
 * @author 50448
 *
 */

public class TestTargetProxyStatic {
	//private TargetBusiness targetBusiness;
	private TargetProxyStatic targetProxyStatic;
	
	@Before
	public void setUp(){
		this.targetProxyStatic=new TargetProxyStatic(new TargetBusiness()) ;
	}

	@Test
	public void testBusiness() {
		targetProxyStatic.business();
	}

}
