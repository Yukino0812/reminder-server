package me.yukino.reminderserver.record;

import me.yukino.reminderserver.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yukino Yukinoshita
 */

@RequestMapping("/record")
@RestController
public class RecordController {

    private RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping("/list")
    public ResponseResult listRecord(@RequestHeader("auth_key") String key,
                                     @RequestParam("name") String name) {
        return recordService.listRecord(key, name);
    }

}
