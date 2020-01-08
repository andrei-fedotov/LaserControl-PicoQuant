package util;

import model.Laser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import main.MainApp;

public class Logger {

    private Laser laser;

    public Logger(Laser laser) {
        this.laser = laser;
    }

    private String makeHeader() {
        String header =
                        "PicoQuant CPDL-S-F\n\n" +
                        "HW-Rev.:\t" + this.laser.getHardwareVersion() + "\n" +
                        "Serial-Num.:\t" + this.laser.getSerialNumber() + "\n" +
                        "Ser.-Num. SEED LD:\t" + this.laser.getSerialNumberOfSeedDiode() + "\n" +
                        "Ser.-Num. PUMP LD:\t" + this.laser.getSerialNumberOfPumpDiode() + "\n" +
                        "Production Date:\t" + this.laser.getProductionDate() + "\n" +
                        "FW-Version:\t"+ this.laser.getFirmwareVersion() + "\n\n";
        return header;
    }

    private String makeTableHeader() {
        String tableHeader =
                            "Date\t"+
                            "Time\t"+
                            "STATE\t"+
                            "SysHour\t"+
                            "SysTemp\t"+
                            "VCCIN\t"+
                            "CURIN\t"+
                            "PWRIN\t"+
                            "SOURCE\t"+
                            "EXT\t"+
                            "INT\t"+
                            "VCC\t"+
                            "BOOST\t"+
                            "REG\t"+
                            "NBA\t"+
                            "NBB\t"+
                            "TEMP\t"+
                            "setNBA\t"+
                            "setNBB\t"+
                            "setSMF\t"+
                            "setSEF\t"+
                            "setSEV\t"+
                            "setSEC\t"+
                            "\t"+
                            "Main power supply pump\t"+
                            "Voltage at monitor diode\t"+
                            "Pump temperature\t"+
                            "Pump current\t"+
                            "Stored value for REG\t"+
                            "Stored value for Offset\t"+
                            "Stored seed temperature\t"+
                            "Stored pump temperature\t"+
                            "Stored pump current\n";
        return tableHeader;
    }

    private String makeUnits() {
        // Remove all digits from string
        String units =
                        "\t" +
                        "\t" +
                        "\t" +
                        this.laser.getSysHours().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getTempSystem().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getVoltageVCCIN().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getCurrentCURIN().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getPowerPWRIN().replaceAll("[\\d.\\s+]", "") + "\t" +
                        "\t" +
                        this.laser.getTriggerFrequencyEXT().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getTriggerFrequencyINT().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getVoltageSeed().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getVoltageBoost().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getVoltageReg().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getVoltageNBA().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getVoltageNBB().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getTempSeed().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getNBA().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getNBB().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getSMF().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getSEF().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getSEV().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getSEC().replaceAll("[\\d.\\s+]", "") + "\t" +
                        "\t" +
                        this.laser.getVoltagePump().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getVoltageMonitor().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getTempPump().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getCurrentPump().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getStoredREG().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getStoredVOF().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getStoredTempSeed().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getStoredTempPump().replaceAll("[\\d.\\s+]", "") + "\t" +
                        this.laser.getStoredCurrentPump().replaceAll("[\\d.\\s+]", "") + "\n";
        return units;
    }

    private String makeData() {

        // Form date and time
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd\tHH:mm:ss.SSS");
        String dateAndTime = dateFormat.format(date);

        // Remove all non-digit characters  from strings
        String data =
                        dateAndTime + "\t" +
                        this.laser.getState() + "\t" +
                        this.laser.getSysHours().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getTempSystem().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getVoltageVCCIN().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getCurrentCURIN().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getPowerPWRIN().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getTriggerSource() + "\t" +
                        this.laser.getTriggerFrequencyEXT().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getTriggerFrequencyINT().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getVoltageSeed().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getVoltageBoost().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getVoltageReg().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getVoltageNBA().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getVoltageNBB().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getTempSeed().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getNBA().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getNBB().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getSMF().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getSEF().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getSEV().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getSEC() + "\t" +
                        "\t" +
                        this.laser.getVoltagePump().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getVoltageMonitor().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getTempPump().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getCurrentPump().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getStoredREG().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getStoredVOF().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getStoredTempSeed().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getStoredTempPump().replaceAll("[^\\d]", "") + "\t" +
                        this.laser.getStoredCurrentPump().replaceAll("[^\\d]", "") + "\n";
        return data;
    }



    private boolean hasHeader(File file, String header, String tableHeader, String units) {
        boolean flag = false;
        try {
            // Read first 10 lines from the file to a string
            String fileContent = "";
            Scanner scanner = new Scanner(file);

            for(int i = 0; i < 11; i++) {
                fileContent += scanner.nextLine() + "\n";
            }

            // Check if headers are equal
            if (fileContent.equals(header+tableHeader+units)) {
                flag = true;
            } else {
                flag = false;
            }

        } catch(Exception e) {
            System.out.println("hasHeader:");
            System.out.println(e);
        }

        return flag;
    }

    public void writeLog() {
        try{
            // Create new file
            String logName = "LaserState.log";
            String path = new File(MainApp.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent() + File.separator;                // log is in the same directory with jar
            File file = new File(path+logName);

            // todo: move this check to constructor
            // If file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);           // true means that lines will be added to file, not overwrote
            BufferedWriter bw = new BufferedWriter(fw);

            // prepare data
            String header = makeHeader();
            String tableHeader = makeTableHeader();
            String units = makeUnits();
            String data = makeData();

            // Write in file
            if (this.laser.getState().equals("BUSY")) {
                // do nothing
            } else if (hasHeader(file, header, tableHeader, units)) {
                bw.write(data);
            } else {
                bw.write(header);
                bw.write(tableHeader);
                bw.write(units);
                bw.write(data);
            }

            // Close connection
            bw.close();


        }
        catch(Exception e){
            System.out.println("main function");
            System.out.println(e);
        }
    }




}
