package com.rmurugaian.service.pricing.server;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.ImmutableSet;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ModelPropertyBuilder;
import springfox.documentation.schema.ModelProperty;
import springfox.documentation.schema.ResolvedTypes;
import springfox.documentation.schema.TypeNameExtractor;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rmurugaian 2019-11-21
 */
@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1008)
public class PricingApiModelPlugin implements ModelBuilderPlugin {

    private static final ImmutableSet<String> CURRENCIES = ImmutableSet.of("USD", "CAD");
    private static final ImmutableSet<String> FIELDS = ImmutableSet.of("varMarkup", "agentMarkup");

    private final TypeResolver typeResolver;
    private final TypeNameExtractor typeNameExtractor;

    public PricingApiModelPlugin(
        final TypeResolver typeResolver,
        final TypeNameExtractor typeNameExtractor) {

        this.typeResolver = typeResolver;
        this.typeNameExtractor = typeNameExtractor;
    }

    @Override
    public void apply(final ModelContext context) {
        final Class<?> erasedType = context.resolvedType(typeResolver).getErasedType();

        if (erasedType.equals(CartPriceResultsDTOMap.class)) {
            processPricesMap(context);
        }

        if (erasedType.equals(CartCurrencyAmountDTOMap.class)) {
            processCurrenciesMap(context);
        }
    }

    @Override
    public boolean supports(final DocumentationType delimiter) {
        return true;
    }

    private void processCurrenciesMap(final ModelContext context) {
        final Map<String, ModelProperty> currenciesProperties = new HashMap<>();
        CURRENCIES.forEach(currencyUnit -> {
            final ModelProperty currencyProperty = new ModelPropertyBuilder()
                .name(currencyUnit)
                .type(typeResolver.resolve(CartCurrencyAmountDTO.class))
                .required(true)
                .isHidden(false)
                .build();
            currencyProperty.updateModelRef(ResolvedTypes.modelRefFactory(context, typeNameExtractor));
            currenciesProperties.put(currencyUnit, currencyProperty);
        });

        context.getBuilder()
            .name("CartCurrencyAmountDTOMap")
            .properties(currenciesProperties)
            .build();
    }

    private void processPricesMap(final ModelContext context) {
        final Map<String, ModelProperty> pricesProperties = new HashMap<>();
        FIELDS
            .forEach(pricingField -> {
                final ModelProperty currencyProperty = new ModelPropertyBuilder()
                    .name(pricingField)
                    .type(typeResolver.resolve(CartPriceResultsDTO.class))
                    .required(true)
                    .isHidden(false)
                    .build();
                currencyProperty.updateModelRef(ResolvedTypes.modelRefFactory(context, typeNameExtractor));
                pricesProperties.put(pricingField, currencyProperty);
            });

        context.getBuilder()
            .name("CartPriceResultsDTOMap")
            .properties(pricesProperties)
            .build();
    }
}
