package sendmsgtoQpid;

	import javax.jms.*;
	import javax.naming.Context;
	import javax.naming.InitialContext;
	import java.util.Properties;
	import org.apache.qpid.AMQException.*;

	public class Hello {

	    public Hello() {
	    }

	    public static void main(String[] args) throws Exception {
	        Hello hello = new Hello();
	        hello.runTest();
	    }
	    private void runTest() throws Exception {
	      Properties properties = new Properties();
	      properties.load(this.getClass().getResourceAsStream("Hello.properties"));
//	           System.out.println("qpid_message --- step2");
	                  Context context = new InitialContext(properties);  
//	          System.out.println("qpid_message --- step3");
	        //   System.out.println(context +"qpid_message --- start");
		
	       // String  CF_LOOKUP_NAME = "qpidConnectionFactory";
	       ConnectionFactory connectionFactory
	          = (ConnectionFactory)context.lookup("qpidConnectionFactory");
	        
	     // ConnectionFactory connectionFactory = (ConnectionFactory)context.lookup("CF_LOOKUP_NAME"); 
	        System.out.println("qpid_message --> step4");
	      Connection connection = connectionFactory.createConnection();                   
	      connection.start(); 
	     
	      Session session = connection.createSession(true,Session.SESSION_TRANSACTED);
	      Queue queue = (Queue)context.lookup("myqueue");
	      MessageProducer producer = session.createProducer(queue);
	      
	          TextMessage message = session.createTextMessage();
	          message.setText("TestMessage123-9/20/2017 10:33AM ");
	          producer.send(message);
	          session.commit();
	          System.out.println("qpid_message Sent");
//	         
	          
//	          MessageConsumer consumer= session.createConsumer(queue);
//	          //MessageConsumer consumer= session.
//	          
//	          TextMessage message1 = (TextMessage)consumer.receive();
//	          session.commit();
////	         
//	          System.out.println(message1.getText());
//	          System.out.println("qpid_message Received");
	           
	         
	          //System.out.println(consumer.toString()); 
	      session.close();
	      connection.close();                                                              
	    }

	}
