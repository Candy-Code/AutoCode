package ${controller.packageName};


import ${bean.packageName}.${bean.className};
import ${service.packageName}.${service.className};
import cn.leap.exam.utils.PageQueryUtil;
import cn.leap.jersey.AlternateMediaType;
import cn.leap.jersey.annotation.LJSONP;
import cn.leap.jersey.utils.ResponseCreaters;
import cn.leap.web.LeapStatus;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ${author} created at ${sysdate?string("yyyy-MM-dd HH:mm:ss")}
 */
@Path("/${bean.className}")
public class ${controller.className} {

    private static final Logger LOG = LoggerFactory.getLogger(${controller.className}.class);

    private ${service.className} ${service.className?cap_first};

    @GET
    @LJSONP
    @Produces({AlternateMediaType.UTF_8_APPLICATION_JSON, AlternateMediaType.UTF_8_APPLICATION_JAVASCRIPT})
    public Response get(@QueryParam("id") Long id,
                        @QueryParam("begin") Integer begin,
                        @QueryParam("num") Integer num){
        try{
            Preconditions.checkArgument(id != null, "Miss Param:id");
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("id",id);

            Map orders = new HashMap();
            orders.put("create_at","desc");
            return ResponseCreaters.OK(${service.className?cap_first}.queryByParams(params, PageQueryUtil.safetyBegin(begin),
                    PageQueryUtil.safetyNum(num), orders));
        }catch(IllegalArgumentException e){
            return ResponseCreaters.OK(LeapStatus.PARAM_INVALID, e.getMessage());
        }
        catch(Exception e){
            return ResponseCreaters.OK(LeapStatus.SERVER_EXCEPTION,e.getMessage());
        }
    }
    @GET
    @Path("/{id:\\d+}")
    @LJSONP
    @Produces({AlternateMediaType.UTF_8_APPLICATION_JSON, AlternateMediaType.UTF_8_APPLICATION_JAVASCRIPT})
    public Response show(@QueryParam("id") Long id,
                        @QueryParam("begin") Integer begin,
                        @QueryParam("num") Integer num){
        try{
            Preconditions.checkArgument(id != null, "Miss Param:id");
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("id",id);

            Map orders = new HashMap();
            orders.put("create_at","desc");
            return ResponseCreaters.OK(${service.className?cap_first}.queryByParams(params, PageQueryUtil.safetyBegin(begin),
                    PageQueryUtil.safetyNum(num),orders));
        }catch(IllegalArgumentException e){
            return ResponseCreaters.OK(LeapStatus.PARAM_INVALID, e.getMessage());
        }
        catch(Exception e){
            return ResponseCreaters.OK(LeapStatus.SERVER_EXCEPTION,e.getMessage());
        }
    }
    @PUT
    @Path("/{id:\\d+}")
    @LJSONP
    @Produces({AlternateMediaType.UTF_8_APPLICATION_JSON, AlternateMediaType.UTF_8_APPLICATION_JAVASCRIPT})
    public Response update(@PathParam("id") Long id){
        try{
            Preconditions.checkArgument(id != null, "Miss Param:id");
            Map params = new HashMap();
            params.put("id",id);
            List<${bean.className}> resultsList =  ${service.className?cap_first}.queryByParams(params);
            if(resultsList == null || resultsList.size() <= 0){
                return ResponseCreaters.OK(LeapStatus.NO_EXIST_RESOURCE);
            }

            ${bean.className} ${bean.className?cap_first} = new ${bean.className}();
            //set column values here

            if(${service.className?cap_first}.save(${bean.className?cap_first})){
                return ResponseCreaters.OK(LeapStatus.SUCCESS);
            }else{
                return ResponseCreaters.OK(LeapStatus.SERVER_EXCEPTION);
            }
        }catch(IllegalArgumentException e){
            return ResponseCreaters.OK(LeapStatus.PARAM_INVALID,e.getMessage());
        }
        catch(Exception e){
            return ResponseCreaters.OK(LeapStatus.SERVER_EXCEPTION,e.getMessage());
        }
    }
    @DELETE
    @Path("/{id:\\d+}")
    @LJSONP
    @Produces({AlternateMediaType.UTF_8_APPLICATION_JSON, AlternateMediaType.UTF_8_APPLICATION_JAVASCRIPT})
    public Response delete(@PathParam("id") Long id){
        try{
            Preconditions.checkArgument(id!=null,"Miss Param:id");
            Map params = new HashMap();
            params.put("id",id);
            List<${bean.className}> resultsList =  ${service.className?cap_first}.queryByParams(params);
            if(resultsList == null || resultsList.size() <= 0){
                return ResponseCreaters.OK(LeapStatus.NO_EXIST_RESOURCE);
            }

            if(${service.className?cap_first}.delete(id)){
                return ResponseCreaters.OK(LeapStatus.SUCCESS);
            }else{
                return ResponseCreaters.OK(LeapStatus.SERVER_EXCEPTION);
            }
        }catch(IllegalArgumentException e){
            return ResponseCreaters.OK(LeapStatus.PARAM_INVALID,e.getMessage());
        }
        catch(Exception e){
            return ResponseCreaters.OK(LeapStatus.SERVER_EXCEPTION,e.getMessage());
        }
    }
    @PUT
    @LJSONP
    @Produces({AlternateMediaType.UTF_8_APPLICATION_JSON, AlternateMediaType.UTF_8_APPLICATION_JAVASCRIPT})
    public Response save(){
        try{
            //check arguments here
            //Preconditions.checkArguments(id!=null,"Miss Param:id");

            ${bean.className} ${bean.className?cap_first} = new ${bean.className}();
            //set column values here

            if(${service.className?cap_first}.save(${bean.className?cap_first})){
                return ResponseCreaters.OK(LeapStatus.SUCCESS);
            }else{
                return ResponseCreaters.OK(LeapStatus.SERVER_EXCEPTION);
            }
        }catch(IllegalArgumentException e){
            return ResponseCreaters.OK(LeapStatus.PARAM_INVALID,e.getMessage());
        }
        catch(Exception e){
            return ResponseCreaters.OK(LeapStatus.SERVER_EXCEPTION, e.getMessage());
        }
    }
}
