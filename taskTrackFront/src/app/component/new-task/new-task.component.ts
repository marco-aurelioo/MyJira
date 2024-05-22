import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-new-task',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './new-task.component.html',
  styleUrl: './new-task.component.css'
})
export class NewTaskComponent {

  onSubmit(taskForm: NgForm) {
    if (taskForm.valid) {
      // Lógica para enviar os dados do formulário
      console.log('Formulário enviado', taskForm.value);
    } else {
      console.log('Formulário inválido');
    }
  }
}
