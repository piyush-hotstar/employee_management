package com.hotstar.employee_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;

@EnableCaching
@SpringBootApplication
public class EmployeeManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(EmployeeManagementApplication.class, args);
//		CaffeineCacheManager cacheManager = new CaffeineCacheManager("employee");
		System.out.println("hola");
//		Cache nativeCache = (Cache) cacheManager.getCache("employee").getNativeCache();
//		System.out.println(nativeCache);
	}

}
