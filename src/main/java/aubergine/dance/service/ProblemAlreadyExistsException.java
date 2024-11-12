package aubergine.dance.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProblemAlreadyExistsException extends RuntimeException {

    public ProblemAlreadyExistsException(long problemId) {
        super("Problem with id " + problemId + " already exists");
        log.error("Problem with id {} already exists", problemId);
    }
}
