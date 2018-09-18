package online.nwen.service.api;

import online.nwen.service.dto.anthology.*;
import online.nwen.service.dto.article.PraiseArticleResultDTO;

public interface IAnthologyService {
    GetAnthologyDetailResultDTO getAnthologyDetail(GetAnthologyDetailDTO getAnthologyDetailDTO);

    SaveAnthologyResultDTO save(SaveAnthologyDTO saveAnthologyDTO);

    PraiseAnthologyResultDTO praiseAnthology(PraiseAnthologyDTO praiseAnthologyDTO);

    BookmarkAnthologyResultDTO bookmarkAnthology(BookmarkAnthologyDTO bookmarkAnthologyDTO);
}