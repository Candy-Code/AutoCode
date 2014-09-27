package cn.leap.exam.repository.impl;

import cn.leap.exam.model.MyApp;
import cn.leap.mongo.repository.LeapMongoCrudRepository;

import java.util.List;

/**
 * User: yantingjun
 * Time: 2014-09-27 17:26:55 中国标准时间
 */
public interface MyAppRepository extends LeapMongoCrudRepository<MyApp, Long> {

    public List<MyApp> findParents(List<Long> ids);
    public MyApp findParent(Long id);
}