package ${dao.packageName};

import ${bean.packageName}.${bean.className};
import cn.leap.mongo.repository.LeapMongoCrudRepository;

import java.util.List;

/**
* @author ${author} created at ${sysdate?string("yyyy-MM-dd HH:mm:ss")}
*/
public interface ${dao.className} extends LeapMongoCrudRepository<${bean.className}, Long> {

}