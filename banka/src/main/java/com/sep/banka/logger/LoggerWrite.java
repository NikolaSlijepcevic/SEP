package com.sep.banka.logger;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerWrite {
	static Handler fileHandler = null;
    private static final Logger LOGGER = Logger.getLogger(LoggerWrite.class
            .getClass().getName());

    public static void setup() {

        try {
            fileHandler = new FileHandler("./logfile.log");//file
            SimpleFormatter simple = new SimpleFormatter();
            fileHandler.setFormatter(simple);

            LOGGER.addHandler(fileHandler);//adding Handler for file

        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }
    public static void main() {
        setup();//calling to the file content
        LOGGER.info("------------------START--------------------");
           //here the Information or Contents that in file
        }   
}
