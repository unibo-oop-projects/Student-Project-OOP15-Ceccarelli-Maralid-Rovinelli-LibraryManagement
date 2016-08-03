package main;
import model.Employee;
import model.IOModel;
import model.Model;
import view.MainView;
import view.MainViewImpl;

import java.awt.Window;

import controller.Controller;
import controller.ControllerImpl;
public class Main {

    public static void main(String[] args) {
        IOModel<Employee,Employee> employeeModel = new IOModel<Employee,Employee>();
        ControllerImpl c = new ControllerImpl(employeeModel);
        MainView v = new MainViewImpl();
        c.setView(v);
        ((Window) v).setVisible(true);
    }

}
