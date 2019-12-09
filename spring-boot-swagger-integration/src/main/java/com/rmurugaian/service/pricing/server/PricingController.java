package com.rmurugaian.service.pricing.server;

import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author rmurugaian 2019-11-26
 */
@RestController
@Api
public class PricingController {

    @GetMapping("/prices")
    public CartPricingResponseDTO cartPricingResponseDTO() {
        final CartCurrencyAmountDTO cartCurrencyAmountDTO = new CartCurrencyAmountDTO();
        cartCurrencyAmountDTO.setTotalPrice(Money.of(CurrencyUnit.USD, BigDecimal.TEN));

        final CartPriceResultsDTO cartPriceResultsDTO = new CartPriceResultsDTO();
        cartPriceResultsDTO.setCurrencies(ImmutableMap.of(CurrencyUnit.USD, cartCurrencyAmountDTO));

        final ImmutableMap<String, CartPriceResultsDTO> map = ImmutableMap.of("displayPrice", cartPriceResultsDTO);
        final CartPricingResponseDTO cartPricingResponseDTO = new CartPricingResponseDTO();

        cartPricingResponseDTO.setPrices(map);

        return cartPricingResponseDTO;
    }

}
