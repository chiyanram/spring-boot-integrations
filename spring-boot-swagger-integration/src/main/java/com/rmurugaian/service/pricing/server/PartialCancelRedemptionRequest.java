package com.rmurugaian.service.pricing.server;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.money.Money;

import java.time.OffsetDateTime;
import java.util.Currency;
import java.util.Map;
import java.util.Optional;

/**
 * @author rmurugaian 2019-12-10
 */
@ApiModel(description = "RESTful API for Partial Cancel Redemption Request")
public interface PartialCancelRedemptionRequest {

    @ApiModelProperty(value = "Globally unique order id, typically the Platform order ID", required = true)
    String getOrderId();

    @ApiModelProperty(value = "Order ID assigned by Var from original redemption", required = true)
    String getVarOrderId();

    @ApiModelProperty(value = "Unique ID assigned by B2S", required = true)
        //TODO: When is this ID created and why isn't it on the full cancellation?
    String getCancellationId();

    // Not required in the API but we default to 0 in the constructor if not provided
    @ApiModelProperty(value = "Portion of cash buy-in amount from original order that will be refunded to the user",
        required = false)
    Money getCashBuyInRefund();

    @ApiModelProperty(value = "Currency of the partial cancellation", required = false)
    Optional<Currency> getCurrency();

    @ApiModelProperty(value = "Date time of the partial order cancelled", required = false)
    Optional<OffsetDateTime> getCancelDateTime();

    @ApiModelProperty(value = "True denotes multi lines", required = false)
    boolean isFullReturn();

    @ApiModelProperty(value = "Additional attributes that the Var may interpret in a custom manner", required = false)
    Map<String, Object> getAdditionalInfo();

}
