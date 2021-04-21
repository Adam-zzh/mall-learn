package com.yqz.zzh.elasticsearch.nosql.document;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author zzh
 * @Description
 * @date 2020-09-26 21:24
 */
@Data
public class EsProductAttributeValue implements Serializable {
    private static final long serialVersionUID = -1L;

    private Long id;
    private Long productAttributeId;
    @Field(type = FieldType.Keyword)
    private String name;//属性名称
    @Field(type = FieldType.Keyword)
    private String value;//自定义属性值
    private Integer type;//属性类型0->规格；1->参数

}
