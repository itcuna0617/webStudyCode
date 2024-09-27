package com.greedy.section03.thumbnail;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

@WebServlet("/transferToThumbnail")
public class TransferToThumbnailImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("location.href로 과연 올까?");
		
		/*
		 * 
		 * https://coobird.github.io/thumbnailator/javadoc/0.4.17/
		 */
		
		/*
		 * thumbnail의 용량 및 사이즈를 줄여야 하는 이유
		 * thumbnail 게시판 형태인 경우 원본 파일을 로드해서 사용자에게 보여줄 때 용량이 큰 경우 로딩도 느려지고,
		 * 모바일로 접속 시 많은 양의 데이터 손실을 야기할 수 있다.
		 * 따라서 thumbnail 게시판 이미지용으로 사용할 이미지는 작은 사이즈로 변환하여 보여주는 작업이 요구된다.
		 */
		
		String originFilePath = request.getServletContext().getRealPath("")  // root 폴더 경로(web폴더)까지의 경로를 알 수 있다.
								+ "resources/origin-image/abcd1234.PNG";	
		System.out.println(originFilePath);
		
		File originFile = new File(originFilePath);
		
		String savePath = "C:\\servletWorkspace\\chap13-file-io-lecture-source\\web"
						  + "resources/thumbnail-image/abcd1234_thumbnail.PNG";
		
		Thumbnails.of(originFile)		// 변환 할 원본 파일의 파일 객체
		          .size(100, 80)		// width, height
		          .toFile(savePath);	// 변환 후 저장 할 파일의 경로
		
		System.out.println("썸네일 변환 성공!!");
		
	}

}






