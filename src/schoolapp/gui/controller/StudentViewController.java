/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolapp.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import schoolapp.be.Attendance;
import schoolapp.be.Student;
import schoolapp.gui.model.SchoolAppModel;

/**
 *
 * @author simge
 */
public class StudentViewController implements Initializable
{

    @FXML
    private Label absence;
    private SchoolAppModel model;
    private Student s;
    @FXML
    private Label name;
    @FXML
    private TableView<Attendance> tableView;
    @FXML
    private TableColumn<Attendance, String> date;
    @FXML
    private TableColumn<Attendance, String> presence;
    @FXML
    private LineChart<String, Double> chart;
    @FXML
    private NumberAxis percentage;
    @FXML
    private CategoryAxis days;

    private ObservableList<String> allOfDays = FXCollections.observableArrayList();
    @FXML
    private Label email;

    public void initialize(URL url, ResourceBundle rb)
    {

        model = new SchoolAppModel();
        showAlert();
        s = model.getStudent(1);
        double ab = s.getAbsencePercentage();
        String toShow = String.format("%.1f", ab);
        absence.setText(toShow + "%");
        name.setText(s.getName());
        date.setSortType(TableColumn.SortType.DESCENDING);

        // init tableview
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        presence.setCellValueFactory(new PropertyValueFactory<>("attendance"));
        tableView.setItems(model.getList());

        // Makes an arraylist with the size of the total number of days the student
        // has been in school. Each String contains the number of the specific day
        String[] dayArray = new String[s.getFullAttendance().size()];
        for (int i = 0; i < s.getFullAttendance().size(); i++)
        {
            dayArray[i] = "" + (i + 1);
        }
        // Convert it to a list and add it to our ObservableList of days.
        allOfDays.addAll(Arrays.asList(dayArray));
        days.setCategories(allOfDays);
        chart.setTitle("Fravær");
        chart.setTitleSide(Side.TOP);
        

        calculateAbsence();
        tableView.getSortOrder().setAll(date);
        
        name.setText(s.getName());
        email.setText(s.getEmail());
    }

    private void calculateAbsence()
    {
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        ArrayList<Attendance> allAttendance = s.getFullAttendance();

        int numberOfDays = 0;
        double daysAttended = 0;

        for (Attendance x : allAttendance)
        {
            numberOfDays++;
            if (x.getWasThere() == true)
            {
                daysAttended++;
            }
            int calAttendance = (int) (100 - daysAttended / numberOfDays * 100);

            series.getData().add(new XYChart.Data("" + numberOfDays, calAttendance));
        }

        chart.getData().add(series);

    }

    private void showAlert()
    {
        if (model.checkForSchoolNetwork() == true && model.checkForDailyAttendance(Calendar.getInstance()) == false)
        {
            Alert showAlert = new Alert(Alert.AlertType.INFORMATION);
            showAlert.setHeaderText("Fraværs alert");
            showAlert.setContentText("Du er ikke registreret for i dag - lad mig gøre det for dig!");
            showAlert.showAndWait();
        }

    }

    @FXML
    private void askForAttendance(ActionEvent event)
    {
        Attendance chosenAttendance = tableView.getSelectionModel().getSelectedItem();
        
        if(chosenAttendance!=null && chosenAttendance.getWasThere()==false && chosenAttendance.getRequestAttendance()==false){
        Alert showAlert = new Alert(Alert.AlertType.INFORMATION);
        showAlert.setHeaderText("Anmodning om godkendelse");
        showAlert.setContentText("Din anmodning om godkendelse af fravær d. "+chosenAttendance.getDate()+" er sendt til din lærer!");
        showAlert.showAndWait();
        chosenAttendance.setAttendance("Fravær (Anmodet om godkendelse)");
        chosenAttendance.setRequestAttendance(true);
        model.askForAttendance(s.getId(), chosenAttendance);
        tableView.refresh();
        }
    }


}
