package com.rmurugaian.service.pricing.server;

import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.money.CurrencyUnit;

import java.util.Optional;

/**
 * @author yjiao 2019-07-26
 */
@ApiModel(description = "Cart prices")
public class CartPriceResultsDTO {

    @ApiModelProperty(value = "Total points price.  Optional in the case of cash-only programs.",
        example = "12000")
    private Optional<Integer> totalPoints;

    @ApiModelProperty(value = "Total points price, with no promotions applied.  Optional in the case of cash-only "
        + "programs.",
        example = "156000")
    private Optional<Integer> unpromotedTotalPoints;

    @ApiModelProperty(value = "Currency prices for the cart.",
        dataType = "com.rmurugaian.service.pricing.server.CartCurrencyAmountDTOMap")
    private ImmutableMap<CurrencyUnit, CartCurrencyAmountDTO> currencies;

    public Optional<Integer> getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(final Optional<Integer> totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Optional<Integer> getUnpromotedTotalPoints() {
        return unpromotedTotalPoints;
    }

    public void setUnpromotedTotalPoints(final Optional<Integer> unpromotedTotalPoints) {
        this.unpromotedTotalPoints = unpromotedTotalPoints;
    }

    public ImmutableMap<CurrencyUnit, CartCurrencyAmountDTO> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(final ImmutableMap<CurrencyUnit, CartCurrencyAmountDTO> currencies) {
        this.currencies = currencies;
    }
}
