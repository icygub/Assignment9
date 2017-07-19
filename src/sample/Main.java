package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.time.LocalTime;


/**
 * John Diaz and Jordan Wells
 * Assignment 9
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Label response = new Label("");
        Label title = new Label("Unsorted");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setTextFill(Color.AQUA);

        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 1000, 1000);

//        for(int i = 0; i < 100; i++) {
//            int randomNumber = (int) (Math.random() * 100);
//            courses.add(new CourseOffering(randomNumber, "Math", "Jones", '3', "102", LocalTime.MIDNIGHT, 25));
//        }

        ObservableList<CourseOffering> courseOfferingObservableList = FXCollections.observableArrayList();
        for(int i = 0; i < 10; i++) {
            int randomNumber = (int) (Math.random() * 10);
            courseOfferingObservableList.add(new CourseOffering(randomNumber, "Math", "Jones", '3', "102", LocalTime.MIDNIGHT, 25));
        }

        TableView<CourseOffering> tvUnsorted;

        tvUnsorted = new TableView<>(courseOfferingObservableList);

        TableColumn<CourseOffering, Integer> courseNumCol = new TableColumn<>("Course Number");
        courseNumCol.setCellValueFactory(new PropertyValueFactory<>("courseNumber"));
        tvUnsorted.getColumns().add(courseNumCol);

        TableColumn<CourseOffering, String> courseNameCol = new TableColumn<>("Course Name");
        courseNameCol.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tvUnsorted.getColumns().add(courseNameCol);

        tvUnsorted.setPrefWidth(300);
        tvUnsorted.setPrefHeight(300);

        TableView.TableViewSelectionModel<CourseOffering> tvSelUnsorted = tvUnsorted.getSelectionModel();

        tvSelUnsorted.selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int index = (int) newValue;
                response.setText("The course number for " + courseOfferingObservableList.get(index).getCourseName() +
                        " is " + courseOfferingObservableList.get(index).getCourseNumber());
            }
        });

        response.setFont(Font.font("Arial", 14));
        root.getChildren().addAll(title, tvUnsorted, response);
        //primaryStage.setTitle("Course List");
        //primaryStage.setScene(scene);
        //primaryStage.show();

        //------------------------------------------------------------------

        Label response2 = new Label("");
        Label title2 = new Label("Sorted");
        title2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title2.setTextFill(Color.AQUA);

        FlowPane root2 = new FlowPane();
        root2.setAlignment(Pos.CENTER);

        TableView<CourseOffering> tvSorted;

        //sort here
        ObservableList<CourseOffering> courseOfferingObservableList2 = FXCollections.observableArrayList();
        final int arrSize = courseOfferingObservableList.size();
        for(int i = 0; i < arrSize; i++) {
            int rand = (int)(Math.random() * courseOfferingObservableList.size());
            courseOfferingObservableList2.add(courseOfferingObservableList.get(rand));
        }
        Courses courses = new Courses();
        courses.quickSort(courseOfferingObservableList2, 0, courseOfferingObservableList2.size()-1);


        tvSorted = new TableView<>(courseOfferingObservableList2);

        TableColumn<CourseOffering, Integer> courseNumCol2 = new TableColumn<>("Course Number");
        courseNumCol2.setCellValueFactory(new PropertyValueFactory<>("courseNumber"));
        tvSorted.getColumns().add(courseNumCol2);

        TableColumn<CourseOffering, String> courseNameCol2 = new TableColumn<>("Course Name");
        courseNameCol2.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tvSorted.getColumns().add(courseNameCol2);

        tvSorted.setPrefWidth(300);
        tvSorted.setPrefHeight(300);

        TableView.TableViewSelectionModel<CourseOffering> tvSelSorted = tvSorted.getSelectionModel();

        tvSelSorted.selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int index = (int) newValue;
            response.setText("The course number for " + courseOfferingObservableList.get(index).getCourseName() +
                    " is " + courseOfferingObservableList.get(index).getCourseNumber());
        });

        response2.setFont(Font.font("Arial", 14));
        root.getChildren().addAll(title2, tvSorted, response2);
        primaryStage.setTitle("Course List");
        primaryStage.setScene(scene);
        primaryStage.show();

//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
