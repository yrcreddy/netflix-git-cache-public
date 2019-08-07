package netflix;

import com.google.common.cache.CacheBuilder;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import netflix.filters.SimpleFilter;
import netflix.service.GitServiceAccessor;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.OrganizationService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@EnableZuulProxy
@SpringBootApplication
@EnableCaching
@Import({Credentials.class, CacheConfig.class})
public class Application {

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public SimpleFilter simpleFilter() {
        return new SimpleFilter();
    }

    @Bean
    public RepositoryService githubRepositoryService(GitHubClient gitHubClient) {
        return new RepositoryService(gitHubClient);
    }

    @Bean
    public OrganizationService githubOrganizationService(GitHubClient gitHubClient) {
        return new OrganizationService(gitHubClient);
    }

    @Bean
    public GitHubClient gitHubClient(Credentials credentials) {
        return new GitHubClient().setOAuth2Token(credentials.githubOuth2Token());
    }

    @Bean
    public GitServiceAccessor getGitAccessor() {
        return new GitServiceAccessor();
    }

    @Bean
    public CacheManager cacheManager(CacheConfig cacheConfig) {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager() {
            @Override
            protected Cache createConcurrentMapCache(String name) {
                ConcurrentMap<Object, Object> cacheMap = CacheBuilder.newBuilder()
                        .expireAfterWrite(cacheConfig.expireAfterWrite(), TimeUnit.MINUTES)
                        .maximumSize(cacheConfig.maximumSize()).build().asMap();

                return new ConcurrentMapCache(name, cacheMap, true);
            }
        };

        return cacheManager;
    }

}