package online.nwen.service.api;

import online.nwen.service.dto.content.ContentParseResultDTO;

public interface IContentService {
    ContentParseResultDTO parse(String content);
}
