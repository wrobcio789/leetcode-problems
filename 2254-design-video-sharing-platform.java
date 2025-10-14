import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class VideoSharingPlatform {
    private int idCounter = 0;
    private final PriorityQueue<Integer> missingIds = new PriorityQueue<>();
    private final Map<Integer, Video> videos = new HashMap<>();

    public int upload(String video) {
        final var id = missingIds.isEmpty() ? idCounter++ : missingIds.poll();
        videos.put(id, new Video(video));
        return id;
    }

    public void remove(int videoId) {
        final var video = videos.get(videoId);
        if (video != null) {
            videos.remove(videoId);
            missingIds.add(videoId);
        }
    }

    public String watch(int videoId, int startMinute, int endMinute) {
        final var video = videos.get(videoId);
        if (video == null) {
            return "-1";
        }

        return video.watch(startMinute, endMinute);
    }

    public void like(int videoId) {
        final var video = videos.get(videoId);
        if (video != null) {
            video.like();
        }
    }

    public void dislike(int videoId) {
        final var video = videos.get(videoId);
        if (video != null) {
            video.dislike();
        }
    }

    public int[] getLikesAndDislikes(int videoId) {
        final var video = videos.get(videoId);
        if (video == null) {
            return new int[]{-1};
        }
        return video.getLikesAndDislikes();
    }

    public int getViews(int videoId) {
        final var video = videos.get(videoId);
        if (video == null) {
            return -1;
        }
        return video.getViews();
    }

    private class Video {
        private String tape;
        private int likes;
        private int dislikes;
        private int views;

        public Video(String tape) {
            this.tape = tape;
            this.likes = this.dislikes = this.views = 0;
        }

        public void like() {
            this.likes++;
        }

        public void dislike() {
            this.dislikes++;
        }

        public String watch(int startMinute, int endMinute) {
            this.views++;
            return tape.substring(startMinute, Math.min(endMinute + 1, tape.length()));
        }

        public int[] getLikesAndDislikes() {
            return new int[]{likes, dislikes};
        }

        public int getViews() {
            return views;
        }
    }
}