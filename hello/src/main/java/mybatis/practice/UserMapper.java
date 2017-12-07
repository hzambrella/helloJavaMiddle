package mybatis.practice;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import model.practice.Article;
import model.practice.User;

public interface UserMapper {
	public User selectUserByID(int id);
	
	public int insertUser(User userInsert);
	public List<User> selectUser();
	
	public int updateUser(User user);
	
	public int clearUser(int id);
	
	public List<Article> getUserArticles(int id);
	
	public List<Article> selectUserArticlesPage(Map<String, Integer> map);
	//注意这个注解
	public Map<String,Object> selectUserByIdAndName(@Param("id")int id,@Param("userName")String userName);
}
