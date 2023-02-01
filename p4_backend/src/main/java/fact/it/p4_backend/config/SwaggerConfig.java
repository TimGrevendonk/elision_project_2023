package fact.it.p4_backend.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/api");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Elision API")
                .description("List all endpoints of the Elision project")
                .version("1.0")
                .build();
    }
}
// http://localhost:8080/swagger-ui.html
