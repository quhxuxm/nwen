package online.nwen.service.impl;

import online.nwen.service.api.IContentService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
class ContentService implements IContentService {
    @Override
    public String parse(String content) {
        Document contentDocument = Jsoup.parse(content);
        Elements imgElements = contentDocument.getElementsByTag("img");
        Elements audioElements = contentDocument.getElementsByTag("audio");
        Elements vadioElements = contentDocument.getElementsByTag("vadio");
        return content;
    }
}
