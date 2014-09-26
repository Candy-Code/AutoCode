package ${bean.packageName};

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * User: ellios
 * Time: 14-9-11 : 上午11:36
 */
@Document(collection = "${bean.className}")
public class ${bean.className} implements Serializable {

    @Id
    private Long id;
    
	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return id;
	}
}
 