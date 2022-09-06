package com.soyaa.memo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.soyaa.memo.common.FileManagerService;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

	// 서버의 특정 경로의 파일을
	// 설정한 경로로 외부에서 접근 가능하도록 설정한다.
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		// 이미지를 어떠한 경로를 포함하면 접근가능하도록 해주겠다
		registry.addResourceHandler("/images/**") 
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH + "/"); // 어떤 디렉토리에 접근가능하게 할거냐
		
		
	}
	
}
