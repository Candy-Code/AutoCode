package ${dao.packageName};

import ${bean.packageName}.${bean.className};
import cn.leap.mongo.repository.LeapMongoCrudRepository;

import java.util.List;

/**
 * User: ${author}
 * Time: ${sysdate?string("yyyy-MM-dd HH:mm:ss zzzz")}
 */
public interface ${dao.className} extends LeapMongoCrudRepository<${bean.className}, Long> {

    public List<${bean.className}> findParents(List<Long> ids);
    public ${bean.className} findParent(Long id);
}