package online.nwen.service.impl;

import online.nwen.service.api.IContentService;
import org.springframework.stereotype.Service;

@Service
class ContentService implements IContentService {
    @Override
    public String parse(String content) {
        return content;
    }
}
