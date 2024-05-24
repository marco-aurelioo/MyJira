import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-new-task',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './new-task.component.html',
  styleUrl: './new-task.component.css'
})
export class NewTaskComponent {

  selectedProject!: string;

  taskProjects = [
    { value: 'project-aka1', label: 'aka 1' },
    { value: 'project-aka2', label: 'projeto 2' },
    { value: 'project-aka3', label: 'Projeto 3' },
    { value: 'project-aka4', label: 'projeto 5' }
  ];

  onSubmit(taskForm: NgForm) {
    if (taskForm.valid) {
      // Lógica para enviar os dados do formulário
      console.log('Formulário enviado', taskForm.value);
    } else {
      console.log('Formulário inválido');
    }
  }
}
