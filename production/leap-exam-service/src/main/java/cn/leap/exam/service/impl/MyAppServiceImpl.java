package cn.leap.exam.service.impl;

import cn.leap.exam.model.MyApp;
import cn.leap.exam.repository.impl.MyAppRepository;
import cn.leap.utils.bean.BeanToMapUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: yantingjun
 * Time: 2014-09-27 17:31:39 中国标准时间
 */
@Service("MyAppService")
public class MyAppServiceImpl implements MyAppService {
    private static final Logger LOG = LoggerFactory.getLogger(MyAppServiceImpl.class);
    @Resource
    private MyAppRepository myAppRepository;

    @Override
    public List<MyApp> findAll() {
        return myAppRepository.findAll();
    }

    @Override
    public MyApp findId(long id) {
        return myAppRepository.findOne(id);
    }

    @Override
    public List<MyApp> findByIds(List<Long> ids) {
        Query query = new Query().addCriteria(new Criteria("id").in(ids));
        return myAppRepository.findByQuery(query);
    }

    @Override
    public boolean save(MyApp entity) {
        return myAppRepository.save(entity);
    }

    @Override
    public MyApp findOne(Long id) {
        return myAppRepository.findOne(id);
    }

    @Override
    public boolean delete(Long id) {
        return myAppRepository.delete(id);
    }
    @Override
    public List<MyApp> queryByParams(Map<String,Object> params,int begin,int num,Map<String,String> orders){
        Query query = new Query();
        for(Map.Entry<String,Object> entry: (Set<Map.Entry<String,Object>)params.entrySet()){
            query.addCriteria(new Criteria(entry.getKey()).is(entry.getValue()));
        }
        for(Map.Entry<String,String> entry: (Set<Map.Entry<String,String>)params.entrySet()){
            if("desc".equalsIgnoreCase(entry.getValue())){
                query.sort().on(entry.getKey(),Order.DESCING);
            }else if("asc".equalsIgnoreCase(entry.getValue())){
                query.sort().on(entry.getKey(),Order.ASCING);
            }
        }
        query.skip(begin).limit(num);
        return myAppRepository.findByQuery(query);
    }
}