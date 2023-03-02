package hello.servelet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@ServletComponentScan // 서블릿 자동 등록
@SpringBootApplication
class ServeletApplication

fun main(args: Array<String>) {
	runApplication<ServeletApplication>(*args)
}
