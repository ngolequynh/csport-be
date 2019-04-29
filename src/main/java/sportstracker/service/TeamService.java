package sportstracker.service;

import sportstracker.common.exception.EntityNotFoundException;
import sportstracker.model.ProfileAccountDto;
import sportstracker.model.TeamDto;
import sportstracker.model.TeamHeaderDto;
import sportstracker.model.TeamIdStringsDto;
import sportstracker.model.TeamMemberDto;
import sportstracker.model.TeamStatisticDto;
import sportstracker.model.TimeInterval;

import java.util.List;

/**
 * Activity interface
 *
 */
public interface TeamService {

    List<TeamDto> getOwnedTeams (String userId) throws EntityNotFoundException;

    List<TeamDto> getJoinedTeam(String userId) throws EntityNotFoundException;

    void updateTeamStatus(String teamId) throws EntityNotFoundException;

    void createTeam(TeamDto teamDto) throws EntityNotFoundException;

    TeamHeaderDto getTeamHeaderDtoSer (String teamId);

    List<TeamMemberDto> getTeamMembersDtoSer (String teamId);

    boolean addMemberToTeam(String teamId, String accountId) throws EntityNotFoundException;

    boolean removeMemberFromTeam(String teamId, String accountId) throws EntityNotFoundException;

    List<ProfileAccountDto> getAccountsInTeamByFullName(String teamId, String fullName) throws EntityNotFoundException;

    List<ProfileAccountDto> getAccountsNotInTeamByFullName(String teamId, String userName) throws EntityNotFoundException;

    TeamStatisticDto getTeamStatistic(String teamId, String timeInterval, String timeZoneOffset) throws EntityNotFoundException;

    List<TeamStatisticDto> get2TeamStatisticsToCompare(String homeTeamId, String awayTeamId, String timeInterval, String timeZoneOffset) throws EntityNotFoundException;

    List<TeamHeaderDto> getOtherTeams(String teamId, String name) throws EntityNotFoundException;

    TimeInterval[] getTimeInterval();

    List<TeamIdStringsDto> getOtherTeamId(String teamId) throws EntityNotFoundException;

    boolean isUserExistedInTeam(String teamId, String accountId);
}