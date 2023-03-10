package neu.edu.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * Application Lifecycle Listener implementation class MongoDBContextListener
 *
 */
@WebListener
public class MongoDBContextListener implements ServletContextListener{

    /**
     * Default constructor. 
     */
    public MongoDBContextListener() {
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

		/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	try {
    		ServletContext application = sce.getServletContext();
        	MongoClient mongoDBClient = new MongoClient(application.getInitParameter("MONGODB_HOST"),Integer.parseInt(application.getInitParameter("MONGODB_PORT")));
        	System.out.println("MongoDBClient initialized successfully");
    		application.setAttribute("mongodbClient", mongoDBClient);
    	}catch (MongoException e) {
			throw new RuntimeException("MongoClient init failed");
		}
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */



	
}
