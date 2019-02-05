/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolapp;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import schoolapp.gui.controller.MainViewController;

/**
 *
 * @author Philip
 */
public class SchoolApp extends Application
{
     private Stage primaryStage;
     private BorderPane rootLayout;
    
    @Override
    public void start(Stage stage) throws Exception
    {
        primaryStage = stage;
        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SchoolApp.class
                .getResource("/schoolapp/gui/view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        
        initView();
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
    private void initView() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SchoolApp.class.getResource("/schoolapp/gui/view/MainView.fxml"));
        AnchorPane logIn = (AnchorPane) loader.load();
        
        MainViewController controller = loader.getController();
        controller.setRootLayout(rootLayout);

        
        // Set person overview into the center of root layout.
        rootLayout.setCenter(logIn);
    }
    
}
