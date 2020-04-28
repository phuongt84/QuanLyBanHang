package spring.qlbh.QUANLYBANHANG.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import spring.qlbh.QUANLYBANHANG.dao.DonHangDAO;
import spring.qlbh.QUANLYBANHANG.dao.DongDonHangDAO;
import spring.qlbh.QUANLYBANHANG.dao.HangDAO;
import spring.qlbh.QUANLYBANHANG.dao.KhuyenMaiDAO;
import spring.qlbh.QUANLYBANHANG.dao.LoaiHangDAO;
import spring.qlbh.QUANLYBANHANG.dao.NguoiDungDAO;
import spring.qlbh.QUANLYBANHANG.dao.impl.DonHangDAOImpl;
import spring.qlbh.QUANLYBANHANG.dao.impl.DongDonHangDAOImpl;
import spring.qlbh.QUANLYBANHANG.dao.impl.HangDAOImpl;
import spring.qlbh.QUANLYBANHANG.dao.impl.KhuyenMaiDAOImpl;
import spring.qlbh.QUANLYBANHANG.dao.impl.LoaiHangDAOImpl;
import spring.qlbh.QUANLYBANHANG.dao.impl.NguoiDungDAOImpl;


@Configuration
@ComponentScan("spring.qlbh.QUANLYBANHANG.*")
@EnableTransactionManagement
//Load to Environment.
@PropertySource("classpath:ds-hibernate-cfg.properties")
public class ApplicationContextConfig {
	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	@Bean(name = "multipartResolver")
	public MultipartResolver getMultipartResolver() {
		CommonsMultipartResolver resover = new CommonsMultipartResolver();
		// 1MB
		resover.setMaxUploadSize(1 * 1024 * 1024);

		return resover;
	}
	
	@Autowired
	private Environment env;

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
		dataSource.setUrl(env.getProperty("ds.url"));
		dataSource.setUsername(env.getProperty("ds.username"));
		dataSource.setPassword(env.getProperty("ds.password"));

		return dataSource;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
		Properties properties = new Properties();

		// Xem: ds-hibernate-cfg.properties
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		properties.put("current_session_context_class", env.getProperty("current_session_context_class"));

		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setPackagesToScan(new String[] { "spring.qlbh.QUANLYBANHANG.entity" }); ////
		factoryBean.setDataSource(dataSource);
		factoryBean.setHibernateProperties(properties);
		factoryBean.afterPropertiesSet();
		//
		SessionFactory sf = factoryBean.getObject();
		return sf;
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	// Load property in message/validator.properties

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
		// Load property in message/validator.properties
		rb.setBasenames(new String[] { "messages/validator" });
		return rb;
	}


	@Bean(name="hang_DAO")
	public HangDAO loadHang() {
		return new HangDAOImpl();
	}
	@Bean(name="loai_hang_DAO")
	public LoaiHangDAO loadMenu() {
		return new LoaiHangDAOImpl();
	}
	@Bean(name="don_hang_DAO")
	public DonHangDAO loadDonHang() {
		return new DonHangDAOImpl();
	}
	@Bean(name="dong_don_hang_DAO")
	public DongDonHangDAO XemDonHang() {
		return new DongDonHangDAOImpl();
	}
	@Bean(name="nguoi_dung_DAO")
	public NguoiDungDAO XemNguoiDung() {
		return new NguoiDungDAOImpl();
	}
	@Bean(name="khuyen_mai_DAO")
	public KhuyenMaiDAO XemKhuyenMai() {
		return new KhuyenMaiDAOImpl();
	}
}
