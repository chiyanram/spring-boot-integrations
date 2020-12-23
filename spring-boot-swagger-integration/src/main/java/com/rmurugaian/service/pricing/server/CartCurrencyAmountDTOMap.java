package com.rmurugaian.service.pricing.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author rmurugaian 2019-11-21
 */
@ApiModel
public class CartCurrencyAmountDTOMap {

    @JsonProperty(value = "AUD")
    private CartCurrencyAmountDTO aud;

    public CartCurrencyAmountDTO getAud() {
        return aud;
    }

    public void setAud(final CartCurrencyAmountDTO aud) {
        this.aud = aud;
    }
}
