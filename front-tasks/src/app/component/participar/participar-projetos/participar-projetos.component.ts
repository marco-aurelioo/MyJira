import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Participar } from 'src/app/models/Participar';
import { Projeto } from 'src/app/models/Projeto';
import { KeycloakService } from 'src/app/services/keycloak.service';
import { ParticiparService } from 'src/app/services/participar.service';
import { ProjetosService } from 'src/app/services/projetos.service';


interface ProjetoParticipar {
  projectId: string;
  name: string;
  description: string;
  isPublic: boolean;
  unicName: string;
  imagem: string;
  categorias?: string[];
  membrosCount?: number;
  tarefasCount?: number;
}

@Component({
  selector: 'app-participar-projetos',
  templateUrl: './participar-projetos.component.html',
  styleUrls: ['./participar-projetos.component.css']
})
export class ParticiparProjetosComponent implements OnInit {

// Estado da página
carregando: boolean = false;
paginaAtual: number = 0;
tamanhoPagina: number = 9;
totalPaginas: number = 0;

// Parâmetros de busca e filtros
termoBusca: string = '';
filtroOrdenacao: 'recent' | 'popular' | 'name' = 'recent';
categorias: string[] = [
  'Tecnologia', 'Educação', 'Saúde', 'Meio Ambiente', 
  'Ciência', 'Social', 'Artes', 'Esportes', 'Design'
];
categoriasSelecionadas: string[] = [];

// Dados de projetos
projetos: ProjetoParticipar[] = [];
projetoSelecionado: ProjetoParticipar | null = null;

 // Modal de solicitação
  mostrarModal: boolean = false;
  solicitacaoMensagem: string = '';
  habilidadesDisponiveis: string[] = [
    'Programação', 'Design', 'Marketing', 'Gestão de Projetos',
    'Análise de Dados', 'Pesquisa', 'Redação', 'Testes',
    'UX/UI', 'DevOps', 'Tradução', 'Apresentação'
  ];
  habilidadesSelecionadas: string[] = [];
  
  
  // Toast de notificação
  mostrarToast: boolean = false;
  toastMensagem: string = '';
  toastTipo: 'success' | 'error' = 'success';
  filtroStatusSolicitacao: string = 'all';


constructor(
  private router: Router,
  private projetoService: ProjetosService,
  private participarService: ParticiparService,
  private keycloakService: KeycloakService ){ }


ngOnInit(): void {
  this.buscarProjetos();
}

//busca projetos no backend
  buscarProjetos(): void {
    this.carregando = true;
    
    // Construir parâmetros de busca
    const params = {
      pagina: this.paginaAtual,
      tamanho: this.tamanhoPagina,
      termo: this.termoBusca,
      ordenacao: this.filtroOrdenacao,
      categorias: this.categoriasSelecionadas.join(',')
    };
    this.projetoService.findPublicProjetos(this.paginaAtual,this.tamanhoPagina,this.termoBusca).subscribe(
      (page)=> {
        console.log(page);
        this.projetos = this.mapToPageProjeto(page.content!);
        this.totalPaginas = page.totalPages!;
        this.carregando = false;
        
      },
      (error) => {
            console.error('Erro ao buscar projetos:', error);
            this.carregando = false;
          //  this.mostrarNotificacao('Erro ao buscar projetos. Tente novamente.', 'error');
      });
  }

  //covnerte modelo projetos
  mapToPageProjeto(content: Projeto[] ): ProjetoParticipar[] {
      return content.map(projeto => ({
        projectId: projeto.projectId || '',
        name: projeto.name || '',
        description: projeto.description || '',
        isPublic: projeto.isPublic || false,
        unicName: projeto.unicName || '',
        imagem: projeto.imagem || '',
        categorias: [], // Pode ser preenchido posteriormente
        membrosCount: 0, // Pode ser preenchido posteriormente
        tarefasCount: 0  // Pode ser preenchido posteriormente
    }));
  }

  //filtro de busca projetos
  aplicarFiltros(): void {
    this.paginaAtual = 0; 
    this.buscarProjetos();
  }

  toggleCategoria(categoria: string): void {
    const index = this.categoriasSelecionadas.indexOf(categoria);
    if (index === -1) {
      this.categoriasSelecionadas.push(categoria);
    } else {
      this.categoriasSelecionadas.splice(index, 1);
    }
    this.aplicarFiltros();
  }
  
  mudarPagina(pagina: number): void {
    this.paginaAtual = pagina;
    this.buscarProjetos();
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
  
  // Funções do modal de solicitação
  abrirModalSolicitacao(projeto: ProjetoParticipar): void {
    
     if (!this.keycloakService.isAuthenticated()) {
       this.router.navigate(['/login'], { queryParams: { returnUrl: this.router.url } });
       return;
     }
    
    this.projetoSelecionado = projeto;
    this.solicitacaoMensagem = '';
    this.habilidadesSelecionadas = [];
    this.mostrarModal = true;
  }
  
  fecharModal(event: Event): void {
    event.preventDefault();
    this.mostrarModal = false;
  }
  
  toggleHabilidade(habilidade: string): void {
    const index = this.habilidadesSelecionadas.indexOf(habilidade);
    if (index === -1) {
      this.habilidadesSelecionadas.push(habilidade);
    } else {
      this.habilidadesSelecionadas.splice(index, 1);
    }
  }
  
  enviarSolicitacao(): void {
    console.log("enviando solicitacao");
    console.log(this.solicitacaoMensagem);
    if (!this.projetoSelecionado || !this.solicitacaoMensagem.trim()) {
      return;
    }
    const participar = new Participar(
      this.projetoSelecionado.projectId,
      this.solicitacaoMensagem,
      this.habilidadesSelecionadas
    );
    this.participarService.enviarSolicitacao(participar).subscribe(
      (page)=> {
        console.log(page);
        this.mostrarModal = false;
      },
      (error) => {
            console.error('Erro ao enviar solicitacao:', error);
            this.carregando = false;
      });
   }

}
