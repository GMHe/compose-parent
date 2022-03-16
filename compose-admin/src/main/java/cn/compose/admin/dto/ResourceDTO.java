package cn.compose.admin.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName ResourceDTO
 * @Description 类描述
 * @Author hgm
 * @Date 2021/8/8 14:11
 * @Version 1.0
 **/
@Data
public class ResourceDTO {

    private String resourceId;
    private String resourceName;
    private String resourceType;
    private String describe;
    private BigDecimal price;
    private String imageUrl;
    private String resourceUrl;
    private String downloadPwd;
}
