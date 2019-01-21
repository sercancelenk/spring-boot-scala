package byzas.works.scalaspring.config

import org.h2.server.web.WebServlet
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class H2Config {

  @Bean
  def h2ServletRegistration(): ServletRegistrationBean[WebServlet] = {
    val registrationBean = new ServletRegistrationBean(new WebServlet)
    registrationBean.addUrlMappings("/console/*")
    registrationBean
  }

}
