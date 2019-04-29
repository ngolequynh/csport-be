package sportstracker.common.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import sportstracker.dao.entity.Account;
import sportstracker.dao.entity.ChatMessage;
import sportstracker.dao.entity.Team;
import sportstracker.model.ChatMessageDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class ChatMessageMapperImpl implements ChatMessageMapper {

    @Override
    public ChatMessage chatMessageDtoToChatMessage(ChatMessageDto chatMessageDto) {
        if ( chatMessageDto == null ) {
            return null;
        }

        ChatMessage chatMessage = new ChatMessage();

        chatMessage.setSender( chatMessageDtoToAccount( chatMessageDto ) );
        chatMessage.setTeam( chatMessageDtoToTeam( chatMessageDto ) );
        chatMessage.setCreatedDate( chatMessageDto.getCreatedDate() );
        chatMessage.setContent( chatMessageDto.getContent() );

        return chatMessage;
    }

    @Override
    public ChatMessageDto chatMessageToChatMessageDto(ChatMessage chatMessage) {
        if ( chatMessage == null ) {
            return null;
        }

        ChatMessageDto chatMessageDto = new ChatMessageDto();

        String accountId = chatMessageSenderAccountId( chatMessage );
        if ( accountId != null ) {
            chatMessageDto.setSenderId( accountId );
        }
        String teamId = chatMessageTeamTeamId( chatMessage );
        if ( teamId != null ) {
            chatMessageDto.setTeamId( teamId );
        }
        chatMessageDto.setContent( chatMessage.getContent() );
        chatMessageDto.setCreatedDate( chatMessage.getCreatedDate() );

        return chatMessageDto;
    }

    protected Account chatMessageDtoToAccount(ChatMessageDto chatMessageDto) {
        if ( chatMessageDto == null ) {
            return null;
        }

        Account account = new Account();

        account.setAccountId( chatMessageDto.getSenderId() );

        return account;
    }

    protected Team chatMessageDtoToTeam(ChatMessageDto chatMessageDto) {
        if ( chatMessageDto == null ) {
            return null;
        }

        Team team = new Team();

        team.setTeamId( chatMessageDto.getTeamId() );

        return team;
    }

    private String chatMessageSenderAccountId(ChatMessage chatMessage) {
        if ( chatMessage == null ) {
            return null;
        }
        Account sender = chatMessage.getSender();
        if ( sender == null ) {
            return null;
        }
        String accountId = sender.getAccountId();
        if ( accountId == null ) {
            return null;
        }
        return accountId;
    }

    private String chatMessageTeamTeamId(ChatMessage chatMessage) {
        if ( chatMessage == null ) {
            return null;
        }
        Team team = chatMessage.getTeam();
        if ( team == null ) {
            return null;
        }
        String teamId = team.getTeamId();
        if ( teamId == null ) {
            return null;
        }
        return teamId;
    }
}
