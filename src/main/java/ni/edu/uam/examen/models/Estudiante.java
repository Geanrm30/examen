package ni.edu.uam.examen.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@NoArgsConstructor@AllArgsConstructor
@Setter@Getter
public class Estudiante {
    public String codigo;
    public String nombres;
    public String apellidos;
    public String carrera;
    public LocalDate fechaNacimiento;


}
