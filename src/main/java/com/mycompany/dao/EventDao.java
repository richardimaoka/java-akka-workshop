package com.mycompany.dao;

import com.mycompany.AppConfig;
import com.mycompany.entity.Event;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;

@Dao(config = AppConfig.class)
public interface EventDao {

  @Insert
  int insert(Event event);
}
