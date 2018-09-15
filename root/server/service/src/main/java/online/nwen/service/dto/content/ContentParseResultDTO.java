package online.nwen.service.dto.content;

import online.nwen.domain.Resource;

import java.util.ArrayList;
import java.util.List;

public class ContentParseResultDTO {
    private String content;
    private List<Resource> resources;

    public ContentParseResultDTO() {
        this.resources = new ArrayList<>();
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
