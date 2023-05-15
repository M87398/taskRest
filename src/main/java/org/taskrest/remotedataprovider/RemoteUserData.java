package org.taskrest.remotedataprovider;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemoteUserData implements Serializable {
    private Long id;
    private String login;
    private String name;
    private String type;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("created_at")
    private String createdAt;
    private Integer followers;
    @JsonProperty("public_repos")
    private Integer publicRepos;

    Float getCalculations() {
        if (followers == 0) {
            return null;
        }
        return 6 / (float) followers * (2 + publicRepos);
    }
}
