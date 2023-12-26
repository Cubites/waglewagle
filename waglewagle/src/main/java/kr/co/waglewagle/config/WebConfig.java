package kr.co.waglewagle.config;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.zaxxer.hikari.HikariDataSource;

import kr.co.waglewagle.auctions.won.AutionGoodsArgumentResolver;
import kr.co.waglewagle.users.ty.util.LoginInterceptor;
import kr.co.waglewagle.users.ty.util.LogoutInterceptor;

@Configuration
@ComponentScan(basePackages = "kr.co.waglewagle")
@EnableWebMvc
@MapperScan(basePackages = "kr.co.waglewagle", annotationClass = Mapper.class)

@EnableTransactionManagement
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${db.driver}")
	private String driver;
	@Value("${db.url}")
	private String url;
	@Value("${db.username}")
	private String username;
	@Value("${db.userpassword}")
	private String userpassword;
	@Value("${file.dir}")
	private String filePath;
	//외부 파일과 실제 디렉토리 경로 설정을 위해 추가
	@Value("${os.root}")
	private String osRoot;

	private String[] loginIntercepterExclude = { 
		"/",
		"/resources/**", 
		"/upload/**", 
		"/users/**", 
		"/board/noticelist/**",
		"/admin/**",
		"/goods/search/**",
		"/add/session/*" // [!추후삭제 필요][테스트용] 유저 세션 추가 기능
	};
	
	@Autowired
	MypageInterceptor mypageInterceptor; // 마이페이지용 인터셉터
	
	@Autowired
	LoginInterceptor loginInterceptor; // 로그인 인터셉터
	
	@Autowired
	LogoutInterceptor logoutInterceptor; // 비회원 체크 인터셉터

	@Autowired
	RelCaculateInterceptor relCaculateInterceptor;
	//argumentResolver등록
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(auctionArgumentResolver());
	}


	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver rs = new InternalResourceViewResolver();
		rs.setPrefix("/WEB-INF/views/");
		rs.setSuffix(".jsp");
		registry.viewResolver(rs);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		// [!추후삭제 필요] 외부 파일 읽어오기 위해서 추가  -> 이미지 파일 경로때문에 삭제될 코드!!!!!!!!!!!!!!!!!!!!!!!!!!
		registry.addResourceHandler("/upload/**").addResourceLocations("file:///"+osRoot+"/");
	}

	// db관련 bean등록
	@Bean(destroyMethod = "close")
	public HikariDataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();

		dataSource.setDriverClassName(driver);
		dataSource.setJdbcUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(userpassword);
		return dataSource;
	}

	// mybatis
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {

		SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		ssf.setMapperLocations(resolver.getResources("classpath:/*/**.xml"));
		return ssf.getObject();
	}

	// sqlSessionFactory
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}

	// 프로퍼티설정 파일 읽기
	@Bean
	public static PropertyPlaceholderConfigurer property() {
		PropertyPlaceholderConfigurer pro = new PropertyPlaceholderConfigurer();
		pro.setLocations(new ClassPathResource("db.properties"), new ClassPathResource("file.properties"),
				new ClassPathResource("mail.properties"));
		return pro;
	}

	// 인터셉터 모음
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// [마이페이지용 인터셉터] 마이페이지의 모든 페이지에 필요한 공통 작업 수행
		registry.addInterceptor(mypageInterceptor).addPathPatterns("/mypage/**").excludePathPatterns().order(2);
		// [모든 페이지 인터셉터] 로그인이 필요한 페이지에 비회원 접근 시, 로그인 페이지로 리다이렉트
		registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
													.excludePathPatterns(loginIntercepterExclude).order(1);
		// [비회원 페이지 인터셉터] 이미 로그인이 되어있을 때 접근할 필요가 없는 페이지(로그인, 회원가입, 회원찾기)로 접근 시, 이전 페이지로 되돌아가게 함
		registry.addInterceptor(logoutInterceptor).addPathPatterns("/users/login","/users/join", "/users/find_info").order(3);
		registry.addInterceptor(relCaculateInterceptor).addPathPatterns("/auctions/complete", "/auctions/report/complete", "/admin/goodsStatus").excludePathPatterns();

	}

	// 파일 업로드를 위한 bean
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(2000000);
		resolver.setDefaultEncoding("UTF-8");
		return resolver;
	}
		
	//messages를 읽기 위한 Bean
	@Bean 
	public MessageSource messageSource() {
		final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		
		
		
		messageSource.setBasename("/errorMessage/error");
		
		messageSource.setDefaultEncoding("utf-8");
		return messageSource;
	}
		
	// 트랜잭션 설정
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager dtm = new DataSourceTransactionManager();
		dtm.setDataSource(dataSource());
		return dtm;
	}
	
	//ArgumentResolver 등록
	@Bean
	public HandlerMethodArgumentResolver auctionArgumentResolver() {
		return new AutionGoodsArgumentResolver();
	}
}
