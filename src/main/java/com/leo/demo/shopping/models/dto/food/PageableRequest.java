package com.leo.demo.shopping.models.dto.food;

import com.leo.demo.shopping.models.base.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * need pageable request can extends this type of Request
 * @author leo
 * @date 2023/1/17
 */
@Data
public class PageableRequest extends BaseRequest {
    @ApiModelProperty(value = "page number")
    @NotNull
    @Min(value = 0, message = "page should great then 0")
    private Integer page;

    @ApiModelProperty(value = "page size")
    @NotNull
    @Range(min = 10, max = 100, message = "size should be in 10 to 100")
    private Integer size;

    public PageableRequest(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public PageableRequest() {
    }
}
