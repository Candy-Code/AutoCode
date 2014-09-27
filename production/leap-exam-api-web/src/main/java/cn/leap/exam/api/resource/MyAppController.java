package cn.leap.exam.api.resource;

/**
 * User: {yantingjun}
 * Time: 2014-09-27 12:23:27 中国标准时间
 */
@Path("/myapp")
public class MyAppController {

    private static final Logger LOG = LoggerFactory.getLogger(MyAppController.class);

    private MyAppService myAppService;

    @GET
    public Response get(@QueryParam Long id){
        try{
            Preconditions.checkArguments(id!=null,"Miss Param:id");
            Map params = new HashMap();
            params.put("id",id);
            return myAppService.queryByParams(params);
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
            return myAppService.queryByParams(params);
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
            List<MyApp> resultsList =  myAppService.queryByParams(params);
            if(resultsList == null || resultsList.size() <= 0){
                return ResponseCreater.OK(LeapStatus.RESOURCE_NOT_EXISTS,e.getMessage());
            }

            MyApp myApp = new MyApp();
            //set column values here

            if(myAppService.save()){
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
            List<MyApp> resultsList =  myAppService.queryByParams(params);
            if(resultsList == null || resultsList.size() <= 0){
                return ResponseCreater.OK(LeapStatus.RESOURCE_NOT_EXISTS,e.getMessage());
            }

            if(myAppService.deleteById(id)){
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

            MyApp myApp = new MyApp();
            //set column values here

            if(myAppService.save()){
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
