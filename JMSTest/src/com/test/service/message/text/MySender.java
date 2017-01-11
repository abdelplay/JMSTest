package com.test.service.message.text;

import java.io.BufferedReader;  
import java.io.InputStreamReader;
import java.util.Properties;

import javax.naming.*;  
import javax.jms.*;  
  
public class MySender {  
	
	static String queue = "jms/topic/MUREX.IN";
	
	public static Context getInitialContext() throws NamingException {
		//for JNDI connection
	      Properties properties = new Properties();
	      properties.put(Context.INITIAL_CONTEXT_FACTORY,
	              "org.jboss.naming.remote.client.InitialContextFactory");
	      properties.put(Context.PROVIDER_URL, "remote://localhost:4447");
	      properties.put(Context.SECURITY_PRINCIPAL, "abdel");
	      properties.put(Context.SECURITY_CREDENTIALS, "123");
	      return new InitialContext(properties);
	}
	
    public static void main(String[] args) {  
        try  
        {   //Create and start connection          	
        	
            Context ctx=MySender.getInitialContext();  
            ConnectionFactory f=(ConnectionFactory)ctx.lookup("jms/RemoteConnectionFactory");  
            Connection con=f.createConnection("abdel", "123");  
            con.start();  
            //2) create queue session  
            Session ses=con.createSession(false, QueueSession.AUTO_ACKNOWLEDGE);  
            //3) get the Queue object  
            Destination t=(Destination)ctx.lookup(queue);  
            //4)create QueueSender object         
            MessageProducer sender=ses.createProducer(t);  
            //5) create TextMessage object  
            TextMessage msg=ses.createTextMessage();  
              
            //6) write message  
            BufferedReader b=new BufferedReader(new InputStreamReader(System.in));  
            while(true)  
            {  
                System.out.println("Enter Msg, end to terminate:");  
                String s=b.readLine();  
                if (s.equals("end"))  {
                    break; 
                    }
                msg.setText(s);  
                //7) send message  
                sender.send(msg);  
                System.out.println("Message successfully sent.");  
            }  
            //8) connection close  
            con.close();  
        }catch(Exception e){
        	System.out.println("ERROR ===> "+e.getMessage());
        	}  
    }  }
