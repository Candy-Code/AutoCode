package ${daoImpl.packageName};


/**
 * User: yantingjun
 * Time: 14-8-8 : 下午1:29
 */
@Repository("categoryRepository")
public class ${daoImpl.className} extends AbstractMongoRepository<${bean.className}>, Long>
        implements ${dao.className} {
    private static final Logger LOG = LoggerFactory.getLogger(${daoImpl.className}.class);

    @Override
    protected Long getId(${bean.className} entity) {
        return entity.getId();
    }

}

