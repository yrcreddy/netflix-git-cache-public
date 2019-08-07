package netflix.controller.views;

import com.google.common.base.Optional;
import com.google.common.collect.Comparators;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import netflix.controller.ReposController;
import netflix.model.RepoView;
import netflix.service.GitServiceAccessor;
import netflix.utils.Constants;
import netflix.utils.RepoViewUtil;
import org.eclipse.egit.github.core.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopOpenIssuesController {
    private static Logger logger = LoggerFactory.getLogger(ReposController.class);

    @Autowired
    private GitServiceAccessor gitServiceAccessor;

    @RequestMapping(
            value = "/view/top/{N}/open_issues",
            method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity get(@RequestParam(value="N", defaultValue="10") String count) throws IOException {

        Integer N = Integer.parseInt(count);
        List<Repository> repositories =
                gitServiceAccessor.getOrgRepositories(Constants.NETFLIX);

        if (repositories != null) {
            List<Repository> orderedRepos = repositories.stream()
                    .collect(Comparators.greatest(N, Comparator.comparingInt(Repository::getOpenIssues)));
            List<RepoView> repoViewResult = orderedRepos.stream().map(
                    e -> RepoViewUtil.convertToRepoView(e.getName(), e.getOpenIssues()))
                    .collect(Collectors.toList());

            String[][] result = RepoViewUtil.getStringArray(repoViewResult);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
