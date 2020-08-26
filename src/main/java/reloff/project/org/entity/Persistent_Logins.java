package reloff.project.org.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Persistent_Logins", 
			uniqueConstraints = { 
        @UniqueConstraint(name = "Persistent_Logins_UK", columnNames = "series") })
public class Persistent_Logins {
	
	@Column(name = "username", length = 64, nullable = false)
    private String username;
 
	@Id
    @Column(name = "series", length = 64, nullable = false)
    private String series;
    
    @Column(name = "token", length = 64, nullable = false)
    private String token;
 
    @Column(name = "last_used", length = 64, nullable = false)
    private Date last_used;
}
