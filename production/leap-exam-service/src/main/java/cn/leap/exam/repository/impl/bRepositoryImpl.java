package cn.leap.exam.repository.impl;


/**
 * User: yantingjun
 * Time: 14-8-8 : 下午1:29
 */
@Repository("categoryRepository")
public class bRepositoryImpl extends AbstractMongoRepository<b>, Long>
        implements bRepository {
    private static final Logger LOG = LoggerFactory.getLogger(bRepositoryImpl.class);

    @Override
    protected Long getId(b entity) {
        return entity.getId();
    }

}

