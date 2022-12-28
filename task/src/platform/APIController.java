package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/code")
public class APIController {
    CodeService codeService;

    @Autowired
    public APIController(CodeService codeService) {
        this.codeService = codeService;
    }

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Content-Type", "application/json");
    }

    @GetMapping("/{uuid}")
    public Code getAsJSON(@PathVariable("uuid") String uuid) {

        Code code = codeService.getSnippetByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (code.isTimeLimited()) {
            codeService.updateTimeByUuid(uuid);
        }

        if (code.isViewsLimited()) {
            codeService.updateViewsByUuid(uuid);
        }

        return code;
    }

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> postNewCodeAPI(@RequestBody Code newCode) {
        return Collections.singletonMap("id", codeService.createNewCode(newCode));
    }

    @GetMapping("/latest")
    public List<Code> getListOfTenAsJSON() {
        return codeService.getTenLatestFromNewToOld();
    }

    @DeleteMapping("/delete")
    public void deleteAll() {codeService.deleteAll();}
}
