package actors;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "extras")
@AllArgsConstructor
@NoArgsConstructor
public class Actor {

    @Column(nullable = false)
    private String fullName;

}
