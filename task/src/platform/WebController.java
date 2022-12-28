package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/code")
public class WebController {

    CodeService codeService;

    @Autowired
    public WebController(CodeService codeService) {
        this.codeService = codeService;
    }

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Content-Type", "text/html");
    }

    @GetMapping("/{uuid}")
    public String getAsHtml(@PathVariable("uuid") String uuid, Model model) {

        Code code = codeService.getSnippetByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (code.isViewsLimited()) {
            codeService.updateViewsByUuid(uuid);
        }

        if (code.isTimeLimited()) {
            codeService.updateTimeByUuid(uuid);
        }

        model.addAttribute("code", code);
        return "viewCode";
    }

    @GetMapping("/new")
    public String postNewCodeWeb() {
        return "createCode";
    }

    @GetMapping("/latest")
    public String getListOfTenAsHtml(Model model) {
        model.addAttribute("codeList", codeService.getTenLatestFromNewToOld());
        return "viewLatest";
    }
}

