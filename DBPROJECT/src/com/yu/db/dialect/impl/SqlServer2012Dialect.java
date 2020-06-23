package com.yu.db.dialect.impl;

import com.yu.core.util.StrUtil;
import com.yu.db.Page;
import com.yu.db.dialect.DialectName;
import com.yu.db.sql.SqlBuilder;
import com.yu.db.sql.Wrapper;

/**
 * SQLServer2012 方言
 * 
 * @author loolly
 *
 */
public class SqlServer2012Dialect extends AnsiSqlDialect {
	private static final long serialVersionUID = -37598166015777797L;

	public SqlServer2012Dialect() {
		//双引号和中括号适用，双引号更广泛
		 wrapper = new Wrapper('"');
	}

	@Override
	protected SqlBuilder wrapPageSql(SqlBuilder find, Page page) {
		if (false == StrUtil.containsIgnoreCase(find.toString(), "order by")) {
			//offset 分页必须要跟在order by后面，没有情况下补充默认排序
			find.append(" order by current_timestamp");
		}
		return find.append(" offset ")
				.append(page.getStartPosition())//
				.append(" row fetch next ")//row和rows同义词
				.append(page.getPageSize())//
				.append(" row only");//
	}

	@Override
	public DialectName dialectName() {
		return DialectName.SQLSERVER2012;
	}
}
