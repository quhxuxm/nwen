package online.nwen.service.impl;

import online.nwen.service.api.IContentService;
import online.nwen.service.api.IResourceService;
import online.nwen.service.dto.content.ContentParseResultDTO;
import online.nwen.service.dto.resources.SaveResourceDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
class ContentService implements IContentService {
    private static final String IMG_TAG = "img";
    private static final String VIDEO_TAG = "video";
    private static final String AUDIO_TAG = "audio";
    private IResourceService resourceService;

    ContentService(IResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Override
    public ContentParseResultDTO parse(String content) {
        ContentParseResultDTO result = new ContentParseResultDTO();
        Document cleanDocument = Jsoup.parse(Jsoup.clean(content, this.createWhiteList()));
        this.parseMediaContent(cleanDocument, IMG_TAG, result);
        this.parseMediaContent(cleanDocument, VIDEO_TAG, result);
        this.parseMediaContent(cleanDocument, AUDIO_TAG, result);
        String contentHtml = cleanDocument.body().html();
        result.setContent(contentHtml);
        return result;
    }

    private Whitelist createWhiteList() {
        return Whitelist.none()
                .addTags("audio", "video", "img", "div", "p", "h1", "h2", "h3", "h4", "b", "i", "blockquote", "br")
                .addAttributes("img", "src")
                .addAttributes("video", "src")
                .addAttributes("audio", "src")
                .addProtocols("img", "src", "data")
                .addProtocols("audio", "src", "data")
                .addProtocols("video", "src", "data");
    }

    private void parseMediaContent(Document document, String tagName,
                                   ContentParseResultDTO contentParseResultDTO) {
        Elements imgElements = document.getElementsByTag(tagName);
        imgElements.forEach(element -> {
            String base64Content = element.attr("src");
            SaveResourceDTO saveResourceDTO = new SaveResourceDTO();
            saveResourceDTO.setContentAsBase64(base64Content);
            this.resourceService.save(saveResourceDTO);
        });
    }
}
