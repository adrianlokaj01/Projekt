import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Biletomat extends Application {

    private Label wiekEtykieta;
    private TextField wiekPoleTekstowe;
    private Label iloscBiletowEtykieta;
    private TextField iloscBiletowPoleTekstowe;
    private Label kosztEtykieta;
    private Label wynikEtykieta;
    private RadioButton normalnyRadio;
    private RadioButton ulgowyRadio;
    private CheckBox kartaMiejskaCheckbox;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Biletomat");

        // Tworzenie etykiet i pól tekstowych
        wiekEtykieta = new Label("Wiek:");
        wiekPoleTekstowe = new TextField();
        iloscBiletowEtykieta = new Label("Ilość biletów:");
        iloscBiletowPoleTekstowe = new TextField();
        kosztEtykieta = new Label("Całkowity koszt:");
        wynikEtykieta = new Label();

        // Tworzenie przycisku "Oblicz"
        Button obliczButton = new Button("Oblicz");
        obliczButton.setOnAction(e -> obliczKoszt());

        // Tworzenie radio buttonów "Normalny" i "Ulgowy"
        normalnyRadio = new RadioButton("Normalny");
        ulgowyRadio = new RadioButton("Ulgowy");
        ulgowyRadio.setSelected(true); // Domyślnie zaznaczony jest bilet ulgowy

        // Tworzenie grupy radio buttonów i dodawanie do niej radio buttonów
        ToggleGroup grupaRadio = new ToggleGroup();
        normalnyRadio.setToggleGroup(grupaRadio);
        ulgowyRadio.setToggleGroup(grupaRadio);

        // Tworzenie checkboxa "Karta miejska"
        kartaMiejskaCheckbox = new CheckBox("Karta miejska");

        // Tworzenie siatki i dodawanie do niej elementów
        GridPane siatka = new GridPane();
        siatka.setAlignment(Pos.CENTER);
        siatka.setHgap(10);
        siatka.setVgap(10);
        siatka.setPadding(new Insets(25, 25, 25, 25));
        siatka.add(wiekEtykieta, 0, 0);
        siatka.add(wiekPoleTekstowe, 1, 0);
        siatka.add(iloscBiletowEtykieta, 0, 1);
        siatka.add(iloscBiletowPoleTekstowe, 1, 1);
        siatka.add(normalnyRadio, 0, 2);
        siatka.add(ulgowyRadio, 1, 2);
        siatka.add(kartaMiejskaCheckbox, 0, 3);
        siatka.add(obliczButton, 0, 4);
        siatka.add(kosztEtykieta, 0, 5);
        siatka.add(wynikEtykieta, 1, 5);

        // Tworzenie sceny i dodawanie do niej siatki scen
        Scene scene = new Scene(siatka, 400, 300);
           // Wyświetlenie okna programu
    primaryStage.setScene(scene);
    primaryStage.show();
}

// Metoda obliczająca koszt biletów
private void obliczKoszt() {
    try {
        int wiek = Integer.parseInt(wiekPoleTekstowe.getText());
        int iloscBiletow = Integer.parseInt(iloscBiletowPoleTekstowe.getText());

        // Sprawdzenie, czy podano poprawny wiek
        if (wiek < 0 || wiek > 120) {
            wynikEtykieta.setText("Podaj poprawny wiek (0-120)");
            return;
        }

        // Obliczenie kosztu biletów
        double cenaBiletu;
        if (ulgowyRadio.isSelected() && wiek < 18) {
            cenaBiletu = 2.50;
        } else {
            cenaBiletu = 5.00;
        }

        double kosztBiletow = iloscBiletow * cenaBiletu;

        // Dodanie opłaty za kartę miejską
        if (kartaMiejskaCheckbox.isSelected()) {
            kosztBiletow += 1.50;
        }

        wynikEtykieta.setText(String.format("%.2f zł", kosztBiletow));
    } catch (NumberFormatException e) {
        wynikEtykieta.setText("Podaj poprawne dane");
    }
}

public static void main(String[] args) {
    launch(args);
}
}