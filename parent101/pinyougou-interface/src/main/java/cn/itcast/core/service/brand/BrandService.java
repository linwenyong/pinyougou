package cn.itcast.core.service.brand;

import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.good.Brand;

import java.util.List;

public interface BrandService {

    /**
     * 查询所有品牌
     * @return
     */
    public List<Brand> findAll();

    //分页
    PageResult findPage(Integer pageNo,Integer pageSize);

    //条件查询
    PageResult search(Integer pageNo,Integer pageSize,Brand brand);

    //保存品牌
    void add(Brand brand);
}
