package nl.fontys.lms.domain.chatmessage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.lms.domain.user.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private String content;
    private User sender;
    private User recipient;
}
