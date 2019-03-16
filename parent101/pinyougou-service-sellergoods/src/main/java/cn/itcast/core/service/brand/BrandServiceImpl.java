package cn.itcast.core.service.brand;

import cn.itcast.core.dao.good.BrandDao;
import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.good.Brand;
import cn.itcast.core.pojo.good.BrandQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    /**
     * 好处：
     * 1、spring的提供的服务，一个框架提供的服务越多性能低
     * 2、与框架的耦合度低
     */
    @Resource
    private BrandDao brandDao;

    @Override
    public List<Brand> findAll() {
        // 根据条件查询
        List<Brand> brands = brandDao.selectByExample(null);
        return brands;
    }

    //分页查询
    @Override
    public PageResult findPage(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        Page<Brand> page = (Page<Brand>) brandDao.selectByExample(null);
        PageResult pageResult = new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    //条件查询
    @Override
    public PageResult search(Integer pageNo, Integer pageSize, Brand brand) {
        PageHelper.startPage(pageNo,pageSize);
        BrandQuery brandQuery = new BrandQuery();
        BrandQuery.Criteria criteria = brandQuery.createCriteria();
        if (brand.getName() != null&&!"".equals(brand.getName().trim())){
            criteria.andNameLike("%"+brand.getName().trim()+"%");
        }
        if (brand.getFirstChar()!=null&&!"".equals(brand.getFirstChar().trim())){
            criteria.andFirstCharEqualTo(brand.getFirstChar().trim());
        }
        //根据ID降序排序
        brandQuery.setOrderByClause("id desc");
        Page<Brand> page = (Page<Brand>)brandDao.selectByExample(brandQuery);
        PageResult pageResult = new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    //保存品牌
    @Transactional
    @Override
    public void add(Brand brand) {
        brandDao.insertSelective(brand);
    }
}
