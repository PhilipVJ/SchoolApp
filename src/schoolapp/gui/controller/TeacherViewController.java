/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolapp.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import schoolapp.be.SchoolClass;
import schoolapp.be.Student;
import schoolapp.gui.model.SchoolAppModel;
import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.runnable;

/**
 * FXML Controller class
 *
 * @author simge
 */
public class TeacherViewController implements Initializable {

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


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        model= new SchoolAppModel();
        
       // init tableview
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        schoolClass.setCellValueFactory(new PropertyValueFactory<>("schoolClass"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        absence.setCellValueFactory(new PropertyValueFactory<>("abPercentage"));
        
        
        classChooser.setItems(model.getAllClasses());
        
        classChooser.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                Platform.runLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                       setTableView(); 
                    }
                });
            
                }

    
        });
   
}
        private void setTableView()
            {
            curClass=FXCollections.observableArrayList(classChooser.getSelectionModel().getSelectedItem().getAllStudents());
            tableView.setItems(curClass);
            }
    
}
