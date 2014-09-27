package cn.leap.exam.service;

import cn.leap.exam.model.b;
import cn.leap.service.LeapCrudService;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;

/**
 * User: yantingjun
 * Time: 2014-09-27 18:19:42 中国标准时间
 */
public interface bService extends LeapCrudService<b, Long>{
    List<b> findAll();

    b findId(long id);

    List<b> findByIds(List<Long> ids);

    public List<b> queryByParams(Map<String,Object> params,int begin,int num,Map<String,String> orders);
}
