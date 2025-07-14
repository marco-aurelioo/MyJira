import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Projeto } from 'src/app/models/Projeto';
import { Tarefa } from 'src/app/models/Tarefa';
import { TaskService } from 'src/app/services/task.service';



@Component({
  selector: 'app-tarefas',
  templateUrl: './tarefas.component.html',
  styleUrls: ['./tarefas.component.css']
})
export class TarefasComponent implements OnInit {

  @Input() projeto!: Projeto;

  tarefas: Tarefa[] = [];
  showModal = false;
  taskForm: FormGroup;
  editingTask: Tarefa | null = null;
  filtroTipo = '';
  filtroPrioridade = '';

  tipos = ['Task', 'Story', 'Epic', 'Bug'];
  prioridades = ['Baixa', 'Média', 'Alta', 'Crítica'];
  
  constructor(private fb: FormBuilder, private taskService: TaskService) {
    this.taskForm = this.fb.group({
      titulo: ['', [Validators.required, Validators.minLength(3)]],
      descricao: ['', [Validators.required, Validators.minLength(10)]],
      tipo: ['Task', Validators.required],
      prioridade: ['Média', Validators.required]
    });
  }

  ngOnInit() {
    this.carregarTarefasBackEnd();
  }

  carregarTarefasBackEnd(){
    this.taskService.buscaTarefasProjeto(this.projeto.unicName!,1,50).subscribe(
      (page) =>{
          this.tarefas = page.content!
          console.log("carregando tarefa");
          console.log(page);
      },
      (error) =>{
        console.error("falha ao salvar tarefa.");
      });
  }


  get tarefasFiltradas(): Tarefa[] {
    return this.tarefas
      .filter(tarefa => {
        const filtroTipoOk = !this.filtroTipo || tarefa.tipo === this.filtroTipo;
        const filtroPrioridadeOk = !this.filtroPrioridade || tarefa.prioridade === this.filtroPrioridade;
        return filtroTipoOk && filtroPrioridadeOk;
      })
      .sort((a, b) => {
        const prioridadeOrdem = { 'Crítica': 4, 'Alta': 3, 'Média': 2, 'Baixa': 1 };
        return 1;//prioridadeOrdem[b.prioridade] - prioridadeOrdem[a.prioridade];
      });
  }

  abrirModal(tarefa?: Tarefa) {
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
    console.log("salvar Tarefa ");
    if (this.taskForm.valid) {
      const formData = this.taskForm.value;
      
      if (this.editingTask) {
        // Editar tarefa existente
        console.log("titulo:"+ this.taskForm.get("titulo")?.value);
        console.log("descricao:"+ this.taskForm.get("descricao")?.value);
        console.log("tipo:"+ this.taskForm.get("tipo")?.value);
        console.log("prioridade:"+ this.taskForm.get("prioridade")?.value);
        console.log("status:"+ this.taskForm.get("status")?.value);

        this.taskService.updateTarefa(this.projeto.unicName!,
          {
            id: this.editingTask!.id,
            titulo: this.taskForm.get("titulo")?.value,
            descricao: this.taskForm.get("descricao")?.value,
            tipo: this.taskForm.get("tipo")?.value,
            prioridade: this.taskForm.get("prioridade")?.value,
            status: this.taskForm.get("status")?.value,
            projeto: this.projeto
          }
        ).subscribe(
            (page) =>{
                console.log("salvando tarefa");
                console.log(page);
            },
            (error) =>{
              console.error("falha ao salvar tarefa.");
            }
        );

      } else {
        
        this.taskService.createTarefa(
        this.projeto.unicName!,
          {
            titulo: this.taskForm.get("titulo")?.value,
            descricao: this.taskForm.get("descricao")?.value,
            tipo: this.taskForm.get("tipo")?.value,
            prioridade: this.taskForm.get("prioridade")?.value,
            status: this.taskForm.get("status")?.value,
            projeto: this.projeto
          }
        ).subscribe(
            (page) =>{
                console.log("salvando tarefa");
                console.log(page);
            },
            (error) =>{
              console.error("falha ao salvar tarefa.");
            }
        );
      }
      
      
      this.fecharModal();
    }
  }

  excluirTarefa(id: string) {
    if (confirm('Tem certeza que deseja excluir esta tarefa?')) {
      this.tarefas = this.tarefas.filter(t => t.id !== id);
    }
  }

  alterarStatus(tarefa: Tarefa) {
    const statusSequencia = ['Backlog', 'Em Progresso', 'Concluída'];
    //const currentIndex = statusSequencia.indexOf(tarefa.status);
   // const nextIndex = (currentIndex + 1) % statusSequencia.length;
    //tarefa.status = statusSequencia[nextIndex] as Tarefa['status'];
  }

  gerarNovoId(): string {
    return "";//this.tarefas.length > 0 ? Math.max(...this.tarefas.map(t => t.id)) + 1 : 1;
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