package cn.leap.exam.service;

import cn.leap.exam.model.a;
import cn.leap.service.LeapCrudService;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;

/**
 * User: yantingjun
 * Time: 2014-09-27 18:13:31 中国标准时间
 */
public interface aService extends LeapCrudService<a, Long>{
    List<a> findAll();

    a findId(long id);

    List<a> findByIds(List<Long> ids);

    public List<a> queryByParams(Map<String,Object> params,int begin,int num,Map<String,String> orders);
}
