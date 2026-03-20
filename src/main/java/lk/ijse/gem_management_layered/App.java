package lk.ijse.gem_management_layered;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

    public class App extends Application {

        private static Scene scene;

        @Override
        public void start(Stage stage) throws IOException {

            scene = new Scene(loadFXML("Dashboard"));

            stage.setScene(scene);
            stage.setTitle("Gem Management System");
            stage.show();
        }

        public static void setRoot(String fxml) throws IOException {
            scene.setRoot(loadFXML(fxml));
        }

        public static Parent loadFXML(String fxml) throws IOException {
            FXMLLoader loader = new FXMLLoader(App.class.getResource( fxml + ".fxml"));
            return loader.load();
        }

        public static void main(String[] args) {
            launch();
        }

}
