package cn.leap.exam.service.impl;

import cn.leap.exam.model.#{args.targetName};
import cn.leap.exam.repository.impl.#{args.targetName}Repository;
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
 * Time: 2014-09-27 18:13:58 中国标准时间
 */
@Service("#{args.targetName}Service")
public class #{args.targetName}ServiceImpl implements #{args.targetName}Service {
    private static final Logger LOG = LoggerFactory.getLogger(#{args.targetName}ServiceImpl.class);
    @Resource
    private #{args.targetName}Repository #{args.targetName}Repository;

    @Override
    public List<#{args.targetName}> findAll() {
        return #{args.targetName}Repository.findAll();
    }

    @Override
    public #{args.targetName} findId(long id) {
        return #{args.targetName}Repository.findOne(id);
    }

    @Override
    public List<#{args.targetName}> findByIds(List<Long> ids) {
        Query query = new Query().addCriteria(new Criteria("id").in(ids));
        return #{args.targetName}Repository.findByQuery(query);
    }

    @Override
    public boolean save(#{args.targetName} entity) {
        return #{args.targetName}Repository.save(entity);
    }

    @Override
    public #{args.targetName} findOne(Long id) {
        return #{args.targetName}Repository.findOne(id);
    }

    @Override
    public boolean delete(Long id) {
        return #{args.targetName}Repository.delete(id);
    }
    @Override
    public List<#{args.targetName}> queryByParams(Map<String,Object> params,int begin,int num,Map<String,String> orders){
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
        return #{args.targetName}Repository.findByQuery(query);
    }
}