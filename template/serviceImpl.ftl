package ${serviceImpl.packageName};

import ${bean.packageName}.${bean.className};
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
 * Time: 14-8-8 : 下午1:35
 */
@Service("${bean.className}Service")
public class ${serviceImpl.className} implements ${service.className} {
    private static final Logger LOG = LoggerFactory.getLogger(${serviceImpl.className}.class);
    @Resource
    private ${dao.className} repository;

    @Override
    public List<${bean.className}> findAll() {
        return repository.findAll();
    }

    @Override
    public ${bean.className} findId(long id) {
        return repository.findOne(id);
    }

    @Override
    public List<${bean.className}> findByIds(List<Long> ids) {
        Query query = new Query().addCriteria(new Criteria("id").in(ids));
        return repository.findByQuery(query);
    }

    @Override
    public boolean save(${bean.className} entity) {
        return repository.save(entity);
    }

    @Override
    public ${bean.className} findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public boolean delete(Long id) {
        return repository.delete(id);
    }
}
