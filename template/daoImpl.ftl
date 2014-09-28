package ${daoImpl.packageName};

import ${bean.packageName}.${bean.className};
import ${dao.packageName}.${dao.className};
import cn.leap.mongo.repository.support.AbstractMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author ${author} created at ${sysdate?string("yyyy-MM-dd HH:mm:ss")}
*/
@Repository("categoryRepository")
public class ${daoImpl.className} extends AbstractMongoRepository<${bean.className}, Long>
        implements ${dao.className} {
    private static final Logger LOG = LoggerFactory.getLogger(${daoImpl.className}.class);

    @Override
    protected Long getId(${bean.className} entity) {
        return entity.getId();
    }

}

