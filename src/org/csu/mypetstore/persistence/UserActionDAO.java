package org.csu.mypetstore.persistence;

import java.sql.Date;
import java.sql.Time;

public interface UserActionDAO {

    public void recordAction(String username,String action, String object,String date);

    public String action(String action, String object);
}
