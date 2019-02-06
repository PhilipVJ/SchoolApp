/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolapp.gui.controller;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import schoolapp.be.Attendance;
import schoolapp.be.SchoolClass;
import schoolapp.be.Student;
import schoolapp.gui.model.SchoolAppModel;
import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.runnable;

/**
 * FXML Controller class
 *
 * @author simge
 */
public class TeacherViewController implements Initializable
{

    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, String> name;
    @FXML
    private TableColumn<Student, String> schoolClass;
    @FXML
    private TableColumn<Student, String> email;
    @FXML
    private TableColumn<Student, Double> absence;
    @FXML
    private ChoiceBox<SchoolClass> classChooser;

    private SchoolAppModel model;

    private ObservableList<Student> curClass;
    @FXML
    private LineChart<String, Double> chart;
    @FXML
    private NumberAxis percentage;
    @FXML
    private CategoryAxis days;

    private ObservableList<String> allOfDays;
    @FXML
    private Label absenceClass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        model = new SchoolAppModel();

        // init tableview
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        schoolClass.setCellValueFactory(new PropertyValueFactory<>("schoolClass"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        absence.setCellValueFactory(new PropertyValueFactory<>("abPercentage"));

        classChooser.setItems(model.getAllClasses());

        classChooser.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                Platform.runLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            setTableView();
                            calculateAverageAbsence();
                        } catch (ParseException ex)
                        {
                            Logger.getLogger(TeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

            }

        });

        tableView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                Platform.runLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        initStudentLineChart();
                    }
                });
            }
        });

    }

    private void setTableView()
    {
        curClass = FXCollections.observableArrayList(classChooser.getSelectionModel().getSelectedItem().getAllStudents());
        tableView.setItems(curClass);
    }

    private void initStudentLineChart()
    {
        clearChart();
        // Gets the selected student
        Student chosenStudent = tableView.getSelectionModel().getSelectedItem();
        if(chosenStudent!=null){
        String[] dayArray = new String[chosenStudent.getFullAttendance().size()];
        for (int i = 0; i < chosenStudent.getFullAttendance().size(); i++)
        {
            dayArray[i] = "" + (i + 1);
        }
        // Convert it to a list and add it to our ObservableList of days.
        ArrayList<String> listArray = new ArrayList<>();
        listArray.addAll(Arrays.asList(dayArray));
        allOfDays = FXCollections.observableArrayList(listArray);
        days.setCategories(allOfDays);
        chart.setTitle("Fravær");

        calculateAbsence(chosenStudent);
        }
    }

    private void clearChart()
    {
        // clears the chart for previous showings
        days.getCategories().clear();
        chart.getData().clear();
        if(allOfDays!=null){
        allOfDays.clear();
        }
    }

    private void calculateAbsence(Student s)
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
                System.out.println("Was there");
                daysAttended++;
            }
            int calAttendance = (int) (100 - daysAttended / numberOfDays * 100);
            System.out.println(""+numberOfDays+"     "+calAttendance);
            series.getData().add(new XYChart.Data("" + numberOfDays, calAttendance));
        }

        chart.getData().add(series);

    }
    
    public void calculateAverageAbsence() throws ParseException
    {
     ArrayList<Student> allStudents = classChooser.getSelectionModel().getSelectedItem().getAllStudents();
     double averageAbsence=0;
     double numberOfStudents=allStudents.size();
     for(Student x:allStudents)
     {
        averageAbsence=averageAbsence+x.getAbPercentage();
     }
     absenceClass.setText(""+averageAbsence/numberOfStudents);
    }
}
