//import javafx.application.Application;
//
//import javafx.stage.Stage;
//
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.layout.FlowPane;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.List;
//
//public class TestGui extends Application {
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        ScrollPane scrollPane = new ScrollPane();
//        scrollPane.setFitToWidth(true);
//
//        FlowPane flowPane = new FlowPane();
//        flowPane.setStyle("-fx-background-color: #ddd;");
//        flowPane.setHgap(2);
//        flowPane.setVgap(2);
//        List<String> values = new ArrayList<>(Arrays.asList(String.values()));
//        values.sort(Comparator.comparing(Enum::name));
//        for (MaterialDesignIcon icon : values) {
//            Button button = MaterialDesignIconFactory.get().createIconButton(icon, icon.name());
//            flowPane.getChildren().add(button);
//        }
//
//        scrollPane.setContent(flowPane);
//
//        primaryStage.setScene(new Scene(scrollPane, 1200, 950));
//        primaryStage.show();
//    }
//}