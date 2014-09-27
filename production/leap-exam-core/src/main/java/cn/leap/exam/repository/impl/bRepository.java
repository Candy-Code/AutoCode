package cn.leap.exam.repository.impl;

import cn.leap.exam.model.b;
import cn.leap.mongo.repository.LeapMongoCrudRepository;

import java.util.List;

/**
 * User: yantingjun
 * Time: 2014-09-27 18:19:42 中国标准时间
 */
public interface bRepository extends LeapMongoCrudRepository<b, Long> {

    public List<b> findParents(List<Long> ids);
    public b findParent(Long id);
}