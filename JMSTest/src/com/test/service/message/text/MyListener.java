package com.test.service.message.text;

import javax.jms.*;  
public class MyListener implements MessageListener {  
  
    public void onMessage(Message m) {  
        try{  
        TextMessage msg=(TextMessage)m;  
        System.out.println("Message reçu : "+msg.getText()); 
        }catch(JMSException e){System.out.println("Error Listener : "+e.getMessage());}  
    }  
}  