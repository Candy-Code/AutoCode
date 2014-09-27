package cn.leap.exam.repository.impl;

import cn.leap.exam.model.#{args.targetName};
import cn.leap.mongo.repository.LeapMongoCrudRepository;

import java.util.List;

/**
 * User: yantingjun
 * Time: 2014-09-27 18:13:58 中国标准时间
 */
public interface #{args.targetName}Repository extends LeapMongoCrudRepository<#{args.targetName}, Long> {

    public List<#{args.targetName}> findParents(List<Long> ids);
    public #{args.targetName} findParent(Long id);
}