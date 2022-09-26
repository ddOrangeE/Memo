package com.soyaa.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	
	// 다른 데에도 쓸 거니까 멤버변수로 만들어 놓기
	public static final String FILE_UPLOAD_PATH = "D:\\99김소연\\spring\\memo\\upload";
	// \는 특수부호기 때문에 구분하기 위해 2개씩 들어감(자바가 알아서 처리해줌)
	// 프로그램 적으로 변수가 수정되지 못하게 하도록 final 키워드 붙여주기 
	// final 변수이름은 일반적으로 전체 대문자
	
	// 파일을 저장하고, 해당 파일을 접근할 경로를 돌려주는 기능
	// 여기서 접근하는 경로란? 클라이언트가 접근할 수 있는 경로!! 즉 url
	public static String saveFile(int userId, MultipartFile file) {
	// 무분별하게 static 을 사용하는 것은 좋지 않으나, 쓸 때마다 객체 생성을 하는 것이 더 효율성이 떨어지기 때문에 static 붙여주기
		
		
		// 서버에 저장할 위치를 잡아준다.
		// D:\\99김소연\\spring\\memo\\upload
		// 이대로 올리면 좋지만 여러 사람들이 똑같은 이름으로 파일을 올릴 수 있다! 이걸 방지하는 방법
		// 디렉토리 (폴더) 생성 : 사용자 별로 폴더를 만들면 좋다! 
		// 사용자 id
		// 한사람의 사용자라고 해서 똑같은 이름의 파일을 안올릴 거라는 보장x
		// 현재시간!! 
		// 사용자 id + 현재 시간
		// 1_16:00:11 근데 너무 안예쁘고 구분하기에도 정신 없어보임
		// UNIX TIME : 1970년 1월 1일을 기준으로 현재까지 흐른 시간 (millisecond기준 millisecond: 1/1000 초)
		// 1_45651445145
		// D:\\99김소연\\spring\\memo\\upload\\1_45651445145\\asdf.jpg
		
		// 기본 경로는 역슬래쉬, 우리가 표현하는 경로는 슬래쉬로 구분
		// /1_2131211232/
		String directoryName = "/" + userId + "_" + System.currentTimeMillis() + "/";
		
		// 디렉토리 생성 (디렉토리도 파일이기 때문에 생성해주어야 한다)
		String filePath =  FILE_UPLOAD_PATH + directoryName;
		File directory = new File(filePath);
		if(directory.mkdir() == false) {
			// 디렉토리 생성 실패!
			return null;
		}
		
	  // file 안에 있는 실제 파일을 꺼내야한다 (byte) 형태
		try {
			byte[] bytes = file.getBytes(); // 파일 정보 꺼내는 법
			
			// bytes 배열에 저장되어 있는 것을 디렉토리에 저장해야한다.
			String fileFullPath = filePath + file.getOriginalFilename(); // 실제 파일 이름 가지고 오는 법
			
			// 파일 경로를 관리하는 객체 : Path
			Path path = Paths.get(fileFullPath);
			
			// 파일 저장
			Files.write(path, bytes);
			
			// 객체 이름 뒤에 메소드 : static!! 
		} catch (IOException e) {
			e.printStackTrace();
			
			// 파일 저장 실패
			return null;
		}
		
		// 클라이언트에서 접근 가능한 경로
		// D:\\99김소연\\spring\\memo\\upload 해당 디렉토리 아래 경로
		// /images/~ 
		
		return "/images" + directoryName + file.getOriginalFilename();
	}
	
	// 파일 삭제 기능
	public static boolean removeFile(String filePath) { // /images/2_90812098510/test.png
		
		// 삭제 경로는 /images 를 제거 하고,
		// 실제 파일 저장 경로를 이어 붙여주면 된다.
		// D:\\김인규쌤\\web_0823\\spring\\memo\\upload/2_9081098510/test.png
		
		if(filePath == null) {
			return true;
		}
		
		String realFilePath = FILE_UPLOAD_PATH + filePath.replace("/images", "");
		
		Path path = Paths.get(realFilePath);
		
		// 파일이 있는 지 확인
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
	//	D:\\김인규쌤\\web_0823\\spring\\memo\\upload/2_90812098510
			
		path = path.getParent();
		
		// 디렉토리 존재하는
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
			
		return true;
	}
		
}
