package ${serviceImpl.packageName};

import ${bean.packageName}.${bean.className};
import ${dao.packageName}.${dao.className};
import ${service.packageName}.${service.className};
import cn.leap.utils.bean.BeanToMapUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* @author ${author} created at ${sysdate?string("yyyy-MM-dd HH:mm:ss")}
*/
@Service("${service.className?uncap_first}")
public class ${serviceImpl.className} implements ${service.className} {
    private static final Logger LOG = LoggerFactory.getLogger(${serviceImpl.className}.class);
    @Resource
    private ${dao.className} ${dao.className?uncap_first};

    @Override
    public List<${bean.className}> findAll() {
        return ${dao.className?uncap_first}.findAll();
    }

    @Override
    public ${bean.className} findId(long id) {
        return ${dao.className?uncap_first}.findOne(id);
    }

    @Override
    public List<${bean.className}> findByIds(List<Long> ids) {
        Query query = new Query().addCriteria(new Criteria("id").in(ids));
        return ${dao.className?uncap_first}.findByQuery(query);
    }

    @Override
    public boolean save(${bean.className} entity) {
        return ${dao.className?uncap_first}.save(entity);
    }

    @Override
    public ${bean.className} findOne(Long id) {
        return ${dao.className?uncap_first}.findOne(id);
    }

    @Override
    public boolean delete(Long id) {
        return ${dao.className?uncap_first}.delete(id);
    }
    @Override
    public List<${bean.className}> queryByParams(Map<String,Object> params,int begin,int num,Map<String,String> orders){
        Query query = new Query();
        for(Map.Entry<String,Object> entry: (Set<Map.Entry<String,Object>>)params.entrySet()){
            query.addCriteria(new Criteria(entry.getKey()).is(entry.getValue()));
        }
        for(Map.Entry<String,String> entry: (Set<Map.Entry<String,String>>)orders.entrySet()){
            if("desc".equalsIgnoreCase(entry.getValue())){
                query.sort().on(entry.getKey(),Order.DESCENDING);
            }else if("asc".equalsIgnoreCase(entry.getValue())){
                query.sort().on(entry.getKey(),Order.ASCENDING);
            }
        }
        query.skip(begin).limit(num);
        return ${dao.className?uncap_first}.findByQuery(query);
    }
    @Override
    public List<${bean.className}> queryByParams(Map<String, Object> params, int begin, int num) {
        Query query = new Query();
        for(Map.Entry<String,Object> entry: (Set<Map.Entry<String,Object>>)params.entrySet()){
            query.addCriteria(new Criteria(entry.getKey()).is(entry.getValue()));
        }
        query.skip(begin).limit(num);
        return ${dao.className?uncap_first}.findByQuery(query);
    }

    @Override
    public List<${bean.className}> queryByParams(Map<String, Object> params) {
        Query query = new Query();
        for(Map.Entry<String,Object> entry: (Set<Map.Entry<String,Object>>)params.entrySet()){
            query.addCriteria(new Criteria(entry.getKey()).is(entry.getValue()));
        }
        return ${dao.className?uncap_first}.findByQuery(query);
    }

    @Override
    public List<${bean.className}> queryByParams(Map<String, Object> params, Map<String, String> orders) {
        Query query = new Query();
        for(Map.Entry<String,Object> entry: (Set<Map.Entry<String,Object>>)params.entrySet()){
            query.addCriteria(new Criteria(entry.getKey()).is(entry.getValue()));
        }
        for(Map.Entry<String,String> entry: (Set<Map.Entry<String,String>>)orders.entrySet()){
        if("desc".equalsIgnoreCase(entry.getValue())){
            query.sort().on(entry.getKey(),Order.DESCENDING);
        }else if("asc".equalsIgnoreCase(entry.getValue())){
            query.sort().on(entry.getKey(),Order.ASCENDING);
        }
        }
        return ${dao.className?uncap_first}.findByQuery(query);
    }

    @Override
    public long countByParams(Map<String, Object> params) {
        return this.${dao.className?uncap_first}.countByCondition(params);
    }
}