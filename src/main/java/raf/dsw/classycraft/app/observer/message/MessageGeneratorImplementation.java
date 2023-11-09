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
        if(type.equals(MessageType.ERROR)){
            text = "Error";
        }
        if(type.equals(MessageType.CANNOT_DELETE_PROJECT_EXPLORER)){
            text = "Project Explorer se ne moze obrisati";
        }
        if(type.equals(type.CANNOT_ADD_CHILD)){
            text = "Ne mozete dodati dete selektovanom cvoru";
        }
        if(type.equals(type.NODE_NOT_SELECTED)){
            text = "Niste selektovali cvor";
        }
        if(type.equals(type.MUST_INSERT_NAME)){
            text = "Morate uneti ime autora";
        }
        if(type.equals(type.CANNOT_DELETE_FILE)){
            text = "Ne mozete obrisati fajl";
        }
        if(type.equals(type.RESOURCE_NOT_FOUND)){
            text = "Resurs nije pronadjen";
        }
        if (type.equals(type.COMPONENT_NOT_SELECTED)) {
            text = "Niste selektovali neku od ponudjenih opcija";
        }
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
