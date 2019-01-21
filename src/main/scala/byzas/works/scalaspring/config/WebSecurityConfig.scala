package byzas.works.scalaspring.config

import javax.sql.DataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.{EnableWebSecurity, WebSecurityConfigurerAdapter}
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig(@Autowired val dataSource: DataSource) extends WebSecurityConfigurerAdapter {

  override def configure(http: HttpSecurity): Unit = {
    http.authorizeRequests.antMatchers("/console", "/console/**", "/console/", "/swagger-ui.html", "/**/*.css", "/**/*.js", "/**/*.png", "/configuration/**", "/swagger-resources", "/v2/**", "/api/**").permitAll
    http.authorizeRequests.anyRequest.authenticated
    http.csrf.disable
    http.headers.frameOptions.disable
    http.httpBasic
  }

  @Bean override def userDetailsService(): UserDetailsService = {
    val manager = new JdbcDaoImpl
    manager.setDataSource(dataSource)
    manager
  }

}
