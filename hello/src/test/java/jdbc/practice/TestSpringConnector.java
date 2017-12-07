/*package jdbc.practice;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

*//**spring+jdbc 
 * 各种问题
 * 
 * @author 50448
 *
 *//*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("../../spring.xml")
public class TestSpringConnector {
	*//**
     * 使用ioc的方式
     *//*
    @Resource(name = "JdbcTemplate")
    private org.springframework.jdbc.core.JdbcTemplate JdbcTemplate;

	@Test
	public void testSpringJDBC() {
		 JdbcTemplate.update("insert into employees values(?,?,?)",1,2,3);

	}

}
*/