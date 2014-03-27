/*
 * Created on Oct 13, 2006
 *
 */
package com.gwccnet.utilities.lookup;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Hashtable;
import java.util.Vector;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.gwccnet.DatabaseAccess.SelectStatement;

/**
 * @author laleyn0
 *
 * Read a type table from DB2 and return code/descriptions in form of Map
 * (must be the 1st and the 2nd string columns of the result set)
 */
public class TypeLookupAccess
{
	public static Map selectTypes(String sql)
		throws Throwable
	{
		return selectTypes(sql, new Vector());
	}
	
	public static Map selectTypes(String sql, List parms)
		throws Throwable
	{
		InitialContext ctx = new InitialContext(); 
		DataSource ds = (javax.sql.DataSource) ctx.lookup("java:comp/env/gwcdb"); 
        Connection con = ds.getConnection(); 
            
		SelectStatement aStmt = new SelectStatement(con, sql, parms)
		{
			Map resultMap = new Hashtable();
			public Object parseResultSet(ResultSet resultSet) throws Exception
			{
				while(resultSet != null && resultSet.next() && resultSet.getString(1) != null)
				{
					String str2 = resultSet.getString(2);
					resultMap.put(resultSet.getString(1).trim(), 
									str2 == null? "": str2.trim());
				}
				return resultMap;
			}
		};
		return (Map) aStmt.execute();
	}

}
