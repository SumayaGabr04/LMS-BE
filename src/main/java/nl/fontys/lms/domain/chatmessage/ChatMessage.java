package nl.fontys.lms.domain.chatmessage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private String senderName;
    private String receiverName;
    private String message;
    private String date;
    private Status status;
}
