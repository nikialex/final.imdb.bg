package movies.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import movies.entities.Genre;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieModel {
    private String id;
    private String name;
    private int year;
    private Genre genre;
    private String picture_path;
    private String trailer_url;
    private String actors;
    private String rating;
}
