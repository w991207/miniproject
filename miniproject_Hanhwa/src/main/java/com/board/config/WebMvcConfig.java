package com.board.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	

	
	//구현된 interceptor 객체를 등록한다.
	@Override	
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginIntercepter())
				.order(1) //우선순위 설정
				.addPathPatterns("/**")
				//전체 요청에 대해 적용
				.excludePathPatterns("/error",
						             "/board/boardList"
									,"/board/boardDetail"
									,"/board/mulDel"
									,"/board/boardUpdate"
						            ,"/","/user/**","/css/**","/js/**","/webapp/**");
	
//		registry.addInterceptor(new LoginInterceptor())
//		.order(2)
//		.addPathPatterns("/**")   //전체 요청에 대해 적용
//		.excludePathPatterns("/board","/","/user/**","/css/**","/js/**");
	}
	
	
}




