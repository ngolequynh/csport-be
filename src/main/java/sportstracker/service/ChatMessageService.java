package sportstracker.service;

import sportstracker.common.exception.EntityNotFoundException;
import sportstracker.model.ChatMessageDto;

import java.util.List;

/**
 * Chat message service
 *
 */
public interface ChatMessageService {

    ChatMessageDto createChatMessage(ChatMessageDto chatMessageDto);

    List<ChatMessageDto> loadHistory(ChatMessageDto chatMessageDto) throws EntityNotFoundException;

    List<ChatMessageDto> loadChat(String teamId) throws EntityNotFoundException;
}
