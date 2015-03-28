/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geminisystems.client;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Main entry point.
 *
 * @author gb
 */
public class GwtEntryPoint implements EntryPoint, ValueChangeHandler<String> {

    private static final List<String> DAYS = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
    private Label lbl = new Label();

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
        final Label label = new Label("Hello, GWT!!!");
        final Button button = new Button("Click me!");

        button.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                label.setVisible(!label.isVisible());

            }
        });

        RootPanel.get().add(button);
        RootPanel.get().add(label);


        // Make some radio buttons, all in one group.
        RadioButton rb0 = new RadioButton("myRadioGroup", "foo");
        RadioButton rb1 = new RadioButton("myRadioGroup", "bar");
        RadioButton rb2 = new RadioButton("myRadioGroup", "baz");
        rb0.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                Window.alert("Hello!");
            }
        });


        // Check 'baz' by default.
        rb2.setChecked(true);

        // Add them to the root panel.
        FlowPanel panel = new FlowPanel();
        panel.add(rb0);
        panel.add(rb1);
        panel.add(rb2);
        panel.setTitle("Checkbox panel");
        RootPanel.get().add(panel);

        // Make a new check box, and select it by default.
        CheckBox cb = new CheckBox("Foo");
        cb.setChecked(true);

        // Hook up a handler to find out when it's clicked.
        cb.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                boolean checked = ((CheckBox) event.getSource()).isChecked();
                Window.alert("It is " + (checked ? "" : "not ") + "checked");
            }
        });

        // Add it to the root panel.
        RootPanel.get().add(cb);


        final DatePicker datePicker = new DatePicker();
        datePicker.setVisible(false);
        final Label text = new Label();

        text.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                datePicker.setVisible(!datePicker.isVisible());
            }
        });

        // Set the value in the text box when the user selects a date
        datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {

            public void onValueChange(ValueChangeEvent<Date> event) {
                Date date = event.getValue();
                String dateString = DateTimeFormat.getMediumDateFormat().format(date);
                text.setText(dateString);
            }
        });

        // Set the default value
        datePicker.setValue(new Date(), true);

        // Add the widgets to the page
        RootPanel.get().add(text);
        RootPanel.get().add(datePicker);

        final ToggleButton toggleButton = new ToggleButton("Up", "Down");
        toggleButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                if (toggleButton.isDown()) {
                    Window.alert("I have been toggled down");
                } else {
                    Window.alert("I have been toggled up");
                }
            }
        });




        RootPanel.get().add(toggleButton);


        // textarea and password

        PasswordTextBox ptb = new PasswordTextBox();
        TextBox tb = new TextBox();

        // TODO(ECC) must be tested.
        tb.addKeyPressHandler(new KeyPressHandler() {

            public void onKeyPress(KeyPressEvent event) {
                if (!Character.isDigit(event.getCharCode())) {
                    ((TextBox) event.getSource()).cancelKey();
                }
            }
        });

        // Let's make an 80x50 text area to go along with the other two.
        TextArea ta = new TextArea();
        ta.setCharacterWidth(80);
        ta.setVisibleLines(10);
        ta.setText("This is site area");
        ta.addKeyPressHandler(new KeyPressHandler() {

            public void onKeyPress(KeyPressEvent event) {
            }
        });

        // Add them to the root panel.
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(tb);
        verticalPanel.add(ptb);
        verticalPanel.add(ta);
        RootPanel.get().add(verticalPanel);


        // hyperlinks

        Hyperlink link0 = new Hyperlink("link to foo", "foo");
        Hyperlink link1 = new Hyperlink("link to bar", "bar");
        Hyperlink link2 = new Hyperlink("link to baz", "baz");

        // If the application starts with no history token, redirect to a new
        // 'baz' state.
        String initToken = History.getToken();
        if (initToken.length() == 0) {
            History.newItem("baz");
        }

        // Add widgets to the root panel.
        VerticalPanel panel2 = new VerticalPanel();
        panel2.add(lbl);
        panel2.add(link0);
        panel2.add(link1);
        panel2.add(link2);
        RootPanel.get().add(panel2);

        // Add history listener
        History.addValueChangeHandler(this);

        // Now that we've setup our listener, fire the initial history state.
        History.fireCurrentHistoryState();



        //listbox
        ListBox lb = new ListBox();
        lb.addItem("foo");
        lb.addItem("bar");
        lb.addItem("baz");
        lb.addItem("toto");
        lb.addItem("tintin");

        lb.addChangeHandler(new ChangeHandler() {

            public void onChange(ChangeEvent event) {
                ListBox listBox = (ListBox) event.getSource();
                Window.alert("Index = " + listBox.getSelectedIndex() + " Value = " + listBox.getValue(listBox.getSelectedIndex()));
            }
        });

        // Make enough room for all five items (setting this value to 1 turns it
        // into a drop-down list).
        lb.setVisibleItemCount(1);

        // Add it to the root panel.
        RootPanel.get().add(lb);




        //cellist
        TextCell textCell = new TextCell();

        // Create a CellList that uses the cell.
        CellList<String> cellList = new CellList<String>(textCell);
        cellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

        // Add a selection model to handle user selection.
        final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
        cellList.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

            public void onSelectionChange(SelectionChangeEvent event) {
                String selected = selectionModel.getSelectedObject();
                if (selected != null) {
                    Window.alert("You selected: " + selected);
                }
            }
        });

        // Set the total row count. This isn't strictly necessary, but it affects
        // paging calculations, so its good habit to keep the row count up to date.
        cellList.setRowCount(DAYS.size(), true);

        // Push the data into the widget.
        cellList.setRowData(0, DAYS);

        // Add it to the root panel.
        RootPanel.get().add(cellList);


        // menu

        Command cmd = new Command() {

            public void execute() {
                Window.alert("You selected a menu item!");
            }
        };

        // Make some sub-menus that we will cascade from the top menu.
        MenuBar fooMenu = new MenuBar(true);
        fooMenu.addItem("the", cmd);
        fooMenu.addItem("foo", cmd);
        fooMenu.addItem("menu", cmd);

        MenuBar barMenu = new MenuBar(true);
        barMenu.addItem("the", cmd);
        barMenu.addItem("bar", cmd);
        barMenu.addItem("menu", cmd);

        MenuBar bazMenu = new MenuBar(true);
        bazMenu.addItem("the", cmd);
        bazMenu.addItem("baz", cmd);
        bazMenu.addItem("menu", cmd);

        // Make a new menu bar, adding a few cascading menus to it.
        MenuBar menu = new MenuBar();
        menu.addItem("foo", fooMenu);
        menu.addItem("bar", barMenu);
        menu.addItem("baz", bazMenu);

        // Add it to the root panel.
        RootPanel.get().add(menu);


        // tree

        // Create a tree with a few items in it.
        TreeItem root = new TreeItem("root");
        root.addItem("item0");
        root.addItem("item1");
        root.addItem("item2");

        // Add a CheckBox to the tree
        TreeItem item = new TreeItem(new CheckBox("item3"));
        item.addItem(new TreeItem(new CheckBox("item4")));
        item.addItem(new TreeItem(new CheckBox("item5")));
        root.addItem(item);


        Tree t = new Tree();
        t.addItem(root);

        // Add it to the root panel.
        RootPanel.get().add(t);

        // richtext

        final RichTextArea rta = new RichTextArea();
        rta.setWidth("50%");
        rta.setHeight("10%");

        rta.addBlurHandler(new BlurHandler() {

            public void onBlur(BlurEvent event) {
                Window.alert(rta.getHTML());
            }
        });

        RootPanel.get().add(rta);


        // grid

        // Grids must be sized explicitly, though they can be resized later.
        Grid g = new Grid(5, 5);

        // Put some values in the grid cells.
        for (int row = 0; row < 5; ++row) {
            for (int col = 0; col < 5; ++col) {
                g.setText(row, col, "" + row + ", " + col);
            }
        }
        // You can use the CellFormatter to affect the layout of the grid's cells.
        g.getCellFormatter().setWidth(0, 2, "256px");
        RootPanel.get().add(g);

        VerticalPanel panel3 = new VerticalPanel();
        TabBar bar = new TabBar();
        bar.addTab("foo");
        bar.addTab("bar");
        bar.addTab("baz");

        // Hook up a tab listener to do something when the user selects a tab.
        bar.addSelectionHandler(new SelectionHandler<Integer>() {

            public void onSelection(SelectionEvent<Integer> event) {
                // Let the user know what they just did.
                Window.alert("You clicked tab " + event.getSelectedItem());
            }
        });

        // Just for fun, let's disallow selection of 'bar'.
        bar.addBeforeSelectionHandler(new BeforeSelectionHandler<Integer>() {

            public void onBeforeSelection(BeforeSelectionEvent<Integer> event) {
                if (event.getItem().intValue() == 1) {
                    event.cancel();
                }
            }
        });
        panel3.add(bar);

        // Add it to the root panel.
        RootPanel.get().add(panel3);

        // tabpanel

        String text1 = "Lorem ipsum dolor sit amet...";
        String text2 = "Sed egestas, arcu nec accumsan...";
        String text3 = "Proin tristique, elit at blandit...";

        TabPanel panel4 = new TabPanel();
        FlowPanel flowpanel;

        flowpanel = new FlowPanel();
        flowpanel.add(new Label(text1));
        panel4.add(flowpanel, "One");

        flowpanel = new FlowPanel();
        flowpanel.add(new Label(text2));
        panel4.add(flowpanel, "Two");

        flowpanel = new FlowPanel();
        flowpanel.add(new Label(text3));
        panel4.add(flowpanel, "Three");

        panel4.selectTab(0);

        panel4.setSize("500px", "250px");
        panel4.addStyleName("table-center");
        RootPanel.get().add(panel4);



    }

    public void onValueChange(ValueChangeEvent<String> event) {
        // This method is called whenever the application's history changes. Set
        // the label to reflect the current history token.
        lbl.setText("The current history token is: " + event.getValue());
    }
}
