import { Component, OnInit } from '@angular/core';
import { PageProjeto } from 'src/app/models/PageProjeto';
import { PageProjetoInvites } from 'src/app/models/PageProjetoInvites';
import { Participar } from 'src/app/models/Participar';
import { Projeto } from 'src/app/models/Projeto';
import { ProjetoInvites } from 'src/app/models/ProjetoInvites';
import { ParticiparService } from 'src/app/services/participar.service';
import { ProjetosService } from 'src/app/services/projetos.service';

@Component({
  selector: 'app-aprovar-solicitacoes',
  templateUrl: './aprovar-solicitacoes.component.html',
  styleUrls: ['./aprovar-solicitacoes.component.css']
})
export class AprovarSolicitacoesComponent implements OnInit {


  pageProjects?: PageProjetoInvites;
  totalPaginasProjetos: number = 0;
  currentPageProjetos: number = 0;
  currentPageSolicitacoes: number = 0;

  solicitacoesParaAprovar: Participar[] = [];
  solicitacaoSelecionada?: Participar;

  filtroStatusSolicitacao: string = 'all';
  carregandoAprovacoes: boolean = false;
  mostrarModal: boolean = false;
  projetoSelecionado?: Projeto;
  mostrarModalResposta: boolean = false;
  acaoResposta?: string;
  respostaFeedback?: string;

  totalPaginasSolicitacoes: number = 0;

  carregandoProjetos: boolean = false;

  constructor(
      private participarService: ParticiparService,
      private projetoService: ProjetosService
    ){
      this.buscaMinhasAprovacoes(this.currentPageProjetos);
    }

    ngOnInit(): void {
      this.buscaMinhasAprovacoes(this.currentPageProjetos);
    }

    buscaMinhasAprovacoes(pagina: number){
      this.carregandoProjetos = true;
      this.projetoService.findMyProjetosParaAprovar(pagina,5,"").subscribe(
        (page) => {
          console.log(page);
          this.pageProjects = page;
          this.totalPaginasProjetos = page.totalPages!;
          this.currentPageProjetos = pagina;
          this.carregandoProjetos = false;
        },
        (error) => {
          console.log(error);
          this.carregandoProjetos = false;
        }
      )
    }

    mudarPaginaProjetos(pagina: number){
      this.buscaMinhasAprovacoes( pagina)
    }

    selecionarProjeto(projeto: Projeto){
      console.log("projeto");
      this.projetoSelecionado = projeto;
      this.carregaInvites(projeto);
    }

    carregaInvites(projeto: Projeto){
      this.projetoSelecionado = projeto;
      this.carregandoAprovacoes = true;
      this.projetoService.findParticipacoes(projeto,this.currentPageSolicitacoes,5).subscribe(
        (page) => {

            console.log(page)
            this.solicitacoesParaAprovar = page.content!;
            this.carregandoAprovacoes = false;
            this.totalPaginasSolicitacoes = page.totalPages!;
            this.currentPageSolicitacoes = page.number!

        },
        (error) =>{
          console.log(error)
          this.carregandoAprovacoes = false;
        }
      )
    }


    mudarPaginaSolicitacoes(pagina: number){
      this.currentPageSolicitacoes = pagina;
      this.carregaInvites(this.projetoSelecionado!);
    }



    getPaginationArray(totalPages: number): number[] {
       if (!this.totalPaginasProjetos || this.totalPaginasProjetos <= 0) {
        return [];
      }
      
      const maxVisiblePages = 5;
      if (totalPages <= maxVisiblePages) {
        return Array(totalPages).fill(0).map((_, index) => index);
      }
      
      let currentPage: number;
      if (this.projetoSelecionado) {
        currentPage = this.currentPageSolicitacoes;
      } else {
        currentPage = this.currentPageProjetos;
      }
      
      let startPage = Math.max(0, currentPage - Math.floor(maxVisiblePages / 2));
      let endPage = Math.min(totalPages - 1, startPage + maxVisiblePages - 1);
      
      if (endPage - startPage + 1 < maxVisiblePages) {
        startPage = Math.max(0, endPage - maxVisiblePages + 1);
      }
      
      const pages = [];
      for (let i = startPage; i <= endPage; i++) {
        pages.push(i);
      }
      
      if (startPage > 0) {
        pages.unshift(0);
        if (startPage > 1) {
          pages.splice(1, 0, -1); 
        }
      }
      
      if (endPage < totalPages - 1) {
        if (endPage < totalPages - 2) {
          pages.push(-1); 
        }
        pages.push(totalPages - 1);
      }
      
      return pages;
    }

    getStatusText(status: string): string {
      return status;
    }

    abrirModalResposta(solicitacap: Participar, status: string){
      this.acaoResposta = status;
      this.mostrarModalResposta = true;
      this.solicitacaoSelecionada = solicitacap;
    }

    fecharModalResposta(event: Event){
      this.mostrarModalResposta = false;
    }

    responderSolicitacao(){
      console.log("responder");
      this.acaoResposta;
      this.solicitacaoSelecionada;
      this.mostrarModalResposta = false;
      this.projetoService.atualizaPArticipacoes(this.solicitacaoSelecionada!,this.acaoResposta!).subscribe(
        (page) => {
          console.log("retorno "+this.acaoResposta);
          console.log(page);
          
        },
        (error)=> {
          console.log("retorno "+this.acaoResposta);
          console.log(error);
        })
    }
    
}
