package ren.oliver.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import ren.oliver.jdbc.JDBCUtils;

/**
 * 测试工具类
 * 
 */
public class TestJDBCUtils {
	/**
	 * 根据id更新用户信息方法
	 */
	@Test
	public void testUpdateById() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 1.获取连接
			conn = JDBCUtils.getConnection();
			// 2.编写sql语句
			String sql = "update tbl_user set upassword=? where uid=?";
			// 3.获取执行sql语句对象
			pstmt = conn.prepareStatement(sql);
			// 4.设置参数
			pstmt.setString(1, "999");
			pstmt.setInt(2, 1);
			// 5.执行更新操作
			int row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("更新成功!");
			} else {
				System.out.println("更新失败!");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 6.释放资源
			JDBCUtils.release(conn, pstmt, null);
		}
	}

	/**
	 * 根据id删除信息方法
	 */
	@Test
	public void testDeleteById() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 1.获取连接
			conn = JDBCUtils.getConnection();
			// 2.编写sql语句
			String sql = "delete from tbl_user where uid=?";
			// 3.获取执行sql语句对象
			pstmt = conn.prepareStatement(sql);
			// 4.设置参数
			pstmt.setInt(1, 1);
			// 5.执行删除操作
			int row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("删除成功!");
			} else {
				System.out.println("删除失败!");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 6.释放资源
			JDBCUtils.release(conn, pstmt, null);
		}
	}

	/**
	 * 添加用户信息方法
	 */
	@Test
	public void testAdd() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 1.获取连接
			conn = JDBCUtils.getConnection();
			// 2.编写sql语句
			String sql = "insert into tbl_user values(null,?,?)";
			// 3.获取执行sql语句对象
			pstmt = conn.prepareStatement(sql);
			// 4.设置参数
			pstmt.setString(1, "lisi");
			pstmt.setString(2, "hehe");
			// 5.执行插入操作
			int row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("添加成功!");
			} else {
				System.out.println("添加失败!");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 6.释放资源
			JDBCUtils.release(conn, pstmt, null);
		}
	}

	/**
	 * 根据id查询用户信息
	 */
	@Test
	public void testFindUserById() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1.获取连接
			conn = JDBCUtils.getConnection();
			// 2.编写sql语句
			String sql = "select * from tbl_user where uid=?";
			// 3.获取执行sql语句对象
			pstmt = conn.prepareStatement(sql);
			// 4.设置参数
			pstmt.setInt(1, 2);
			// 5.执行查询操作
			rs = pstmt.executeQuery();
			// 6.处理结果集
			while (rs.next()) {
				System.out.println(rs.getString(2) + "----" + rs.getString("upassword"));
			}
			// 释放资源放在此处行么？【不行滴！】
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7.释放资源
			JDBCUtils.release(conn, pstmt, rs);
		}
	}
}
