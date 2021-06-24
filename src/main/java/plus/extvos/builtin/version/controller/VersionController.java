package plus.extvos.builtin.version.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import plus.extvos.builtin.version.config.GitProperties;

import java.util.Map;

/**
 * @author Mingcai SHEN
 */
@Api(tags = {"版本信息"})
@RestController
@RequestMapping("/_builtin/version")
public class VersionController {

    @ApiOperation(value = "原始信息", notes = "编译时打包的版本原始信息")
    @ResponseBody
    @GetMapping("/raw")
    public String getVersionRaw() {
        return GitProperties.getGitRawProps(null);
    }

    @ApiOperation(value = "版本信息", notes = "编译时打包的版本信息")
    @GetMapping("/info")
    public Map<String, Object> getVersionInfo() {
        return GitProperties.getGitProps(null);
    }


}
