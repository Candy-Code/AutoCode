package cn.leap.exam.repository.impl;


/**
 * User: yantingjun
 * Time: 14-8-8 : 下午1:29
 */
@Repository("categoryRepository")
public class MyAppRepositoryImpl extends AbstractMongoRepository<MyApp>, Long>
        implements MyAppRepository {
    private static final Logger LOG = LoggerFactory.getLogger(MyAppRepositoryImpl.class);

    @Override
    protected Long getId(MyApp entity) {
        return entity.getId();
    }

}

