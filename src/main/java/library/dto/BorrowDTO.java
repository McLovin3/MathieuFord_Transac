package library.dto;

import library.model.library.Borrow;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public record BorrowDTO(long id,
                        long clientId,
                        long documentId,
                        String borrowDate,
                        String returnDate,
                        boolean returned)
{

    public static List<BorrowDTO> BooksToDTO(List<Borrow> borrows)
    {
        List<BorrowDTO> borrowDTOs = new ArrayList<>();
        for (Borrow borrow : borrows)
        {
            if (borrow.getClient() != null
                    && borrow.getDocument() != null
                    && borrow.getBorrowDate() != null
                    && borrow.getDocument() != null)
            {
                borrowDTOs.add(new BorrowDTO(borrow.getId(),
                        borrow.getClient().getId(),
                        borrow.getDocument().getId(),
                        borrow.getBorrowDate().toString(),
                        borrow.getReturnDate().toString(),
                        borrow.isReturned()));
            }
        }
        return borrowDTOs;
    }
}
