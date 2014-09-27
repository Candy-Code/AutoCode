package cn.leap.exam.repository.impl;

import cn.leap.exam.model.a;
import cn.leap.mongo.repository.LeapMongoCrudRepository;

import java.util.List;

/**
 * User: yantingjun
 * Time: 2014-09-27 18:13:31 中国标准时间
 */
public interface aRepository extends LeapMongoCrudRepository<a, Long> {

    public List<a> findParents(List<Long> ids);
    public a findParent(Long id);
}