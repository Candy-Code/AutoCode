package cn.leap.exam.api.resource;

/**
 * User: {yantingjun}
 * Time: 2014-09-27 18:13:58 中国标准时间
 */
@Path("/#{args.targetname}")
public class #{args.targetName}Controller {

    private static final Logger LOG = LoggerFactory.getLogger(#{args.targetName}Controller.class);

    private #{args.targetName}Service #{args.targetName}Service;

    @GET
    public Response get(@QueryParam Long id){
        try{
            Preconditions.checkArguments(id!=null,"Miss Param:id");
            Map params = new HashMap();
            params.put("id",id);
            return #{args.targetName}Service.queryByParams(params);
        }catch(IllegalArgumentsException e){
            ResponseCreater.OK(LeapStatus.PARAM_INVALID,e.getMessage());
        }
        catch(Exception e){
            ResponseCreater.OK(LeapStatus.SERVER_EXCEPTION,e.getMessage());
        }
    }
    @GET
    @Path("/{id:\d+}")
    public Response show(@PathParam Long id){
        try{
            Preconditions.checkArguments(id!=null,"Miss Param:id");
            Map params = new HashMap();
            params.put("id",id);
            return #{args.targetName}Service.queryByParams(params);
        }catch(IllegalArgumentsException e){
            ResponseCreater.OK(LeapStatus.PARAM_INVALID,e.getMessage());
        }
        catch(Exception e){
            ResponseCreater.OK(LeapStatus.SERVER_EXCEPTION,e.getMessage());
        }
    }
    @PUT
    @Path("/{id:\d+}")
    public Response update(@PathParam Long id){
        try{
            Preconditions.checkArguments(id!=null,"Miss Param:id");
            Map params = new HashMap();
            params.put("id",id);
            List<#{args.targetName}> resultsList =  #{args.targetName}Service.queryByParams(params);
            if(resultsList == null || resultsList.size() <= 0){
                return ResponseCreater.OK(LeapStatus.RESOURCE_NOT_EXISTS,e.getMessage());
            }

            #{args.targetName} #{args.targetName} = new #{args.targetName}();
            //set column values here

            if(#{args.targetName}Service.save()){
                return ResponseCreater.OK(LeapStatus.SUCCESS);
            }else{
                return ResponseCreater.OK(LeapStatus.SERVER_EXCEPTION);
            }
        }catch(IllegalArgumentsException e){
            return ResponseCreater.OK(LeapStatus.PARAM_INVALID,e.getMessage());
        }
        catch(Exception e){
            return ResponseCreater.OK(LeapStatus.SERVER_EXCEPTION,e.getMessage());
        }
    }
    @DELETE
    @Path("/{id:\d+}")
    public Response update(@PathParam Long id){
        try{
            Preconditions.checkArguments(id!=null,"Miss Param:id");
            Map params = new HashMap();
            params.put("id",id);
            List<#{args.targetName}> resultsList =  #{args.targetName}Service.queryByParams(params);
            if(resultsList == null || resultsList.size() <= 0){
                return ResponseCreater.OK(LeapStatus.RESOURCE_NOT_EXISTS,e.getMessage());
            }

            if(#{args.targetName}Service.deleteById(id)){
                return ResponseCreater.OK(LeapStatus.SUCCESS);
            }else{
                return ResponseCreater.OK(LeapStatus.SERVER_EXCEPTION);
            }
        }catch(IllegalArgumentsException e){
            return ResponseCreater.OK(LeapStatus.PARAM_INVALID,e.getMessage());
        }
        catch(Exception e){
            return ResponseCreater.OK(LeapStatus.SERVER_EXCEPTION,e.getMessage());
        }
    }
    @PUT
    public Response save(){
        try{
            //check arguments here
            //Preconditions.checkArguments(id!=null,"Miss Param:id");

            #{args.targetName} #{args.targetName} = new #{args.targetName}();
            //set column values here

            if(#{args.targetName}Service.save()){
                return ResponseCreater.OK(LeapStatus.SUCCESS);
            }else{
                return ResponseCreater.OK(LeapStatus.SERVER_EXCEPTION);
            }
        }catch(IllegalArgumentsException e){
            return ResponseCreater.OK(LeapStatus.PARAM_INVALID,e.getMessage());
        }
        catch(Exception e){
            return ResponseCreater.OK(LeapStatus.SERVER_EXCEPTION,e.getMessage());
        }
    }
}
