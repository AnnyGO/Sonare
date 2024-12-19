package sonare.api_tcc.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sonare.api_tcc.Enum.DiaDaSemana;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("diasDaSemana")
public class DiaDaSemanaController {
    @GetMapping
    public List<String> getDiasDaSemana(){
        return Arrays.stream(DiaDaSemana.values())
                .map(Enum::name)
                .toList();
    }
}
