package util;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;


public class ComPort {

    private String[] portsList;
    private SerialPort serialPort;


    public String[] getPortsList() {
        this.portsList = SerialPortList.getPortNames();
        return this.portsList;
    }


    public SerialPort getPort() {
        return this.serialPort;
    }


    public void choosePort(String portName) {
        this.serialPort = new SerialPort(portName);     // Set the port we would like to use
    }


    public void setPortParameters() {           // todo: use it
        try {
            this.serialPort.setParams(SerialPort.BAUDRATE_115200,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);        // Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }


    public void openPort() {
        try{
            this.serialPort.openPort();
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }


    public Boolean isOpened() {
        return this.serialPort.isOpened();
    }



    public void sendToPort(String command) {
        try {
            this.serialPort.setParams(SerialPort.BAUDRATE_115200,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);                            // Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
            this.serialPort.writeBytes((command+"\r").getBytes());      // Write data to port; "\r" correspond to 0xA0 (stop bit, which is necessary after each command)

        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
        // Set timeout
        try {
            Thread.sleep(0250);         // Minimum timeout seems to be 231 ms, otherwise .readString() returns null
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public String readFromPort() {
        String serialPortOutput = null;     // Necessary, because variables are not visible outside try-catch
        try {
            serialPortOutput = this.serialPort.readString();
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }

        return serialPortOutput;
    }


    public void closePort() {
        try{
            this.serialPort.closePort();
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

}