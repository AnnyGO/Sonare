package sonare.api_tcc.Exception.Handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sonare.api_tcc.Exception.ConflitoDiaDeAulaException;
import sonare.api_tcc.Exception.CpfException;
import sonare.api_tcc.Exception.IdNaoEncontradoException;
import sonare.api_tcc.Exception.TurmaNaoPodeSerExcluidaException;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(CpfException.class)
    private ResponseEntity<ExceptionHandlerMessage> cpfInvalido (CpfException exception){
        ExceptionHandlerMessage message = new ExceptionHandlerMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(IdNaoEncontradoException.class)
    private ResponseEntity<ExceptionHandlerMessage> idNaoEncontrado (IdNaoEncontradoException exception){
        ExceptionHandlerMessage message = new ExceptionHandlerMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(ConflitoDiaDeAulaException.class)
    private ResponseEntity<ExceptionHandlerMessage> conflitoDiaDeAula (ConflitoDiaDeAulaException exception){
        ExceptionHandlerMessage message = new ExceptionHandlerMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(TurmaNaoPodeSerExcluidaException.class)
    private ResponseEntity<ExceptionHandlerMessage> turmaNaoPodeSerExcluida (TurmaNaoPodeSerExcluidaException exception){
        ExceptionHandlerMessage message = new ExceptionHandlerMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
