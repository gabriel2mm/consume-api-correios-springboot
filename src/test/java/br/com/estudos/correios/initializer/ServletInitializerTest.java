package br.com.estudos.correios.initializer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CorreiosApplication.class)
class ServletInitializerTest {

    @Mock
    private SpringApplicationBuilder springApplicationBuilder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void configure() {
        ServletInitializer servletInitializer = new ServletInitializer();
        Mockito.when(springApplicationBuilder.sources(CorreiosApplication.class)).thenReturn(springApplicationBuilder);
        SpringApplicationBuilder result = servletInitializer.configure(springApplicationBuilder);
        Mockito.verify(springApplicationBuilder).sources(CorreiosApplication.class);
        assertEquals(springApplicationBuilder,result);
    }
}