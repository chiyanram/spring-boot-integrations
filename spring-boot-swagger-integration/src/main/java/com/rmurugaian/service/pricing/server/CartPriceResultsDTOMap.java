package com.rmurugaian.service.pricing.server;

import io.swagger.annotations.ApiModel;

/**
 * @author rmurugaian 2019-11-21
 */
@ApiModel
public class CartPriceResultsDTOMap {

    private CartPriceResultsDTO displayPrice;

    public CartPriceResultsDTO getDisplayPrice() {
        return displayPrice;
    }

    public void setDisplayPrice(final CartPriceResultsDTO displayPrice) {
        this.displayPrice = displayPrice;
    }
}
