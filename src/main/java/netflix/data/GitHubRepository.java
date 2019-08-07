package netflix.data;

import java.util.Date;
import lombok.Data;

@Data
public final class GitHubRepository {

    private final String repositoryId;
    private final String repositoryOwner;
    private final String repositoryName;
    private final String description;
    private final boolean fork;
    private final Integer forkCount;
    private final Integer issueCount;
    private final Integer stargazersCount;
    private final Date createdAt;
    private final Date updateAt;
}
