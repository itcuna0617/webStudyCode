package com.greedy.section01.cos.singlefileupload;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.greedy.common.UUIDFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/cos/single")
public class CosSingleFileUploadServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* entType이 multipart/form-data로 전송되는 경우 일반적인 HttpServletRequest로 생성되어 넘어오지 않는다. */
		/* 아무런 라이브러리도 사용하지 않고 구현할 수 는 있지만 어무 어렵다. 참고 : https://dololak.tistory.com/726 */
		/* commons-file-upload 혹은 cos 라이브러리를 이용하면 파일업로드를 보다 간단하게 구현할 수 있다. */
		/* web.xml에 파일을 저장할 경로를 먼저 셋팅해주자 */
		
		/* 요청 헤더의 content-type이 multipart/form-date인 경우 true, 아닌 경우 false를 반환함  */
		if(ServletFileUpload.isMultipartContent(request)) {
			
			ServletContext context = request.getServletContext();
			String rootLocation = context.getInitParameter("upload-location");
			int maxFileSize = Integer.parseInt(context.getInitParameter("max-file-size"));
			String encodingType = context.getInitParameter("encoding-type");
			
			System.out.println("파일 저장 ROOT 경로 : " + rootLocation);
			System.out.println("최대 업로드 파일 용량 : " + maxFileSize);
			System.out.println("인코딩 방식 : " + encodingType);
			
			String singleFileUploadDirectory = rootLocation + "/cos/single";
			
			File directory = new File(singleFileUploadDirectory);
			
			/* 파일 저장경로가 존재하지 않는 경우 디렉토리를 생성한다. */
			if(!directory.exists()) {
				/* 폴더를 한 개만 생성할거면 mkdir, 상위 폴더도 존재하지 않으면 한 번에 생성하란 의미로 mkdirs를 이용한다. */
				System.out.println("폴더 생성 : " + directory.mkdirs());
			}
			
			/* request, 파일 저장 경로, 최대 파일 크기(-1로 지정하면 최대 용량이 없다), 인코딩타입, 파일이름변경정책 의 정보가 필요하다.
			 * MultipartRequest 객체가 생성될 때 파일 저장을 완료를 하게 된다.
			 * */
//			MultipartRequest multiRequest = new MultipartRequest(request, singleFileUploadDirectory, maxFileSize, encodingType, new DefaultFileRenamePolicy());
			

			MultipartRequest multiRequest = new MultipartRequest(request, singleFileUploadDirectory, maxFileSize, encodingType, new UUIDFileRenamePolicy());
			
			/* multipart에서는 일반 request에서 파라미터로 꺼내오지 못하던 값도 꺼내올 수 있다. */
			String description = multiRequest.getParameter("description");
			System.out.println(description);
			
			/* 폼으로 전송된 파일의 정보도 확인 가능하다. */
			
			/* 전송된 폼의 name속성값 */
			Enumeration<String> names = multiRequest.getFileNames();
			while(names.hasMoreElements()) {
				System.out.println(names.nextElement());
			}
			
			/* 전송된 name값을 이용하여 폼으로 전송된 파일의 원본 이름을 리턴받음 */
			String originFileName = multiRequest.getOriginalFileName("singlefile");
			System.out.println("form 전송된 파일의 원본 이름 : " + originFileName);
			
			/* 파일 이름 변경 후 파일시스템에 저장된 파일의 이름도 리턴받을 수 있다. */
			String changedFileName = multiRequest.getFilesystemName("singlefile");
			System.out.println("파일 시스템에 저장된 변경된 파일의 이름 : " + changedFileName);
			
			/* 저장된 파일의 경로는 따로 불러올 수 없기 때문에 필요시에는 별도로 DB에 저장해두어야 한다. */
			String filePath = singleFileUploadDirectory;
			
			/* 비지니스 로직이 실패하는 경우 파일을 제거해 주어야 한다. */
			File remover = new File(singleFileUploadDirectory + "/" + changedFileName);
			
			System.out.println("파일 삭제 성공? : " + remover.delete());
			
		}
	}

}
