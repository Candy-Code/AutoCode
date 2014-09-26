package ${dao.packageName};

import ${bean.packageName}.${bean.className};
import cn.leap.mongo.repository.LeapMongoCrudRepository;

import java.util.List;

/**
 * 用于定义考试分类的特有数据库操作行为
 * User: yantingjun
 * Time: 14-8-8 : 下午1:02
 */
public interface ${dao.className} extends LeapMongoCrudRepository<${bean.className}, Long> {

    public List<${bean.className}> findParents(List<Long> ids);
    public ${bean.className} findParent(Long id);
}