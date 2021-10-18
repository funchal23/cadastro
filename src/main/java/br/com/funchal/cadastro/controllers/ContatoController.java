package br.com.funchal.cadastro.controllers;

import br.com.funchal.cadastro.dtos.ContatoDTO;
import br.com.funchal.cadastro.models.Contato;
import br.com.funchal.cadastro.responses.ContatoResponse;
import br.com.funchal.cadastro.services.ContatoService;
import br.com.funchal.cadastro.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/{id}")
    public ResponseEntity<ContatoResponse> buscaContato(@PathVariable Long id){
        return ResponseEntity.ok().body(service.buscaContato(id));
    }

    @PostMapping("/{idPessoa}")
    public ResponseEntity<ContatoResponse> cadastraUmContato(@PathVariable Long idPessoa, @RequestBody @Valid ContatoDTO dto) throws Exception {
        Contato contato = service.cadastraUmContato(dto);
        pessoaService.adicionaContato(contato, idPessoa);
        return ResponseEntity.created(URI.create("http://localhost:8080/contato/" + contato.getId())).body(new ContatoResponse(contato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoResponse> editaContato(@PathVariable Long id, @RequestBody @Valid ContatoDTO dto){
        return ResponseEntity.ok().body(service.editaContato(dto, id));
    }

    @DeleteMapping("/{idPessoa}/{id}")
    public ResponseEntity<ContatoResponse> deletaContato(@PathVariable Long id, @PathVariable Long idPessoa) throws Exception {
        return ResponseEntity.ok().body(service.deletaContato(id, idPessoa));
    }
}
