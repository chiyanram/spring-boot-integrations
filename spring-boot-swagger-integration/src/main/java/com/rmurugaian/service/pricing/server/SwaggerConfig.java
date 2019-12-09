package com.rmurugaian.service.pricing.server;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.ImmutableList;
import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;

/**
 * @author vmuniyandi 2/9/2017
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final TypeResolver typeResolver;

    @Autowired
    public SwaggerConfig(final TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    @Bean
    public Docket docketApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.rmurugaian.service.pricing.server"))
            .build()
            .genericModelSubstitutes(Optional.class)
            .directModelSubstitute(CurrencyUnit.class, String.class)
            .directModelSubstitute(Money.class, String.class)
            .directModelSubstitute(BigMoney.class, String.class)
            .alternateTypeRules(
                wildCardTypeSubstitute(Iterable.class, List.class),
                immutableTypeSubstitute(CurrencyUnit.class, List.class),
                optionalTypeSubstitute(CurrencyUnit.class, String.class),
                optionalTypeSubstitute(Money.class, String.class),
                optionalTypeSubstitute(BigMoney.class, String.class));
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
            .title("Pricing Service API")
            .description("Service provides to access Pricing")
            .version("6.x")
            .build();
    }

    private AlternateTypeRule optionalTypeSubstitute(
        final Class<?> optionalParamType,
        final Class<?> substitutionType) {

        return new AlternateTypeRule(
            typeResolver.resolve(Optional.class, optionalParamType),
            typeResolver.resolve(substitutionType),
            Ordered.HIGHEST_PRECEDENCE);
    }

    private AlternateTypeRule immutableTypeSubstitute(
        final Class<?> optionalParamType,
        final Class<?> substitutionType) {

        return new AlternateTypeRule(
            typeResolver.resolve(ImmutableList.class, optionalParamType),
            typeResolver.resolve(substitutionType, String.class),
            Ordered.HIGHEST_PRECEDENCE);
    }

    private AlternateTypeRule wildCardTypeSubstitute(
        final Class<?> originalType,
        final Class<?> substitutionType) {

        return new AlternateTypeRule(
            typeResolver.resolve(originalType, WildcardType.class),
            typeResolver.resolve(substitutionType, WildcardType.class));
    }
}
