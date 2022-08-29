package br.com.bb.letscode.idempotencia.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope
@Service("horarioService")
public class HorarioService {

    public HorarioService() {
    }

    public String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:MM:ss"));
    }

}
