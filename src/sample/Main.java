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

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Label response = new Label("");
        Label title = new Label("Course List\n");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setTextFill(Color.AQUA);

        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 450, 450);

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
