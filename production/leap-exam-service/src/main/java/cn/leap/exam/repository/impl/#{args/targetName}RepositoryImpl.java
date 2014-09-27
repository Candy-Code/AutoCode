package cn.leap.exam.repository.impl;


/**
 * User: yantingjun
 * Time: 14-8-8 : 下午1:29
 */
@Repository("categoryRepository")
public class #{args.targetName}RepositoryImpl extends AbstractMongoRepository<#{args.targetName}>, Long>
        implements #{args.targetName}Repository {
    private static final Logger LOG = LoggerFactory.getLogger(#{args.targetName}RepositoryImpl.class);

    @Override
    protected Long getId(#{args.targetName} entity) {
        return entity.getId();
    }

}

