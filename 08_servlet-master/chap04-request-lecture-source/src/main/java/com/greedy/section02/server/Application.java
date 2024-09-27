package com.greedy.section02.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/* tcp 소켓 통신을 하는 톰캣을 흉내내 보자. */
public class Application {

	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(8002);
		
		System.out.println("Http Server started at 8002 port");
		
		int count = 0;
		while(true) {
			count++;
			Socket socket = listener.accept();
			
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			
			int value = 0;
			while((value = in.read()) != -1 && count == 2) {
				System.out.println((char)value);
			}
			
			String responseText = "<h1>Hello World</h1><h2>반가워</h2>";
			
			String responseGeneralHeader = "HTTP/1.1 200 OK \r\n";
			String contentType = "Content-Type: text/html; charset=UTF-8\r\n";
			String contentLength = "Content-Length: " + (responseText.length() + 1) + "\r\n";
			String whiteLine = "\r\n";
			
			/* response header */
			out.write(responseGeneralHeader.getBytes());
			out.write(contentType.getBytes());
			out.write(contentLength.getBytes());
			out.write(whiteLine.getBytes());	// header 다음에 body 내용은 반드시 개행 처리를 해 주어야 한다.
			
			/* response body: 보내는 실제 내용들 */
			out.write(responseText.getBytes());
			out.write(whiteLine.getBytes());
			
			out.flush();
		}
	}
}
