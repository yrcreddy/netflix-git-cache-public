package netflix.controller;

import java.io.IOException;
import java.util.List;
import netflix.service.GitServiceAccessor;
import org.eclipse.egit.github.core.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MembersController {
    private static Logger logger = LoggerFactory.getLogger(MembersController.class);

    @Autowired
    private GitServiceAccessor gitServiceAccessor;

    @RequestMapping(
            value = "/orgs/{organization}/members",
            method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    public List<User> get(
            @PathVariable String organization) throws IOException {

        return gitServiceAccessor.getOrgMembers(organization);

    }
}