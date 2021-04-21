package com.yqz.zzh.rabbitMQ.nosql.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author zzh
 * @Description
 * @date 2020-11-08 14:27
 */
@Document
@Data
public class MemberReadHistory {
    @Id
    private String id;

    @Indexed
    private Long memberId;

    private String memberNickName;

    private String memberIcon;

    @Indexed
    private Long productId;

    private String productName;

    private String productPic;

    private String productPrice;

    private String productSubTitle;

    private Date createTime;

}
