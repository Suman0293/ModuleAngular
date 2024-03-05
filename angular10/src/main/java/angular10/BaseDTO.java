package angular10;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class BaseDTO {
	
	@Id
	@GeneratedValue(generator = "pk")
	@GenericGenerator(name = "pk", strategy = "native")
	@Column(name = "ID", unique = true, nullable = false)
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
