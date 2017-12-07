package mybatis.practice;

import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.practice.Article;
import model.practice.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 学习mybatis
 * 
 * @author 50448
 * 
 */
public class HelloMybatis {
	// sessionFactory
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;

	static {
		try {
			/*
			 * //读取配置文件 reader = Resources.getResourceAsReader("MybatisConfig.xml");
			 * //创建sessionFactory sqlSessionFactory = new
			 * SqlSessionFactoryBuilder().build(reader);
			 */
			String resource = "MybatisConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	public static void main(String[] args) {
		// 获得session
		SqlSession session = sqlSessionFactory.openSession();
		try {

			UserMapper mapper = session.getMapper(UserMapper.class);

			selectAll(mapper);
			selectOne(session, mapper, 1);
			updateUser(mapper,1);
			selectAll(mapper);
			insertUser(session, mapper);
			selectAll(mapper);
			clearUser(session,mapper);
			joinQuery(mapper,1);
			selectUserArticlesPage(mapper,2,1,1);
			selectUserByIdAndName(mapper,1,"haozhoa");

		} finally {
			session.close();
 		}
	}
	
	
	/**关键在@Param注解
	 * 
	 * @param mapper
	 * @param id
	 * @param userName
	 */
	
	private static void selectUserByIdAndName(UserMapper mapper, int id,
			String userName) {
		System.out.println("===========多条件查询=========");
		Map map=mapper.selectUserByIdAndName(id, userName);
		System.out.println(map);
		
	}

	/**分页
	 * 
	 * @param mapper
	 * @param limit
	 * @param page
	 * @param id
	 */
	private static void selectUserArticlesPage(UserMapper mapper, int limit, int page,
			int id) {
		
		if (page<0){
			System.out.println("invalid page:"+page);
			return;
		}
		
		if (limit<0){
			System.out.println("invalid limit:"+limit);
			return;
		}
		
		System.out.println("==========分页查询 +第"+page+"页"+"(limit="+limit+")=====");
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("limit", limit);
		map.put("offset", page-1);
		map.put("id", id);

		List<Article>la=mapper.selectUserArticlesPage(map);
		for (Article article:la){
			System.out.println(article);
		}
		
		
	}

	/**连表查询
	 * 
	 * @param mapper
	 * @param id
	 */
	private static void joinQuery(UserMapper mapper ,int id){
		System.out.println("==========连表查询=====");
		List<Article> la=mapper.getUserArticles(id);
		//System.out.println(la.size());
		for (Article article:la){
			System.out.println(article);
		}
	}

	/**删除
	 * @param session 
	 * @param mapper 
	 * 
	 */
	private static void clearUser(SqlSession session, UserMapper mapper) {
		System.out.println("===clear  expect id="+1);
		mapper.clearUser(1);
		//必须commit 才能存到数据库
		session.commit();
	}

	/** 更新
	 * 
	 * @param mapper
	 * @param i
	 */
	private static void updateUser(UserMapper mapper, int id) {
		//若字段为空，会导致其它字段被空值覆盖,解决的办法是动态sql，见xml
		System.out.println("====update id="+id+"====");
		User userUpdate = new User();
		userUpdate.setId(id);
		userUpdate.setUserName("haozhoa");
		mapper.updateUser(userUpdate);

	}
	
	/**查看一个
	 * 
	 * @param session
	 * @param mapper
	 * @param id
	 */

	private static void selectOne(SqlSession session, UserMapper mapper, int id) {
		System.out.println("==========result of select one id="+id+"===============");
		// 使用这种，很神奇，不用写UserMapper的代码
		//@SuppressWarnings("unused")
		//User user2 = (User)session.selectOne("models.practice.UserMapper.selectUserByID", id);
		// 下面的要写UserMapper的代码
		User user = mapper.selectUserByID(id);
		System.out.println(user.getUserAddress());
		System.out.println(user.getUserName());

	}

	/**
	 * 添加
	 * 
	 * @param session
	 * @param mapper
	 */
	private static void insertUser(SqlSession session, UserMapper mapper) {
		System.out.println("=============result of insert===============");
		User userInsert = new User();
		userInsert.setUserAddress("wukeda");
		userInsert.setUserAge("23");
		userInsert.setUserName("huangmei");
		System.out.println(mapper.insertUser(userInsert));

		// 必须要commit 否则不会写到数据库
		session.commit();
	}

	/**
	 * 查看全部
	 * 
	 * @param mapper
	 */
	private static void selectAll(UserMapper mapper) {
		System.out.println("=============result of select all==========");
		List<User> userList1 = mapper.selectUser();
		for (User user2 : userList1) {
			System.out.println(user2.toString());
		}
	}

}
