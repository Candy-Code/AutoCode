package ${service.packageName};

import ${bean.packageName}.${bean.className};
import cn.leap.service.LeapCrudService;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;

/**
 * User: yantingjun
 * Time: 14-8-8 : 下午1:25
 */
public interface ${service.className} extends LeapCrudService<${bean.className}, Long>{
    List<${bean.className}> findAll();

    ${bean.className} findId(long id);

    List<${bean.className}> findByIds(List<Long> ids);
}
