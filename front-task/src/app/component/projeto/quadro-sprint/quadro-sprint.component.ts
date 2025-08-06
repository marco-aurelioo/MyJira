import { Component, Input, OnInit } from '@angular/core';
import { CdkDragDrop, DragDropModule, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Projeto } from 'src/app/models/Projeto';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { TaskserviceService } from 'src/app/service/taskservice.service';
import { Tarefa } from 'src/app/models/Tarefa';

export interface TaskCard {
  id: string;
  title: string;
  description?: string;
  startDate: string;
  assignee: {
    name: string;
    initials: string;
    avatar?: string;
  };
  type: 'story' | 'bug' | 'task' | 'epic';
  commentsCount: number;
  hasImpediment: boolean;
  impedimentReason?: string;
  priority?: 'alta' | 'media' | 'baixa';
  tarefa?: Tarefa;
}

export interface KanbanColumn {
  id: string;
  title: string;
  status: string;
  tasks: TaskCard[];
}


@Component({
  selector: 'app-quadro-sprint',
  templateUrl: './quadro-sprint.component.html',
  styleUrls: ['./quadro-sprint.component.css'],
  standalone: true,
  imports:[DragDropModule,RouterModule, CommonModule, ReactiveFormsModule]
})
export class QuadroSprintComponent implements OnInit {
  
  @Input() projeto!: Projeto;
  
  constructor(private tarefaService: TaskserviceService){}


  columns: KanbanColumn[] = [
    {
      id: 'backlog',
      title: 'Backlog',
      status: 'Backlog',
      tasks: []
    },
    {
      id: 'todo',
      title: 'To Do',
      status: 'todo',
      tasks: []
    },
    {
      id: 'doing',
      title: 'Doing',
      status: 'doing',
      tasks: []
    },
    {
      id: 'test',
      title: 'Test',
      status: 'test',
      tasks: []
    },
    {
      id: 'validate',
      title: 'Validate',
      status: 'validate',
      tasks: []
    },
    {
      id: 'done',
      title: 'Done',
      status: 'done',
      tasks: []
    }
  ];

  // Dados de exemplo
  private sampleTasks: TaskCard[] = [
    {
      id: '1',
      title: 'Implementar sistema de login',
      startDate: '2024-03-15',
      assignee: { name: 'João Silva', initials: 'JS' },
      type: 'story',
      commentsCount: 2,
      hasImpediment: false
    },
    {
      id: '2',
      title: 'Corrigir erro no cadastro de usuários',
      startDate: '2024-03-10',
      assignee: { name: 'Maria Santos', initials: 'MS' },
      type: 'bug',
      commentsCount: 5,
      hasImpediment: true,
      impedimentReason: 'Aguardando aprovação'
    },
    {
      id: '3',
      title: 'Configurar ambiente de desenvolvimento',
      startDate: '2024-03-20',
      assignee: { name: 'Pedro Costa', initials: 'PC' },
      type: 'task',
      commentsCount: 0,
      hasImpediment: false
    },
    {
      id: '4',
      title: 'Criar dashboard administrativo',
      startDate: '2024-03-25',
      assignee: { name: 'Ana Lima', initials: 'AL' },
      type: 'story',
      commentsCount: 3,
      hasImpediment: false
    },
    {
      id: '5',
      title: 'Integração com API externa',
      startDate: '2024-04-01',
      assignee: { name: 'Ricardo Ferreira', initials: 'RF' },
      type: 'epic',
      commentsCount: 1,
      hasImpediment: false
    },
    {
      id: '6',
      title: 'Implementar validação de formulários',
      startDate: '2024-03-12',
      assignee: { name: 'Carlos Souza', initials: 'CS' },
      type: 'task',
      commentsCount: 4,
      hasImpediment: false
    },
    {
      id: '7',
      title: 'Resolver problema de performance',
      startDate: '2024-03-08',
      assignee: { name: 'Luiza Lima', initials: 'LL' },
      type: 'bug',
      commentsCount: 7,
      hasImpediment: true,
      impedimentReason: 'Dependência externa'
    },
    {
      id: '8',
      title: 'Módulo de relatórios',
      startDate: '2024-03-05',
      assignee: { name: 'Thiago Melo', initials: 'TM' },
      type: 'story',
      commentsCount: 2,
      hasImpediment: false
    },
    {
      id: '9',
      title: 'Configuração de segurança',
      startDate: '2024-03-02',
      assignee: { name: 'Fernanda Braga', initials: 'FB' },
      type: 'task',
      commentsCount: 1,
      hasImpediment: false
    },
    {
      id: '10',
      title: 'Sistema de autenticação',
      startDate: '2024-02-28',
      assignee: { name: 'Gustavo Reis', initials: 'GR' },
      type: 'story',
      commentsCount: 8,
      hasImpediment: false
    },
    {
      id: '11',
      title: 'Correção de layout responsivo',
      startDate: '2024-02-25',
      assignee: { name: 'Igor Santos', initials: 'IS' },
      type: 'bug',
      commentsCount: 3,
      hasImpediment: false
    }
  ];


  loadTasks(){
    this.tarefaService.buscaTarefasProjeto(this.projeto.unicName! , 0, 100).subscribe(
      (page) => {
        console.log("projeto => "+ this.projeto.unicName!);
        console.log(this.projeto);
        console.log(page);
        this.loadTasksOnBoard(page.content!);

      },
      (error) => {
        console.log(error);

      }
    )
  }

  loadTasksOnBoard(listaTarefas: Tarefa[]){
    for (const tarefa of listaTarefas) {
      for (const coluna of this.columns) {
        if (tarefa.status === coluna.status) {
          coluna.tasks.push(
            {
              id: tarefa.id!,
              title: tarefa.titulo!,
              description: tarefa.descricao,
              startDate: Date(),
              assignee: {
                name: "teste",
                initials: "teste",
                avatar: "teste",
              },
              type: 'story',
              commentsCount: 0,
              hasImpediment: false,
              impedimentReason: "",
              priority: 'media', 
              tarefa: tarefa
            }
          );
          break;
        }
    }
  }

  }

  ngOnInit(): void {
    this.loadTasks();
  }

  

  // Método para mover itens entre colunas
  onDrop(event: CdkDragDrop<TaskCard[]>): void {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
      const task = event.previousContainer.data[event.previousIndex];
      console.log(task.id+" ordem "+event.currentIndex);
    } else {
      const task = event.previousContainer.data[event.previousIndex];
      const targetColumn = this.columns.find(col => col.tasks === event.container.data);
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
      
      if (targetColumn) {
       // this.showMoveNotification(task, targetColumn);
        console.log("atualizando tarefa");
        task.tarefa!.status = targetColumn.status;
        this.tarefaService.updateTarefa(this.projeto.unicName!, task.tarefa!).subscribe(
         (page) => {
            console.log("atualizado => "+ page);
        },
          (error) => {
            console.log("Error " + error);
          }
        );
        console.log("fim atualizacao");
      }
    }
  }

  // Método para mostrar notificação de movimento
  private showMoveNotification(task: TaskCard, targetColumn: KanbanColumn): void {
    // Implementar notificação (pode usar Angular Material Snackbar ou similar)
    console.log(`"${task.title}" movido para ${targetColumn.title}`);
    
    // Exemplo com alert (substituir por notificação adequada)
    setTimeout(() => {
      alert(`"${task.title}" movido para ${targetColumn.title}`);
    }, 100);
  }

  // Método para lidar com clique nos comentários
  onCommentsClick(task: TaskCard): void {
    alert(`Comentários para: ${task.title}\n\nEsta funcionalidade seria integrada com o sistema de comentários real.`);
  }

  // Método para lidar com clique no impedimento
  onImpedimentClick(task: TaskCard): void {
    if (task.hasImpediment && task.impedimentReason) {
      alert(`Impedimento detectado:\n${task.impedimentReason}`);
    }
  }

  // Método para obter classe CSS do tipo de tarefa
  getTaskTypeClass(type: string): string {
    return `${type}`;
  }

  // Método para obter ícone do tipo de tarefa
  getTaskTypeIcon(type: string): string {
    const icons = {
      'story': 'S',
      'bug': 'B',
      'task': 'T',
      'epic': 'E'
    };
    return icons[type as keyof typeof icons] || 'T';
  }

  // Método para obter label do tipo de tarefa
  getTaskTypeLabel(type: string): string {
    const labels = {
      'story': 'Story',
      'bug': 'Bug',
      'task': 'Task',
      'epic': 'Epic'
    };
    return labels[type as keyof typeof labels] || 'Task';
  }

  // Método para formatar data
  formatDate(dateString: string): string {
    const date = new Date(dateString);
    return date.toLocaleDateString('pt-BR');
  }

  // Método para obter IDs das colunas conectadas (para drag and drop)
  getConnectedDropLists(): string[] {
    return this.columns.map(column => column.id);
  }


  trackByColumnId(index: number, column: KanbanColumn): string {
    return column.id;
  }
  
  trackByTaskId(index: number, task: TaskCard): string {
    return task.id;
  }
}
