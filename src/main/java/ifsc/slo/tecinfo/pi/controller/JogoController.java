package ifsc.slo.tecinfo.pi.controller;

import ifsc.slo.tecinfo.pi.modelo.Avaliacao;
import ifsc.slo.tecinfo.pi.modelo.Jogo;
import ifsc.slo.tecinfo.pi.repositorio.AvaliacaoRepositorio;
import ifsc.slo.tecinfo.pi.repositorio.JogoRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author analice
 */
@Controller
@RequestMapping("/jogos/")
public class JogoController {
    
    private JogoRepositorio JogoRepositorio;
    @Autowired
    private AvaliacaoRepositorio avaliacaoRepositorio;
    
    @Autowired 
    public JogoController(JogoRepositorio repositorio) {
        this.JogoRepositorio = repositorio;
    }
    
    @GetMapping("cadastrar")
    public String showSignUpForm(Jogo jogo, Model model) {
        
              
        model.addAttribute("jogos", JogoRepositorio.findAll());
        return "add-game";
    }
    
    @GetMapping("mostrar")
    public String showUpdateForm(Model model) {
   
        model.addAttribute("jogos", JogoRepositorio.findAll());
        return "index";
    }
    
     @PostMapping("add")
    public String addGame(@Valid Jogo jogo, BindingResult result, Model model) {

        JogoRepositorio.save(jogo);
       
        return "redirect:mostrar";
    }

     @GetMapping("editar/{codJogo}")
    public String showUpdateForm(@PathVariable("codJogo") int codJogo, Model model) {
        Jogo jogo = JogoRepositorio.findById(codJogo)
				.orElseThrow(() -> new IllegalArgumentException("O código do jogo é inválido:" + codJogo));
        model.addAttribute("jogo", jogo);
        return "update-game";
    }
    
    @PostMapping("update/{codJogo}")
    public String updateGame(@PathVariable("codJogo") int codJogo, @Valid Jogo jogo, Model model) {

        JogoRepositorio.save(jogo);
        return "redirect:/jogos/mostrar";
    }

    @GetMapping("remover/{codJogo}")
    public String deleteGame(@PathVariable("codJogo") int codJogo, Model model) {
        Jogo jogo = JogoRepositorio.findById(codJogo)
				.orElseThrow(() -> new IllegalArgumentException("O código do jogo é inválido:" + codJogo));
        JogoRepositorio.delete(jogo);
        return "redirect:/jogos/mostrar";
    }
    
    //@GetMapping("/cadastrar/avaliacao")
    //public String showSignUpForm(Avaliacao avaliacao, Model model) {
            
        //model.addAttribute("avaliacao", avaliacaoRepositorio.findAll());
      //  return "add-avaliacao";
    //}
 
    @GetMapping("cadastrar/avaliacao/{codJogo}")
    public String showSignUpForm(@PathVariable("codJogo") int codJogo, Model model) {
       Avaliacao avaliacao = new Avaliacao();
       avaliacao.setCodJogo(codJogo);
        
        model.addAttribute("avaliacao", avaliacao);
        return "add-avaliacao";
    }
    
    @PostMapping("/add/avaliacao")
    public String addAvaliacao(@Valid Avaliacao avaliacao, BindingResult result, Model model) {
        System.out.println(avaliacao.getCodJogo()); 
        Jogo jogo = JogoRepositorio.findById(avaliacao.getCodJogo())
				.orElseThrow(() -> new IllegalArgumentException("O código do jogo é inválido:" + avaliacao.getCodJogo()));
        jogo.getAvaliacoes().add(avaliacao);
         
        avaliacaoRepositorio.save(avaliacao);
        JogoRepositorio.save(jogo);
        
        return "redirect:/jogos/mostrar";
    }
    
    
}
