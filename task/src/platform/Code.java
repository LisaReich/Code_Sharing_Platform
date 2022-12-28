package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "snippets")
@JsonPropertyOrder({"code", "date", "time", "views"})
public class Code {

    @Id
    @JsonIgnore
    private String uuid = UUID.randomUUID().toString();
    private String date = Instant.now().toString();
    @NotNull
    private String code;
    @NotNull
    private Long time;
    @NotNull
    private Long views;
    @JsonIgnore
    private boolean timeLimited;
    @JsonIgnore
    private boolean viewsLimited;

    public Code(String code, String date, Long time, Long views,
                boolean timeLimited, boolean viewsLimited) {
        this.date = date;
        this.code = code;
        this.time = time;
        this.views = views;
        this.timeLimited = timeLimited;
        this.viewsLimited = viewsLimited;
    }
}
