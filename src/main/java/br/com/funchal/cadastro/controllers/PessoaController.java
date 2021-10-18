package br.com.funchal.cadastro.controllers;

import br.com.funchal.cadastro.dtos.PessoaDTO;
import br.com.funchal.cadastro.responses.PessoaResponse;
import br.com.funchal.cadastro.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @GetMapping
    public Page<PessoaResponse> listaPessoas(@RequestParam(required = false) String nome,
                                            @PageableDefault(sort = "nome", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao){
        if (nome == null) return service.buscaListaPessoas(paginacao);
        else return service.buscaListaPessoas(nome, paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> buscaPessoa(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(service.buscaPessoa(id));
    }

    @PostMapping
    public ResponseEntity<PessoaResponse> cadastraUmaPessoa(@RequestBody @Valid PessoaDTO dto){
        PessoaResponse response = service.cadastraUmaPessoa(dto);
        return ResponseEntity.created(URI.create("http://localhost:8080/pessoa/" + response.getId())).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponse> editaPessoa(@PathVariable Long id, @RequestBody @Valid PessoaDTO dto) throws Exception {
        return ResponseEntity.ok().body(service.editaPessoa(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PessoaResponse> deletaPessoa(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(service.deletaPessoa(id));
    }
}
