import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Profile } from 'src/app/models/Profile';
import { Projeto } from 'src/app/models/Projeto';
import { KeycloakService } from 'src/app/services/keycloak.service';
import { ProfileService } from 'src/app/services/profile.service';
import { ProjetosService } from 'src/app/services/projetos.service';

interface Tarefa {
  id: string;
  titulo: string;
  descricao: string;
  status: 'available' | 'in-progress' | 'completed';
  prioridade: 'baixa' | 'media' | 'alta';
  prazo: string;
  responsavel: Profile | null;
  tags: string[];
}

@Component({
  selector: 'app-projeto',
  templateUrl: './projeto.component.html',
  styleUrls: ['./projeto.component.css']
})
export class ProjetoComponent implements OnInit {
  projetoId: string = '';
  projeto: Projeto | null = null;
  tarefas: Tarefa[] = [];
  tarefasFiltradas: Tarefa[] = [];
  
  activeTab: 'tarefas' | 'membros' | 'configuracoes' = 'tarefas';
  filtroStatus: 'all' | 'available' | 'in-progress' | 'completed' = 'all';
  termoPesquisa: string = '';
  
  paginaAtual: number = 0;
  tamanhoPagina: number = 10;
  totalPaginas: number = 0;
  
  usuarioAtual?: Profile;
  isProjetoMembro: boolean = false;
  
  constructor(
    private route: ActivatedRoute,
    private projetoService: ProjetosService,
    private profile: ProfileService
  ) {
   
    this.profile.getProfile().subscribe(
      (user) => { this.usuarioAtual = user },
      (error) => { console.log(error) }
    );
    
  }
  
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.projetoId = params['id'];
      this.carregarProjeto();
      this.carregarTarefas();
      this.verificarMembro();
    });
  }
  
  carregarProjeto(): void {
    this.projetoService.findProjetos(this.projetoId).subscribe(
      (data) => {
        console.log("carregando projeto");
        console.log(data);
        this.projeto = data;
      },
      (error) => {
        console.error('Erro ao carregar projeto:', error);
        // Adicionar tratamento de erro adequado
      }
    );
  }
  
  carregarTarefas(pagina: number = 0): void {
    // this.tarefaService.listarTarefasProjeto(this.projetoId, pagina, this.tamanhoPagina).subscribe(
    //   (data) => {
    //     this.tarefas = data.content;
    //     this.paginaAtual = data.number;
    //     this.totalPaginas = data.totalPages;
    //     this.aplicarFiltros();
    //   },
    //   (error) => {
    //     console.error('Erro ao carregar tarefas:', error);
    //     // Adicionar tratamento de erro adequado
    //   }
    // );
  }
  
  verificarMembro(): void {
    // this.projetoService.verificarMembro(this.projetoId, this.usuarioAtual.id).subscribe(
    //   (isMembro) => {
    //     this.isProjetoMembro = isMembro;
    //   }
    // );
  }
  
  aplicarFiltros(): void {
    this.tarefasFiltradas = this.tarefas.filter(tarefa => {
      // Filtro por status
      if (this.filtroStatus !== 'all' && tarefa.status !== this.filtroStatus) {
        return false;
      }
      
      // Filtro por texto de pesquisa
      if (this.termoPesquisa && !this.correspondeAoPesquisar(tarefa, this.termoPesquisa)) {
        return false;
      }
      
      return true;
    });
  }
  
  correspondeAoPesquisar(tarefa: Tarefa, termo: string): boolean {
    const termoCaseInsensitive = termo.toLowerCase();
    return (
      tarefa.titulo.toLowerCase().includes(termoCaseInsensitive) ||
      tarefa.descricao.toLowerCase().includes(termoCaseInsensitive) ||
      tarefa.tags.some(tag => tag.toLowerCase().includes(termoCaseInsensitive))
    );
  }
  
  inscreverTarefa(tarefaId: string): void {
    // this.tarefaService.inscreverEmTarefa(tarefaId, this.usuarioAtual.id).subscribe(
    //   () => {
    //     // Atualizar lista de tarefas após inscrição
    //     this.carregarTarefas(this.paginaAtual);
    //   },
    //   (error) => {
    //     console.error('Erro ao se inscrever na tarefa:', error);
    //     // Adicionar tratamento de erro adequado
    //   }
    // );
  }
  
  concluirTarefa(tarefaId: string): void {
    // this.tarefaService.concluirTarefa(tarefaId).subscribe(
    //   () => {
    //     // Atualizar lista de tarefas após conclusão
    //     this.carregarTarefas(this.paginaAtual);
    //   },
    //   (error) => {
    //     console.error('Erro ao concluir tarefa:', error);
    //     // Adicionar tratamento de erro adequado
    //   }
    // );
  }
  
  verDetalhesTarefa(tarefaId: string): void {
    // Implementar navegação para detalhes da tarefa
    // Pode ser um modal ou uma nova página
  }
  
  criarNovaTarefa(): void {
    // Implementar criação de nova tarefa
    // Pode ser um modal ou redirecionamento para formulário
  }
  
  mudarPagina(pagina: number): void {
    this.carregarTarefas(pagina);
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
  
  getPrioridadeLabel(prioridade: string): string {
    switch(prioridade) {
      case 'alta': return 'Alta';
      case 'media': return 'Média';
      case 'baixa': return 'Baixa';
      default: return prioridade;
    }
  }
}
