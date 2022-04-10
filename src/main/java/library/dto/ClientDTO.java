package library.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public record ClientDTO(long id,
                        String name,
                        String password)
{
}
