package auto.x.util.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.v1.Paging;
import twitter4j.v1.Status;

import java.util.List;
import java.util.Set;

@Service
public class AutoRetweeter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoRetweeter.class);

    @Value("#{'${usernames.to.retweet}'.split(',')}")
    private Set<String> usernamesToRetweet;

    @Value("${tweet.since.id:0}")
    private long tweetSinceId;  // Use this value for the starting tweet ID (optional)

    @Value("${tweet.max.id:9999999999}")
    private long tweetMaxId;

    @Autowired
    private Twitter twitter;

    public void retweetAll() {
        for (String username : usernamesToRetweet) {
            LOGGER.info("Processing username: {}", username);
            int page = 1;
            int count = 100;

            while (true) {
                try {
                    Paging paging = Paging.ofPage(page)
                            .count(count)
                            .sinceId(tweetSinceId)
                            .maxId(tweetMaxId);
                    List<Status> tweets = twitter.v1().timelines().getUserTimeline(username, paging);

                    if (tweets.isEmpty()) {
                        LOGGER.info("No more tweets found for user: {} after page: {}", username, page);
                        break;
                    }

                    for (Status tweet : tweets) {
                        if (!tweet.isRetweetedByMe()) {
                            try {
                                twitter.v1().tweets().retweetStatus(tweet.getId());
                                LOGGER.info("Retweeted tweet id: {} from user: {} with text: {} ", tweet.getId(), username, tweet.getText());
                            } catch (TwitterException te) {
                                if (te.getErrorCode() == 327) {
                                    // Already retweeted — safe to ignore
                                    LOGGER.info("Tweet with tweet id: {} from user: {} has already been retweeted", tweet.getId(), username);
                                } else {
                                    LOGGER.error("Error retweeting tweet id: {} from user: {}. Exception: {}", tweet.getId(), username, te.getMessage(), te);
                                }
                            }
                        }
                    }
                    page++;
                } catch (TwitterException te) {
                    LOGGER.error("Failed to fetch timeline for user: {} on page: {}. Exception: {}", username, page, te.getMessage(), te);
                    break;
                }
            }
        }
    }
}
