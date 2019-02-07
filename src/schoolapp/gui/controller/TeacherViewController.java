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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import schoolapp.be.Attendance;
import schoolapp.be.SchoolClass;
import schoolapp.be.Student;
import schoolapp.be.Teacher;
import schoolapp.gui.model.SchoolAppModel;

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

    private ObservableList<String> allWeekDays;
    @FXML
    private Label absenceClass;
    @FXML
    private BarChart<String, Integer> dayChart;
    @FXML
    private NumberAxis dayY;
    @FXML
    private CategoryAxis dayX;
    
    private Teacher teacher;
    @FXML
    private Label tName;
    @FXML
    private Label tMail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        model = new SchoolAppModel();
        teacher=model.getTeacher();

        // init tableview
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        schoolClass.setCellValueFactory(new PropertyValueFactory<>("schoolClass"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        absence.setCellValueFactory(new PropertyValueFactory<>("abPercentage"));
        absence.setSortType(TableColumn.SortType.DESCENDING);
        classChooser.setItems(model.getAllClasses());
        chart.setTitle("Fravær");
        dayChart.setTitle("Fravær pr. dag");
        tName.setText(teacher.getName());
        tMail.setText(teacher.getEmail());
        

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
                        initStudentBarChart();
                    }

                });
            }
        });

        String[] dayArray = new String[5];
        dayArray[0] = "Man";
        dayArray[1] = "Tir";
        dayArray[2] = "Ons";
        dayArray[3] = "Tor";
        dayArray[4] = "Fre";
        // Convert it to a list and add it to our ObservableList of days.
        ArrayList<String> listArray = new ArrayList<>();
        listArray.addAll(Arrays.asList(dayArray));
        allWeekDays = FXCollections.observableArrayList(listArray);
        dayX.setCategories(allWeekDays);
        

    }

    private void setTableView()
    {
        curClass = FXCollections.observableArrayList(classChooser.getSelectionModel().getSelectedItem().getAllStudents());
        tableView.setItems(curClass);
        tableView.getSortOrder().setAll(absence);

    }

    private void initStudentLineChart()
    {
        clearChart();
        // Gets the selected student
        Student chosenStudent = tableView.getSelectionModel().getSelectedItem();
        if (chosenStudent != null)
        {
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
            

            calculateAbsence(chosenStudent);
        }
    }

    private void clearChart()
    {
        // clears the chart for previous showings
        days.getCategories().clear();
        chart.getData().clear();
        if (allOfDays != null)
        {
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
            System.out.println("" + numberOfDays + "     " + calAttendance);
            series.getData().add(new XYChart.Data("" + numberOfDays, calAttendance));
        }

        chart.getData().add(series);

    }

    public void calculateAverageAbsence() throws ParseException
    {
        ArrayList<Student> allStudents = classChooser.getSelectionModel().getSelectedItem().getAllStudents();
        double averageAbsence = 0;
        double numberOfStudents = allStudents.size();
        for (Student x : allStudents)
        {
            averageAbsence = averageAbsence + x.getAbPercentage();
        }
        absenceClass.setText("" + averageAbsence / numberOfStudents);
    }

    private void initStudentBarChart()
    {

        dayChart.getData().clear();
        // Gets the selected student

        Student chosenStudent = tableView.getSelectionModel().getSelectedItem();
        if (chosenStudent != null)
        {

            calculateWeekdayAbsence(chosenStudent);

        }

    }

    private void calculateWeekdayAbsence(Student chosenStudent)
    {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        ArrayList<Integer> weekDays = chosenStudent.getMostAbsentDay();

        series.getData().add(new XYChart.Data("Man", weekDays.get(0)));
        series.getData().add(new XYChart.Data("Tir", weekDays.get(1)));
        series.getData().add(new XYChart.Data("Ons", weekDays.get(2)));
        series.getData().add(new XYChart.Data("Tors", weekDays.get(3)));
        series.getData().add(new XYChart.Data("Fre", weekDays.get(4)));

        dayChart.getData().add(series);

        System.out.println("Done");
    }
}
