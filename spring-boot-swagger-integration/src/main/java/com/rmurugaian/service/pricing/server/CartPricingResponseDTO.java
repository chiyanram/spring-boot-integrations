package com.rmurugaian.service.pricing.server;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.money.CurrencyUnit;

/**
 * @author yjiao 2019-05-17
 */
@ApiModel(description = "com.rmurugaian.service.pricing.server.CartPricingResponseDTO")
public class CartPricingResponseDTO {

    private CurrencyUnit currencyUnit;

    @ApiModelProperty(example = "[\"USD\"]")
    private ImmutableList<CurrencyUnit> targetCurrencies;
    @ApiModelProperty(required = true,
        value = "Computed values of total Points/Prices",
        dataType = "com.rmurugaian.service.pricing.server.CartPriceResultsDTOMap")
    private ImmutableMap<String, CartPriceResultsDTO> prices;

    public ImmutableMap<String, CartPriceResultsDTO> getPrices() {
        return prices;
    }

    public void setPrices(final ImmutableMap<String, CartPriceResultsDTO> prices) {
        this.prices = prices;
    }

    public CurrencyUnit getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(final CurrencyUnit currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    public ImmutableList<CurrencyUnit> getTargetCurrencies() {
        return targetCurrencies;
    }

    public void setTargetCurrencies(final ImmutableList<CurrencyUnit> targetCurrencies) {
        this.targetCurrencies = targetCurrencies;
    }
}
