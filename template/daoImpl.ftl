package ${daoImpl.packageName};

import ${bean.packageName}.${bean.className};
import ${dao.packageName}.${dao.className};
import cn.leap.mongo.repository.support.AbstractMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.util.Assert;
import java.util.Date;
import cn.leap.exam.utils.SecurityIdGenerator;
import cn.leap.id.api.IdType;
import cn.leap.utils.LeapDateUtils;

/**
* @author ${user.name} created at ${sysdate?string("yyyy-MM-dd HH:mm:ss")}
*/
@Repository("${dao.className?uncap_first}")
public class ${daoImpl.className} extends AbstractMongoRepository<${bean.className}, Long>
        implements ${dao.className} {
    private static final Logger LOG = LoggerFactory.getLogger(${daoImpl.className}.class);

    @Override
    protected Long getId(${bean.className} entity) {
        return entity.getId();
    }

    @Override
    public boolean save(${bean.className} entity) {
        Assert.notNull(entity);

        if(entity.getId() == null || entity.getId() <= 0){
            entity.setId(SecurityIdGenerator.nextId(IdType.${hump2snake(bean.className)?upper_case}));
        }

        ${bean.className} old = findOne(entity.getId());

        if (null == old) {
            entity.setCreate_at(LeapDateUtils.formatYMDHMS(new Date()));
        }else{
            entity.setCreate_at(old.getCreate_at());
        }
        entity.setUpdate_at(LeapDateUtils.formatYMDHMS(new Date()));
        return super.save(entity);
    }

}

