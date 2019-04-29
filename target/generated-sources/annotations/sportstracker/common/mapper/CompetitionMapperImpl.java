package sportstracker.common.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import sportstracker.dao.entity.Account;
import sportstracker.dao.entity.Competition;
import sportstracker.model.CompetitionDto;
import sportstracker.model.UserInforDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class CompetitionMapperImpl implements CompetitionMapper {

    @Override
    public CompetitionDto competitionToCompetitionDto(Competition competition) {
        if ( competition == null ) {
            return null;
        }

        CompetitionDto competitionDto = new CompetitionDto();

        String accountId = competitionHostAccountId( competition );
        if ( accountId != null ) {
            competitionDto.setHostId( accountId );
        }
        String accountId1 = competitionInviteeAccountId( competition );
        if ( accountId1 != null ) {
            competitionDto.setInviteeId( accountId1 );
        }
        competitionDto.setCompetitionId( competition.getCompetitionId() );
        competitionDto.setCreatedDate( competition.getCreatedDate() );
        competitionDto.setHost( accountToUserInforDto( competition.getHost() ) );
        competitionDto.setInvitee( accountToUserInforDto( competition.getInvitee() ) );

        return competitionDto;
    }

    @Override
    public Competition competitionDtoToCompetition(CompetitionDto competitionDto, Competition competition) {
        if ( competitionDto == null ) {
            return null;
        }

        if ( competition.getInvitee() == null ) {
            competition.setInvitee( new Account() );
        }
        competitionDtoToAccount( competitionDto, competition.getInvitee() );
        if ( competition.getHost() == null ) {
            competition.setHost( new Account() );
        }
        competitionDtoToAccount1( competitionDto, competition.getHost() );
        competition.setCreatedDate( competitionDto.getCreatedDate() );

        return competition;
    }

    private String competitionHostAccountId(Competition competition) {
        if ( competition == null ) {
            return null;
        }
        Account host = competition.getHost();
        if ( host == null ) {
            return null;
        }
        String accountId = host.getAccountId();
        if ( accountId == null ) {
            return null;
        }
        return accountId;
    }

    private String competitionInviteeAccountId(Competition competition) {
        if ( competition == null ) {
            return null;
        }
        Account invitee = competition.getInvitee();
        if ( invitee == null ) {
            return null;
        }
        String accountId = invitee.getAccountId();
        if ( accountId == null ) {
            return null;
        }
        return accountId;
    }

    protected UserInforDto accountToUserInforDto(Account account) {
        if ( account == null ) {
            return null;
        }

        UserInforDto userInforDto = new UserInforDto();

        return userInforDto;
    }

    protected void competitionDtoToAccount(CompetitionDto competitionDto, Account mappingTarget) {
        if ( competitionDto == null ) {
            return;
        }

        mappingTarget.setAccountId( competitionDto.getInviteeId() );
    }

    protected void competitionDtoToAccount1(CompetitionDto competitionDto, Account mappingTarget) {
        if ( competitionDto == null ) {
            return;
        }

        mappingTarget.setAccountId( competitionDto.getHostId() );
    }
}
