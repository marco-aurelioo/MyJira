import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Organizacao } from 'src/app/models/Organizacao ';
import { OrganizacaoService } from 'src/app/services/organization.service';

@Component({
  selector: 'app-organization',
  templateUrl: './organization.component.html',
  styleUrls: ['./organization.component.css']
})
export class OrganizationComponent implements OnInit {
  
  organizacoes: Organizacao[] = [];
  novaOrganizacao: Organizacao = { titulo: '' };

  constructor(private organizacaoService: OrganizacaoService) {}

  ngOnInit(): void {
    this.carregarOrganizacoes();
  }

  carregarOrganizacoes(): void {
    this.organizacaoService.getOrganizacoes().subscribe(
      (organizacoes) => {
        this.organizacoes = organizacoes;
      },
      (erro) => {
        console.error('Erro ao carregar organizações:', erro);
      }
    );
  }

  adicionarOrganizacao(): void {
    if (this.novaOrganizacao.titulo.trim()) {
      this.organizacaoService.addOrganizacao(this.novaOrganizacao).subscribe(
        (organizacao) => {
          this.organizacoes.push(organizacao);
          this.novaOrganizacao = { titulo: '' }; // Limpa o formulário
        },
        (erro) => {
          console.error('Erro ao adicionar organização:', erro);
        }
      );
    }
  }
}