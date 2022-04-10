package library.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public record BorrowDTO(long id,
                        long clientId,
                        long documentId,
                        String borrowDate,
                        String returnDate,
                        boolean returned)
{
}
