package library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowDTO
{
    private long id;
    private long clientId;
    private long documentId;
    private String borrowDate;
    private String returnDate;
    private boolean returned;
}
