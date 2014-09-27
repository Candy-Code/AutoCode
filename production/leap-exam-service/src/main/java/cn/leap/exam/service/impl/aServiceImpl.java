package cn.leap.exam.service.impl;

import cn.leap.exam.model.a;
import cn.leap.exam.repository.impl.aRepository;
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
 * Time: 2014-09-27 18:13:31 中国标准时间
 */
@Service("aService")
public class aServiceImpl implements aService {
    private static final Logger LOG = LoggerFactory.getLogger(aServiceImpl.class);
    @Resource
    private aRepository aRepository;

    @Override
    public List<a> findAll() {
        return aRepository.findAll();
    }

    @Override
    public a findId(long id) {
        return aRepository.findOne(id);
    }

    @Override
    public List<a> findByIds(List<Long> ids) {
        Query query = new Query().addCriteria(new Criteria("id").in(ids));
        return aRepository.findByQuery(query);
    }

    @Override
    public boolean save(a entity) {
        return aRepository.save(entity);
    }

    @Override
    public a findOne(Long id) {
        return aRepository.findOne(id);
    }

    @Override
    public boolean delete(Long id) {
        return aRepository.delete(id);
    }
    @Override
    public List<a> queryByParams(Map<String,Object> params,int begin,int num,Map<String,String> orders){
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
        return aRepository.findByQuery(query);
    }
}