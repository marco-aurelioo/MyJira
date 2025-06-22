import { Component, OnInit } from '@angular/core';
import { Tarefa } from 'src/app/models/Tarefa';


@Component({
  selector: 'app-tarefas',
  templateUrl: './tarefas.component.html',
  styleUrls: ['./tarefas.component.css']
})
export class TarefasComponent  implements OnInit  {


  filtroStatus :string  = "";
  termoPesquisa: string = "";
  tarefasFiltradas: Tarefa[] = [];
  isProjetoMembro: boolean = true;
  totalPaginas: number = 0;
  paginaAtual: number = 0;



  ngOnInit(){

  }


  getPrioridadeLabel(prioriade: string): string {
    return "Alta"
  }

  criarNovaTarefa(){

  }

  mudarPagina(pagina: number){

  }

  inscreverTarefa(idTarefa: string){

  }

  concluirTarefa(idTarefa: string){

  }

  verDetalhesTarefa(idTarefa: string){

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

}
