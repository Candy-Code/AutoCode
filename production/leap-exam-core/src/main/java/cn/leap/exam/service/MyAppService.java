package cn.leap.exam.service;

import cn.leap.exam.model.MyApp;
import cn.leap.service.LeapCrudService;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;

/**
 * User: yantingjun
 * Time: 2014-09-27 17:31:25 �й���׼ʱ��
 */
public interface MyAppService extends LeapCrudService<MyApp, Long>{
    List<MyApp> findAll();

    MyApp findId(long id);

    List<MyApp> findByIds(List<Long> ids);

    public List<MyApp> queryByParams(Map<String,Object> params,int begin,int num,Map<String,String> orders);
}
