package com.mycompany.dao;

import com.mycompany.*;
import com.mycompany.entity.*;
import org.seasar.doma.*;

@Dao(config = AppConfig.class)
public interface TicketDao {

  @Select
  Ticket selectById(int id);

  @Update
  int update(Ticket ticket);
}
