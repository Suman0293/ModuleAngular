package angular10;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "User")
public class UserCtl<F extends UserForm, T extends UserDTO, S extends UserService<UserDTO, UserDAO<UserDTO>> > {
       
	@Autowired
	UserService service;
	
	@PostMapping("save")
	public String add(@RequestBody UserForm form) {
		
		UserDTO dto = new UserDTO();
		
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setLoginId(form.getLoginId());
		dto.setPassword(form.getPassword());
		
		service.add(dto);
		return "User Added...!!!";
	}
	
	
	@PostMapping("delete/{id}")
	public String delete(@RequestBody UserForm form, @PathVariable Long id ) {			
		service.delete(id);
		return "User deleted...!!!";
	}
	

	@PostMapping("update/{id}")
	public String update(@RequestBody UserForm form, @PathVariable Long id) {
		
		UserDTO dto = service.findById(id);
		
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setLoginId(form.getLoginId());
		dto.setPassword(form.getPassword());
		
		service.update(dto);
		return "User updated...!!!";
	}
	
	
	@GetMapping(value = "/Search")
	public List Search() {
		List list = service.search();

		return list;

	}

	@GetMapping(value = "/Search/{id}")
	public UserDTO Search(@PathVariable long id) {
		UserDTO dto = service.findById(id);
		return dto;
	}

	@PostMapping("login")
	public UserDTO auhenticate(@RequestBody UserForm f) {
		UserDTO dto = service.authenticate(f.getLoginId(), f.getPassword());
		return dto;
	}
	
	
	@PostMapping("/Profilepic/{userId}")
	public String uploadpic(@PathVariable Long userId,@RequestParam("file") MultipartFile file) {
		 UserDTO dto = service.findById(userId);
		 
		 AttachmentDTO doc = new AttachmentDTO(file);
		 doc.setUserId(userId);
		 
		 if(dto.getImageId() != null && dto.getImageId() > 0) {
			 doc.setId(dto.getImageId());
		 }
		 Long imageId = service.save(doc);
		 
		 if(dto.getImageId() == null || dto.getImageId() == 0) {
			 dto.setImageId(imageId);
			 service.update(dto);
		 }
		return "Image Uploaded...!!!!";
		 
	}
	@PostMapping("/doc/{userId}")
	public String Upload(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {
		AttachmentDTO doc = new AttachmentDTO(file);
		doc.setUserId(userId);

		service.save(doc);
		return "doc uploaded";
	}
	
	 @GetMapping("/Profiledown/{userid}") 
	 public void downloadpic(@PathVariable Long userid, HttpServletResponse response) throws IOException { 
		 UserDTO dto = service.findById(userid);
	  AttachmentDTO adto =  service.findById1(dto.getImageId());
	  
	  if (adto != null)
	  { response.setContentType(adto.getType()); 
	  OutputStream out = response.getOutputStream();
	  out.write(adto.getDoc());
	  out.close();
	  }
}
	
}
