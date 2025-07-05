import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

export interface Task {
  id: number;
  titulo: string;
  descricao: string;
  tipo: 'Task' | 'Story' | 'Epic' | 'Bug';
  prioridade: 'Baixa' | 'Média' | 'Alta' | 'Crítica';
  dataCriacao: Date;
  status: 'Backlog' | 'Em Progresso' | 'Concluída';
}

@Component({
  selector: 'app-tarefas',
  templateUrl: './tarefas.component.html',
  styleUrls: ['./tarefas.component.css']
})
export class TarefasComponent implements OnInit {
  tarefas: Task[] = [];
  showModal = false;
  taskForm: FormGroup;
  editingTask: Task | null = null;
  filtroTipo = '';
  filtroPrioridade = '';

  tipos = ['Task', 'Story', 'Epic', 'Bug'];
  prioridades = ['Baixa', 'Média', 'Alta', 'Crítica'];
  
  constructor(private fb: FormBuilder) {
    this.taskForm = this.fb.group({
      titulo: ['', [Validators.required, Validators.minLength(3)]],
      descricao: ['', [Validators.required, Validators.minLength(10)]],
      tipo: ['Task', Validators.required],
      prioridade: ['Média', Validators.required]
    });
  }

  ngOnInit() {
    this.carregarTarefasExemplo();
  }

  carregarTarefasExemplo() {
    this.tarefas = [
      {
        id: 1,
        titulo: 'Implementar autenticação',
        descricao: 'Desenvolver sistema de login e logout com JWT',
        tipo: 'Story',
        prioridade: 'Alta',
        dataCriacao: new Date('2024-01-15'),
        status: 'Backlog'
      },
      {
        id: 2,
        titulo: 'Corrigir bug no formulário',
        descricao: 'Validação não funciona corretamente no campo email',
        tipo: 'Bug',
        prioridade: 'Crítica',
        dataCriacao: new Date('2024-01-16'),
        status: 'Em Progresso'
      },
      {
        id: 3,
        titulo: 'Refatorar componente header',
        descricao: 'Melhorar responsividade e performance do header',
        tipo: 'Task',
        prioridade: 'Baixa',
        dataCriacao: new Date('2024-01-14'),
        status: 'Backlog'
      }
    ];
  }

  get tarefasFiltradas(): Task[] {
    return this.tarefas
      .filter(tarefa => {
        const filtroTipoOk = !this.filtroTipo || tarefa.tipo === this.filtroTipo;
        const filtroPrioridadeOk = !this.filtroPrioridade || tarefa.prioridade === this.filtroPrioridade;
        return filtroTipoOk && filtroPrioridadeOk;
      })
      .sort((a, b) => {
        const prioridadeOrdem = { 'Crítica': 4, 'Alta': 3, 'Média': 2, 'Baixa': 1 };
        return prioridadeOrdem[b.prioridade] - prioridadeOrdem[a.prioridade];
      });
  }

  abrirModal(tarefa?: Task) {
    this.editingTask = tarefa || null;
    
    if (tarefa) {
      this.taskForm.patchValue({
        titulo: tarefa.titulo,
        descricao: tarefa.descricao,
        tipo: tarefa.tipo,
        prioridade: tarefa.prioridade
      });
    } else {
      this.taskForm.reset({
        tipo: 'Task',
        prioridade: 'Média'
      });
    }
    
    this.showModal = true;
  }

  fecharModal() {
    this.showModal = false;
    this.editingTask = null;
    this.taskForm.reset();
  }

  salvarTarefa() {
    if (this.taskForm.valid) {
      const formData = this.taskForm.value;
      
      if (this.editingTask) {
        // Editar tarefa existente
        const index = this.tarefas.findIndex(t => t.id === this.editingTask!.id);
        this.tarefas[index] = {
          ...this.editingTask,
          ...formData
        };
      } else {
        // Criar nova tarefa
        const novaTarefa: Task = {
          id: this.gerarNovoId(),
          ...formData,
          dataCriacao: new Date(),
          status: 'Backlog' as const
        };
        this.tarefas.push(novaTarefa);
      }
      
      this.fecharModal();
    }
  }

  excluirTarefa(id: number) {
    if (confirm('Tem certeza que deseja excluir esta tarefa?')) {
      this.tarefas = this.tarefas.filter(t => t.id !== id);
    }
  }

  alterarStatus(tarefa: Task) {
    const statusSequencia = ['Backlog', 'Em Progresso', 'Concluída'];
    const currentIndex = statusSequencia.indexOf(tarefa.status);
    const nextIndex = (currentIndex + 1) % statusSequencia.length;
    tarefa.status = statusSequencia[nextIndex] as Task['status'];
  }

  gerarNovoId(): number {
    return this.tarefas.length > 0 ? Math.max(...this.tarefas.map(t => t.id)) + 1 : 1;
  }

  getPrioridadeClass(prioridade: string): string {
    const classes = {
      'Crítica': 'priority-critical',
      'Alta': 'priority-high',
      'Média': 'priority-medium',
      'Baixa': 'priority-low'
    };
    return classes[prioridade as keyof typeof classes] || '';
  }

  getTipoClass(tipo: string): string {
    const classes = {
      'Epic': 'type-epic',
      'Story': 'type-story',
      'Task': 'type-task',
      'Bug': 'type-bug'
    };
    return classes[tipo as keyof typeof classes] || '';
  }
}