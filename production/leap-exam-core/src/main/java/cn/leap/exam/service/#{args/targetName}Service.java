package cn.leap.exam.service;

import cn.leap.exam.model.#{args.targetName};
import cn.leap.service.LeapCrudService;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;

/**
 * User: yantingjun
 * Time: 2014-09-27 18:13:58 中国标准时间
 */
public interface #{args.targetName}Service extends LeapCrudService<#{args.targetName}, Long>{
    List<#{args.targetName}> findAll();

    #{args.targetName} findId(long id);

    List<#{args.targetName}> findByIds(List<Long> ids);

    public List<#{args.targetName}> queryByParams(Map<String,Object> params,int begin,int num,Map<String,String> orders);
}
