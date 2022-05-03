package library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorrowDTO
{
    private long id;
    private String documentName;
    private long clientId;
    private long documentId;
    private String borrowDate;
    private String returnDate;
    private boolean returned;
}
