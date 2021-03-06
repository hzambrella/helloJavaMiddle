package springMavenPluto.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import springMavenPluto.model.MobileBookModel;

public interface mobileBookMapper {
	public MobileBookModel selectMobileById(Integer Id);
	public List<MobileBookModel> selectMobileByUserId(Integer userId);
	public int insertMobileByUserId(MobileBookModel mb);
	public int updateMobileById(MobileBookModel mb);
	public int removeMobileById(Integer id);
}
