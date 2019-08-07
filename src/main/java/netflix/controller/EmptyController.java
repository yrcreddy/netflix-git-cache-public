package netflix.controller;

import com.google.common.base.Optional;
import java.io.IOException;
import netflix.service.GitServiceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmptyController {
    private static Logger logger = LoggerFactory.getLogger(EmptyController.class);

    @Autowired
    private GitServiceAccessor gitServiceAccessor;

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity get() throws IOException {

        Optional<Object> defaultUriResponseOptional =
                gitServiceAccessor.getDefaultUriResponse();

        if (defaultUriResponseOptional.isPresent()) {
            return new ResponseEntity<>(defaultUriResponseOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}