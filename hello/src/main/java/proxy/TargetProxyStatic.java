package proxy;

/**静态代理
 * 测试见TestTargetProxyStatic
 * @author 50448
 *
 */
public class TargetProxyStatic implements TargetInterface{
	public TargetInterface targetBusiness;
	
	public TargetProxyStatic(TargetInterface targetBusiness){
		this.targetBusiness=targetBusiness;
	}
	
	
	public void business() {
		System.out.println("pefore do business");
		targetBusiness.business();//调用代理业务
		System.out.println("after do business");
	}
}
