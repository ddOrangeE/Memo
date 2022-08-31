package com.soyaa.memo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
	
	// static : 객체 생성 없이 메소드 사용가능!! (멤버변수 없음 : 멤버변수 없이 메소드만 순수하게 사용될 때 사용하는 방법)
	
	// 암호화 기능
	public static String md5(String message) {
		// 암호화된 결과를 return 할 것이니까 return 값 String
		// 암호를 전달 받아야하니까 parameter 값 받기
		
		String resultData = ""; // try 안에 있으면 return 이 안됨
		
		// 암호화를 할 수 있는 암호 클래스를 만들어서 이 변수에 넣어라
		// 암호화 할수 있는 객체 만들기 (바이트 단위로 암호화해준다)
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			
			// 문자열 -> byte[]
			byte[] bytes = message.getBytes();
			md.update(bytes);
			
			// 암호화된 결과 얻어 오기
			byte[] digest = md.digest();
			
			// 16진수 형태의 문자열로 변환 (배열안에 있는 값 하나하나 꺼내서 문자열로 변환)
			for(int i = 0; i < digest.length; i++) {
				resultData += Integer.toHexString(digest[i] & 0xff); // 비트연산
			}
			
//			digest[i] & 0xff : 비트 연산
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		
		return resultData;
	}
}
