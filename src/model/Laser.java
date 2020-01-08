package model;

public class Laser {

    private String report;
    private String state;
    private String productionDate;
    private String hardwareVersion;
    private String serialNumber;
    private String serialNumberOfSeedDiode;
    private String serialNumberOfPumpDiode;
    private String firmwareVersion;
    private String sysHours;            // Total uptime
    private String voltageVCCIN;        // Voltage external power supply in mV
    private String voltageSeed;         // Main power supply seed in mV
    private String voltageBoost;        // Power supply for first amplifier stage in mV
    private String voltageReg;          // Power supply for second amplifier stage in mV
    private String voltageNBA;          // Negative bias voltage at first amplifier stage
    private String voltageNBB;          // Negative bias voltage at second amplifier stage
    private String tempSystem;          // System temperature in m°C (command is no specified in the manual)
    private String tempSeed;            // Seed temperature in m°C
    private String currentCURIN;        // Current drawn from external power supply in mA
    private String triggerSource;       // Current trigger source
    private String triggerFrequencyEXT; // Measured frequency from external trigger input. Measurement is only a rough estimation of the input frequency
    private String triggerFrequencyINT; // Stored frequency for internal source
    // The following parameters can be obtained only from report SYS:REP?
    private String NBA;
    private String NBB;
    private String SMF;
    private String SEF;
    private String SEV;
    private String SEC;
    private String powerPWRIN;          // This value is calculated as (VCCIN * CURIN / 1000) in mW
    // The following parameters can be obtained only by calling commands
    private String voltagePump;         // Main power supply in pump in mV
    private String voltageMonitor;      // Voltage at monitor diode in mV
    private String tempPump;            // Pump temperature in m°C
    private String currentPump;         // Pump current in mA
    private String storedREG;           // Stored value for REG in mV
    private String storedVOF;           // Stored value for Offset in mV (this offset is added to REG)
    private String storedTempSeed;      // Stored seed temperature in m°C
    private String storedTempPump;      // Stored pump temperature in m°C
    private String storedCurrentPump;   // Stored pump current in %

    
    public Laser() {
        this.report = "--";
        this.state = "--";
        this.productionDate = "--";
        this.hardwareVersion = "--";
        this.serialNumber = "--";
        this.serialNumberOfSeedDiode = "--";
        this.serialNumberOfPumpDiode = "--";
        this.firmwareVersion = "--";
        this.sysHours = "--";
        this.voltageVCCIN = "--";
        this.voltageSeed = "--";
        this.voltageBoost = "--";
        this.voltageReg = "--";
        this.voltageNBA = "--";
        this.voltageNBB = "--";
        this.tempSystem = "--";
        this.tempSeed = "--";
        this.currentCURIN = "--";
        this.triggerSource = "--";
        this.triggerFrequencyEXT = "--";
        this.triggerFrequencyINT = "--";
        this.NBA = "--";
        this.NBB = "--";
        this.SMF = "--";
        this.SEF = "--";
        this.SEV = "--";
        this.SEC = "--";
        this.powerPWRIN = "--";
        this.voltagePump = "--";
        this.voltageMonitor = "--";
        this.tempPump = "--";
        this.currentPump = "--";
        this.storedREG = "--";
        this.storedVOF = "--";
        this.storedTempSeed = "--";
        this.storedTempPump = "--";
        this.storedCurrentPump = "--";
    }
    

    public void setReport(String report) {
        this.report = report;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setSerialNumberOfSeedDiode(String serialNumberOfSeedDiode) {
        this.serialNumberOfSeedDiode = serialNumberOfSeedDiode;
    }

    public void setSerialNumberOfPumpDiode(String serialNumberOfPumpDiode) {
        this.serialNumberOfPumpDiode = serialNumberOfPumpDiode;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public void setSysHours(String sysHours) {
        this.sysHours = sysHours;
    }

    public void setVoltageVCCIN(String voltageVCCIN) {
        this.voltageVCCIN = voltageVCCIN;
    }

    public void setVoltageSeed(String voltageSeed) {
        this.voltageSeed = voltageSeed;
    }

    public void setVoltageBoost(String voltageBoost) {
        this.voltageBoost = voltageBoost;
    }

    public void setVoltageReg(String voltageReg) {
        this.voltageReg = voltageReg;
    }

    public void setVoltageNBA(String voltageNBA) {
        this.voltageNBA = voltageNBA;
    }

    public void setVoltageNBB(String voltageNBB) {
        this.voltageNBB = voltageNBB;
    }

    public void setTempSystem(String tempSystem) {
        this.tempSystem = tempSystem;
    }

    public void setTempSeed(String tempSeed) {
        this.tempSeed = tempSeed;
    }

    public void setCurrentCURIN(String currentSystem) {
        this.currentCURIN = currentSystem;
    }

    public void setTriggerSource(String triggerSource) {
        this.triggerSource = triggerSource;
    }

    public void setTriggerFrequencyEXT(String triggerFrequencyEXT) {
        this.triggerFrequencyEXT = triggerFrequencyEXT;
    }

    public void setTriggerFrequencyINT(String triggerFrequencyINT) {
        this.triggerFrequencyINT = triggerFrequencyINT;
    }

    public void setNBA(String NBA) {
        this.NBA = NBA;
    }

    public void setNBB(String NBB) {
        this.NBB = NBB;
    }

    public void setSMF(String SMF) {
        this.SMF = SMF;
    }

    public void setSEF(String SEF) {
        this.SEF = SEF;
    }

    public void setSEV(String SEV) {
        this.SEV = SEV;
    }

    public void setSEC(String SEC) {
        this.SEC = SEC;
    }

    public void setPowerPWRIN(String powerPWRIN) {
        this.powerPWRIN = powerPWRIN;
    }

    public void setVoltagePump(String voltagePump) {
        this.voltagePump = voltagePump;
    }

    public void setVoltageMonitor(String voltageMonitor) {
        this.voltageMonitor = voltageMonitor;
    }

    public void setTempPump(String tempPump) {
        this.tempPump = tempPump;
    }

    public void setCurrentPump(String currentPump) {
        this.currentPump = currentPump;
    }

    public void setStoredREG(String storedREG) {
        this.storedREG = storedREG;
    }

    public void setStoredVOF(String storedVOF) {
        this.storedVOF = storedVOF;
    }

    public void setStoredTempSeed(String storedTempSeed) {
        this.storedTempSeed = storedTempSeed;
    }

    public void setStoredTempPump(String storedTempPump) {
        this.storedTempPump = storedTempPump;
    }

    public void setStoredCurrentPump(String storedCurrentPump) {
        this.storedCurrentPump = storedCurrentPump;
    }



    public String getReport() {
        return report;
    }

    public String getState() {
        return state;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getSerialNumberOfSeedDiode() {
        return serialNumberOfSeedDiode;
    }

    public String getSerialNumberOfPumpDiode() {
        return serialNumberOfPumpDiode;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public String getSysHours() {
        return sysHours;
    }

    public String getVoltageVCCIN() {
        return voltageVCCIN;
    }

    public String getVoltageSeed() {
        return voltageSeed;
    }

    public String getVoltageBoost() {
        return voltageBoost;
    }

    public String getVoltageReg() {
        return voltageReg;
    }

    public String getVoltageNBA() {
        return voltageNBA;
    }

    public String getVoltageNBB() {
        return voltageNBB;
    }

    public String getTempSystem() {
        return tempSystem;
    }

    public String getTempSeed() {
        return tempSeed;
    }

    public String getCurrentCURIN() {
        return currentCURIN;
    }

    public String getTriggerSource() {
        return triggerSource;
    }

    public String getTriggerFrequencyEXT() {
        return triggerFrequencyEXT;
    }

    public String getTriggerFrequencyINT() {
        return triggerFrequencyINT;
    }

    public String getNBA() {
        return NBA;
    }

    public String getNBB() {
        return NBB;
    }

    public String getSMF() {
        return SMF;
    }

    public String getSEF() {
        return SEF;
    }

    public String getSEV() {
        return SEV;
    }

    public String getSEC() {
        return SEC;
    }

    public String getPowerPWRIN() {
        return powerPWRIN;
    }

    public String getVoltagePump() {
        return voltagePump;
    }

    public String getVoltageMonitor() {
        return voltageMonitor;
    }

    public String getTempPump() {
        return tempPump;
    }

    public String getCurrentPump() {
        return currentPump;
    }

    public String getStoredREG() {
        return storedREG;
    }

    public String getStoredVOF() {
        return storedVOF;
    }

    public String getStoredTempSeed() {
        return storedTempSeed;
    }

    public String getStoredTempPump() {
        return storedTempPump;
    }

    public String getStoredCurrentPump() {
        return storedCurrentPump;
    }




    public String commandReport() {
        return "SYS:REP?";                              // Print report once
    }

    // Reset System
    // Switch‘all laser components off
    // system restarts with stored values
    // if reset is set in WORKING state and
    //      an intern trigger is used or
    //      a valid external trigger is present
    //      then system will again return to working state
    public String commandReset() {
        return "SYS:RES!";
    }

    public String commandErrorState() {
        return "SYS:ERR!";                              // Change to error state
    }

    public String commandState() {
        return "SYS:STAT?";                             // Print current state
    }

    public String commandProductionDate() {
        return "SYS:DATE?";                             // Print production date
    }

    public String commandHardwareVersion() {
        return "SYS:HW?";                               // Print hardware revision
    }

    public String commandSerialNumber() {
        return "SYS:SN?";                               // Print serial number
    }

    public String commandSerialNumberOfSeedDiode() {
        return "SYS:SSN?";                              // Print serial number of seed diode
    }

    public String commandSerialNumberOfPumpDiode() {
        return "SYS:PSN?";                              // Print serial number of pump diode
    }

    public String commandFirmwareVersion() {
        return "SYS:FW?";                               // Print firmware version
    }

    public String commandSysHours() {
        return "SYS:HOUR?";                             // Print total uptime
    }

    public String commandVoltageVCCIN() {
        return "MEAS:VOLT:VCC?";                        // Voltage external power supply in mV
    }

    public String commandVoltageSeed() {
        return "MEAS:VOLT:SEE?";                        // Main power supply seed in mV
    }

    public String commandVoltageBoost() {
        return "MEAS:VOLT:BOO?";                        // Power supply for first amplifier stage in mV
    }

    public String commandVoltageReg() {
        return "MEAS:VOLT:REG?";                        // Power supply for second amplifier stage in mV
    }

    public String commandVoltageNBA() {
        return "MEAS:VOLT:NBA?";                        // Negative bias voltage at first amplifier stage
    }

    public String commandVoltageNBB() {
        return "MEAS:VOLT:NBB?";                        // Negative bias voltage at second amplifier stage
    }

    public String commandTempSystem() {
        return "MEAS:TEMP:SYS?";                        // System temperature in m°C
    }

    public String commandTempSeed() {
        return "MEAS:TEMP:SEE?";                        // Seed temperature in m°C
    }

    public String commandCurrentCURIN() {
        return "MEAS:CURR:SYS?";                        // Current drawn from external power supply in mA
    }

    public String commandTriggerSource() {
        return "TRIG:SOUR?";                             // Print current trigger source
    }

    public String commandSetTriggerSource(String triggerSourceValue) {
        return "TRIG:SOUR " + triggerSourceValue;       // Change trigger source
    }

    public String commandTriggerFrequencyEXT() {
        return "MEAS:FREQ:TRIG?";                       // Print measured frequency from external trigger input. Measurement is only a rough estimation of the input frequency.
    }

    public String commandTriggerFrequencyINT() {
        return "TRIG:FREQ?";                            // Print stored frequency for internal source
    }

    public String commandSetTriggerFrequencyINT(int triggerFrequencyValue) {
        return "TRIG:FREQ " + triggerFrequencyValue;    // Set frequency for internal source
    }

    public String commandVoltagePump() {
        return "MEAS:VOLT:PUM?";                        // Main power supply pump in mV
    }

    public String commandVoltageMonitor() {
        return "MEAS:VOLT:MON?";                        // Voltage at monitor diode in mV
    }

    public String commandTempPump() {
        return "MEAS:TEMP:PUM?";                        // Pump temperature in m°C
    }

    public String commandCurrentPump() {
        return "MEAS:CURR:PUM?";                        // Pump current in mA
    }

    public String commandStoredREG() {
        return "SOUR:VOLT:REG?";                        // Print stored value for REG in mV
    }

    public String commandSetREG(int regValue) {
        return "SOUR:VOLT:REG " + regValue;             // Set value for REG in mV
    }

    public String commandStoredVOF() {
        return "SOUR:VOLT:VOF?";                        // Print stored value for Offset in mV, this offset is added to REG every time a new frequency is set (internal tigger mode), or continuously (external trigger mode)
    }

    public String commandSetVOF(int vofValue) {
        return "SOUR:VOLT:VOF " + vofValue;             // Set value for Offset in mV (max. 999 mV). Factory set to 500.
    }

    public String commandStoredTempSeed() {
        return "SOUR:TEMP:SEE?";                        // Print stored seed temperature in m°C
    }

    public String commandSetTempSeed(int tempSeedValue) {
        return "SOUR:TEMP:SEE " + tempSeedValue;        // Set seed temperature in m°C (max. 50000)
    }

    public String commandStoredTempPump() {
        return "SOUR:TEMP:PUM?";                        // Print stored pump temperature in m°C
    }

    public String commandStoredCurrentPump() {
        return "SOUR:CURR:PUM?";                        // Print stored pump current in %
    }

    public String commandSetCurrentPump(int currentPumpValue) {
        return "SOUR:CURR:PUM " + currentPumpValue;     // Set pump current in %
    }


}
