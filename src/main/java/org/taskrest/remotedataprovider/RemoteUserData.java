package org.taskrest.remotedataprovider;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
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

    public Float getCalculations() {
        if(followers==0){
            return null;
        }
        return 6/(float)followers*(2+publicRepos);
    }
}
