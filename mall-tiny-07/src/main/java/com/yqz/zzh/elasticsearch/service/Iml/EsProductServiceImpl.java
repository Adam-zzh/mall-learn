package com.yqz.zzh.elasticsearch.service.Iml;

import com.yqz.zzh.elasticsearch.mapper.EsProductDao;
import com.yqz.zzh.elasticsearch.nosql.document.EsProduct;
import com.yqz.zzh.elasticsearch.nosql.repository.EsProductRepository;
import com.yqz.zzh.elasticsearch.service.EsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zzh
 * @Description
 * @date 2020-09-27 21:20
 */
@Service
public class EsProductServiceImpl implements EsProductService {
    @Autowired
    private EsProductRepository productRepository;
    @Autowired
    private EsProductDao esProductDao;

    @Override
    public int importAll() {
        List<EsProduct> esProduct = esProductDao.getAllEsProductList(null);
        Iterable<EsProduct> esProductIterable = productRepository.saveAll(esProduct);
        Iterator<EsProduct> iterator = esProductIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public EsProduct create(Long id) {
        EsProduct result = null;
        List<EsProduct> esProductList = esProductDao.getAllEsProductList(id);
        if (esProductList.size() > 0) {
            EsProduct esProduct = esProductList.get(0);
            result = productRepository.save(esProduct);
        }
        return result;
    }

    @Override
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsProduct> esProductList = new ArrayList<>();
            for (Long id : ids) {
                EsProduct esProduct = new EsProduct();
                esProduct.setId(id);
                esProductList.add(esProduct);
            }
            productRepository.deleteAll(esProductList);
        }
    }

    @Override
    public Page<EsProduct> search(String keyWord, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<EsProduct> pageData = productRepository.findByNameOrSubTitleOrKeywords(keyWord, keyWord, keyWord, pageable);
        return pageData;
    }
}