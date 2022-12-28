package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CodeService {

    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public Optional<Code> getSnippetByUuid(String uuid) {
        return codeRepository.findByUuid(uuid);
    }

    public String createNewCode(Code code) {
        code.setTimeLimited(code.getTime() > 0);
        code.setViewsLimited(code.getViews() > 0);
        codeRepository.save(code);
        return code.getUuid();
    }

    public void updateTimeByUuid(String uuid) {
        Optional<Code> optional = codeRepository.findByUuid(uuid);
        if (optional.isPresent()) {
            Code code = optional.get();

            Instant creationDate = Instant.parse(code.getDate());
            long expTimeInSec = Instant.ofEpochSecond(code.getTime()).getEpochSecond();
            Instant expDate = creationDate.plus(expTimeInSec, ChronoUnit.SECONDS);
            long dif = Duration.between(Instant.now(), expDate).getSeconds();

            if (dif > 0) {
                code.setTime(dif);
                codeRepository.save(code);
            } else {
                codeRepository.delete(code);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
    }

    public void updateViewsByUuid(String uuid) {
        Optional<Code> optional = codeRepository.findByUuid(uuid);
        if (optional.isPresent()) {
            Code code = optional.get();
            long views = code.getViews();

            if (views > 0) {
                views--;
                code.setViews(views);
                codeRepository.save(code);
            } else {
                codeRepository.delete(code);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
    }

    public List<Code> getTenLatestFromNewToOld() {

        List<Code> list = codeRepository.findAll();

        List<Code> listFiltered = list.stream()
                .filter(x -> x.getTime() <= 0)
                .filter(x -> x.getViews() <= 0)
                .sorted(Comparator.comparing(Code::getDate))
                .collect(Collectors.toList());

        Collections.reverse(listFiltered);

        return listFiltered.stream()
                .limit(10)
                .collect(Collectors.toList());
    }

    public void deleteAll() {
        codeRepository.deleteAll();
    }

}
