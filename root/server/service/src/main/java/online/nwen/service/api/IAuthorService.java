package online.nwen.service.api;

import online.nwen.service.dto.author.GetAuthorDetailDTO;
import online.nwen.service.dto.author.GetAuthorDetailResultDTO;
import online.nwen.service.dto.author.RegisterAuthorDTO;
import online.nwen.service.dto.author.RegisterAuthorResultDTO;

public interface IAuthorService {
    RegisterAuthorResultDTO register(RegisterAuthorDTO registerAuthorDTO);

    GetAuthorDetailResultDTO getAuthorDetail(GetAuthorDetailDTO getAuthorDetailDTO);
}
