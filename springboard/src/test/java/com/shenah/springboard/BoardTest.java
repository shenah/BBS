package com.shenah.springboard;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardTest {

	@Inject
	private DataSource ds;
	
	@Test
	public void testConection()throws Exception{
		Connection con = null;
		try {
			con = ds.getConnection();
			System.out.println(con);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}	
	}
	
	@Autowired
	private SqlSession sqlSession; 
	
	@Test
	public void sqlSessionTest() {
		System.out.println(sqlSession);
	}
	
	
}
