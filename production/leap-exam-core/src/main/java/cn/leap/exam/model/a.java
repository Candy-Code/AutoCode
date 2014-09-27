package cn.leap.exam.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * User: yantingjun
 * Time: 2014-09-27 18:13:31 中国标准时间
 */
@Document(collection = "a")
public class a implements Serializable {

    @Id
    private Long id;
    private String name = "";
    private int age = 0;
    private int sex = 0;
    private boolean married = false;

	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return id;
	}
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age=age;
    }
    public int getSex(){
        return sex;
    }
    public void setSex(int sex){
        this.sex=sex;
    }
    public boolean getMarried(){
        return married;
    }
    public void setMarried(boolean married){
        this.married=married;
    }
}
