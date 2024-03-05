package angular10;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService<T extends UserDTO, D extends UserDAO<UserDTO>> {

	@Autowired
	public UserDAO dao;
	
	@Transactional
	public long add(UserDTO dto) {		
		return dao.add(dto);
	}
	@Transactional
	public long add(AttachmentDTO dto) {		
		return dao.add(dto);
	}
	
	
	public UserDTO findById(Long id) {
		UserDTO dto = dao.findByPk(id);
		return dto;
	}
	public AttachmentDTO findById1(Long id) {
		AttachmentDTO dto = dao.findByPk1(id);
		return dto;
	}
	
	
	
	public void update(UserDTO dto) {
		dao.update(dto);
	}
	public void update(AttachmentDTO dto) {
		dao.update(dto);
	}
	
	
	public UserDTO delete(long id) {
		UserDTO dto = findById(id);
		dao.delete(dto);
		return dto;
	}
	
	
	public Long save(UserDTO dto) {

		Long id = dto.getId();
		
		if(id != null && id > 0) {
			update(dto);
		}else {
			id = add(dto);
		}
		return id;
	}
	

	public Long save(AttachmentDTO dto) {

		Long id = dto.getId();
		
		if(id != null && id > 0) {
			update(dto);
		}else {
			id = add(dto);
		}
		return id;
	}

	public List search() {
		return dao.search();
	}
	
	
	public UserDTO findByloginId(String loginId) {
		UserDTO dto = dao.findByUniquekey("loginId", loginId);
		return dto;
	}
	
	public UserDTO authenticate(String loginId, String password) {
		UserDTO dto = findByloginId(loginId);
		if (dto != null) {
			if (password.equals(dto.getPassword())) {
				return dto;
			}
		}
		return null;
	}

	
	
}
	 

	

	

