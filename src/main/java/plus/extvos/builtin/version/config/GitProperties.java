package plus.extvos.builtin.version.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * {
 * "git.branch" : "master",
 * "git.build.host" : "cc3ecf18a0d0",
 * "git.build.time" : "2020-08-21 06:45:26",
 * "git.build.user.email" : "",
 * "git.build.user.name" : "",
 * "git.build.version" : "0.0.1-${git.commit.id.abbrev}",
 * "git.closest.tag.commit.count" : "",
 * "git.closest.tag.name" : "",
 * "git.commit.id" : "c3415c9cfd51b98bf38840154a85c77cb96e991a",
 * "git.commit.id.abbrev" : "c3415c9",
 * "git.commit.id.describe" : "c3415c9-dirty",
 * "git.commit.id.describe-short" : "c3415c9-dirty",
 * "git.commit.message.full" : "Updated.",
 * "git.commit.message.short" : "Updated.",
 * "git.commit.time" : "2020-08-21 05:54:32",
 * "git.commit.user.email" : "archsh@gmail.com",
 * "git.commit.user.name" : "Mingcai SHEN",
 * "git.dirty" : "true",
 * "git.local.branch.ahead" : "0",
 * "git.local.branch.behind" : "0",
 * "git.remote.origin.url" : "https://github.com/quickstart/backend-server.git",
 * "git.tags" : "",
 * "git.total.commit.count" : "18"
 * }
 *
 * @author Mingcai SHEN
 */
public class GitProperties {

    public static final String VERSION = "git.build.version";
    public static final String USERNAME = "git.commit.user.name";
    public static final String COMMIT_ABBREV = "git.commit.id.abbrev";
    public static final String BRANCH = "git.branch";
    public static final String COMMIT = "git.commit.id";
    public static final String USER_EMAIL = "git.commit.user.email";
    public static final String TAG_NAME = "git.closest.tag.name";
    public static final String BUILD_TIME = "git.build.time";
    public static final String COMMIT_TIME = "git.commit.time";
    public static final String COMMIT_MSG = "git.commit.message.full";

    private final static Logger log = LoggerFactory.getLogger(GitProperties.class);

    static class Properties extends HashMap<String, Object> {

    }

    private static Properties gitProps;

    public static String get(String key) {
        if (null == gitProps) {
            getGitProps(null);
        }
        if (null == gitProps) {
            return "";
        }
        if (gitProps.containsKey(key)) {
            return (String) gitProps.get(key);
        }
        return "";
    }

    public static Properties getGitProps(String fileName) {
        if (null != gitProps) {
            return gitProps;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            gitProps = mapper.readValue(getGitRawProps(fileName), Properties.class);
            return gitProps;
        } catch (Exception e) {
            Properties r = new Properties();
            r.put("error", e);
            return r;
        }
    }

    public static String getGitRawProps(String fileName) {
        InputStream inputStream = null;
        fileName = fileName == null || fileName.isEmpty() ? "git.properties" : fileName;
        try {
            ClassLoader classLoader = GitProperties.class.getClassLoader();
            inputStream = classLoader.getResourceAsStream(fileName);
            // 读取文件内容，自定义一个方法实现即可
            return loadAsString(inputStream);
        } catch (Exception e) {
            // log.error("get git version info fail", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                // log.error("close inputstream fail", e);
            }
        }
        return "{}";
    }

    public static String loadAsString(InputStream input) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String buffer = "";
            while ((buffer = reader.readLine()) != null) {
                result.append(buffer).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            log.error(">>", e);

        }
        return result.toString();
    }
}
