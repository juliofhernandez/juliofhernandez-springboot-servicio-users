package com.microservices.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringbootServicioUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootServicioUsersApplication.class, args);
    }

    /**
     * Crea un bean para PasswordEncoder utlizando BCryptPasswordEncoder.
     *
     * Este metodo define un bean de PasswordEncoder que proporciona un mecanismo seguro para codificar y validar contraseñas.
     * La instancia de PasswordEncoder devuleta puede ser inyectada en otros componentes de la aplicacion.
     * Un bean es un objeto administrado por el contenedor de Spring que puede ser inyectado en otras partes de la aplicación.
     *
     * @return Ina instancia de BCryptPasswordEncoder con la configuracion predeterminada
     * @see BCryptPasswordEncoder
     * @see PasswordEncoder
     *
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
