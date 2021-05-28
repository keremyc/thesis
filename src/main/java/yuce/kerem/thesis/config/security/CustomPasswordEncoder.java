package yuce.kerem.thesis.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/25/21 2:54 PM
 */

@Configuration
public class CustomPasswordEncoder {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
