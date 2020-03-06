package com.mycompany;

import io.github.cdimascio.dotenv.Dotenv;
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
    Dotenv dotenv = Dotenv.load();
    String url = dotenv.get("DATABASE.URL");
    String user =  dotenv.get("DATABASE.USER");
    String password = dotenv.get("DATABASE.PASSWORD");

    if (url == null || user == null || password == null)
      throw new RuntimeException("Missing Environment variables: one or more of 'DATABASE.URL', 'DATABASE.USER', 'DATABASE.PASSWORD' is not found.");
    else {
      dialect = new MysqlDialect();
      dataSource = new LocalTransactionDataSource(url, user, password);
      transactionManager = new LocalTransactionManager(dataSource.getLocalTransaction(getJdbcLogger()));
    }
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