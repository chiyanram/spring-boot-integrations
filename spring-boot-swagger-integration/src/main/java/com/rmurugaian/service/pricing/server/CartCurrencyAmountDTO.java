package com.rmurugaian.service.pricing.server;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.money.Money;

import java.util.Optional;

/**
 * @author rmurugaian 2019-07-26
 */
@ApiModel(description = "Cart currency amount")
public class CartCurrencyAmountDTO {

    @ApiModelProperty(value = "The total price of the cart, expressed in a currency.", example = "CAD 91.00")
    private Money totalPrice;

    @ApiModelProperty(value = "The total price of the cart, expressed a currency, with no promotion applied.",
        example = "CAD 101.00")
    private Money unpromotedTotalPrice;

    @ApiModelProperty
    private Optional<Boolean> isBoolean;

    public Money getTotalPrice() {
        return totalPrice;
    }

    public Money getUnpromotedTotalPrice() {
        return unpromotedTotalPrice;
    }

    public void setTotalPrice(final Money totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setUnpromotedTotalPrice(final Money unpromotedTotalPrice) {
        this.unpromotedTotalPrice = unpromotedTotalPrice;
    }

    public Optional<Boolean> getIsBoolean() {
        return isBoolean;
    }

    public void setIsBoolean(final Optional<Boolean> isBoolean) {
        this.isBoolean = isBoolean;
    }
}
