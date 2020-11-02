声明式事务：

事务的五大属性

传播机制
  一个带有事务的方法A运行在另一个事务方法B时，内层方法使用自己的事务还是外层的事务
  常用的值：
  REQUIRED：如果外层方法有事务，使用外层方法的事务，如果外层方法没有事务，使用自己的事务
  REQUIRES_NEW ：不管外层方法有没有事务，都使用自己的事务
  SUPPORTS ： 如果外层方法有事务，就用外层方法的事务，如果外层方法没有事务，内层方法就不使用事务了
REQUIRED和REQUIRES_NEW的方法组合 如果REQUIRES_NEW的方法抛出异常则REQUIRED的事务会回滚，因为REQUIRES_NEW方法将事务回滚后会将异常向外层抛出
但是如果REQUIRED在REQUIRES_NEW之后抛出异常，REQUIRES_NEW事务不会回滚


隔离级别
select @@tx_isolation
READ_UNCOMMITTED 读未提交
READ_COMMITED 读已提交

回滚属性

事务不是遇到所有的异常都会回滚，只是运动RunTimeException才会回滚
编译时异常 rollbackfor。filenotfound

超时属性
	多次操作数据库之间的时间不能超过超时时间
只读属性
	加快查询效率

基于xml的事务