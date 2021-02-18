package control;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class MySessionListener
 *
 */
public class MyListener implements HttpSessionListener {

   
    public void sessionCreated(HttpSessionEvent se)  { 
         System.out.println("session created....");
    }

	
    public void sessionDestroyed(HttpSessionEvent se)  { 
         System.out.println("session destroyed....");
    }
	
}
