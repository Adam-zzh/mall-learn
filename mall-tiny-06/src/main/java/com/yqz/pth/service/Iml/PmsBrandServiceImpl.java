package com.yqz.pth.service.Iml;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yqz.pth.mbg.mapper.PmsBrandMapper;
import com.yqz.pth.mbg.model.PmsBrand;
import com.yqz.pth.mbg.model.PmsBrandExample;
import com.yqz.pth.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author zzh
 * @Description
 * @date 2020-09-04 16:17
 */
@Service
@Transactional
public class PmsBrandServiceImpl implements PmsBrandService {

    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    @Override
    public List<PmsBrand> getlist(String name, Integer minProductCount, Integer maxProductCount) {
        PmsBrandExample example = new PmsBrandExample();
        example.setOrderByClause("id desc");
        PmsBrandExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andProductCountBetween(minProductCount,maxProductCount);
        List<PmsBrand> pmsBrands = pmsBrandMapper.selectByExample(example);
        return pmsBrands;
    }

    @Override
    public PageInfo<PmsBrand> getListByPaginition(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize, "id desc");
        List<PmsBrand> pmsBrands = pmsBrandMapper.selectByExample(new PmsBrandExample());
        PageInfo<PmsBrand> pmsBrandPageInfo = new PageInfo<>(pmsBrands);
        return pmsBrandPageInfo;
    }

    @Override
    public List<PmsBrand> getAllBranks() {
        return  pmsBrandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public int addBrand(PmsBrand brand) {
       return pmsBrandMapper.insertSelective(brand);
    }

    @Override
    public int updateById(Long id,PmsBrand brand) {
        brand.setId(id);
        return pmsBrandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public PageInfo<PmsBrand> queryBrandsByPaginition(PmsBrand brand, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize, "id desc");
        PmsBrandExample example = new PmsBrandExample();
        if (brand!=null){
            PmsBrandExample.Criteria criteria = example.createCriteria();
            if (!StringUtils.isEmpty(brand.getName())){
                criteria.andNameEqualTo(brand.getName());
            }
        }
        List<PmsBrand> list = pmsBrandMapper.selectByExample(example);
        PageInfo<PmsBrand> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int deleteBrandById(Long id) {
        return pmsBrandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PmsBrand getBrandById(Long id) {
        return pmsBrandMapper.selectByPrimaryKey(id);
    }

}