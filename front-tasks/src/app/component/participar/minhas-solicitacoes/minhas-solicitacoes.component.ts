import { Component, OnInit } from '@angular/core';
import { Participar } from 'src/app/models/Participar';
import { ParticiparService } from 'src/app/services/participar.service';

@Component({
  selector: 'app-minhas-solicitacoes',
  templateUrl: './minhas-solicitacoes.component.html',
  styleUrls: ['./minhas-solicitacoes.component.css']
})
export class MinhasSolicitacoesComponent implements OnInit {


  minhasSolicitacoes: Participar[] = [];
  filtroStatusSolicitacao: string = 'all';
  carregandoMinhasSolicitacoes: boolean = false;

  totalPaginas: number = 0;
  paginaAtual: number = 0;
    
  constructor(
    private participarService: ParticiparService
  ){

  }

  ngOnInit(): void {
    this.buscarMinhasSolicitacoes();
  }

  buscarMinhasSolicitacoes(): void {
    this.participarService.buscarMinhasSolicitacoes().subscribe(
      (page)=> {
        console.log(page);
        this.minhasSolicitacoes = page.content!;
        this.totalPaginas = page.totalPages!;
        this.carregandoMinhasSolicitacoes = false;
      },
      (error) => {
            console.error('Erro ao buscar projetos:', error);
            this.carregandoMinhasSolicitacoes = false;
      });
  }

  getStatusText(status: string): string {
    return status;
  }

  cancelarSolicitacao(idSolicitacao: string) {
    console.log("cancelar solicitacao.");
    this.participarService.cancelarSolicitacaos(idSolicitacao).subscribe(
      (page) => {
        this.buscarMinhasSolicitacoes();
        console.log(page);
      },
      (error) => {
        console.log(error);
      }
    )
  }

  abrirModalSolicitacao(idSolicitacao: string) {
    console.log("abrir modal solicitacao.");
    this.participarService.buscarMinhasSolicitacoes().subscribe(
      (page)=> {
        console.log(page);
        this.minhasSolicitacoes = page.content!;
        this.totalPaginas = page.totalPages!;
        this.carregandoMinhasSolicitacoes = false;
      },
      (error) => {
            console.error('Erro ao buscar projetos:', error);
            this.carregandoMinhasSolicitacoes = false;
      });
  }

  getPaginasDisponiveis(): number[] {
    const paginasVisiveis = 5;
    let inicio = Math.max(0, this.paginaAtual - Math.floor(paginasVisiveis / 2));
    let fim = Math.min(this.totalPaginas - 1, inicio + paginasVisiveis - 1);
    
    if (fim - inicio + 1 < paginasVisiveis) {
      inicio = Math.max(0, fim - paginasVisiveis + 1);
    }
    
    return Array.from({length: fim - inicio + 1}, (_, i) => inicio + i);
  }

  mudarPagina(pagina: number): void {
    this.paginaAtual = pagina;
    this.buscarMinhasSolicitacoes();
  }


}
