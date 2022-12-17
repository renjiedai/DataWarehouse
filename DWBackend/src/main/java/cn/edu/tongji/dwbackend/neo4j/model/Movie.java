package cn.edu.tongji.dwbackend.neo4j.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    private String movie_id; //好像还有一个单纯的id，那个好像是自动生成的
    @Property("movie_name")
    private String name;

    /*
    @Relationship(type="act_in",direction=INCOMING)
    private Set<Actor> acters=new HashSet<>();

    @Relationship(type="star_in",direction =INCOMING)
    private Set<Actor> starers=new HashSet<>();

    @Relationship(type="direct_in",direction =INCOMING)
    private Set<Director> directors=new HashSet<>();
     */
}
