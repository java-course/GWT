/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geminisystems.client;

import com.google.gwt.user.client.Window;
import com.geminisystems.client.model.User;
import com.geminisystems.client.service.UserService;
import com.geminisystems.client.service.UserServiceAsync;
import com.geminisystems.client.ui.UserTable;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.List;

/**
 * Main entry point.
 *
 * @author gb
 */
public class GwtEntryPoint implements EntryPoint {

    //private FlexTable table = new FlexTable();
    private UserTable table = new UserTable(null);
    private VerticalPanel mainPanel = new VerticalPanel();
    private VerticalPanel addPanel = new VerticalPanel();
    private TextBox nameBox = new TextBox();
    private Label nameLabel = new Label("Name");
    private TextBox emailBox = new TextBox();
    private Label emaiLabel = new Label("Email");
    private TextBox ageBox = new TextBox();
    private Label ageLabel = new Label("Age");
    private Button addButton = new SubmitButton("Add");
    private Grid grid = new Grid(4, 2);
    

    /**
     * Creates a new instance of gwtEntryPoint
     */
    public GwtEntryPoint() {
    }

    /** 
     * The entry point method, called automatically by loading a module
     * that declares an implementing class as an entry-point
     */
    public void onModuleLoad() {


        grid.setWidget(0, 0, nameLabel);
        grid.setWidget(0, 1, nameBox);
        grid.setWidget(1, 0, emaiLabel);
        grid.setWidget(1, 1, emailBox);
        grid.setWidget(2, 0, ageLabel);
        grid.setWidget(2, 1, ageBox);
        grid.setWidget(3, 1, addButton);



        mainPanel.add(table);
        mainPanel.add(grid);

        RootPanel.get().add(mainPanel);
        populateUsers();





        addButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                User user = new User(nameBox.getText(), emailBox.getText(), Integer.parseInt(ageBox.getText()));
                UserServiceAsync async = UserService.App.getInstance();
                async.addUser(user,  new AsyncCallback<Void>(){


                    @Override
                    public void onFailure(Throwable throwable) {
                        Window.alert(throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Void aVoid) {
                        populateUsers();
                    }

                });
                populateUsers();
            }
        });



    }

    private void populateUsers() {

        UserService.App.getInstance().getAllUsers(new AsyncCallback<List<User>>() {

            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }

            public void onSuccess(List<User> result) {
                table.setUsers(result);
            }
        });
    }
}
