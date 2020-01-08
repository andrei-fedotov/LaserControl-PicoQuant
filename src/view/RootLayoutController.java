package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import model.Laser;
import util.ComPort;
import util.CommandHandler;
import util.LaserStateService;
import util.Logger;

import java.util.Optional;


public class RootLayoutController {

    // Control panel block
    @FXML
    public ChoiceBox chooseSerialPort;                      // todo: change public to private
    private ObservableList<String> serialPortList;          // todo: add FXCollections.observableArrayList from getPortList
    @FXML
    public TextField textFieldRepRate;
    @FXML
    public ChoiceBox choosePrefix;
    private ObservableList<String> prefixList = FXCollections.observableArrayList("Hz", "kHz", "MHz");
    @FXML
    public TextField textFieldTemperature;
    @FXML
    private ChoiceBox chooseTrigger;
    private ObservableList<String> triggerList = FXCollections.observableArrayList("EXT", "INT");
    @FXML
    public ToggleButton indicator;

    // System info block
    @FXML
    private Text textHardwareVersion;
    @FXML
    private Text textSerialNumber;
    @FXML
    private Text textSerialNumberOfSeedDiode;
    @FXML
    private Text textSerialNumberOfPumpDiode;
    @FXML
    private Text textProductionDate;
    @FXML
    private Text textFirmwareVersion;

    // System block
    @FXML
    private Text textState;
    @FXML
    private Text textSysHours;
    @FXML
    private Text textTempSystem;
    @FXML
    private Text textVCCIN;
    @FXML
    private Text textCURIN;
    @FXML
    private Text textPWRIN;

    // Seed block
    @FXML
    private Text textVoltageSeed;
    @FXML
    private Text textVoltageBoost;
    @FXML
    private Text textVoltageReg;
    @FXML
    private Text textVoltageNBA;
    @FXML
    private Text textVoltageNBB;
    @FXML
    private Text textTempSeed;

    // Set values block
    @FXML
    private Text textNBA;
    @FXML
    private Text textNBB;
    @FXML
    private Text textSMF;
    @FXML
    private Text textSEF;
    @FXML
    private Text textSEV;
    @FXML
    private Text textSEC;

    // Trigger block
    @FXML
    private Text textTriggerSource;
    @FXML
    private Text textTriggerFrequencyEXT;
    @FXML
    private Text textTriggerFrequencyINT;

    // Command block
    @FXML
    private TextField textFieldCommand;
    @FXML
    private Button buttonCommandHelp;
    @FXML
    private Label labelHelp;
    @FXML
    private TextArea textAreaTerminal;



    private Laser laser = new Laser();
    private ComPort comPort = new ComPort();
    private CommandHandler commandHandler = new CommandHandler(laser, comPort);

    private Logger logger = new Logger(this.laser);
    private LaserStateService laserStateService = new LaserStateService(this, logger);



    @FXML
    public void initialize() {
        serialPortList = FXCollections.observableArrayList(comPort.getPortsList());
        chooseSerialPort.setItems(serialPortList);      // todo: why IDE highlight chooseSerialPort and chooseTrigger
        chooseSerialPort.getSelectionModel().selectedIndexProperty().addListener((v, oldValue, newValue) -> setPortAndStart(newValue));

        // numeric only in the TextField
        textFieldTemperature.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            textFieldTemperature.setText(newValue.replaceAll("[^\\d]", ""));
        });
        textFieldTemperature.setOnAction(e -> changeSeedTemperature());

        // numeric only in the TextField
        textFieldRepRate.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            textFieldRepRate.setText(newValue.replaceAll("[^\\d]", ""));
        });
        textFieldRepRate.setOnAction(e -> changeRepRate());

        choosePrefix.setItems(prefixList);
        choosePrefix.setValue(prefixList.get(0));
        chooseTrigger.setItems(triggerList);

        textFieldRepRate.setDisable(true);
        choosePrefix.setDisable(true);
        textFieldTemperature.setDisable(true);
        chooseTrigger.setDisable(true);                 // deactivate trigger ChoiceBox by default
        indicator.setDisable(true);                     // deactivate indicator
        textFieldCommand.setDisable(true);
        textAreaTerminal.setDisable(true);

        textFieldCommand.setOnAction(e -> handleCommandViaTerminal());         // add text to terminal by pressing Enter on a keyboard
    }



    private void setPortAndStart(Number value) {
        String serialPortName = serialPortList.get(value.intValue());
        this.comPort.choosePort(serialPortName);        // todo: would be better if listener returns string, not Number
                                                        //        this.comPort.setPortParameters();
        this.comPort.openPort();

        // Activate interface elements
        textFieldRepRate.setDisable(false);
        choosePrefix.setDisable(false);
        textFieldTemperature.setDisable(false);
        chooseTrigger.setDisable(false);
        indicator.setDisable(false);
        textFieldCommand.setDisable(false);
        textAreaTerminal.setDisable(false);

        // Get info about trigger and set type of trigger in ChoiceBox
        this.commandHandler.handleCommandTriggerSource();
        System.out.println(this.laser.getTriggerSource());
        chooseTrigger.setValue(this.laser.getTriggerSource());
        chooseTrigger.getSelectionModel().selectedIndexProperty().addListener((v, oldValue, newValue) -> changeTrigger(newValue));

        this.startThread();
    }



    public void startThread() {
        Thread thread = new Thread(laserStateService.createTask());
        thread.start();
        System.out.println("thread started");
    }



    public void stopThread() {
        this.laserStateService.finish();
        System.out.println("thread stopped");
        System.out.println("Pause 1 sec...");
        try {
            Thread.sleep(1000);                 // pause to be sure that thread was stopped
        } catch(InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("1 sec gone");
    }



    private void fillAllParameters() {
        // System info block
        textHardwareVersion.setText(this.laser.getHardwareVersion());
        textSerialNumber.setText(this.laser.getSerialNumber());
        textSerialNumberOfSeedDiode.setText(this.laser.getSerialNumberOfSeedDiode());
        textSerialNumberOfPumpDiode.setText(this.laser.getSerialNumberOfPumpDiode());
        textProductionDate.setText(this.laser.getProductionDate());
        textFirmwareVersion.setText(this.laser.getFirmwareVersion());
        // System block
        textState.setText(this.laser.getState());
        textSysHours.setText(this.laser.getSysHours());
        textTempSystem.setText(this.laser.getTempSystem());
        textVCCIN.setText(this.laser.getVoltageVCCIN());
        textCURIN.setText(this.laser.getCurrentCURIN());
        textPWRIN.setText(this.laser.getPowerPWRIN());
        // Seed block
        textVoltageSeed.setText(this.laser.getVoltageSeed());
        textVoltageBoost.setText(this.laser.getVoltageBoost());
        textVoltageReg.setText(this.laser.getVoltageReg());
        textVoltageNBA.setText(this.laser.getVoltageNBA());
        textVoltageNBB.setText(this.laser.getVoltageNBB());
        textTempSeed.setText(this.laser.getTempSeed());
        // Set values block
        textNBA.setText(this.laser.getNBA());
        textNBB.setText(this.laser.getNBB());
        textSMF.setText(this.laser.getSMF());
        textSEF.setText(this.laser.getSEF());
        textSEV.setText(this.laser.getSEV());
        textSEC.setText(this.laser.getSEC());
        // Trigger block
        textTriggerSource.setText(this.laser.getTriggerSource());
        textTriggerFrequencyEXT.setText(this.laser.getTriggerFrequencyEXT());
        textTriggerFrequencyINT.setText(this.laser.getTriggerFrequencyINT());
    }



    public void displayLaserInfo() {
        System.out.println("displayLaserInfo start");
        System.out.println(" this.commandHandler.handleCommandReport();");
        this.commandHandler.handleCommandReport();
        System.out.println(" this.commandHandler.setAllLaserParametersFromReport();");
        this.commandHandler.setAllLaserParametersFromReport();
        this.fillAllParameters();

        switch (this.laser.getState()) {
            case "IDLE":
                textState.setFill(Color.GREY);
                textState.setStyle("-fx-font-weight: bold");
                indicator.setStyle("-fx-background-color:grey; -fx-background-radius: 20px;");
                break;
            case "WORKING":
                textState.setFill(Color.LIGHTGREEN);
                textState.setStyle("-fx-font-weight: bold");
                indicator.setStyle("-fx-background-color:lightgreen; -fx-background-radius: 20px;");
                break;
            case "ERROR":
                textState.setFill(Color.RED);
                textState.setStyle("-fx-font-weight: bold");
                indicator.setStyle("-fx-background-color:red; -fx-background-radius: 20px;");
                // todo: add error sound
                break;
            case "BUSY":
                textState.setFill(Color.BLUE);
                textState.setStyle("-fx-font-weight: bold");
                indicator.setStyle("-fx-background-color:blue; -fx-background-radius: 20px;");
                break;
        }
    }



    private void changeSeedTemperature() {
        stopThread();

        String toTerminal;
        int temp;

        temp = Integer.parseInt(textFieldTemperature.getText());
        textFieldTemperature.clear();
        toTerminal = this.commandHandler.handleCommandSetTempSeed(temp);
        textAreaTerminal.appendText(toTerminal);

        startThread();
    }



    private void changeRepRate() {
        stopThread();

        String toTerminal;
        int freq;
        String prefix;

        freq = Integer.parseInt(textFieldRepRate.getText());
        textFieldRepRate.clear();
        prefix = choosePrefix.getSelectionModel().getSelectedItem().toString();
        switch (prefix) {
            case "Hz":
                freq = freq;
                break;
            case "kHz":
                freq = freq * 1000;
                break;
            case "MHz":
                freq = freq * 1000000;
                break;
        }

        toTerminal = this.commandHandler.handleSetCommandSetTriggerFrequencyINT(freq);
        textAreaTerminal.appendText(toTerminal);

        startThread();
    }



    private void changeTrigger(Number value) {
        stopThread();

        String toTerminal;
        switch (value.intValue()) {
            case 0:
                System.out.println("changeTrigger" + triggerList.get(0));
                toTerminal = this.commandHandler.handleSetCommandTriggerSource(triggerList.get(0));
                textAreaTerminal.appendText(toTerminal);
                break;
            case 1:
                System.out.println("changeTrigger" + triggerList.get(1));
                toTerminal = this.commandHandler.handleSetCommandTriggerSource(triggerList.get(1));
                textAreaTerminal.appendText(toTerminal);
                break;
        }

        startThread();
    }



    private void handleCommandViaTerminal() {
        stopThread();

        if ((textFieldCommand.getText() != null) && (textFieldCommand.getText().trim().length() != 0)) {
            String command = textFieldCommand.getText().trim().toUpperCase();
            textFieldCommand.clear();

            String commandAndResponse = this.commandHandler.handleEnteredCommand(command);
            textAreaTerminal.appendText(commandAndResponse);
        }

        startThread();
    }



    public void workingLaserAlert(Event event) {
        if (this.laser.getState().equals("WORKING")) {
            Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
            Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(ButtonType.OK);
            exitButton.setText("Exit");
            closeConfirmation.setHeaderText(null);
            closeConfirmation.setContentText("Are you sure you want to exit?\nThe application will be closed, but the laser will continue to operate.");
            closeConfirmation.initModality(Modality.APPLICATION_MODAL);
            // closeConfirmation.initOwner(primaryStage);

            Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
            if (!ButtonType.OK.equals(closeResponse.get())) {
                event.consume();
            } else {

                stopThread();
                closePortAtExit();                                      // laser is on: close the port only if "Exit" button was clicked
            }
        } else {
            System.out.println(laserStateService.getState());
            stopThread();
            closePortAtExit();                                          // laser is off: just close the port
        }
    }



    public void closePortAtExit() {
        if ((this.comPort.getPort() != null) && this.comPort.isOpened()) {      // check that the port exists and is open, otherwise you will get NPE
            this.comPort.closePort();
            System.out.println("Port closed");
        } else {
            System.out.println("Port closure: nothing to close");                             // todo: delete it later
        }
    }



    public void showCommandHelp() {
        CommandHelp.display();
    }

    public void showAboutWindow() {
        AboutWindow.display();
    }

}
