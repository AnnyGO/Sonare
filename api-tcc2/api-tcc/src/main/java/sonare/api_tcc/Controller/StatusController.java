package sonare.api_tcc.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sonare.api_tcc.Enum.AlunoStatus;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("status")
public class StatusController {
    @GetMapping
    public List<String> getStatus() {
        return Arrays.stream(AlunoStatus.values())
                .map(Enum::name)
                .toList();
    }
}
