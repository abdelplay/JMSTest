package com.test.service.message.xml;


import javax.jms.*;
import javax.naming.Context;
  
public class MyReceiver {  
    public static void main(String[] args) {  
        try{  
            //1) Create and start connection  
        	Context ctx=MySender.getInitialContext();   
            ConnectionFactory f=(ConnectionFactory)ctx.lookup("jms/RemoteConnectionFactory");  
            Connection con=f.createConnection("abdel", "123");  
            con.start();  
            //2) create Queue session  
            Session ses=con.createSession(false, Session.AUTO_ACKNOWLEDGE);  
            //3) get the Queue object  
            Destination t=(Destination)ctx.lookup(MySender.queue);  
            //4)create QueueReceiver  
            MessageConsumer receiver=ses.createConsumer(t);  
              
            //5) create listener object  
            MyListener listener=new MyListener();  
              
            //6) register the listener object with receiver  
            receiver.setMessageListener(listener);  
              
            System.out.println("Receiver1 is ready, waiting for messages...");  
            System.out.println("press Ctrl+c to shutdown...");  
            while(true){                  
                Thread.sleep(1000);  
            }  
        }catch(Exception e){System.out.println("ERROR ===> "+e.getMessage());}  
    }  
  
}  