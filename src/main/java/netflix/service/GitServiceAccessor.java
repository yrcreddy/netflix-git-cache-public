package netflix.service;

import com.google.common.base.Optional;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.GitHubRequest;
import org.eclipse.egit.github.core.service.OrganizationService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class GitServiceAccessor {

    private static final Logger logger = LoggerFactory.getLogger(GitServiceAccessor.class);

    @Autowired
    private GitHubClient gitHubClient;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private RepositoryService repositoryService;


    @Cacheable("org")
    public User getOrganization(String organization) throws IOException {
        logger.info("Getting a information for organization {}" , organization);
        User repository = organizationService.getOrganization(organization);
        return repository;
    }

    @Cacheable("repos")
    public List<Repository> getOrgRepositories(String organization) throws IOException {
        logger.info("Getting a repositories for organization {}" , organization);
        List<Repository> repositories = repositoryService.getOrgRepositories(organization);
        return repositories;
    }

    @Cacheable("members")
    public List<User> getOrgMembers(String organization) throws IOException {
        logger.info("Getting a members for organization {}" , organization);
        List<User>  members = organizationService.getMembers(organization);
        return members;
    }

    @Cacheable("default")
    public Optional<Object> getDefaultUriResponse() throws  IOException {
        logger.info("Getting default URI response");
        StringBuilder uri = new StringBuilder();
        uri.append('/');
        GitHubRequest request = new GitHubRequest();
        request.setUri(uri);
        request.setType(Map.class);
        logger.info(request.generateUri());
        return Optional.of(gitHubClient.get(request).getBody());
    }
}
