package sportstracker.service;

import sportstracker.common.exception.EntityNotFoundException;
import sportstracker.model.CompetitionDto;

import javax.persistence.EntityExistsException;
import java.util.List;

/**
 * Competition Service
 *
 */
public interface CompetitionService {

    void createCompetition(CompetitionDto competitionDto) throws EntityExistsException;

    void removeCompetition(String competitionId) throws EntityNotFoundException;

    List<CompetitionDto> getCompetition(String accountId) throws EntityNotFoundException;
}