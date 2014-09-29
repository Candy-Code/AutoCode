package ${bean.packageName};

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
* @author ${author} created at ${sysdate?string("yyyy-MM-dd HH:mm:ss")}
*/
@Document(collection = "${hump2snake(bean.className)}s")
public class ${bean.className} implements Serializable {

    @Id
    private Long id;
    <#list bean.props as prop>
    private ${prop.type} ${prop.name} = ${prop.default_value};
    </#list>

	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return id;
	}
    <#list bean.props as prop>
    public ${prop.type} get${prop.name?cap_first}(){
        return ${prop.name};
    }
    public void set${prop.name?cap_first}(${prop.type} ${prop.name}){
        this.${prop.name}=${prop.name};
    }
    </#list>
}
 