package superCRM.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import superCRM.pojos.CustomerEO;

public class CustomerDaoMysqlImpl implements ICustomerDao{

	public CustomerEO findById(int id) {
		int row = -1;
		// 读记录的sql语句
		String sql = "select * from customer where customer_id=" + id
				+ " and active_status='Y'";
		ResultSet rs = DbManager.getResultSet(sql);// 执行sql语句并返回ResultSet
		try {
			rs.last();// 移动到最后一行
			row = rs.getRow();// 得到总记录数
			if (row == 1) {// 如果只查询到一条记录，则代表该记录存在并更新该类的属性
				CustomerEO customer = new CustomerEO();
				customer.setId( rs.getInt("customer_id"));
				customer.setDisplayName( rs.getString("display_name"));
				customer.setWebSite(rs.getString("web_site"));
				customer.setCategory(rs.getString("customer_category"));
				customer.setNumberEmployee(rs.getString("num_employee"));
				return customer;
			} else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (rs.getStatement() != null)
					rs.getStatement().close();
				DbManager.releaseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List findBykeywords(String keywords) {
		//查询记录的sql语句
		String sql = "select customer_id from customer where active_status='Y'"
			+"and (display_name like '%"+keywords+"%' or web_site like '%"+keywords+"%' or customer_category like '%"+keywords+"%,')";;
		return getCustomerResult(sql);
	}

	public List getAllCustomers() {
		//查询所有客户记录的sql语句
		String sql = "select customer_id from customer where active_status='Y'";
		return getCustomerResult(sql);
	}

	public List getCustomerResult(String sql) {
		//执行sql语句并返回ResultSet
		ResultSet rs = DbManager.getResultSet(sql);
		List list =  new ArrayList();
		try {
			while(rs.next()){
				int id = rs.getInt("customer_id");
				CustomerEO c = findById( id );
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbManager.releaseConnection();
		return list;
	}

	public void updateCustomer(CustomerEO c) {
		//更新客户的sql语句
		String sql = "update customer set display_name='"+c.getDisplayName()+"',web_site='"
			+c.getWebSite()+"', customer_category='"+c.getCategory()+"',num_employee='"+c.getNumberEmployee()
			+"' where customer_id="+c.getId();
		try {
			DbManager.excute(sql);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		DbManager.releaseConnection();
	}
	 
	public CustomerEO addCustomer(CustomerEO  c) {
		//添加客户的sql语句
		String sql = "select customer_id from customer ";
		int row = getCustomerResult(sql).size()+1;
		sql ="insert into customer values ("+row+",'"+c.getDisplayName()+"'" +
				",'" +c.getWebSite()+"','"+c.getCategory()+"','"+c.getNumberEmployee()+"','"+c.getActiveStatus()+"')";
		try {
			DbManager.excute(sql);
			DbManager.releaseConnection();
			return findById( row ) ;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}

	}

	public List getContacts(CustomerEO customer) {
		//查询该客户的联系人sql语句
		String sql = "select contact_id from contact where active_status='Y'and customer_id="+customer.getId();
		DaoFactory daoFactory = new DaoFactory();
		return daoFactory.getContactDao().getContactResult(sql);
	}

}
