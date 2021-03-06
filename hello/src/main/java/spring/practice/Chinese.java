package spring.practice;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 中国人
 * 
 * @author 50448
 * 
 */
@Component("Chinese")
public class Chinese implements Person {
	// 砍柴速度
	@Value("${chinese.speed:10}")
	private int speed;
	@Resource(name="stoneAxe")
	private Axe axe;

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setAxe(Axe axe) {
		this.axe = axe;
	}

	public int useAxe() {
		//System.out.println(axe.chop());
		return axe.chop() + this.speed;
	}

}
