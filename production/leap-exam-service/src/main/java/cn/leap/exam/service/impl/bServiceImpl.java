package cn.leap.exam.service.impl;

import cn.leap.exam.model.b;
import cn.leap.exam.repository.impl.bRepository;
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
 * Time: 2014-09-27 18:19:42 中国标准时间
 */
@Service("bService")
public class bServiceImpl implements bService {
    private static final Logger LOG = LoggerFactory.getLogger(bServiceImpl.class);
    @Resource
    private bRepository bRepository;

    @Override
    public List<b> findAll() {
        return bRepository.findAll();
    }

    @Override
    public b findId(long id) {
        return bRepository.findOne(id);
    }

    @Override
    public List<b> findByIds(List<Long> ids) {
        Query query = new Query().addCriteria(new Criteria("id").in(ids));
        return bRepository.findByQuery(query);
    }

    @Override
    public boolean save(b entity) {
        return bRepository.save(entity);
    }

    @Override
    public b findOne(Long id) {
        return bRepository.findOne(id);
    }

    @Override
    public boolean delete(Long id) {
        return bRepository.delete(id);
    }
    @Override
    public List<b> queryByParams(Map<String,Object> params,int begin,int num,Map<String,String> orders){
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
        return bRepository.findByQuery(query);
    }
}