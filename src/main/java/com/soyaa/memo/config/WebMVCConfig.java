package com.soyaa.memo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.soyaa.memo.common.FileManagerService;
import com.soyaa.memo.common.PermissionInterceptor;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
	
	@Autowired
	private PermissionInterceptor interceptor;

	// 서버의 특정 경로의 파일을
	// 설정한 경로로 외부에서 접근 가능하도록 설정한다.
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		// 이미지를 어떠한 경로를 포함하면 접근가능하도록 해주겠다
		registry.addResourceHandler("/images/**") 
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH + "/"); // 어떤 디렉토리에 접근가능하게 할거냐
		
		
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(interceptor)
		.addPathPatterns("/**")  // 인터셉터를 거쳐서 처리할 페이지 규칙 (ex:.addPathPatterns("/user/**") -> user 와 관련된 페이지만 인터셉터를 거쳐간다)
		.excludePathPatterns("/static/**", "/images/**"); // 제외할 페이지 규칙
	}
	
}
