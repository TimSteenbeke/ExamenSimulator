package be.kdg.Simulator;

import be.kdg.MessageBroker.Message;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class LoadTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //example file layout
            //type;rideid;sectionId;blockNr;delay
            //detectie;31;1;1;10
            Message m = new Message();

            File file = new File("D:\\Users\\timst\\Documents\\000 - School\\Software Engeneering 3\\ExamenProject\\Examenproject_simulator\\src\\main\\resources\\loadtest.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //split line
                String[] parts = line.split(";");
                //check if valid line
                if(parts.length == 5){
                    try{
                        long defaultSleep = 0;
                        String messageType = parts[0];
                        switch (messageType) {
                            case "detectie":
                                //send detectie message
                                m.detectionMessage(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                                defaultSleep = Long.parseLong(parts[4]);
                                break;
                            case "signalisatie":
                                m.signalMessage(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                                defaultSleep = Long.parseLong(parts[4])*100;
                                break;
                            default:
                                //invalid line
                                //log that it's an invalid line and continue
                                System.out.println("Invalid line");
                        }
                        Thread.sleep(defaultSleep);
                    }catch(NumberFormatException nfe){
                        //TODO log nfe exception and continue to next line
                        System.out.println("nfe");
                    }catch(InterruptedException ie){
                        //TODO log IE exception, stop process?
                        System.out.println("IE");
                    }
                }else{
                    //TODO log that this is an invalid line
                }
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}