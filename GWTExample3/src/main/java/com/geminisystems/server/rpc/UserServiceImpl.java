/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geminisystems.server.rpc;

import com.geminisystems.client.model.User;
import com.geminisystems.client.service.UserService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;

/**
 *
 * @author gb
 */
public class UserServiceImpl extends RemoteServiceServlet implements UserService {

    private List<User> users = new ArrayList<User>();

    @Override
    public void init() throws ServletException {
        super.init();
        users.add(new User("Ivan", "ivan@yandex.ru", 20));
        users.add(new User("Irina", "irina@mail.ru", 22));
        users.add(new User("Vova", "vova@gmail.com", 24));
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void deleteUser(User user) {
        Iterator i = users.iterator();
        while(i.hasNext()){
            User u = (User) i.next();
            if(u.equals(user)){
                users.remove(u);
                break;
            }    
        }
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getById(long id) {
        for (User u: users){
            if (u.getId() == id)
                return u;
        }
        return null;
    }
}
