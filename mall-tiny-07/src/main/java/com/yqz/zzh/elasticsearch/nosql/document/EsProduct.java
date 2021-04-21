package com.yqz.zzh.elasticsearch.nosql.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author zzh
 * @Description 搜索中商品的信息
 * @date 2020-09-26 20:55
 */
//indexName索引库名，个人建议以项目名称命名 type类型，个人建议以实体类名称命名
//shards分区数  replicas每个分区的备份数
//@Document(indexName = "${productIndexName}", type = "product",shards = 1, replicas = 0, refreshInterval = "-1")
@Document(indexName = "pms", type = "product",shards = 1, replicas = 0, refreshInterval = "-1")
@Data
public class EsProduct implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String productSn;//货号
    private Long brandId;
    @Field(type = FieldType.Keyword)
    private String brandName;
    private Long productCategoryId;
    @Field(type = FieldType.Keyword)
    private String productCategoryName;//商品分类名称
    private String pic;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String name;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String subTitle;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String keywords;
    private BigDecimal price;
    private Integer sale;//销量
    private Integer newStatus;//是否是新品
    private Integer recommandStatus;//是否是推荐的
    private Integer stock;//库存
    private Integer promotionType;//促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购
    private Integer sort;//排序
    @Field(type = FieldType.Nested)//对象内部list集合复杂对象类型
    List<EsProductAttributeValue> attrValueList;
}
