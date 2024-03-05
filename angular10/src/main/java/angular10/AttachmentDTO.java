package angular10;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "Attachment")
public class AttachmentDTO extends AttachmentBaseDTO {
   
	    public AttachmentDTO() {
	    	
	    	
	    }
	    
	    public AttachmentDTO(MultipartFile file) {
	    	
	    	name = file.getOriginalFilename();
	    	type = file.getContentType();
	    	try {
	    	    doc = file.getBytes();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    
	    @Lob
	    @Column(name = "DOC")
	    protected byte[] doc;

		public byte[] getDoc() {
			return doc;
		}

		public void setDoc(byte[] doc) {
			this.doc = doc;
		}
} 
