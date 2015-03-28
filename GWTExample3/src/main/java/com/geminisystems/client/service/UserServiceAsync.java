/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.geminisystems.client.service;

import com.geminisystems.client.model.User;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 *
 * @author gb
 */

public interface UserServiceAsync extends RemoteService {

    public void getAllUsers(AsyncCallback<List<User>> callback);
    public void getById(long id, AsyncCallback<User> callback);
    public void deleteUser(User user, AsyncCallback<Void> callback);
    public void addUser(User user, AsyncCallback<Void> asyncCallback);

}
