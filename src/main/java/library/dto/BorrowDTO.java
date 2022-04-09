package library.dto;

import java.util.ArrayList;
import java.util.List;

import library.model.library.Borrow;
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
