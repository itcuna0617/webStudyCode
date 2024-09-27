package com.greedy.parameter;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("/first")			// 이 클래스의 핸들러 메소드는 요청 시 /first로 시작한다.
@SessionAttributes({"id", "pwd", "name"})	// 이 클래스의 핸들러 메소드에서 "id" 또는 "pwd", "name"을 key로 해서 Model에 담기면 sessionScope에 저장 
public class FirstController {

	/* 핸들러 메소드에서 반환값이 없으면(void) 요청 주소를 문자열로 반환한다. */
	@GetMapping("/regist")
	public void regist() {}
	
	/* 1. HttpServletRequest 방식으로 값 꺼내기 */
	@PostMapping("/regist")
	public String regist(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		int price = Integer.valueOf(request.getParameter("price"));
		int categoryCode = Integer.valueOf(request.getParameter("categoryCode"));
		
		String message = name + "을(를) 신규 메뉴 목록의 " + categoryCode + "번 카테고리에 "
				         + price + "원으로 등록하셨습니다!";
		
		System.out.println(message);
		
		model.addAttribute("message", message);
		model.addAttribute("message2", "또 다른 메세지");
		
		return "first/messagePrinter";
	}
	
	/*
	 * 2. @RequestParam 방식으로 값 꺼내기
	 * 
	 * 요청 파라미터를 매핑하여 호출 시 값을 넣어주는 어노테이션이다.
	 * 
	 * 값이 넘어오지 않게 되면 ""와 같은 빈 문자열이 넘어오거나 Null이 넘어오는데, 
	 * 이때 Parsing과정(자료형 변환)에서 NullPointerException이 발생할 수 있다.
	 * defaultValuef를 사용하면 이러한 문제를 방지할 수도 있다.
	 * 
	 * @RequestParam 어노테이션을 통째로 안 적고 매개변수명만 input태그의 name과 일치시키면
	 * 생략해도 되지만 가독성 측면이나 defaultValue같은 옵션을 달 때는 어노테이션을 달아주는 것이 좋다.
	 */
	@GetMapping("/modify")
	public void modify() {}
	
	/* @RequestParam 시 매개변수의 이름을 넘어온 파라미터의 키(input 태그의 name)와 맞추면 알아서 인지해줌 */
	@PostMapping("/modify")
	public String modify(@RequestParam("modifyName") String freeName,
						@RequestParam(defaultValue="0") int modifyPrice,
						Model model) {
		String message = freeName + "메뉴의 가격을 " + modifyPrice + "로 변경하였습니다.";
		
		System.out.println(message);
		
		model.addAttribute("message", message);
		
		return "first/messagePrinter";
	}
	
	@PostMapping("/modifyAll")
	public String modify(@RequestParam Map<String, String> parameters, Model model) {
		
		String modifyName = parameters.get("modifyName2");
		int modifyPrice = Integer.valueOf(parameters.get("modifyPrice2"));
		
		String message = modifyName + "메뉴의 가격을 " + modifyPrice + "로 변경하였습니다.";
		
		model.addAttribute("message", message);
		
		return "first/messagePrinter";
	}
	
	/*
	 * 3. @ModelAttribute를 이용하는 방법
	 * 
	 * DTO 같은 모델을 커맨드 객체를 사용해서 전달 받아보자.
	 * 
	 * @ModelAttribute의 경우 커맨드 객체를 생성하여 매개변수로 전달해 준 뒤 해당 인스턴스를 Model에
	 * 담는다.
	 * 
	 * @ModelAttribute("모델에 담을 key값")을 지정할 수 있으며, 지정하지 않으면 타입의 앞 글자를 소문자로한
	 * 네이밍 규칙을 따른다. 즉, menuDTO라는 키 값으로 모델에 담긴다.
	 */
	@GetMapping("/search")
	public void search() {}
	
	@PostMapping("/search")
	public String search(@ModelAttribute("menu") MenuDTO menu) {
		return "first/searchResult";
	}
	
	/* HttpSession 이용하기 */
	@GetMapping("/login")
	public void login() {}
	
	@PostMapping("/login1")
	public String loginTest(@RequestParam String id
						  , @RequestParam String pwd
						  , HttpSession session) {	// 서블릿에서와 달리 HttpSession 객체를 바로 활용 가능
		
		session.setAttribute("id", id);
		session.setAttribute("pwd", pwd);
		session.setAttribute("name", "오레오");
		
		return "first/loginResult";
	}
	
	@GetMapping("/logout1")
	public String logoutTest(HttpSession session) {
		session.invalidate();
		
		return "first/loginResult";
	}
	
	/*
	 * 기존의 HttpSession에서 제공하는 invalidate() 메소드로는 @SessionAttributes로 등록시
	 * attribute 무효화가 되지 않고 SessionStatus의 setComplete() 메소드를 호출해야 사용이 만료된다.
	 */
	@PostMapping("/login2")
	public String loginTest2(@RequestParam String id
			               , @RequestParam String pwd
			               , Model model) {
		model.addAttribute("id", id);
		model.addAttribute("pwd", pwd);
		model.addAttribute("name", "육레오");
		
		return "first/loginResult";
	}
	
	@GetMapping("/logout2")
	public String logoutTest2(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		
		return "first/loginResult";
	}
	
	@GetMapping("/body")
	public void requestBody() {}
	
	@PostMapping("/body")
	public String requestBody(
			@RequestBody String list,
			@RequestHeader("content-type") String contentType,
			@CookieValue("JSESSIONID") String sessionId
			) {
		System.out.println("RequestBody에 담겨온 내용 확인: " + list);
		System.out.println("RequestHeader에서 content-type속성만 확인: " + contentType);
		System.out.println("Cookie에 담긴 JSESSIONID 속성만 확인: " + sessionId);
		
		return "main";
	}
	
	@PostMapping("/test")
	public String test(
			@RequestBody String bodyData) {
		System.out.println("여기로 오나");
		System.out.println("body로 넘어온 data: " + bodyData);
		
		/* 화면단에서 전달받은 json문자열을 자바에서 파싱 처리(해당 자바의 타입으로 변환 및 짤라내기)해 보기 */
//		JsonParser parser = new JsonParser();
//		JsonElement element = parser.parse(bodyData);
//		JsonObject object = element.getAsJsonObject();
		
		JsonObject object = new Gson().fromJson(bodyData, JsonObject.class);
		String message = object.getAsJsonObject().get("message").getAsString();
		
		System.out.println(message);
		
		return "main";
	}
	
	/*
	 * PathVariable로 전달되는 값은 매개변수 이름이 동일하게 쓰이는 것이 편하다.
	 * 만약 동일하지 않으면 @PathVariable("이름")을 설정해 주어야 한다.
	 * 
	 * 핸들러 메소드에서 요청 객체를 들춰서 전달된 값을 꺼내볼 필요 없이 url 경로에 위치한 값을
	 * value로 인식하는 방법이다.
	 * 특히 REST형 웹 서비스를 설계할 때 유용하다.
	 * 
	 * 스프링 4.3버전부터 지원되는 어노테이션들
	 * 요청 메소드           어노테이션
	 * POST				   @PostMapping
	 * GET				   @GetMapping
	 * DELETE			   @DeleteMapping
	 * PUT				   @PutMapping
	 * ...				   ...
	 */
	@GetMapping("/delete/{orderNo}")
	public String deleteOrder(@PathVariable("orderNo") int num) {
		System.out.println("삭제하기 위해 넘어온 주문 번호: " + num);
		
		return "main";
	}
}















