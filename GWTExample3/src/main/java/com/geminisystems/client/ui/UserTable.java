/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geminisystems.client.ui;

import com.geminisystems.client.model.User;
import com.geminisystems.client.service.UserService;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import java.util.List;

/**
 *
 * @author gb
 */
public class UserTable extends FlexTable {

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }


    public UserTable(List<User> users) {
        super();
        this.setCellPadding(1);
        this.setCellSpacing(0);
        this.setWidth("100%");
        this.setUsers(users);
    }

    public void setUsers(List<User> users) {
        for (int i = this.getRowCount(); i > 0; i--) {
            this.removeRow(0);
        }
        if (users == null) {
            return;
        }

        // set headers
        this.setText(0, 0, "ID");
        this.setText(0, 1, "NAME");
        this.setText(0, 2, "EMAIL");
        this.setText(0, 3, "AGE");
        this.setText(0, 4, "");
        this.setText(0, 5, "");
        this.getRowFormatter().addStyleName(0, "tableHeader");

        // set users

        int i = 1;
        for (User u: users) {
            this.setText(i, 0, String.valueOf(u.getId()));
            this.setText(i, 1, u.getName());
            this.setText(i, 2, u.getEmail());
            this.setText(i, 3, String.valueOf(u.getAge()));
            this.setWidget(i, 4, new Button("Delete", getDeleteClickHandler(this, i, u)));
            this.getRowFormatter().addStyleName(i, "tableRow");
            i++;
        }
        this.users = users;
    }

    public void addUser(User user){
        users.add(user);
        setUsers(users);
    }

    private ClickHandler getDeleteClickHandler(final UserTable table, final int row, final User u){
        ClickHandler clickHandler = new ClickHandler() {

            public void onClick(ClickEvent event) {
                table.removeRow(row);
                UserService.App.getInstance().deleteUser(u, null);
            }
        };
        return clickHandler;
    }



}
