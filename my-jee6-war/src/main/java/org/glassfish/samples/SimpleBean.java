package org.glassfish.samples;

import javax.faces.bean.ManagedBean;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

@ManagedBean(name = "simplebean")
public class SimpleBean {

	public SimpleBean() {
	}

	@NotNull
	private String name;
	@DecimalMax(value = "24")
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
