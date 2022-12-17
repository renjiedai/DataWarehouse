package cn.edu.tongji.dwbackend.neo4j.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING;

@Node("actor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actor {
    @Id
    @Property("actor_name")
    private String name;

    @Relationship(type="act_in",direction=OUTGOING)
    private Set<Movie> actmovie=new HashSet<>();

    @Relationship(type="star_in",direction=OUTGOING)
    private Set<Movie> starmovie=new HashSet<>();

    /*
    @Relationship(type="act_in")
    private List<Movie> actmovies=new ArrayList<>();

    @Relationship(type="star_in")
    private List<Movie> starmovies=new ArrayList<>();
     */

}
