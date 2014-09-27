﻿package cn.leap.exam.api.resource;

/**
 * User: {yantingjun}
 * Time: 2014-09-27 18:13:31 中国标准时间
 */
@Path("/a")
public class aController {

    private static final Logger LOG = LoggerFactory.getLogger(aController.class);

    private aService aService;

    @GET
    public Response get(@QueryParam Long id){
        try{
            Preconditions.checkArguments(id!=null,"Miss Param:id");
            Map params = new HashMap();
            params.put("id",id);
            return aService.queryByParams(params);
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
            return aService.queryByParams(params);
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
            List<a> resultsList =  aService.queryByParams(params);
            if(resultsList == null || resultsList.size() <= 0){
                return ResponseCreater.OK(LeapStatus.RESOURCE_NOT_EXISTS,e.getMessage());
            }

            a a = new a();
            //set column values here

            if(aService.save()){
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
            List<a> resultsList =  aService.queryByParams(params);
            if(resultsList == null || resultsList.size() <= 0){
                return ResponseCreater.OK(LeapStatus.RESOURCE_NOT_EXISTS,e.getMessage());
            }

            if(aService.deleteById(id)){
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

            a a = new a();
            //set column values here

            if(aService.save()){
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