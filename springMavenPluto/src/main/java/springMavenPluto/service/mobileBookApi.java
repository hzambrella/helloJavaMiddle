package springMavenPluto.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import springMavenPluto.View.Result;
import springMavenPluto.model.MobileBookModel;

public interface mobileBookApi {
	public Result getMobileById(Integer id);
	public Result getMobileByUserId(Integer userId);
	public Result addMobileByUserId(Integer userId,String name,String number);
	public Result updateMobileById(Integer id,String name,String number);
	public Result removeMobileByUserId(Integer id);
}
