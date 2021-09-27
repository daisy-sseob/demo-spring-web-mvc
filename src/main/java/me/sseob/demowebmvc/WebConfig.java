package me.sseob.demowebmvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	/*
		@MatrixVariable 을 이용해 mapping하기 위한 설정.
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		urlPathHelper.setRemoveSemicolonContent(false); // semi colon을 삭제하지 않도록 설정한다.
		configurer.setUrlPathHelper(urlPathHelper);
	}
}
