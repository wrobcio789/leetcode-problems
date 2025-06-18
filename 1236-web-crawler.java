import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        final var hostname = getHostname(startUrl);
        final var visited = new HashSet<String>();

        dfs(startUrl, hostname, htmlParser, visited);

        return new ArrayList<>(visited);
    }

    private void dfs(String root, String hostname, HtmlParser parser, Set<String> visited) {
        visited.add(root);

        for (final var url : parser.getUrls(root)) {
            if (url.startsWith(hostname) && !visited.contains(url)) {
                dfs(url, hostname, parser, visited);
            }
        }
    }

    private static String getHostname(String startUrl) {
        try {
            final var uri = new URI(startUrl);
            return uri.getScheme() + "://" + uri.getHost();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
