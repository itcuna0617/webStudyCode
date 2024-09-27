package com.greedy.section02.commonsio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.coobird.thumbnailator.Thumbnails;



@WebServlet("/commons/single")
public class CommonsFileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String rootLocation;
	private int maxFileSize;
	private String encodingType;
	
	@Override
    public void init() throws ServletException {
		
		/* 
		 * web.xml에 설정되어 context 객체에 처음부터 parameter로 던져지는 값을 꺼내서
		 * 서블릿 인스턴스가 생성되는 시점에 필드에 담기 위한 코드
		 */
		ServletContext context = getServletContext();	// context 객체를 불러오는 코드
		
		rootLocation = context.getInitParameter("upload-location");
		maxFileSize = Integer.valueOf(context.getInitParameter("max-file-size"));
		encodingType = context.getInitParameter("encoding-type");
    }
       
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * input 태그의 type이 file인 경우 form태그는 encType="multipart/form-data"라는 어트리뷰트를 추가해야
		 * 파일을 서버로 넘길 수 있다. 하지만 어트리뷰트를 추가하게 되면 기존의 방법(getParameter() 활용)대로 넘어온 값을
		 * 추출할 수 없다.
		 */
//		String singlefile = request.getParameter("singlefile");
//		String description = request.getParameter("description");
//		
//		System.out.println("singlefile: " + singlefile);
//		System.out.println("description: " + description);
		
		/* multipart/form-data인지 아닌지 확인하는 구문 */
//		System.out.println(ServletFileUpload.isMultipartContent(request));
		
		/*
		 * 파일 업로드를 하기 위한 환경 설정
		 * 1. 파일이 저장 될 경로
		 * 2. 업로드 할 파일의 최대 용량
		 * 3. 인코딩 설정
		 * (web.xml에 설정할 것)
		 */
		
		/*
		 * 우리가 사용 할려는 라이브러리를 세팅하자.
		 * (commons-fileupload, commons-io)
		 */
		if(ServletFileUpload.isMultipartContent(request)) {
//			System.out.println("파일 저장 root경로: " + rootLocation);
//			System.out.println("최대 업로드 파일 용량: " + maxFileSize);
//			System.out.println("인코딩 방식: " + encodingType);
			
			String fileUploadDirectory = rootLocation + "/commons";
			File directory = new File(fileUploadDirectory);
			
			/* 해당 경로에 폴더가 없으면 만들어 주기 위한 조건문 */
			if(!directory.exists()) {		// exists(): 해당 경로의 폴더가 존재하지 않으면 false, 존재하면 true를 반환
				System.out.println("폴더 생성(중간 폴더 포함 모두): " + directory.mkdirs());	// File객체의 경로가 존재하지 않으면 중간 폴더를 다 만들어 줌
			}
			
			/*
			 * DB에 정보들을 묶어서 전달하기 위한 컬렉션 선언
			 * 
			 * 1. file로 넘어오지 않은 값들만 담을 Map 선언
			 * 2. file이 한개 또는 여러개 넘어올 때 모두 담을 수 있는 List 선언
			 */
			Map<String, String> parameter = new HashMap<>();
			List<Map<String, String>> fileList = new ArrayList<>();
			
			
			/* 파일을 업로드 할 시 최대 크기나 임시 저장할 폴더의 경로 등을 포함하기 위한 인스턴스 */
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			fileItemFactory.setRepository(directory);
			
			/* request를 파싱하여 데이터 하나하나를 FileItem 타입의 인스턴들로 변환 */
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			fileUpload.setSizeMax(maxFileSize);
			
			try {
				List<FileItem> fileItems = fileUpload.parseRequest(request);
				for(FileItem item : fileItems) {	// getParameter로 못 꺼냈던 값들을 FileItem들로 파싱되어 담겨 있는 것을 확인
					System.out.println(item);
					
					if(!item.isFormField()) {		// 해당 FileItem이 업로드 된 파일이 들어있다면
						
						/* 용량이 0byte를 초과해야 저장할 가치가 있는 유의미한 파일이다. */
						if(item.getSize() > 0) {	
							String fieldName = item.getFieldName();		// input 태그 name값
							String originFileName = item.getName();		// 올린 파일명(확장자 포함)
							
							/*
							 * 파일은 저장 시 이름을 변경해야 한다.
							 * 변경해야 하는 이유
							 * 1. 한글이나 공백, 특수문자 등은 운영체제에 따라 문제가 발생할 수 있다.
							 * 2. 파일 이름이 겹치게 되면 덮어쓰게 된다.
							 */
							
							/* 1. 저장 할 새로운 이름 생성(rename) */
							/* 올린 파일의 확장자 추출 */
							int dot = originFileName.lastIndexOf(".");
							String ext = originFileName.substring(dot);
							
							System.out.println("올린 파일 확장자: " + ext);
							
//							System.out.println(UUID.randomUUID().toString().replace("-", "") + ext);
							
							String randomFileName = UUID.randomUUID().toString().replace("-", "") + ext;
							
							/* 2. 저장 경로에 rename한 이름으로 파일 저장 */
							File storeFile = new File(fileUploadDirectory + "/" + randomFileName);	// 파일명까지 알고 있는 File 객체 생성
							
							item.write(storeFile);	// item(해당 파일)을 rename한 이름의 파일 경로(storeFile)로 저장(write())
							
							
							
							/* 3. 하나의 컬렉션으로 묶어내기(Map: 현재 저장한 파일(1개)의 추가 정보들을 하나의 Map으로 저장(DB의 한 행에 insert될 정보)) */
							Map<String, String> fileMap = new HashMap<>();
							fileMap.put("fieldName", fieldName);			// 해당 파일의 input 태그 name값
							fileMap.put("originFileName", originFileName);  // 사용자가 올릴 당시의 원래 파일 이름
							fileMap.put("savedFileName", randomFileName);   // 저장하기 위해 rename한 파일 이름
							fileMap.put("savePath", fileUploadDirectory);	// 해당 파일을 저장한 경로
							
//							Set<String> key = fileMap.keySet();
//							Iterator<String> iter = key.iterator();
//							while(iter.hasNext()) {
//								String nextKey = iter.next();
//								System.out.println(nextKey + ": " + fileMap.get(nextKey));
//							}
							
							fileList.add(fileMap);		// 파일 하나의 정보를 지닌 Map을 List에 저장
						} 
					} else {						    // 해당 FileItem이 파일과 관련 없다면
						
						/* catch 테스트를 위한 file 관련 내용 끝나고 test 타입일 때 에러 발생해 보기 */
//						String str = "";
//						System.out.println(str.charAt(0));
						
						/* FileItem의 getString()으로 value를 꺼내면 한글이 깨진다. */
//						System.out.println("input 태그의 value: " + item.getString());	
						
						/* String의 매개변수 있는 생성자를 통해 원하는 인코딩의 문자열로 변환할 수 있다. */
//						System.out.println("input 태그의 value: " 					
//											+ new String(item.getString().getBytes("ISO-8859-1"), encodingType));
						
						/* 파일이 아닌 데이터들을 담을 Map(key - input 태그의 name, value - input 태그의 value */
						parameter.put(item.getFieldName(), 
								new String(item.getString().getBytes("ISO-8859-1"), encodingType));
						
					}
				}
				
			} catch (Exception e) {
				
				/* 파일 업로드 시 에러가 발생하면 에러가 발생하기 전까지 해당 경로에 저장된 파일을 다시 삭제 */
				int cnt = 0;
				
				for(int i = 0; i < fileList.size(); i++) {
					Map<String, String> file = fileList.get(i);
					
					File deletedFile =
							new File(fileUploadDirectory + "/" + file.get("savedFileName"));
					
					boolean isDeleted = deletedFile.delete();
					
					if(isDeleted) {
						cnt++;
					}
				}
				
				if(cnt == fileList.size()) {
					System.out.println("업로드에 실패한 모든 사진 삭제 완료!");
				} else {
					System.out.println("업로드에 실패한 모든 사진 삭제 실패!");
				}
			}
			
			/* 파일과 파일이 아닌 데이터들이 각각 fileList와 parameter로 묶여서 이후 Service 계층으로 넘어감 */
			
			/* file들이 잘 담겨 있는지 확인 */
			for(Map<String, String> fileMap : fileList) {
				System.out.println(fileMap);
			}
			
			/* file이 아닌 데이터들이 잘 담겨 있는지 확인*/
			Set<String> keys = parameter.keySet();
			Iterator<String> iter = keys.iterator();
			while(iter.hasNext()) {
				String key = iter.next();
				System.out.println("key: " + key + ", value: " + parameter.get(key));
			}
			
		} else {
			System.out.println("entType이 multipart/formData가 아닌경우 - getParameter()를 쓸 수 있는 영역");
		}
	}
}






