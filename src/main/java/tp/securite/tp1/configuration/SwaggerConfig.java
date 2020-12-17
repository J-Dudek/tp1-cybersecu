package tp.securite.tp1.configuration;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  private static final String AUTHORIZATION = "Authorization";

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)//
            .select()//
            .apis(RequestHandlerSelectors.any())//
            .paths(Predicates.not(PathSelectors.regex("/error")))//
            .build()//
            .apiInfo(metadata())//
            .useDefaultResponseMessages(false)//
            .securitySchemes(Collections.singletonList(apiKey()))
            .securityContexts(Collections.singletonList(securityContext()))
            .tags(new Tag("USERS", "Operations about users."))
            .tags(new Tag("BOOKS","User operation on their books collection."))
            .tags(new Tag("LIBRARY","Consult catalogue, user can take a book here."))
            .genericModelSubstitutes(Optional.class);

  }

  private ApiInfo metadata() {
    return new ApiInfoBuilder()//
        .title("TP de Cybersécurité - Projet Troué")//
        .description("Apres vous être enregistré ou authentifié , cliquer sur`Authorize` et introduire le token obtenu en reponse précédé de `Bearer `.")//
        .version("0.0.1-SNAPSHOT")//
        .license("MIT License").licenseUrl("http://opensource.org/licenses/MIT")//
        .contact(new Contact(null, null, "julien.dudek@lacatholille.fr"))//
        .build();
  }
  
  private ApiKey apiKey() {
    return new ApiKey(AUTHORIZATION, AUTHORIZATION, "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(defaultAuth())
        .forPaths(PathSelectors.any())
        .build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Arrays.asList(new SecurityReference(AUTHORIZATION, authorizationScopes));
  }

}