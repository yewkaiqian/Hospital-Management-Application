import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainGUI extends Application {

    // Colour Palette
    // Brand
    private static final String PRIMARY       = "#00B4D8";
    private static final String PRIMARY_DARK  = "#0077B6";
    private static final String PRIMARY_LIGHT = "#CAF0F8";
    private static final String CYAN_1 = "#ADE8F4";
    private static final String CYAN_2 = "#90E0EF";
    
    // Neutrals
    private static final String WHITE      = "#FFFFFF";
    private static final String BACKGROUND = "#EFFBFE";
    private static final String DARK_TEXT  = "#03045E";
    private static final String GREY_TEXT  = "#5C7A94";
    private static final String BORDER     = "#D6F1F8";

    // Per-module accent colours
    private static final String ACCENT_DOCTORS     = "#0096C7";
    private static final String ACCENT_PATIENTS    = "#00B4D8";
    private static final String ACCENT_MEDICAL     = "#0077B6";
    private static final String ACCENT_LABS        = "#06AED5";
    private static final String ACCENT_FACILITIES  = "#219EBC";
    private static final String ACCENT_STAFF       = "#0BA6A6";

    // Fonts
    private static final String DISPLAY_FONT = "Book Antiqua";
    private static final String BODY_FONT    = "Trebuchet MS";

    private Stage primaryStage;
    private HospitalManagement hospital;
    private BorderPane root;
    private Label dateTimeLabel;

    private final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");

    private final DateTimeFormatter timeFormatter =
            DateTimeFormatter.ofPattern("hh:mm:ss a");

    // Start
    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        hospital = new HospitalManagement();

        root = new BorderPane();
        root.setStyle("-fx-background-color: " + BACKGROUND + ";");

        createHeader();
        showDashboard();

        Scene scene = new Scene(root, 1250, 780);

        primaryStage.setTitle("Hospital Management System");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1050);
        primaryStage.setMinHeight(680);
        primaryStage.show();
    }

    // Header
    private void createHeader() {
        BorderPane header = new BorderPane();

        header.setPadding(new Insets(16, 32, 16, 32));

        header.setStyle(
                "-fx-background-color: " + WHITE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 0 1 0;"
        );

        DropShadow headerShadow = new DropShadow();
        headerShadow.setColor(Color.web("#0A2E38", 0.06));
        headerShadow.setRadius(14);
        headerShadow.setOffsetY(3);
        header.setEffect(headerShadow);

        HBox logoArea = new HBox(14);
        logoArea.setAlignment(Pos.CENTER_LEFT);

        StackPane logo = createLogo();

        VBox logoText = new VBox(1);

        Label title = new Label("HMS");
        title.setFont(Font.font(DISPLAY_FONT, FontWeight.BOLD, 24));
        title.setTextFill(Color.web(PRIMARY_DARK));

        Label subtitle = new Label("Hospital Management System");
        subtitle.setFont(Font.font(BODY_FONT, 11));
        subtitle.setTextFill(Color.web(GREY_TEXT));

        logoText.getChildren().addAll(title, subtitle);
        logoArea.getChildren().addAll(logo, logoText);

        VBox clockArea = new VBox(3);
        clockArea.setAlignment(Pos.CENTER_RIGHT);

        Label today = new Label("CURRENT DATE & TIME");
        today.setFont(Font.font(BODY_FONT, FontWeight.BOLD, 10));
        today.setStyle("-fx-letter-spacing: 1px;");
        today.setTextFill(Color.web(GREY_TEXT));

        dateTimeLabel = new Label();
        dateTimeLabel.setFont(Font.font(BODY_FONT, FontWeight.BOLD, 13));
        dateTimeLabel.setTextFill(Color.web(PRIMARY_DARK));

        updateDateTime();

        clockArea.getChildren().addAll(today, dateTimeLabel);

        header.setLeft(logoArea);
        header.setRight(clockArea);

        root.setTop(header);

        Timeline clock = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateDateTime())
        );

        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }


    private StackPane createLogo() {
        StackPane logo = new StackPane();

        Circle circle = new Circle(24);
        circle.setFill(new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web(PRIMARY)),
                new Stop(1, Color.web(PRIMARY_DARK))
        ));

        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.web(PRIMARY_DARK, 0.35));
        shadow.setRadius(10);
        shadow.setOffsetY(2);
        circle.setEffect(shadow);

        Label plus = new Label("+");
        plus.setTextFill(Color.WHITE);
        plus.setFont(Font.font(BODY_FONT, FontWeight.BOLD, 26));

        logo.getChildren().addAll(circle, plus);

        return logo;
    }


    private void updateDateTime() {
        LocalDateTime now = LocalDateTime.now();

        dateTimeLabel.setText(
                now.format(dateFormatter) +
                "   •   " +
                now.format(timeFormatter)
        );
    }

    // Dashboard
    private void showDashboard() {
        VBox page = new VBox(24);

        page.setPadding(new Insets(35, 45, 40, 45));
        page.setAlignment(Pos.CENTER);

        Label welcome = new Label("Welcome to HMS");
        welcome.setFont(Font.font(DISPLAY_FONT, FontWeight.BOLD, 36));
        welcome.setTextFill(Color.web(DARK_TEXT));

        Label subtitle = new Label(
                "Hospital Management System Dashboard"
        );

        subtitle.setFont(Font.font(BODY_FONT, 16));
        subtitle.setTextFill(Color.web(PRIMARY));

        Label instruction = new Label(
                "Select a section below to view or manage hospital records."
        );

        instruction.setFont(Font.font(BODY_FONT, 13));
        instruction.setTextFill(Color.web(GREY_TEXT));

        VBox welcomeArea = new VBox(6);
        welcomeArea.setAlignment(Pos.CENTER);
        welcomeArea.getChildren().addAll(
                welcome,
                subtitle,
                instruction
        );


        Label menuTitle = new Label("Main Menu");
        menuTitle.setFont(Font.font(DISPLAY_FONT, FontWeight.BOLD, 27));
        menuTitle.setTextFill(Color.web(DARK_TEXT));


        GridPane menuGrid = new GridPane();

        menuGrid.setHgap(18);
        menuGrid.setVgap(18);

        String[] names = {
                "Doctors",
                "Patients",
                "Medical",
                "Laboratories",
                "Facilities",
                "Staff"
        };

        String[] descriptions = {
                "Manage doctor records",
                "Manage patient records",
                "Manage medical supplies",
                "Manage laboratory services",
                "Manage hospital facilities",
                "Manage staff records"
        };

        String[] icons = {
                "DR",
                "PT",
                "MD",
                "LB",
                "FC",
                "ST"
        };

        String[] accents = {
                ACCENT_DOCTORS,
                ACCENT_PATIENTS,
                ACCENT_MEDICAL,
                ACCENT_LABS,
                ACCENT_FACILITIES,
                ACCENT_STAFF
        };

        for (int i = 0; i < names.length; i++) {
            VBox card = createMenuCard(
                    names[i],
                    descriptions[i],
                    icons[i],
                    accents[i]
            );

            menuGrid.add(card, i % 3, i / 3);
        }

        for (int i = 0; i < 3; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(33.33);
            menuGrid.getColumnConstraints().add(column);
        }

        VBox menuSection = new VBox(20);
        menuSection.setPadding(new Insets(28, 30, 32, 30));
        menuSection.setStyle(
                "-fx-background-color: " + WHITE  + ";" +
                "-fx-background-radius: 16;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1.5;" +
                "-fx-border-radius: 16;"
        );

        DropShadow menuSectionShadow = new DropShadow();
        menuSectionShadow.setColor(Color.web(PRIMARY_DARK, 0.08));
        menuSectionShadow.setRadius(18);
        menuSectionShadow.setOffsetY(5);
        menuSection.setEffect(menuSectionShadow);

        menuSection.getChildren().addAll(menuTitle, menuGrid);
        VBox.setMargin(menuSection, new Insets(20, 0, 0, 0));

        page.getChildren().addAll(
                welcomeArea,
                menuSection
        );

        ScrollPane scroll = new ScrollPane(page);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        scroll.setStyle("-fx-background-color: transparent;");

        root.setCenter(scroll);
    }

    // Menu Card
    private VBox createMenuCard(
            String title,
            String description,
            String iconText,
            String accentColor
    ) {
        VBox card = new VBox(11);

        card.setPadding(new Insets(22));
        card.setPrefHeight(158);
        card.setMaxWidth(Double.MAX_VALUE);

        setMenuCardStyle(card, accentColor, false);

        Label icon = new Label(iconText);

        icon.setMinSize(52, 52);
        icon.setMaxSize(52, 52);
        icon.setAlignment(Pos.CENTER);
        icon.setTextFill(Color.WHITE);
        icon.setFont(Font.font(BODY_FONT, FontWeight.BOLD, 15));

        icon.setStyle(
                "-fx-background-color: " + accentColor + ";" +
                "-fx-background-radius: 14;"
        );

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font(DISPLAY_FONT, FontWeight.BOLD, 18));
        titleLabel.setTextFill(Color.web(DARK_TEXT));

        Label descriptionLabel = new Label(description);
        descriptionLabel.setFont(Font.font(BODY_FONT, 12));
        descriptionLabel.setTextFill(Color.web(GREY_TEXT));
        descriptionLabel.setWrapText(true);

        card.getChildren().addAll(
                icon,
                titleLabel,
                descriptionLabel
        );

        card.setOnMouseEntered(
                event -> setMenuCardStyle(card, accentColor, true)
        );

        card.setOnMouseExited(
                event -> setMenuCardStyle(card, accentColor, false)
        );

        card.setOnMouseClicked(event -> {
            switch (title) {
                case "Doctors":
                    showDoctors();
                    break;
                case "Patients":
                    showPatients();
                    break;
                case "Medical":
                    showMedical();
                    break;
                case "Laboratories":
                    showLaboratories();
                    break;
                case "Facilities":
                    showFacilities();
                    break;
                case "Staff":
                    showStaff();
                    break;
            }
        });

        return card;
    }


    private void setMenuCardStyle(VBox card, String accentColor, boolean hover) {
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.web(accentColor, hover ? 0.28 : 0.10));
        shadow.setRadius(hover ? 22 : 12);
        shadow.setOffsetY(hover ? 8 : 3);

        card.setEffect(shadow);
        card.setTranslateY(hover ? -3 : 0);

        if (hover) {
            card.setStyle(
                    "-fx-background-color: " + WHITE + ";" +
                    "-fx-background-radius: 14;" +
                    "-fx-border-color: " + accentColor + ";" +
                    "-fx-border-width: 1.5;" +
                    "-fx-border-radius: 14;" +
                    "-fx-cursor: hand;"
            );
        } else {
            card.setStyle(
                    "-fx-background-color: " + WHITE + ";" +
                    "-fx-background-radius: 14;" +
                    "-fx-border-color: " + BORDER + ";" +
                    "-fx-border-radius: 14;" +
                    "-fx-cursor: hand;"
            );
        }
    }

    // Page Header
    private VBox createPageHeader(
            String title,
            String description
    ) {
        VBox header = new VBox(5);

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font(DISPLAY_FONT, FontWeight.BOLD, 30));
        titleLabel.setTextFill(Color.web(DARK_TEXT));

        Label descriptionLabel = new Label(description);
        descriptionLabel.setFont(Font.font(BODY_FONT, 13));
        descriptionLabel.setTextFill(Color.web(GREY_TEXT));

        header.getChildren().addAll(
                titleLabel,
                descriptionLabel
        );

        return header;
    }

    // Navigation — single "Back" button
    private HBox createNavigationBar() {
        HBox navigation = new HBox(14);

        navigation.setAlignment(Pos.CENTER_LEFT);
        navigation.setPadding(new Insets(12, 0, 0, 0));

        Button back = createBackButton("← Back");

        back.setOnAction(event -> showDashboard());

        navigation.getChildren().add(back);

        return navigation;
    }

    // Navigation — "Back" + "Main Menu", used only by the Ambulance
    private HBox createAmbulanceNavigationBar() {
        HBox navigation = new HBox(14);

        navigation.setAlignment(Pos.CENTER_LEFT);
        navigation.setPadding(new Insets(12, 0, 0, 0));

        Button back = createBackButton("← Back");
        Button mainMenu = createMainMenuButton();

        back.setOnAction(event -> showFacilities());

        mainMenu.setOnAction(event -> {
            if (confirmMainMenu()) {
                showDashboard();
            }
        });

        navigation.getChildren().addAll(
                back,
                mainMenu
        );

        return navigation;
    }


    private Button createBackButton(String text) {
        Button button = new Button(text);

        button.setPrefWidth(180);
        button.setPrefHeight(48);

        button.setFont(Font.font(BODY_FONT, FontWeight.BOLD, 13));
        button.setTextFill(Color.web(PRIMARY_DARK));

        button.setStyle(
                "-fx-background-color: " + WHITE + ";" +
                "-fx-background-radius: 9;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1.5;" +
                "-fx-border-radius: 9;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(event ->
                button.setStyle(
                        "-fx-background-color: " + PRIMARY_LIGHT + ";" +
                        "-fx-background-radius: 9;" +
                        "-fx-border-color: " + PRIMARY + ";" +
                        "-fx-border-width: 1.5;" +
                        "-fx-border-radius: 9;" +
                        "-fx-cursor: hand;"
                )
        );

        button.setOnMouseExited(event ->
                button.setStyle(
                        "-fx-background-color: " + WHITE + ";" +
                        "-fx-background-radius: 9;" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-width: 1.5;" +
                        "-fx-border-radius: 9;" +
                        "-fx-cursor: hand;"
                )
        );

        return button;
    }


    private Button createMainMenuButton() {
        Button button = new Button("Main Menu");

        button.setPrefWidth(180);
        button.setPrefHeight(48);

        button.setFont(Font.font(BODY_FONT, FontWeight.BOLD, 13));
        button.setTextFill(Color.WHITE);

        button.setStyle(
                "-fx-background-color: " + PRIMARY + ";" +
                "-fx-background-radius: 9;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(event ->
                button.setStyle(
                        "-fx-background-color: " + PRIMARY_DARK + ";" +
                        "-fx-background-radius: 9;" +
                        "-fx-cursor: hand;"
                )
        );

        button.setOnMouseExited(event ->
                button.setStyle(
                        "-fx-background-color: " + PRIMARY + ";" +
                        "-fx-background-radius: 9;" +
                        "-fx-cursor: hand;"
                )
        );

        return button;
    }

    // Doctors
	private void showDoctors() {
        VBox page = new VBox(18);

        page.setPadding(new Insets(30, 40, 30, 40));

        page.getChildren().add(
                createPageHeader(
                        "Doctors",
                        "View and manage registered doctors."
                )
        );

        TableView<Doctor> table = new TableView<>();

        styleTable(table);

        TableColumn<Doctor, String> id = new TableColumn<>("ID");
        id.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getId())
        );

        TableColumn<Doctor, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getName())
        );

        TableColumn<Doctor, String> specialist =
                new TableColumn<>("Specialist");

        specialist.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getSpecialist())
        );

        TableColumn<Doctor, String> timing =
                new TableColumn<>("Working Time");

        timing.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getWorkTime())
        );

        TableColumn<Doctor, String> qualification =
                new TableColumn<>("Qualification");

        qualification.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getQualification())
        );

        TableColumn<Doctor, String> room =
                new TableColumn<>("Room");

        room.setCellValueFactory(data ->
                new SimpleStringProperty(
                        String.valueOf(data.getValue().getRoom())
                )
        );

        table.getColumns().addAll(
                id,
                name,
                specialist,
                timing,
                qualification,
                room
        );

        table.setItems(
                FXCollections.observableArrayList(
                        hospital.getDoctors()
                )
        );

        Button add = createPrimaryButton("+  Add New Doctor");

        add.setOnAction(event -> showAddDoctor());

        HBox actionBar = new HBox(add);
        actionBar.setAlignment(Pos.CENTER_RIGHT);

        page.getChildren().addAll(
                table,
                actionBar,
                createNavigationBar()
        );

        VBox.setVgrow(table, Priority.ALWAYS);

        root.setCenter(page);
    }

    // Add Doctor
    private void showAddDoctor() {
        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.setTitle("Add New Doctor");
        dialog.setHeaderText("Enter Doctor Information");

        GridPane form = createFormGrid();

        TextField id = createTextField("Doctor ID");
        TextField name = createTextField("Doctor Name");
        TextField specialist = createTextField("Specialist");
        TextField timing = createTextField("Working Time");
        TextField qualification = createTextField("Qualification");
        TextField room = createTextField("Room Number");

        addFormRow(form, "ID:", id, 0);
        addFormRow(form, "Name:", name, 1);
        addFormRow(form, "Specialist:", specialist, 2);
        addFormRow(form, "Timing:", timing, 3);
        addFormRow(form, "Qualification:", qualification, 4);
        addFormRow(form, "Room No.:", room, 5);

        dialog.getDialogPane().setContent(form);

        ButtonType save = new ButtonType(
                "Save Doctor",
                ButtonBar.ButtonData.OK_DONE
        );

        ButtonType cancel = new ButtonType(
                "Cancel",
                ButtonBar.ButtonData.CANCEL_CLOSE
        );

        dialog.getDialogPane().getButtonTypes().addAll(
                save,
                cancel
        );

        Button saveButton =
                (Button) dialog.getDialogPane().lookupButton(save);

        saveButton.setOnAction(event -> {
            if (!validateTextFields(
                    id,
                    name,
                    specialist,
                    timing,
                    qualification,
                    room
            )) {
                event.consume();
                showError("Please fill in all fields.");
                return;
            }

            if (!InputValidator.isPositiveInteger(room.getText())) {
                event.consume();
                showError("Room number must be a positive integer.");
                return;
            }

            int roomNumber = Integer.parseInt(room.getText().trim());

            ArrayList<String> existingIds = new ArrayList<>();
            for (Doctor d : hospital.getDoctors()) {
                existingIds.add(d.getId());
            }

            if (!InputValidator.isUniqueId(id.getText().trim(), existingIds)) {
                event.consume();
                showError("A doctor with this ID already exists.");
                return;
            }

            Doctor doctor = new Doctor(
                    id.getText().trim(),
                    name.getText().trim(),
                    specialist.getText().trim(),
                    timing.getText().trim(),
                    qualification.getText().trim(),
                    roomNumber
            );

            hospital.addDoctor(doctor);
            showSuccess("Doctor added successfully.");
        });

        dialog.showAndWait();
        showDoctors();
    }

    // Patients
	private void showPatients() {
        VBox page = new VBox(18);

        page.setPadding(new Insets(30, 40, 30, 40));

        page.getChildren().add(
                createPageHeader(
                        "Patients",
                        "View and manage registered patients."
                )
        );

        TableView<Patient> table = new TableView<>();
        styleTable(table);

        TableColumn<Patient, String> id = new TableColumn<>("ID");
        id.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getId())
        );

        TableColumn<Patient, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getName())
        );

        TableColumn<Patient, String> disease =
                new TableColumn<>("Disease");

        disease.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getDisease())
        );

        TableColumn<Patient, String> sex = new TableColumn<>("Sex");
        sex.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getSex())
        );

        TableColumn<Patient, String> status =
                new TableColumn<>("Admission Status");

        status.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getAdmitStatus())
        );

        TableColumn<Patient, String> age = new TableColumn<>("Age");
        age.setCellValueFactory(data ->
                new SimpleStringProperty(
                        String.valueOf(data.getValue().getAge())
                )
        );

        table.getColumns().addAll(
                id,
                name,
                disease,
                sex,
                status,
                age
        );

        table.setItems(
                FXCollections.observableArrayList(
                        hospital.getPatients()
                )
        );

        Button add = createPrimaryButton("+  Add New Patient");
        add.setOnAction(event -> showAddPatient());

        HBox actionBar = new HBox(add);
        actionBar.setAlignment(Pos.CENTER_RIGHT);

        page.getChildren().addAll(
                table,
                actionBar,
                createNavigationBar()
        );

        VBox.setVgrow(table, Priority.ALWAYS);

        root.setCenter(page);
    }


    private void showAddPatient() {
        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.setTitle("Add New Patient");
        dialog.setHeaderText("Enter Patient Information");

        GridPane form = createFormGrid();

        TextField id = createTextField("Patient ID");
        TextField name = createTextField("Patient Name");
        TextField disease = createTextField("Disease");

        ComboBox<String> sex = new ComboBox<>();
        sex.getItems().addAll("Male", "Female");
        sex.setPromptText("Select Sex");
        sex.setPrefWidth(300);

        ComboBox<String> status = new ComboBox<>();
        status.getItems().addAll("Admitted", "Discharged");
        status.setPromptText("Select Status");
        status.setPrefWidth(300);

        TextField age = createTextField("Age");

        addFormRow(form, "ID:", id, 0);
        addFormRow(form, "Name:", name, 1);
        addFormRow(form, "Disease:", disease, 2);
        addFormRow(form, "Sex:", sex, 3);
        addFormRow(form, "Status:", status, 4);
        addFormRow(form, "Age:", age, 5);

        dialog.getDialogPane().setContent(form);

        ButtonType save = new ButtonType(
                "Save Patient",
                ButtonBar.ButtonData.OK_DONE
        );

        ButtonType cancel = new ButtonType(
                "Cancel",
                ButtonBar.ButtonData.CANCEL_CLOSE
        );

        dialog.getDialogPane().getButtonTypes().addAll(
                save,
                cancel
        );

        Button saveButton =
                (Button) dialog.getDialogPane().lookupButton(save);

        saveButton.setOnAction(event -> {
            if (!validateTextFields(id, name, disease, age)
                    || sex.getValue() == null
                    || status.getValue() == null) {

                event.consume();
                showError("Please fill in all fields.");
                return;
            }

            if (!InputValidator.isPositiveInteger(age.getText())) {
                event.consume();
                showError("Age must be a positive integer.");
                return;
            }

            int ageValue = Integer.parseInt(age.getText().trim());

            ArrayList<String> existingIds = new ArrayList<>();
            for (Patient p : hospital.getPatients()) {
                existingIds.add(p.getId());
            }

            if (!InputValidator.isUniqueId(id.getText().trim(), existingIds)) {
                event.consume();
                showError("A patient with this ID already exists.");
                return;
            }

            Patient patient = new Patient(
                    id.getText().trim(),
                    name.getText().trim(),
                    disease.getText().trim(),
                    sex.getValue(),
                    status.getValue(),
                    ageValue
            );

            hospital.addPatient(patient);
            showSuccess("Patient added successfully.");
        });

        dialog.showAndWait();
        showPatients();
    }

    // MEDICAL
	private void showMedical() {
        VBox page = new VBox(18);

        page.setPadding(new Insets(30, 40, 30, 40));

        page.getChildren().add(
                createPageHeader(
                        "Medical",
                        "View and manage medical supplies."
                )
        );

        TableView<Medical> table = new TableView<>();
        styleTable(table);

        TableColumn<Medical, String> name =
                new TableColumn<>("Medical Name");

        name.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getName())
        );

        TableColumn<Medical, String> manufacturer =
                new TableColumn<>("Manufacturer");

        manufacturer.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getManufacturer())
        );

        TableColumn<Medical, String> expiry =
                new TableColumn<>("Expiry Date");

        expiry.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getExpiryDate())
        );

        TableColumn<Medical, String> cost =
                new TableColumn<>("Cost (RM)");

        cost.setCellValueFactory(data ->
                new SimpleStringProperty(
                        String.valueOf(data.getValue().getCost())
                )
        );

        TableColumn<Medical, String> count =
                new TableColumn<>("Quantity");

        count.setCellValueFactory(data ->
                new SimpleStringProperty(
                        String.valueOf(data.getValue().getCount())
                )
        );

        table.getColumns().addAll(
                name,
                manufacturer,
                expiry,
                cost,
                count
        );

        table.setItems(
                FXCollections.observableArrayList(
                        hospital.getMedicals()
                )
        );

        Button add = createPrimaryButton("+  Add New Medical");
        add.setOnAction(event -> showAddMedical());

        HBox actionBar = new HBox(add);
        actionBar.setAlignment(Pos.CENTER_RIGHT);

        page.getChildren().addAll(
                table,
                actionBar,
                createNavigationBar()
        );

        VBox.setVgrow(table, Priority.ALWAYS);

        root.setCenter(page);
    }


    private void showAddMedical() {
        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.setTitle("Add New Medical");
        dialog.setHeaderText("Enter Medical Information");

        GridPane form = createFormGrid();

        TextField name = createTextField("Medical Name");
        TextField manufacturer = createTextField("Manufacturer");
        TextField expiry = createTextField("Expiry Date");
        TextField cost = createTextField("Cost");
        TextField count = createTextField("Quantity");

        addFormRow(form, "Name:", name, 0);
        addFormRow(form, "Manufacturer:", manufacturer, 1);
        addFormRow(form, "Expiry Date:", expiry, 2);
        addFormRow(form, "Cost (RM):", cost, 3);
        addFormRow(form, "Quantity:", count, 4);

        dialog.getDialogPane().setContent(form);

        ButtonType save = new ButtonType(
                "Save Medical",
                ButtonBar.ButtonData.OK_DONE
        );

        ButtonType cancel = new ButtonType(
                "Cancel",
                ButtonBar.ButtonData.CANCEL_CLOSE
        );

        dialog.getDialogPane().getButtonTypes().addAll(
                save,
                cancel
        );

        Button saveButton =
                (Button) dialog.getDialogPane().lookupButton(save);

        saveButton.setOnAction(event -> {
            if (!validateTextFields(
                    name,
                    manufacturer,
                    expiry,
                    cost,
                    count
            )) {
                event.consume();
                showError("Please fill in all fields.");
                return;
            }

            if (!InputValidator.isPositiveInteger(cost.getText())
                    || !InputValidator.isPositiveInteger(count.getText())) {
                event.consume();
                showError(
                        "Cost and quantity must be positive integers."
                );
                return;
            }

            int costValue = Integer.parseInt(cost.getText().trim());
            int countValue = Integer.parseInt(count.getText().trim());

            Medical medical = new Medical(
                    name.getText().trim(),
                    manufacturer.getText().trim(),
                    expiry.getText().trim(),
                    costValue,
                    countValue
            );

            hospital.addMedical(medical);
            showSuccess("Medical record added successfully.");
        });

        dialog.showAndWait();
        showMedical();
    }

    // Laboratories
	private void showLaboratories() {
        VBox page = new VBox(18);

        page.setPadding(new Insets(30, 40, 30, 40));

        page.getChildren().add(
                createPageHeader(
                        "Laboratories",
                        "View and manage laboratory services."
                )
        );

        TableView<Lab> table = new TableView<>();
        styleTable(table);

        TableColumn<Lab, String> laboratory =
                new TableColumn<>("Laboratory");

        laboratory.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getLab())
        );

        TableColumn<Lab, String> cost =
                new TableColumn<>("Cost (RM)");

        cost.setCellValueFactory(data ->
                new SimpleStringProperty(
                        String.valueOf(data.getValue().getCost())
                )
        );

        table.getColumns().addAll(laboratory,cost);

        table.setItems(
                FXCollections.observableArrayList(
                        hospital.getLaboratories()
                )
        );

        Button add = createPrimaryButton(
                "+  Add New Laboratory"
        );

        add.setOnAction(event -> showAddLaboratory());

        HBox actionBar = new HBox(add);
        actionBar.setAlignment(Pos.CENTER_RIGHT);

        page.getChildren().addAll(
                table,
                actionBar,
                createNavigationBar()
        );

        VBox.setVgrow(table, Priority.ALWAYS);

        root.setCenter(page);
    }


    private void showAddLaboratory() {
        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.setTitle("Add New Laboratory");
        dialog.setHeaderText("Enter Laboratory Information");

        GridPane form = createFormGrid();

        TextField laboratory =
                createTextField("Laboratory Name");

        TextField cost = createTextField("Cost");

        addFormRow(form, "Laboratory:", laboratory, 0);
        addFormRow(form, "Cost (RM):", cost, 1);

        dialog.getDialogPane().setContent(form);

        ButtonType save = new ButtonType(
                "Save Laboratory",
                ButtonBar.ButtonData.OK_DONE
        );

        ButtonType cancel = new ButtonType(
                "Cancel",
                ButtonBar.ButtonData.CANCEL_CLOSE
        );

        dialog.getDialogPane().getButtonTypes().addAll(
                save,
                cancel
        );

        Button saveButton =
                (Button) dialog.getDialogPane().lookupButton(save);

        saveButton.setOnAction(event -> {
            if (!validateTextFields(laboratory, cost)) {
                event.consume();
                showError("Please fill in all fields.");
                return;
            }

            if (!InputValidator.isPositiveInteger(cost.getText())) {
                event.consume();
                showError("Cost must be a positive integer.");
                return;
            }

            int costValue = Integer.parseInt(cost.getText().trim());

            Lab lab = new Lab(
                    laboratory.getText().trim(),
                    costValue
            );

            hospital.addLaboratory(lab);
            showSuccess("Laboratory added successfully.");
        });

        dialog.showAndWait();
        showLaboratories();
    }

    // Facilities
    private void showFacilities() {
        VBox page = new VBox(18);

        page.setPadding(new Insets(30, 40, 30, 40));

        page.getChildren().add(
                createPageHeader(
                        "Facilities",
                        "View hospital facilities and ambulance services."
                )
        );

        TableView<Facility> table = new TableView<>();
        styleTable(table);

        TableColumn<Facility, String> facility =
                new TableColumn<>("Facility");

        facility.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getFacility())
        );

        table.getColumns().add(facility);

        table.setItems(
                FXCollections.observableArrayList(
                        hospital.getFacilities()
                )
        );


        // Facility buttons
        Button addFacility =
                createPrimaryButton("+  Add Facility");

        addFacility.setOnAction(
                event -> showAddFacility()
        );


        Button ambulance =
                createAmbulanceButton("🚑  Ambulance Service");

        ambulance.setOnAction(
                event -> showAmbulances()
        );


        HBox actionBar = new HBox(12);
        actionBar.setAlignment(Pos.CENTER_RIGHT);

        actionBar.getChildren().addAll(
                ambulance,
                addFacility
        );


        page.getChildren().addAll(
                table,
                actionBar,
                createNavigationBar()
        );

        VBox.setVgrow(table, Priority.ALWAYS);

        root.setCenter(page);
    }

    // Add Facility
    private void showAddFacility() {
        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.setTitle("Add New Facility");
        dialog.setHeaderText("Enter Facility Information");

        GridPane form = createFormGrid();

        TextField facility =
                createTextField("Facility Name");

        addFormRow(form, "Facility:", facility, 0);

        dialog.getDialogPane().setContent(form);

        ButtonType save = new ButtonType(
                "Save Facility",
                ButtonBar.ButtonData.OK_DONE
        );

        ButtonType cancel = new ButtonType(
                "Cancel",
                ButtonBar.ButtonData.CANCEL_CLOSE
        );

        dialog.getDialogPane().getButtonTypes().addAll(
                save,
                cancel
        );

        Button saveButton =
                (Button) dialog.getDialogPane().lookupButton(save);

        saveButton.setOnAction(event -> {
            if (!validateTextFields(facility)) {
                event.consume();
                showError("Please enter a facility name.");
                return;
            }

            Facility newFacility =
                    new Facility(facility.getText().trim());

            hospital.addFacility(newFacility);
            showSuccess("Facility added successfully.");
        });

        dialog.showAndWait();
        showFacilities();
    }

    // Ambulance Service
	private void showAmbulances() {
        VBox page = new VBox(18);

        page.setPadding(new Insets(30, 40, 30, 40));

        page.getChildren().add(
                createPageHeader(
                        "Ambulance Service",
                        "View and manage hospital ambulance vehicles."
                )
        );


        TableView<Ambulance> table =
                new TableView<>();

        styleTable(table);


        TableColumn<Ambulance, String> id =
                new TableColumn<>("ID");

        id.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getId()
                )
        );


        TableColumn<Ambulance, String> name =
                new TableColumn<>("Ambulance Name");

        name.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getName()
                )
        );


        TableColumn<Ambulance, String> vehicle =
                new TableColumn<>("Vehicle No.");

        vehicle.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getVehicleNo()
                )
        );


        TableColumn<Ambulance, String> capacity =
                new TableColumn<>("Capacity");

        capacity.setCellValueFactory(data ->
                new SimpleStringProperty(
                        String.valueOf(
                                data.getValue().getCapacity()
                        )
                )
        );


        table.getColumns().addAll(
                id,
                name,
                vehicle,
                capacity
        );


        table.setItems(
                FXCollections.observableArrayList(
                        hospital.getAmbulances()
                )
        );


        Button add =
                createPrimaryButton("+  Add New Ambulance");

        add.setOnAction(
                event -> showAddAmbulance()
        );


        HBox actionBar = new HBox(add);
        actionBar.setAlignment(Pos.CENTER_RIGHT);


        page.getChildren().addAll(
                table,
                actionBar,
                createAmbulanceNavigationBar()
        );


        VBox.setVgrow(
                table,
                Priority.ALWAYS
        );

        root.setCenter(page);
    }

    // Add Ambulance
    private void showAddAmbulance() {
        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.setTitle("Add New Ambulance");
        dialog.setHeaderText("Enter Ambulance Information");

        GridPane form = createFormGrid();

        TextField id =
                createTextField("Ambulance ID");

        TextField name =
                createTextField("Ambulance Name");

        TextField vehicleNo =
                createTextField("Vehicle Number");

        TextField capacity =
                createTextField("Capacity");


        addFormRow(form, "ID:", id, 0);
        addFormRow(form, "Name:", name, 1);
        addFormRow(form, "Vehicle No.:", vehicleNo, 2);
        addFormRow(form, "Capacity:", capacity, 3);


        dialog.getDialogPane().setContent(form);


        ButtonType save = new ButtonType(
                "Save Ambulance",
                ButtonBar.ButtonData.OK_DONE
        );

        ButtonType cancel = new ButtonType(
                "Cancel",
                ButtonBar.ButtonData.CANCEL_CLOSE
        );


        dialog.getDialogPane().getButtonTypes().addAll(
                save,
                cancel
        );


        Button saveButton =
                (Button) dialog.getDialogPane().lookupButton(save);


        saveButton.setOnAction(event -> {
            if (!validateTextFields(
                    id,
                    name,
                    vehicleNo,
                    capacity
            )) {
                event.consume();
                showError("Please fill in all fields.");
                return;
            }


            if (!InputValidator.isPositiveInteger(capacity.getText())) {
                event.consume();
                showError(
                        "Capacity must be a positive integer."
                );
                return;
            }

            int capacityValue = Integer.parseInt(capacity.getText().trim());

            ArrayList<String> existingIds = new ArrayList<>();
            for (Ambulance a : hospital.getAmbulances()) {
                existingIds.add(a.getId());
            }

            if (!InputValidator.isUniqueId(id.getText().trim(), existingIds)) {
                event.consume();
                showError(
                        "An ambulance with this ID already exists."
                );
                return;
            }


            Ambulance ambulance = new Ambulance(
                    id.getText().trim(),
                    name.getText().trim(),
                    vehicleNo.getText().trim(),
                    capacityValue
            );


            hospital.addAmbulance(ambulance);

            showSuccess(
                    "Ambulance added successfully."
            );
        });


        dialog.showAndWait();

        showAmbulances();
    }

    // Staff
	private void showStaff() {
        VBox page = new VBox(18);

        page.setPadding(new Insets(30, 40, 30, 40));

        page.getChildren().add(
                createPageHeader(
                        "Staff",
                        "View and manage hospital staff."
                )
        );

        TableView<Staff> table = new TableView<>();
        styleTable(table);

        TableColumn<Staff, String> id =
                new TableColumn<>("ID");

        id.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getId()
                )
        );

        TableColumn<Staff, String> name =
                new TableColumn<>("Name");

        name.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getName()
                )
        );

        TableColumn<Staff, String> designation =
                new TableColumn<>("Designation");

        designation.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getDesignation()
                )
        );

        TableColumn<Staff, String> sex =
                new TableColumn<>("Sex");

        sex.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getSex()
                )
        );

        TableColumn<Staff, String> salary =
                new TableColumn<>("Salary (RM)");

        salary.setCellValueFactory(data ->
                new SimpleStringProperty(
                        String.valueOf(
                                data.getValue().getSalary()
                        )
                )
        );

        table.getColumns().addAll(
                id,
                name,
                designation,
                sex,
                salary
        );

        table.setItems(
                FXCollections.observableArrayList(
                        hospital.getStaffs()
                )
        );

        Button add =
                createPrimaryButton("+  Add New Staff");

        add.setOnAction(
                event -> showAddStaff()
        );

        HBox actionBar = new HBox(add);
        actionBar.setAlignment(Pos.CENTER_RIGHT);

        page.getChildren().addAll(
                table,
                actionBar,
                createNavigationBar()
        );

        VBox.setVgrow(table, Priority.ALWAYS);

        root.setCenter(page);
    }

    // Add Staff
    private void showAddStaff() {
        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.setTitle("Add New Staff");
        dialog.setHeaderText("Enter Staff Information");

        GridPane form = createFormGrid();

        TextField id = createTextField("Staff ID");
        TextField name = createTextField("Staff Name");
        TextField designation = createTextField("Designation");

        ComboBox<String> sex = new ComboBox<>();

        sex.getItems().addAll(
                "Male",
                "Female"
        );

        sex.setPromptText("Select Sex");
        sex.setPrefWidth(300);

        TextField salary =
                createTextField("Salary");

        addFormRow(form, "ID:", id, 0);
        addFormRow(form, "Name:", name, 1);
        addFormRow(form, "Designation:", designation, 2);
        addFormRow(form, "Sex:", sex, 3);
        addFormRow(form, "Salary (RM):", salary, 4);

        dialog.getDialogPane().setContent(form);

        ButtonType save = new ButtonType(
                "Save Staff",
                ButtonBar.ButtonData.OK_DONE
        );

        ButtonType cancel = new ButtonType(
                "Cancel",
                ButtonBar.ButtonData.CANCEL_CLOSE
        );

        dialog.getDialogPane().getButtonTypes().addAll(
                save,
                cancel
        );

        Button saveButton =
                (Button) dialog.getDialogPane().lookupButton(save);

        saveButton.setOnAction(event -> {
            if (!validateTextFields(id, name, designation, salary)
                    || sex.getValue() == null) {

                event.consume();
                showError("Please fill in all fields.");
                return;
            }

            if (!InputValidator.isPositiveInteger(salary.getText())) {
                event.consume();
                showError(
                        "Salary must be a positive integer."
                );
                return;
            }

            int salaryValue = Integer.parseInt(salary.getText().trim());

            ArrayList<String> existingIds = new ArrayList<>();
            for (Staff s : hospital.getStaffs()) {
                existingIds.add(s.getId());
            }

            if (!InputValidator.isUniqueId(id.getText().trim(), existingIds)) {
                event.consume();
                showError(
                        "A staff member with this ID already exists."
                );
                return;
            }

            Staff staff = new Staff(
                    id.getText().trim(),
                    name.getText().trim(),
                    designation.getText().trim(),
                    sex.getValue(),
                    salaryValue
            );

            hospital.addStaff(staff);
            showSuccess(
                    "Staff member added successfully."
            );
        });

        dialog.showAndWait();
        showStaff();
    }

    // Table Styling
    private void styleTable(TableView<?> table) {
        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setFixedCellSize(47);

        table.setStyle(
                "-fx-background-color: " + WHITE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12;" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 5;"
        );

        DropShadow tableShadow = new DropShadow();
        tableShadow.setColor(Color.web("#0A2E38", 0.06));
        tableShadow.setRadius(16);
        tableShadow.setOffsetY(4);
        table.setEffect(tableShadow);

        table.skinProperty().addListener(
                (obs, oldSkin, newSkin) -> {
                    table.lookupAll(".column-header").forEach(node ->
                            node.setStyle(
                                    "-fx-background-color: " + PRIMARY_DARK + ";" +
                                    "-fx-border-color: " + PRIMARY_DARK + ";"
                            )
                    );

                    table.lookupAll(".column-header .label")
                            .forEach(node ->
                                    ((Label) node).setTextFill(
                                            Color.WHITE
                                    )
                            );
                }
        );
    }

    // FORM HELPERS
    private GridPane createFormGrid() {
        GridPane grid = new GridPane();

        grid.setHgap(15);
        grid.setVgap(14);
        grid.setPadding(new Insets(15));

        return grid;
    }


    private TextField createTextField(String prompt) {
        TextField field = new TextField();

        field.setPromptText(prompt);
        field.setPrefWidth(300);
        field.setPrefHeight(38);

        field.setStyle(
                "-fx-background-radius: 7;" +
                "-fx-border-radius: 7;" +
                "-fx-border-color: " + BORDER + ";"
        );

        field.focusedProperty().addListener((obs, wasFocused, isFocused) ->
                field.setStyle(
                        "-fx-background-radius: 7;" +
                        "-fx-border-radius: 7;" +
                        "-fx-border-color: " + (isFocused ? PRIMARY : BORDER) + ";" +
                        (isFocused ? "-fx-border-width: 1.5;" : "")
                )
        );

        return field;
    }


    private void addFormRow(
            GridPane grid,
            String labelText,
            Control control,
            int row
    ) {
        Label label = new Label(labelText);

        label.setFont(
                Font.font(
                        BODY_FONT,
                        FontWeight.BOLD,
                        12
                )
        );

        label.setTextFill(Color.web(DARK_TEXT));

        grid.add(label, 0, row);
        grid.add(control, 1, row);
    }


    // Validate that every given text field is non-empty.
    private boolean validateTextFields(
            TextField... fields
    ) {
        for (TextField field : fields) {
            if (!InputValidator.isNonEmpty(field.getText())) {
                return false;
            }
        }

        return true;
    }

    // Buttons
    private Button createPrimaryButton(String text) {
        Button button = new Button(text);

        button.setPrefHeight(45);
        button.setMinWidth(175);

        button.setFont(
                Font.font(
                        BODY_FONT,
                        FontWeight.BOLD,
                        13
                )
        );

        button.setTextFill(Color.WHITE);

        button.setStyle(
                "-fx-background-color: " + PRIMARY + ";" +
                "-fx-background-radius: 9;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(event ->
                button.setStyle(
                        "-fx-background-color: " + PRIMARY_DARK + ";" +
                        "-fx-background-radius: 9;" +
                        "-fx-cursor: hand;"
                )
        );

        button.setOnMouseExited(event ->
                button.setStyle(
                        "-fx-background-color: " + PRIMARY + ";" +
                        "-fx-background-radius: 9;" +
                        "-fx-cursor: hand;"
                )
        );

        return button;
    }


    private Button createAmbulanceButton(String text) {
        Button button = new Button(text);

        button.setPrefHeight(45);
        button.setMinWidth(200);

        button.setFont(
                Font.font(
                        BODY_FONT,
                        FontWeight.BOLD,
                        13
                )
        );

        button.setTextFill(Color.web(PRIMARY_DARK));

        button.setStyle(
                "-fx-background-color: " + PRIMARY_LIGHT + ";" +
                "-fx-background-radius: 9;" +
                "-fx-border-color: " + CYAN_2 + ";" +
                "-fx-border-radius: 9;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(event ->
                button.setStyle(
                        "-fx-background-color: " + CYAN_1 + ";" +
                        "-fx-background-radius: 9;" +
                        "-fx-border-color: " + PRIMARY + ";" +
                        "-fx-border-radius: 9;" +
                        "-fx-cursor: hand;"
                )
        );

        button.setOnMouseExited(event ->
                button.setStyle(
                        "-fx-background-color: " + PRIMARY_LIGHT + ";" +
                        "-fx-background-radius: 9;" +
                        "-fx-border-color: " + CYAN_2 + ";" +
                        "-fx-border-radius: 9;" +
                        "-fx-cursor: hand;"
                )
        );

        return button;
    }

    // Alerts
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Input Error");
        alert.setHeaderText("Unable to save record");
        alert.setContentText(message);

        alert.showAndWait();
    }


    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }


    private boolean confirmMainMenu() {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION
        );

        alert.setTitle("Return to Main Menu");
        alert.setHeaderText("Return to Main Menu?");
        alert.setContentText(
                "Are you sure you want to return to the main dashboard?"
        );

        ButtonType yes = new ButtonType(
                "Yes",
                ButtonBar.ButtonData.YES
        );

        ButtonType no = new ButtonType(
                "Stay Here",
                ButtonBar.ButtonData.NO
        );

        alert.getButtonTypes().setAll(
                yes,
                no
        );

        return alert.showAndWait().orElse(no) == yes;
    }

    // Main
    public static void main(String[] args) {
        launch(args);
    }
}
