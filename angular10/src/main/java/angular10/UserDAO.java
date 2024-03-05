package angular10;

import java.util.List;
import javax.persistence.criteria.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO<T extends UserDTO> {	
	
	@PersistenceContext
	EntityManager entityManager;
	
	
	public long add(UserDTO dto) {
		entityManager.persist(dto);
		return dto.getId();
	}
	public long add(AttachmentDTO dto) {
		entityManager.persist(dto);
		return dto.getId();
	}
	
	
	
	
	public UserDTO update(UserDTO dto) {		
		return entityManager.merge(dto);
	}
	public AttachmentDTO update(AttachmentDTO dto) {		
		return entityManager.merge(dto);
	}
	
	
	
	public void delete(UserDTO dto) {		
		entityManager.remove(dto);
	}
	
	
	
	public UserDTO findByPk(long pk) {
	 T dto = (T) entityManager.find(UserDTO.class, pk);
		return dto;
		
	}
	public AttachmentDTO findByPk1(long pk) {
		AttachmentDTO dto = entityManager.find(AttachmentDTO.class, pk);
		return dto;
	}

	

	public List search() {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		  CriteriaQuery<UserDTO> cq = builder.createQuery(getDTOClass()); 
		  Root<UserDTO> root = cq.from(UserDTO.class); 
		  cq.select(root);
		  
		  TypedQuery<UserDTO> t = entityManager.createQuery(cq);
		  
		  List list = t.getResultList();
		  
		  return list;

	} 
    
      private Class getDTOClass() {		
		return UserDTO.class;
	}
      
      
      
      
	public UserDTO findByUniquekey(String attribute,String value) {
		
		CriteriaBuilder builder= entityManager.getCriteriaBuilder();		
		CriteriaQuery<UserDTO> cq =builder.createQuery(UserDTO.class);
		Root<UserDTO> root=cq.from(UserDTO.class);
		
       Predicate condition = builder.equal(root.get(attribute),value);
		
		cq.where(condition);
		
		TypedQuery<UserDTO> t = entityManager.createQuery(cq);
		
		List<UserDTO> list = t.getResultList();
		
		UserDTO dto = null;
		
		if(list.size()>0) {
			dto = list.get(0);
		}
		
		return dto;
		
	}
}
