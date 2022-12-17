package cn.edu.tongji.dwbackend.neo4j.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING;

@Node("director")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Director {
    @Id
    @Property("director_name")
    private String name;


    @Relationship(type="direct_in",direction =OUTGOING)
    private Set<Movie> movies=new HashSet<>();

}
