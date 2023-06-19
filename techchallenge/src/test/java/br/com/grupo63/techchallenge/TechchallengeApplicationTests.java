package br.com.grupo63.techchallenge;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class TechchallengeApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("testes!");
    }

}
