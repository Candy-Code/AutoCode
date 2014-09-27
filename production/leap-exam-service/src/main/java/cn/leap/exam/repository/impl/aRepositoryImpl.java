package cn.leap.exam.repository.impl;


/**
 * User: yantingjun
 * Time: 14-8-8 : 下午1:29
 */
@Repository("categoryRepository")
public class aRepositoryImpl extends AbstractMongoRepository<a>, Long>
        implements aRepository {
    private static final Logger LOG = LoggerFactory.getLogger(aRepositoryImpl.class);

    @Override
    protected Long getId(a entity) {
        return entity.getId();
    }

}

