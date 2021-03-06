package springMavenPluto;

import static org.junit.Assert.*;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import springMavenPluto.View.Result;
import springMavenPluto.model.MobileBookModel;
import springMavenPluto.service.mobileBookApi;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

@SuppressWarnings("rawtypes")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
public class TestMobileBook {
	@Resource(name="mobileBookApi")
	private mobileBookApi mba;
	
	@Test
	public void testResultJson(){
		Result result=new Result(0,"success1111",null);
		
		System.out.println(JSON.toJSONString(result));
	}

	@Test
	public void testGet() {
		Result result=mba.getMobileByUserId(1);
		Map map=result.getMap();
		System.out.println(map);
		Result result2=mba.getMobileById(0);
		System.out.println(result2.getMap().isEmpty());
	}
	
	@Test
	public void testAll() {
		Result result=mba.addMobileByUserId(1, "ambrella", "110110");
		assertEquals(((Map) result.getMap().get("result")).get("number"), "110110");
		assertEquals(((Map) result.getMap().get("result")).get("name"), "ambrella");
		Integer id=Integer.parseInt((String) ((Map) result.getMap().get("result")).get("id"));
		System.out.println(id);
		mba.updateMobileById(id, null, "12313");
		Result result2=mba.getMobileById(id);
		assertEquals(result2.getMap().get("number"), "12313");
		mba.removeMobileByUserId(id);
		//result.getMap();
		//mba.removeMobileByUserId(id);
	}
	
	
}
