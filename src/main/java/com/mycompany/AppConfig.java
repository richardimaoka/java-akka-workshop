package com.mycompany;

import org.seasar.doma.SingletonConfig;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;

import javax.sql.DataSource;

@SingletonConfig
public class AppConfig implements Config {
  private static final AppConfig CONFIG = new AppConfig();

  private final Dialect dialect;

  private final LocalTransactionDataSource dataSource;

  private final TransactionManager transactionManager;

  private AppConfig() {
    dialect = new MysqlDialect();
    dataSource = new LocalTransactionDataSource(
      "jdbc:mysql://127.0.0.1:3306", "root", null);
    transactionManager = new LocalTransactionManager(
      dataSource.getLocalTransaction(getJdbcLogger()));
  }

  @Override
  public Dialect getDialect() {
    return dialect;
  }

  @Override
  public DataSource getDataSource() {
    return dataSource;
  }

  @Override
  public TransactionManager getTransactionManager() {
    return transactionManager;
  }

  public static AppConfig singleton() {
    return CONFIG;
  }
}