package hello.servelet

import hello.servelet.web.springmvc.v1.SpringMemberFormControllerV1
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.annotation.Bean

@ServletComponentScan // 서블릿 자동 등록
@SpringBootApplication
class ServeletApplication

fun main(args: Array<String>) {
	@Bean
	fun springMemberFormControllerV1(): SpringMemberFormControllerV1 {
		println("SpringMemberFormControllerV1 Bean 등록!!")
		return SpringMemberFormControllerV1()
	}

	runApplication<ServeletApplication>(*args)
}


