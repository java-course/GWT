/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.geminisystems.client.service;

import com.geminisystems.client.model.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 *
 * @author gb
 */
@RemoteServiceRelativePath("userService")
public interface UserService extends RemoteService {

    public List<User> getAllUsers();
    public User getById(long id);
    public void addUser(User user);
    public void deleteUser(User user);

    public static class App {
        private static UserServiceAsync ourInstance = GWT.create(UserService.class);
        public static synchronized UserServiceAsync getInstance() {
            return ourInstance;
        }
    }

}
