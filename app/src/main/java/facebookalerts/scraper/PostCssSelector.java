package facebookalerts.scraper;

public class PostCssSelector {
    public String createPostCssSelector(int postNumber) {
        return String.format("[data-ad-preview=\"message\"]:nth-of-type(%d)", postNumber);
    }
}
