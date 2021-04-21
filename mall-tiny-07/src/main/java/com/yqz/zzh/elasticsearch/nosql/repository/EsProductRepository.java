package com.yqz.zzh.elasticsearch.nosql.repository;

import com.yqz.zzh.elasticsearch.nosql.document.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author zzh
 * @Description
 * @date 2020-09-27 21:05
 */
public interface EsProductRepository extends ElasticsearchRepository<EsProduct, Long> {

    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keyWords, Pageable pageable);
}
