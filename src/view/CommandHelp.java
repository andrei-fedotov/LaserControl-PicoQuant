package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class CommandHelp {

    public static void display() {
        Stage window = new Stage();

        //Block events to other windows
        window.setTitle("List of commands");

        Text text = new Text();
        text.setText("SYS:REP?\t\t\t\t\tPrint report once\n" +
                "SYS:RES!\t\t\t\t\tReset System\n" +
                "SYS:ERR!\t\t\t\t\tChange to error state\n" +
                "SYS:STAT?\t\t\t\tPrint current state\n" +
                "SYS:DATE?\t\t\t\tPrint production date\n" +
                "SYS:HW?\t\t\t\t\tPrint hardware revision\n" +
                "SYS:SN?\t\t\t\t\tPrint serial number\n" +
                "SYS:SSN?\t\t\t\t\tPrint serial number of seed diode\n" +
                "SYS:PSN?\t\t\t\t\tPrint serial number of pump diode\n" +
                "SYS:FW?\t\t\t\t\tPrint firmware version  \n" +
                "SYS:HOUR?\t\t\t\tPrint total uptime\n" +
                "MEAS:VOLT:VCC?\t\t\tVoltage external power supply in mV\n" +
                "MEAS:VOLT:SEE?\t\t\tMain power supply seed in mV\n" +
                "MEAS:VOLT:BOO?\t\t\tPower supply for first amplifier stage in mV\n" +
                "MEAS:VOLT:REG?\t\t\tPower supply for second amplifier stage in mV\n" +
                "MEAS:VOLT:PUM?\t\t\tMain power supply pump in mV\n" +
                "MEAS:VOLT:MON?\t\t\tVoltage at monitor diode in mV\n" +
                "MEAS:VOLT:NBA?\t\t\tNegative bias voltage at first amplifier stage\n" +
                "MEAS:VOLT:NBB?\t\t\tNegative bias voltage at second amplifier stage\n" +
                "MEAS:TEMP:SYS?\t\t\tSystem temperature in m°C\n" +
                "MEAS:TEMP:SEE?\t\t\tSeed temperature in m°C\n" +
                "MEAS:TEMP:PUM?\t\t\tPrint stored pump temperature in m°C\n" +
                "MEAS:CURR:PUM?\t\t\tPump current in mA\n" +
                "MEAS:CURR:SYS?\t\t\tCurrent drawn from external power supply in mA\n" +
                "MEAS:FREQ:TRIG?\t\t\tPrint measured (rough estimation) frequency from ext. trigger input\n" +
                "SOUR:VOLT:REG?\t\t\tPrint stored value for REG in mV\n" +
                "SOUR:VOLT:REG <val>\t\tSet value for REG in mV\n" +
                "SOUR:VOLT:VOF?\t\t\tPrint stored value for Offset in mV, this offset is added to REG every\n" +
                "\t\t\t\t\t\ttime a new frequency is set (INT mode), or continuously (EXT mode)\n" +
                "SOUR:VOLT:VOF <val>\t\tSet value for Offset in mV (max. 999 mV). Factory set to 500.\n" +
                "SOUR:TEMP:SEE?\t\t\tPrint stored seed temperature in m°C\n" +
                "SOUR:TEMP:SEE <val>\t\tSet seed temperature in m°C (max. 50000)\n" +
                "SOUR:TEMP:PUM?\t\t\tPrint stored pump temperature in m°C\n" +
                "SOUR:CURR:PUM?\t\t\tPrint stored pump current in %\n" +
                "SOUR:CURR:PUM <val>\t\tSet pump current in %\n" +
                "TRIG:SOUR?\t\t\t\tPrint stored frequency for internal source\n" +
                "TRIG:SOUR <val>\t\t\tSet frequency for internal source\n" +
                "TRIG:FREQ?\t\t\t\tPrint stored frequency for internal source\n" +
                "TRIG:FREQ <val>\t\t\tSet frequency for internal source");
        text.setStyle("-fx-font: 13 helvetica;");

        // Creating a Grid Pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 15, 10, 15));

        // Arranging all the nodes in the grid
        gridPane.add(text, 0, 0);


        // Creating a scene object
        Scene scene = new Scene(gridPane);

        //Display window and wait for it to be closed before returning
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();
    }

}