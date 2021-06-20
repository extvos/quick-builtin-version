# quick-builtin-version

内置版本信息服务模块

## 使用方法

1. `POM`依赖
    ```xml
    <dependency>
        <groupId>org.extvos.builtin</groupId>
        <artifactId>quick-builtin-version</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```
2. `POM`的`plugin`配置
    ```xml
    <plugin>
        <groupId>pl.project13.maven</groupId>
        <artifactId>git-commit-id-plugin</artifactId>
        <version>4.0.0</version>
        <executions>
            <execution>
                <id>get-the-git-infos</id>
                <phase>initialize</phase>
                <goals>
                    <goal>revision</goal>
                </goals>
            </execution>
        </executions>
        <configuration>
            <dotGitDirectory>${project.basedir}/.git</dotGitDirectory>
            <verbose>false</verbose>
            <dateFormat>yyyy-MM-dd HH:mm:ss</dateFormat>
            <prefix>git</prefix>
            <generateGitPropertiesFile>true</generateGitPropertiesFile>
            <generateGitPropertiesFilename>${project.build.outputDirectory}/git.properties
            </generateGitPropertiesFilename>
            <format>json</format>
            <gitDescribe>
                <skip>false</skip>
                <always>false</always>
                <dirty>-dirty</dirty>
            </gitDescribe>
        </configuration>
    </plugin>
    ```

## 接口服务

### `GET` `/_builtin/version/info`

返回数据：

```json
{
  "git.branch" : "master",
  "git.build.host" : "cc3ecf18a0d0",
  "git.build.time" : "2020-08-21 06:45:26",
  "git.build.user.email" : "",
  "git.build.user.name" : "",
  "git.build.version" : "0.0.1-${git.commit.id.abbrev}",
  "git.closest.tag.commit.count" : "",
  "git.closest.tag.name" : "",
  "git.commit.id" : "c3415c9cfd51b98bf38840154a85c77cb96e991a",
  "git.commit.id.abbrev" : "c3415c9",
  "git.commit.id.describe" : "c3415c9-dirty",
  "git.commit.id.describe-short" : "c3415c9-dirty",
  "git.commit.message.full" : "Updated.",
  "git.commit.message.short" : "Updated.",
  "git.commit.time" : "2020-08-21 05:54:32",
  "git.commit.user.email" : "archsh@gmail.com",
  "git.commit.user.name" : "Mingcai SHEN",
  "git.dirty" : "true",
  "git.local.branch.ahead" : "0",
  "git.local.branch.behind" : "0",
  "git.remote.origin.url" : "https://github.com/quickstart/backend-server.git",
  "git.tags" : "",
  "git.total.commit.count" : "18"
}
```