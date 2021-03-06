package springMavenPluto.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.beanutils.BeanUtils;

import springMavenPluto.Dao.mobileBookMapper;
import springMavenPluto.View.Result;
import springMavenPluto.model.MobileBookModel;

@Service("mobileBookApi")
public class mobileBookApiImpl implements mobileBookApi {

	@Autowired 
	private mobileBookMapper mbm;
	
	@SuppressWarnings("unchecked")
	public Result getMobileById(Integer id) {
		
		Result result=new Result(0, "sucessfull", null);
		MobileBookModel mb= mbm.selectMobileById(id);
		
		@SuppressWarnings("rawtypes")
		Map m=new HashMap();	
		try {
			m = BeanUtils.describe(mb);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		result.setMap(m);
		return result;
	}
	
	public  Result getMobileByUserId(Integer userId) {
		Result result=new Result(0, "sucessfull", null);
		List<MobileBookModel>list= mbm.selectMobileByUserId(userId);
		
		List<Map<String, String>> resultList=new ArrayList<Map<String, String>>();
		for (MobileBookModel l:list){
			Map<String, String> m=new HashMap<String,String>();	
			try {
				m = BeanUtils.describe(l);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resultList.add(m);
		}
		
		HashMap<String, Object> map=new HashMap<String,Object>();
		map.put("resultList", resultList);
		result.setMap(map);
		return result;
	}
	
	public Result addMobileByUserId(Integer userId, String name, String number) {
		MobileBookModel mb=new MobileBookModel();
		mb.setName(name);
		mb.setNumber(number);
		mb.setUserId(userId);
		Result result=new Result(0, "sucessfull", null);
		int key=mbm.insertMobileByUserId(mb);
		if (key==0){
			result.setCode(500);
			result.setMessage("fail");
		}
		HashMap<String, Object> map=new HashMap<String,Object>();
		try {
			map.put("result",BeanUtils.describe(mb));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.setMap(map);
		return result;
	}

	public Result updateMobileById(Integer id, String name, String number) {
		MobileBookModel mb=new MobileBookModel();
		mb.setId(id);
		mb.setName(name);
		mb.setNumber(number);
		
		Result result=new Result(0, "sucessfull", null);
		if (mbm.updateMobileById(mb)==0){
			result.setCode(500);
			result.setMessage("fail");
		}
		
		HashMap<String, Object> map=new HashMap<String,Object>();
		map.put("result",mb);
		result.setMap(map);
		return result;
	}
	
	public Result removeMobileByUserId(Integer id) {
		Result result=new Result(0, "sucessfull", null);
		if (mbm.removeMobileById(id)==0){
			result.setCode(500);
			result.setMessage("fail");
		}
		return result;
	}


}
