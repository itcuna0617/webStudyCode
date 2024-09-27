package com.greedy.section01.contextlistener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListenerTest implements ServletContextListener, ServletContextAttributeListener {

    public ContextListenerTest() {
    	
    	/* context가 생성될 때 변화를 감지하는 Listener 인스턴스가 함께 생성된다. */
    	System.out.println("conext listener 인스턴스 생성!");
    }

    public void attributeAdded(ServletContextAttributeEvent scae)  {
    	
    	/* context에 attribute가 추가될 때 동작한다. */
    	System.out.println("context attribute added!");
    }

    public void attributeRemoved(ServletContextAttributeEvent scae)  {
    	
    	/* context에 attribute가 제거될 때 동작한다.  */
    	System.out.println("context attribute removed!");
    }

    public void contextDestroyed(ServletContextEvent sce)  {
    	
    	/* context가 소멸할 때 동작한다. */
    	System.out.println("context destroyed!");
    }

    public void attributeReplaced(ServletContextAttributeEvent scae)  {
    	
    	/* context에 attribute가 변경될 때 동작한다. */
    	System.out.println("context attribute replaced!");
    }

    public void contextInitialized(ServletContextEvent sce)  {
    	
    	/* context가 생성될 때 생성자 호출 이후 동작하는 메소드이다. */
    	System.out.println("context init!");
    }
	
}
