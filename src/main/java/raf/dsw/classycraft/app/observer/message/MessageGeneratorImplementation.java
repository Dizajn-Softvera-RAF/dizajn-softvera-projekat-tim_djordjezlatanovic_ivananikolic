package raf.dsw.classycraft.app.observer.message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageGeneratorImplementation implements MessageGenerator {
    private Message message;
    private List<Logger> loggers;


    public MessageGeneratorImplementation() {

    }
    
    public void generateMessage(String text, MessageType type, LocalDateTime vreme){
        message = new Message(text, type, vreme);
        notifyLoggers(message);
        //return message;
    }

    public void addLogger(Logger logger) {
        if(logger == null)
            return;
        if(this.loggers ==null)
            this.loggers = new ArrayList<>();
        if(this.loggers.contains(logger))
            return;
        this.loggers.add(logger);
    }

    public void removeLogger(Logger logger) {
        if(logger == null || this.loggers == null || !this.loggers.contains(logger))
            return;
        this.loggers.remove(logger);
    }

    public void notifyLoggers(Message poruka) {

        if(poruka == null || this.loggers == null || this.loggers.isEmpty())
            return;

        for(Logger log : loggers){
            log.update(poruka);
        }
    }

}
