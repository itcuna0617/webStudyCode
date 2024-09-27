package com.greedy.section03.requestlistener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RequestListenerTest implements ServletRequestAttributeListener, ServletRequestListener {

    public RequestListenerTest() {
    	System.out.println("request listener 인스턴스 생성");
    }

    public void requestDestroyed(ServletRequestEvent sre)  {
    	System.out.println("request destroyed!!");
    }

    public void attributeRemoved(ServletRequestAttributeEvent srae)  {
    	System.out.println("request attribute removed!!");
    }

    public void requestInitialized(ServletRequestEvent sre)  {
    	System.out.println("request init!!");
    }

    public void attributeAdded(ServletRequestAttributeEvent srae)  {
    	System.out.println("request attribute added!!");
    }

    public void attributeReplaced(ServletRequestAttributeEvent srae)  {
    	
    	/*
    	 * request는 처음에 attribute가 한번 갱신된다.
    	 * org.apache.catalina.ASYNC_SUPPORTED라는 attribute가 자동 수정되기 때문이고
    	 * 서블릿 3.0버전부터 비동기 방식의 요청 처리를 지원하기 위한 것(중요한 것 아님!)
    	 */
    	System.out.println("request attribute replaced!!");
    	System.out.println(srae.getName() + ", " + srae.getValue());
    }
}









