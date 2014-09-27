package ${service.packageName};

import ${bean.packageName}.${bean.className};
import cn.leap.service.LeapCrudService;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;

/**
 * User: ${author}
 * Time: ${sysdate?string("yyyy-MM-dd HH:mm:ss zzzz")}
 */
public interface ${service.className} extends LeapCrudService<${bean.className}, Long>{
    List<${bean.className}> findAll();

    ${bean.className} findId(long id);

    List<${bean.className}> findByIds(List<Long> ids);

    public List<${bean.className}> queryByParams(Map<String,Object> params,int begin,int num,Map<String,String> orders);
}
