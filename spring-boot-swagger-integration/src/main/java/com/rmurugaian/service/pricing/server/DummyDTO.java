package com.rmurugaian.service.pricing.server;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Dummy Swagger DTO")
@Data
public class DummyDTO {

    @ApiModelProperty(required = true, name = "Dummy value")
    private String dummyValue;
}
