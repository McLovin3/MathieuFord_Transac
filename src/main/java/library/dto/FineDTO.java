package library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FineDTO
{
    private long id;
    private long clientId;
    private double amount;
    private boolean paid;
}

