package view.dao;

import java.util.List;
import java.util.Map;
import view.beans.LoginBean;


public class LoginDAO {

    public boolean login(LoginBean login){
            List<Map> result = ConnectionManager.executeQuery("select * from security where usuario='"+login.getUsername()+"' and password='"+login.getPassword()+"'");
            if(result!=null){
                for(Map map:result){
                    if(map.get("id_registro")!=null)
                        return true;
                    else
                        return false;
                }
            }
        return false;
    }
}
