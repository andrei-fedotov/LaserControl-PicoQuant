package util;

import model.Laser;
import java.util.Arrays;

public class CommandHandler {

    private Laser laser;
    private ComPort comPort;


    private String[] arrayOfBusy = new String[67];


    public CommandHandler(Laser laser, ComPort comPort) {
        this.laser = laser;
        this.comPort = comPort;
    }


    private String handleCommand(String command) {
        this.comPort.sendToPort(command);
        System.out.println("   command was sent to port");
        String response = cleanResponse(this.comPort.readFromPort());
        if (response.length() > 20) {
            System.out.println("   handleCommand, response: REPORT TABLE");
        } else {
            System.out.println("   handleCommand, response: " + response);
        }

        return response;    // remove extra lines from a response and return it
        // todo: check if command and the first line of response are similar
    }


    // Remove extra lines from a command response
    private String cleanResponse(String responseValue) {
        String[] array = responseValue.trim().split("\n");      // trim() removes extra spaces, split() forms and array
        if (array[0].equals("SYS:REP?\r")) {                          // note! new line not \n, but \r
            return responseValue;
        } else {
            return array[1].trim();                                          // 1st line is command itself, 2nd returned value
        }
    }

    // Parsing "SYS:RER?" response
    private String[] reportParser() {
        String report = this.laser.getReport();
        String cleanedReport = report
                .replace("|","")                            // remove frame
                .replace(": ", ":\n")                       // move each value to a new line
                .replaceAll("(?m)^[ ]*\r?\n", "")           // remove empty lines, (?m) means multiline regime
                .replaceAll("[ ]+", " ")                    // remove double whitespaces
                .replaceAll("(?m)^[ ]+|[ ]+$", "")          // trim line
                .replace(" :", ":");                        // remove extra whitespace before colon

        // todo: remove it:
        int length = cleanedReport.split("\n").length;
        System.out.println(length);

        Arrays.fill(arrayOfBusy, "busy");
        arrayOfBusy[19] = "BUSY";
        
        if (length < 67) {
            return arrayOfBusy;
        } else {
            return cleanedReport.split("\n");
        }
        
    }

    // There are two options of getting laser parameters:
    //  * get all parameters from report "SYS:REP?" by calling function handleCommandReport() and then setAllLaserParametersFromReport(),
    //  * call function setAllLaserParameters() which calls all handleCommand...() functions.
    // The first one is faster, because only one command is sent and one response is received and then parsed. The second one is slower, since
    // there is a necessary timeout between sending and receiving (250 milliseconds for each command). However


    public void setAllLaserParametersFromReport() {
        String[] array = reportParser();
        // todo: uncomment and assign values
        this.laser.setState(array[19]);
        this.laser.setProductionDate(array[12]);
        this.laser.setHardwareVersion(array[4]);
        this.laser.setSerialNumber(array[6]);
        this.laser.setSerialNumberOfSeedDiode(array[8]);
        this.laser.setSerialNumberOfPumpDiode(array[10]);
        this.laser.setFirmwareVersion(array[14]);
        this.laser.setSysHours(array[23]);
        this.laser.setVoltageVCCIN(array[27]);
        this.laser.setVoltageSeed(array[41]);
        this.laser.setVoltageBoost(array[43]);
        this.laser.setVoltageReg(array[45]);
        this.laser.setVoltageNBA(array[47]);
        this.laser.setVoltageNBB(array[49]);
        this.laser.setTempSystem(array[25]);
        this.laser.setTempSeed(array[51]);
        this.laser.setCurrentCURIN(array[29]);
        this.laser.setTriggerSource(array[34]);
        this.laser.setTriggerFrequencyINT(array[38]);
        this.laser.setTriggerFrequencyEXT(array[36]);
        this.laser.setNBA(array[54]);
        this.laser.setNBB(array[56]);
        this.laser.setSMF(array[58]);
        this.laser.setSEF(array[60]);
        this.laser.setSEV(array[62]);
        this.laser.setSEC(array[64]);
        this.laser.setPowerPWRIN(array[31]);
        this.laser.setVoltagePump("--");
        this.laser.setVoltageMonitor("--");
        this.laser.setTempPump("--");
        this.laser.setCurrentPump("--");
        this.laser.setStoredREG("--");
        this.laser.setStoredVOF("--");
        this.laser.setStoredTempSeed("--");
        this.laser.setStoredTempPump("--");
        this.laser.setStoredCurrentPump("--");
    }

    public void setAllLaserParameters() {
        handleCommandReport();
        handleCommandState();
        handleCommandProductionDate();
        handleCommandHardwareVersion();
        handleCommandSerialNumber();
        handleCommandSerialNumberOfSeedDiode();
        handleCommandSerialNumberOfPumpDiode();
        handleCommandFirmwareVersion();
        handleCommandSysHours();
        handleCommandVoltageVCCIN();
        handleCommandVoltageSeed();
        handleCommandVoltageBoost();
        handleCommandVoltageReg();
        handleCommandVoltageNBA();
        handleCommandVoltageNBB();
        handleCommandTempSystem();
        handleCommandTempSeed();
        handleCommandCurrentCURIN();
        handleCommandTriggerFrequencyEXT();
        handleCommandTriggerSource();
        handleCommandTriggerFrequencyINT();
        handleCommandPowerPWRIN();
        handleCommandVoltagePump();
        handleCommandVoltageMonitor();
        handleCommandTempPump();
        handleCommandCurrentPump();
        handleCommandStoredREG();
        handleCommandStoredVOF();
        handleCommandStoredTempSeed();
        handleCommandStoredTempPump();
        handleCommandStoredCurrentPump();
    }

    public String handleEnteredCommand(String command) {
        String response = handleCommand(command);
        return command + "\n" + response + "\n\n";
    }

    public void handleCommandReport() {
        String command = this.laser.commandReport();
        System.out.println("  handleCommandReport, command: " + command);
        String response = handleCommand(command);
        if (response.length() > 20) {
            System.out.println("   handleCommand, response: REPORT TABLE");
        } else {
            System.out.println("   handleCommand, response: " + response);
        }
        this.laser.setReport(response);
        System.out.println("  this.laser.setReport(response);");
    }

    public void handleCommandState() {
        String command = this.laser.commandState();
        String response = handleCommand(command);
        this.laser.setState(response);
    }

    public void handleCommandProductionDate() {
        String command = this.laser.commandProductionDate();
        String response = handleCommand(command);
        this.laser.setProductionDate(response);
    }

    public void handleCommandHardwareVersion() {
        String command = this.laser.commandHardwareVersion();
        String response = handleCommand(command);
        this.laser.setHardwareVersion(response);
    }

    public void handleCommandSerialNumber() {
        String command = this.laser.commandSerialNumber();
        String response = handleCommand(command);
        this.laser.setSerialNumber(response);
    }

    public void handleCommandSerialNumberOfSeedDiode() {
        String command = this.laser.commandSerialNumberOfSeedDiode();
        String response = handleCommand(command);
        this.laser.setSerialNumberOfSeedDiode(response);
    }

    public void handleCommandSerialNumberOfPumpDiode() {
        String command = this.laser.commandSerialNumberOfPumpDiode();
        String response = handleCommand(command);
        this.laser.setSerialNumberOfPumpDiode(response);
    }

    public void handleCommandFirmwareVersion() {
        String command = this.laser.commandFirmwareVersion();
        String response = handleCommand(command);
        this.laser.setFirmwareVersion(response);
    }

    public void handleCommandSysHours() {
        String command = this.laser.commandSysHours();
        String response = handleCommand(command);
        this.laser.setSysHours(response);
    }

    public void handleCommandVoltageVCCIN() {
        String command = this.laser.commandVoltageVCCIN();
        String response = handleCommand(command);
        this.laser.setVoltageVCCIN(response);
    }

    public void handleCommandVoltageSeed() {
        String command = this.laser.commandVoltageSeed();
        String response = handleCommand(command);
        this.laser.setVoltageSeed(response);
    }

    public void handleCommandVoltageBoost() {
        String command = this.laser.commandVoltageBoost();
        String response = handleCommand(command);
        this.laser.setVoltageBoost(response);
    }

    public void handleCommandVoltageReg() {
        String command = this.laser.commandVoltageReg();
        String response = handleCommand(command);
        this.laser.setVoltageReg(response);
    }

    public void handleCommandVoltageNBA() {
        String command = this.laser.commandVoltageNBA();
        String response = handleCommand(command);
        this.laser.setVoltageNBA(response);
    }

    public void handleCommandVoltageNBB() {
        String command = this.laser.commandVoltageNBB();
        String response = handleCommand(command);
        this.laser.setVoltageNBB(response);
    }

    public void handleCommandTempSystem() {
        String command = this.laser.commandTempSystem();
        String response = handleCommand(command);
        this.laser.setTempSystem(response);
    }

    public void handleCommandTempSeed() {
        String command = this.laser.commandTempSeed();
        String response = handleCommand(command);
        this.laser.setTempSeed(response);
    }

    public void handleCommandCurrentCURIN() {
        String command = this.laser.commandCurrentCURIN();
        String response = handleCommand(command);
        this.laser.setCurrentCURIN(response);
    }

    public void handleCommandTriggerSource() {
        String command = this.laser.commandTriggerSource();
        String response = handleCommand(command);
        this.laser.setTriggerSource(response);
    }

    public String handleSetCommandTriggerSource(String value) {
        String command = this.laser.commandSetTriggerSource(value);
        String response = handleCommand(command);
        return command + "\n" + response + "\n\n";
    }

    public void handleCommandTriggerFrequencyINT() {
        String command = this.laser.commandTriggerFrequencyINT();
        String response = handleCommand(command);
        this.laser.setTriggerFrequencyINT(response);
    }

    public void handleCommandTriggerFrequencyEXT() {
        String command = this.laser.commandTriggerFrequencyEXT();
        String response = handleCommand(command);
        this.laser.setTriggerFrequencyEXT(response);
    }

    public String handleSetCommandSetTriggerFrequencyINT(int value) {
        String command = this.laser.commandSetTriggerFrequencyINT(value);
        String response = handleCommand(command);
        return command + "\n" + response + "\n\n";
    }

    public void handleCommandPowerPWRIN() {
        // voltageVCCIN or currentCURIN might not have been initialized => ERROR
        if ((this.laser.getVoltageVCCIN().equals("--")) || (this.laser.getCurrentCURIN().equals("--"))) {
            this.laser.setPowerPWRIN("--");
        } else {
            int VCCIN = Integer.parseInt(this.laser.getVoltageVCCIN().replaceAll("[^\\d]", "" ));
            int CURIN = Integer.parseInt(this.laser.getCurrentCURIN().replaceAll("[^\\d]", "" ));
            String PWRIN = VCCIN * CURIN / 1000 + "";
            this.laser.setPowerPWRIN(PWRIN);
        }
    }

    public void handleCommandVoltagePump() {
        String command = this.laser.commandVoltagePump();
        String response = handleCommand(command);
        this.laser.setVoltagePump(response);
    }

    public void handleCommandVoltageMonitor() {
        String command = this.laser.commandVoltageMonitor();
        String response = handleCommand(command);
        this.laser.setVoltageMonitor(response);
    }

    public void handleCommandTempPump() {
        String command = this.laser.commandTempPump();
        String response = handleCommand(command);
        this.laser.setTempPump(response);
    }

    public void handleCommandCurrentPump() {
        String command = this.laser.commandCurrentPump();
        String response = handleCommand(command);
        this.laser.setCurrentPump(response);
    }

    public void handleCommandStoredREG() {
        String command = this.laser.commandStoredREG();
        String response = handleCommand(command);
        this.laser.setStoredREG(response);
    }

    public void handleCommandStoredVOF() {
        String command = this.laser.commandStoredVOF();
        String response = handleCommand(command);
        this.laser.setStoredVOF(response);
    }

    public void handleCommandStoredTempSeed() {
        String command = this.laser.commandStoredTempSeed();
        String response = handleCommand(command);
        this.laser.setStoredTempSeed(response);
    }

    public String handleCommandSetTempSeed(int tempSeedValue){
        String command = this.laser.commandSetTempSeed(tempSeedValue);
        String response = handleCommand(command);
        return command + "\n" + response + "\n\n";
    }

    public void handleCommandStoredTempPump() {
        String command = this.laser.commandStoredTempPump();
        String response = handleCommand(command);
        this.laser.setStoredTempPump(response);
    }

    public void handleCommandStoredCurrentPump() {
        String command = this.laser.commandStoredCurrentPump();
        String response = handleCommand(command);
        this.laser.setStoredCurrentPump(response);
    }

    // todo: add functions for commands that set values (see Laser.java)


}
