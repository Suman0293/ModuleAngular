package angular10;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import io.micrometer.core.annotation.Counted;

@MappedSuperclass
public class AttachmentBaseDTO extends BaseDTO{
	
	@Column(name = "NAME", length = 50)
	protected String name = null;
	
	@Column(name = "TYPE", length = 50)
	protected String type = null;
	
	@Column(name = "USERID")
	protected Long userId = null;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
